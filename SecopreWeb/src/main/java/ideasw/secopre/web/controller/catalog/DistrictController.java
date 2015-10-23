package ideasw.secopre.web.controller.catalog;

import java.util.HashMap;
import java.util.List;

import ideasw.secopre.model.catalog.Address;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.catalog.Position;
import ideasw.secopre.model.catalog.State;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.SecopreCache;
import ideasw.secopre.web.controller.base.AuthController;
import ideasw.secopre.model.catalog.Person;
import ideasw.secopre.model.security.Menu;
import ideasw.secopre.model.security.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller principal encargada de administrar el catalogo de Distritos,
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>cat: Indica que esta en el modulo de catalogos</li>
 * <li>district: Indica que la configuracion pertenece a Distritos</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */




@Controller
public class DistrictController extends AuthController {
	
	@Autowired
	private SecopreCache secopreCahe; 

	@RequestMapping(value = "cat/district/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(ModelMap model, RedirectAttributes attributes) {
		District district = new District();
		Person person = new Person();
		Address address = new Address();
		State state = new State();
		model.addAttribute("districtList", baseService.findAll(District.class));
		model.addAttribute("district", district);
		model.addAttribute("person", person);
		model.addAttribute("address", address);
		model.addAttribute("state", state);
		
		
		
		model.addAttribute("states", secopreCahe.getAllStatesMap());
		
		return SecopreConstans.MV_CAT_DISTRICT;
	}
	
	@RequestMapping(value = "cat/district/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("district") District district,ModelMap model) {
		try {
			baseService.save(district);
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el Distrito:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_DISTRICT;
	}
	
	@RequestMapping(value = "cat/district/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id ) {
		District district = baseService.findById(District.class , id);
		return SecopreConstans.MV_CAT_DISTRICT;
	}
	
	@RequestMapping(value = "cat/district/delete", method = RequestMethod.POST)
	public String delete(ModelMap model,  @RequestParam("id") Long id ) {
		try {
			District district = baseService.findById(District.class , id);
			if (district!=null){
				baseService.remove(district);
			}
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el menu:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_DISTRICT;
	}
	
	@RequestMapping(value = "cat/district/changeStatus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String changeStatus( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id,@RequestParam("activo") Boolean activo  ) {
		District districtEdit = baseService.findById(District.class , id);
		districtEdit.setActivo(activo);
		baseService.save(districtEdit);
		
		District district = new District();
		Person person = new Person();
		Address address = new Address();
		State state = new State();
		model.addAttribute("districtList", baseService.findAll(District.class));
		model.addAttribute("district", district);
		model.addAttribute("person", person);
		model.addAttribute("address", address);
		model.addAttribute("state", state);
		
		model.addAttribute("states", secopreCahe.getAllStatesMap());
		
		return SecopreConstans.MV_CAT_DISTRICT;
	}
	
}
