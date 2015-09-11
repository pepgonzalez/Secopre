package ideasw.secopre.web.controller.catalog;

import ideasw.secopre.model.Person;
import ideasw.secopre.model.security.Permission;
import ideasw.secopre.model.security.Role;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.utils.encryption.Encryption;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller principal encargada de administrar el catalogo de Personas,
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>cat: Indica que esta en el modulo de catalogos</li>
 * <li>person: Indica que la configuracion pertenece a Usuarios</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jesus.gallardos@gmail.com
 *
 */
@Controller
public class PersonController extends AuthController {

	@Autowired
	private AccessService accessService;

	@RequestMapping(value = "cat/person/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getPersonList(ModelMap model, RedirectAttributes attributes) {
		Person person = new Person();
		model.addAttribute("personList", baseService.findAll(Person.class));
		model.addAttribute("person", person);
		model.addAttribute("roles", baseService.findAll(Role.class));
		model.addAttribute("permissions", baseService.findAll(Permission.class));
		return SecopreConstans.MV_CAT_PERSON;
	}


}
