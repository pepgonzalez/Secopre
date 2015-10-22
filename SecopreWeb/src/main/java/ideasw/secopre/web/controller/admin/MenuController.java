package ideasw.secopre.web.controller.admin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ideasw.secopre.model.catalog.Person;
import ideasw.secopre.model.security.Menu;
import ideasw.secopre.model.security.Path;
import ideasw.secopre.model.security.Permission;
import ideasw.secopre.model.security.Role;
import ideasw.secopre.model.security.User;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		
		return SecopreConstans.MV_ADM_MENU;
	}
	
	@RequestMapping(value = "adm/menu/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("menu") Menu menu,@ModelAttribute("path") Path path, @RequestParam("pathid") Long pathid, ModelMap model,  @RequestParam("id") Long id ) {
		try {
			baseService.save(menu);
			path.setId(pathid);
			path.setMenu(menu);
			baseService.save(path);
			
			
			
		} catch (Exception e) {
			e.getStackTrace();
			e.printStackTrace();
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el menu:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_ADM_MENU;
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
		return SecopreConstans.MV_ADM_MENU;
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
		return SecopreConstans.MV_ADM_MENU;
	}
	
	
}
