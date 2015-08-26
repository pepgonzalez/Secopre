package ideasw.secopre.web.controller.admin;

import ideasw.secopre.model.security.Role;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller principal encargada del modulo de administracion de
 * roles,dentro de este controller se podran configurar los roles de la
 * aplicacion
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>adm: Indica que esta en el modulo de administracion</li>
 * <li>role: Indica que la configuracion pertenece a Roles</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Controller
public class RoleController extends AuthController {

	@Autowired
	private AccessService accessService;

	@RequestMapping(value = "adm/role/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getRoleList(ModelMap model, RedirectAttributes attributes) {
		List<Role> roleList = baseService.findAll(Role.class);
		Role role = new Role();
		model.addAttribute("roleList", roleList);
		model.addAttribute("role", role);
		return SecopreConstans.MV_ADM_ROLE;
	}

	@RequestMapping(value = "adm/role", method = RequestMethod.PUT)
	public String getUser(ModelMap model, Principal principal) {
		return null;
	}

	@RequestMapping(value = "adm/role/{rolename}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable String rolename, ModelMap model,
			Principal principal) {
		return null;
	}

	@RequestMapping(value = "adm/role/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("role") Role role, ModelMap model) {
		try {
			baseService.persist(role);
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el rol:"
							+ e.getMessage()));
		}
		return "redirect:" + SecopreConstans.MV_LIST;
	}
}
