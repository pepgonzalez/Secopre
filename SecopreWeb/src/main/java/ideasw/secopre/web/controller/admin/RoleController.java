package ideasw.secopre.web.controller.admin;

import ideasw.secopre.model.security.Role;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "adm/role/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("role") Role role, ModelMap model) {
		try {
			if(role.getId() == null)
			role.setActive(Boolean.TRUE);
			
			baseService.save(role);
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el rol:"                    
							+ e.getMessage()));
		}
		return SecopreConstans.MV_ADM_ROLE_LIST;
	}
	
	@RequestMapping(value = "adm/role/delete", method = RequestMethod.POST)
	public String delete(ModelMap model,  @RequestParam("id") Long id ) {
		try {
			Role role = baseService.findById(Role.class , id);
			if (role!=null){
				baseService.remove(role);
			}
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al eliminar la persona:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_ADM_ROLE_LIST;
	}
	
	
	@RequestMapping(value = "adm/role/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id ) {
		Role role = baseService.findById(Role.class , id);
		List<Role> roleList = baseService.findAll(Role.class);
		model.addAttribute("roleList", roleList);
		model.addAttribute("role", role);
		return SecopreConstans.MV_ADM_ROLE_ADD;
	}
	
	@RequestMapping(value = "adm/role/changeStatus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String changeStatus( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id,@RequestParam("activo") Boolean activo  ) {
		Role roleEdit = baseService.findById(Role.class , id);
		roleEdit.setActive(activo);
		baseService.save(roleEdit);
		
		return SecopreConstans.MV_ADM_ROLE_LIST;
	}
	
	
	
	
}
