package ideasw.secopre.web.controller.admin;

import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 * @author jesus.gallardos@gmail.com
 *
 */
@Controller
public class ProfileController extends AuthController {

	@Autowired
	private AccessService accessService;
	
	@Autowired
	private AccessNativeService accessNativeService;

	@RequestMapping(value = "adm/profile/show", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getUserList(ModelMap model,Principal principal, HttpServletRequest request) {
			String name = principal.getName(); // get logged in username
			model.addAttribute("username", name);
			User loggedUser = baseService.findByProperty(User.class, "username",
					name).get(0);
			model.addAttribute("loggedUser", loggedUser);
		return SecopreConstans.MV_ADM_PROFILE;
	}
	
	@RequestMapping(value = "adm/profile/showProfileAccount/{idUser}", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String showProfileAccount(ModelMap model, @PathVariable("idUser") Long idUser,Principal principal, HttpServletRequest request) {
	
			
			String name = principal.getName(); // get logged in username
			model.addAttribute("username", name);
			User loggedUser = baseService.findByProperty(User.class, "username",
					name).get(0);
			model.addAttribute("loggedUser", loggedUser);
		    
			User user = baseService.findById(User.class , idUser);
			Person person = baseService.findById(Person.class , user.getPerson().getId());

			model.addAttribute("person", person);
			
		return SecopreConstans.MV_ADM_PROFILE_ACCOUNT;
	}


	
	
}
