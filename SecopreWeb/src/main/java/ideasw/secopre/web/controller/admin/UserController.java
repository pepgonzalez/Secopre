package ideasw.secopre.web.controller.admin;

import ideasw.secopre.dto.Request;
import ideasw.secopre.model.security.Permission;
import ideasw.secopre.model.security.Role;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.utils.encryption.Encryption;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ideasw.secopre.model.catalog.Person;

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
public class UserController extends AuthController {

	@Autowired
	private AccessService accessService;

	@RequestMapping(value = "adm/usr/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getUserList(ModelMap model, RedirectAttributes attributes) {
		User user = new User();
		model.addAttribute("userList", baseService.findAll(User.class));
		model.addAttribute("user", user);
		model.addAttribute("roles", baseService.findAll(Role.class));
		model.addAttribute("permissions", baseService.findAll(Permission.class));
		
		List<Person> person = baseService.findAll(Person.class);
		
		HashMap<Long, String> personMap = new HashMap<Long, String>();
		for (Person p : person) {
			personMap.put(p.getId(),p.getName() );
		}
		
		
		Request requestForm = new Request();
		
		model.addAttribute("persons", personMap);
		model.addAttribute("requestForm", requestForm);
		
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
	public String add(@ModelAttribute("user") User user, @RequestParam("roles") String role,@RequestParam("permissions") String permission,ModelMap model, RedirectAttributes attributes) {
		
		try {
			user.setPassword(Encryption.encrypByBCrypt(user.getPassword()));
			user.setActive(Boolean.TRUE);
			
			//Person p = baseService.findById(Person.class, Long.parseLong(person));
			//user.setPerson(p);
			
			List<Role> authorities  = new ArrayList<Role>();
			List<String> items = Arrays.asList(role.split("\\s*,\\s*"));
			
			for (String rolid : items) {
				Role rol= baseService.findById(Role.class, Long.parseLong(rolid));
				authorities.add(rol);
			//	user.setAuthorities(authorities);
			}
			
			
			baseService.persist(user);
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el usuario:"
							+ e.getMessage()));
			e.getStackTrace();
			e.getCause();
		}
		return SecopreConstans.MV_ADM_USR;
	}


	@RequestMapping(value = "start" , method = RequestMethod.GET)
	public String home(ModelMap model, Principal principal,
			HttpServletRequest request) {
		
		String name = principal.getName(); // get logged in username
		model.addAttribute("username", name);
		// Se colocan los menus del usuario en session
		request.getSession().setAttribute("menus",
				accessService.getMenuByUserName(name));
		User loggedUser = baseService.findByProperty(User.class, "username",
				name).get(0);
		model.addAttribute("loggedUser", loggedUser);

		return SecopreConstans.AUTH_INDEX;
	}
}