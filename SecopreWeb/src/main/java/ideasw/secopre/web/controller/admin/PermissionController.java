package ideasw.secopre.web.controller.admin;

import ideasw.secopre.model.security.Permission;
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
 * permisos,dentro de este controller se podran configurar los permisios de la
 * aplicacion
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>adm: Indica que esta en el modulo de administracion</li>
 * <li>perm: Indica que la configuracion pertenece a Permisos</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Controller
public class PermissionController extends AuthController {

	@Autowired
	private AccessService accessService;

	@RequestMapping(value = "adm/perm/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getRoleList(ModelMap model, RedirectAttributes attributes) {
		List<Permission> permList = baseService.findAll(Permission.class);
		Permission permission = new Permission();
		model.addAttribute("permList", permList);
		model.addAttribute("permission", permission);
		return SecopreConstans.MV_ADM_PERMISSION;
	}

	@RequestMapping(value = "adm/perm", method = RequestMethod.PUT)
	public String getUser(ModelMap model, Principal principal) {
		return null;
	}

	@RequestMapping(value = "adm/perm/{rolename}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable String rolename, ModelMap model,
			Principal principal) {
		return null;
	}

	@RequestMapping(value = "adm/perm/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("permission") Permission permission, ModelMap model) {
		try {
			baseService.persist(permission);
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el permiso:"
							+ e.getMessage()));
		}
		return "redirect:" + SecopreConstans.MV_LIST;
	}
}
