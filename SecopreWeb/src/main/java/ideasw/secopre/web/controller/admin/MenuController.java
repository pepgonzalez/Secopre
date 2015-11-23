package ideasw.secopre.web.controller.admin;
import ideasw.secopre.model.security.Menu;
import ideasw.secopre.model.security.Path;
import ideasw.secopre.model.security.Permission;
import ideasw.secopre.model.security.Role;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.util.ArrayList;
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
 * Controller principal encargada de administrar el catalogo de Menus,
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>cat: Indica que esta en el modulo de catalogos</li>
 * <li>position: Indica que la configuracion pertenece a Puestos</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jesus.gallardos@gmail.com
 *
 */
@Controller
public class MenuController extends AuthController {
	
	@Autowired
	private AccessNativeService accessNativeService;

	@RequestMapping(value = "adm/menu/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(ModelMap model, RedirectAttributes attributes) {
		Menu menu = new Menu();
	    Path path = new Path();
		model.addAttribute("menuList", baseService.findAll(Menu.class));
		model.addAttribute("menu", menu);
		model.addAttribute("path", path); 
		
		List<Menu> parent = baseService.findAll(Menu.class);
		
		HashMap<Long, String> parentMap = new HashMap<Long, String>();
		for (Menu p : parent) {
			parentMap.put(p.getId(),p.getName() );
		}
		model.addAttribute("parents", parentMap);
				
		return SecopreConstans.MV_ADM_MENU;
	}
	
	@RequestMapping(value = "adm/menu/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id ) {
		Menu menu = baseService.findById(Menu.class , id);
		model.addAttribute("menu", menu);
	
		
		List<Menu> parent = baseService.findAll(Menu.class);
		
		HashMap<Long, String> parentMap = new HashMap<Long, String>();
		for (Menu p : parent) {
			parentMap.put(p.getId(),p.getName() );
		}
		model.addAttribute("parents", parentMap);
			
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("menu.id", menu.getId());
		List<Path> pathList  = baseService.findByProperties(Path.class, parameters);
		for (Path path : pathList) {
			model.addAttribute("path", path);
		}
		
		return SecopreConstans.MV_ADM_MENU_ADD;
	}
	
	@RequestMapping(value = "adm/menu/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("menu") Menu menu,@ModelAttribute("path") Path path, @RequestParam("pathid") Long pathid, ModelMap model,  @RequestParam("id") Long id ) {
		try {
			
			
			if(menu.getId() == null)
			{
				menu.setActive(Boolean.TRUE);
			}
			else
			{
			   Menu menuEdit = baseService.findById(Menu.class , menu.getId());	
			   menuEdit.setIcon(menu.getIcon());
			   menuEdit.setDescription(menu.getDescription());
			   menuEdit.setCssClass(menu.getCssClass());
			   menuEdit.setJsFunction(menu.getJsFunction());
			   menuEdit.setName(menu.getName());
			   menuEdit.setOrder(menu.getOrder());
			   menuEdit.setParentId(menu.getParentId());
			   menu = menuEdit;
			}
			
			//Se Crea el menu
			baseService.save(menu);
			//Se crea el path asociado al menu
			path.setId(pathid);
			path.setMenu(menu);
			baseService.save(path);
			
			path = baseService.getReference(Path.class, pathid);
			//Se crea un permisio asociado a ese Path
			
			Permission permission = new Permission();
			permission.setPath(path);
			permission.setName("MENU_"+menu.getName());
			permission.setActive(Boolean.TRUE);
			
			baseService.save(permission);
		} catch (Exception e) {
			e.getStackTrace();
			e.printStackTrace();
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el menu:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_ADM_MENU_LIST;
	}
	
	@RequestMapping(value = "adm/menu/delete", method = RequestMethod.POST)
	public String delete(ModelMap model,  @RequestParam("id") Long id ) {
		try {
			Menu menu = baseService.findById(Menu.class , id);
			if (menu!=null){
				baseService.remove(menu);
			}
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el menu:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_ADM_MENU_LIST;
	}
	
	@RequestMapping(value = "adm/menu/changeStatus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String changeStatus( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id,@RequestParam("activo") Boolean activo  ) {
		Menu menuEdit = baseService.findById(Menu.class , id);
		menuEdit.setActive(activo);
		baseService.save(menuEdit);
		
		Menu menu = new Menu();
	    Path path = new Path();
		model.addAttribute("menuList", baseService.findAll(Menu.class));
		model.addAttribute("menu", menu);
		model.addAttribute("path", path); 
		List<Menu> parent = baseService.findAll(Menu.class);
		
		HashMap<Long, String> parentMap = new HashMap<Long, String>();
		for (Menu p : parent) {
			parentMap.put(p.getId(),p.getName() );
		}
		model.addAttribute("parents", parentMap);
		return SecopreConstans.MV_ADM_MENU_LIST;
	}
	
	@RequestMapping(value = "adm/menu/getRoles/{idMenu}", method= {RequestMethod.GET})
	public @ResponseBody Map<String, Object> getRoles(@PathVariable("idMenu") Long idMenu){
		Map<String, Object> returnObject = new HashMap<String, Object>();
		//Listado de Roles
		List<Role> authorities  = accessNativeService.getRolesByMenu(idMenu);
		
		 //List of numbers we want to concatenate
	    List<Long> numbers = new ArrayList<Long>();
	    for (Role r : authorities) {
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
