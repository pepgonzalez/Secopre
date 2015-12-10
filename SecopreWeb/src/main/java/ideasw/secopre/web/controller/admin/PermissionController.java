package ideasw.secopre.web.controller.admin;

import ideasw.secopre.model.security.Menu;
import ideasw.secopre.model.security.Path;
import ideasw.secopre.model.security.Permission;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.util.HashMap;
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

		List<Path> path = baseService.findAll(Path.class);
		HashMap<Long, String> pathMap = new HashMap<Long, String>();
		for (Path p : path) {
			pathMap.put(p.getId(),p.getUrl());
		}
		model.addAttribute("paths", pathMap);
		
		return SecopreConstans.MV_ADM_PERMISSION;
	}


	@RequestMapping(value = "adm/perm/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("permission") Permission permission, ModelMap model) {
		try {
			
			
			if(permission.getId() == null)
			{
				permission.setActive(Boolean.TRUE);
			}
			else
			{
			   Permission permissionEdit = baseService.findById(Permission.class , permission.getId());	
			   permissionEdit.setName(permission.getName());  
			   permissionEdit.setPath(permission.getPath());  
			   permission = permissionEdit;
			}
			
			baseService.save(permission);
		} catch (Exception e) {
			e.getStackTrace();
			e.printStackTrace();
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el permiso:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_ADM_PERMISSION_LIST;
	}
	
	@RequestMapping(value = "adm/perm/delete", method = RequestMethod.POST)
	public String delete(ModelMap model,  @RequestParam("id") Long id ) {
		try {
			Permission permission = baseService.findById(Permission.class , id);
			if (permission!=null){
				baseService.remove(permission);
			}
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al eliminar la persona:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_ADM_PERMISSION_LIST;
	}
	
	
	@RequestMapping(value = "adm/perm/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id ) {
		Permission permission = baseService.findById(Permission.class , id);
		List<Permission> permList = baseService.findAll(Permission.class);
		model.addAttribute("permList", permList);
		model.addAttribute("permission", permission);
		
		List<Path> path = baseService.findAll(Path.class);
		HashMap<Long, String> pathMap = new HashMap<Long, String>();
		for (Path p : path) {
			pathMap.put(p.getId(),p.getUrl());
		}
		model.addAttribute("paths", pathMap);
		
		
		return SecopreConstans.MV_ADM_PERMISSION_ADD;
	}
	
	@RequestMapping(value = "adm/perm/changeStatus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String changeStatus( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id,@RequestParam("activo") Boolean activo  ) {
		Permission permissionEdit = baseService.findById(Permission.class , id);
		permissionEdit.setActive(activo);
		baseService.save(permissionEdit);
		
		return SecopreConstans.MV_ADM_PERMISSION_LIST;
	}
	
	
	
}
