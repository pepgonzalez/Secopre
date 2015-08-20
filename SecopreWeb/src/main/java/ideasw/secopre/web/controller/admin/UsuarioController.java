package ideasw.secopre.web.controller.admin;

import ideasw.secopre.model.security.User;
import ideasw.secopre.utils.encryption.Encryption;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller principal encargada del modulo de administracion de
 * usuarios,dentro de este controller se podran configurar los usuarios de la
 * aplicacion
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>adm: Indica que esta en el modulo de administracion</li>
 * <li>usr: Indica que la configuracion pertenece a Usuarios</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Controller
public class UsuarioController extends AuthController {

	@RequestMapping(value = "adm/usrList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getUserList(ModelMap model, RedirectAttributes attributes) {
		List<User> userList = baseService.findAll(User.class);
		User user = new User();
		model.addAttribute("userList", userList);
		model.addAttribute("user", user);
		return SecopreConstans.MV_ADM_USR;
	}

	@RequestMapping(value = "adm/directory", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getDirectory(ModelMap model, Principal principal) {
		return SecopreConstans.MV_ADM_DIR;
	}

	@RequestMapping(value = "adm/usr/", method = RequestMethod.PUT)
	public String getUser(ModelMap model, Principal principal) {
		return null;
	}

	@RequestMapping(value = "adm/usr/{username}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable String username, ModelMap model,
			Principal principal) {
		return null;
	}

	@RequestMapping(value = "adm/usr/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("user") User user, ModelMap model) {
		try {
			user.setPassword(Encryption.encrypByBCrypt(user.getPassword()));
			baseService.persist(user);
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el usuario:"
							+ e.getMessage()));
		}
		return "redirect:" + SecopreConstans.MV_ADM_USR_LIST;
	}

	@RequestMapping(value = "start")
	public String home(ModelMap model, Principal principal) {
		String name = principal.getName(); // get logged in username
		model.addAttribute("username", name);
	    
	    User loggedUser =  baseService.findByProperty(User.class, "username", name ).get(0);
	    model.addAttribute("loggedUser", loggedUser);
	    
		return SecopreConstans.AUTH_INDEX;
	}
}
