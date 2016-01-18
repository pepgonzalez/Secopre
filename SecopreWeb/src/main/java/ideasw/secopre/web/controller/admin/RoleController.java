package ideasw.secopre.web.controller.admin;

import ideasw.secopre.model.security.Permission;
import ideasw.secopre.model.security.Role;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@Autowired
	private AccessNativeService accessNativeService;

	@RequestMapping(value = "adm/role/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getRoleList(ModelMap model, RedirectAttributes attributes) {
		List<Role> roleList = baseService.findAll(Role.class);
		Role role = new Role();
		model.addAttribute("roleList", roleList);
		model.addAttribute("role", role);
		model.addAttribute("perms", baseService.findAll(Permission.class));

		return SecopreConstans.MV_ADM_ROLE;
	}

	@RequestMapping(value = "adm/role/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("role") Role role, @RequestParam("perms") String permission,ModelMap model, RedirectAttributes attributes) {
		try {
			if(role.getId() == null)
			{
			   role.setActive(Boolean.TRUE);
			}
			else
			{
			   Role roleEdit = baseService.findById(Role.class , role.getId());	
			   roleEdit.setRolename(role.getRolename());
			   role = roleEdit;
			}
			
			
			if (permission!=null)
			{   
				
				accessNativeService.cleanRolePermission(role.getId());
				List<Permission> permissionList  = new ArrayList<Permission>();
				List<String> items = Arrays.asList(permission.split("\\s*,\\s*"));
				
				for (String permid : items) {
					Permission perm= baseService.findById(Permission.class, Long.parseLong(permid));
					permissionList.add(perm);	
				}
			role.setPermissions(permissionList);
			}	
			
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
		model.addAttribute("perms", baseService.findAll(Permission.class));
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
	
	@RequestMapping(value = "adm/role/getPermissions/{idRole}", method= {RequestMethod.GET})
	public @ResponseBody Map<String, Object> getPermissions(@PathVariable("idRole") Long idRole){
		Map<String, Object> returnObject = new HashMap<String, Object>();
		//Listado de Permisos
		List<Permission> permissionList  = accessNativeService.getPermissionsByRole(idRole);
		
		 //List of numbers we want to concatenate
	    List<Long> numbers = new ArrayList<Long>();
	    for (Permission r : permissionList) {
	    	numbers.add(r.getId());
		}

	    //The string builder used to construct the string
	    StringBuilder commaSepValueBuilder = new StringBuilder();

	    //Looping through the list
	    for ( int i = 0; i< numbers.size(); i++){
	      //append the value into the builder
	      commaSepValueBuilder.append(numbers.get(i));

	      //if the value is not the last element of the list
	      //then append the comma(,) as well
	      if ( i != numbers.size()-1){
	        commaSepValueBuilder.append(",");
	      }
	    }
	    System.out.println(commaSepValueBuilder.toString());
		
	    String result = commaSepValueBuilder.toString().trim();
		
		returnObject.put("result", result);
		return returnObject;
	}
	
	
	
	
	
	
}
