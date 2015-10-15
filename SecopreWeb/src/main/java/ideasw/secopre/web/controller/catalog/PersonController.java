package ideasw.secopre.web.controller.catalog;

import java.util.HashMap;
import java.util.List;

import ideasw.secopre.enums.Gender;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.catalog.Address;
import ideasw.secopre.model.catalog.Person;
import ideasw.secopre.model.catalog.State;
import ideasw.secopre.model.security.Permission;
import ideasw.secopre.model.security.Role;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		Address address = new Address();
		model.addAttribute("personList", baseService.findAll(Person.class));
		model.addAttribute("person", person);
		model.addAttribute("roles", baseService.findAll(Role.class));
		model.addAttribute("permissions", baseService.findAll(Permission.class));
		model.addAttribute("address", address);
		model.addAttribute("gender",  Gender.values());
		
		List<State> state = baseService.findAll(State.class);
		
		HashMap<Long, String> stateMap = new HashMap<Long, String>();
		for (State e : state) {
			stateMap.put(e.getId(),e.getName() );
		}
		
		
		Request requestForm = new Request();
		
		model.addAttribute("states", stateMap);
		model.addAttribute("requestForm", requestForm);
		
		
		
		return SecopreConstans.MV_CAT_PERSON;
	}
	
	@RequestMapping(value = "cat/person/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("person") Person person,@ModelAttribute("address") Address address, @RequestParam("addressid") Long addressid  , ModelMap model) {
		try {
			address.setId(addressid);
			baseService.save(address);
			person.setAddress(address);
			baseService.save(person);
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el usuario:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_PERSON;
	}
	
	@RequestMapping(value = "cat/person/delete", method = RequestMethod.POST)
	public String delete(ModelMap model,  @RequestParam("id") Long id ) {
		try {
			Person person = baseService.findById(Person.class , id);
			if (person!=null){
				baseService.remove(person);
			}
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al eliminar la persona:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_PERSON;
	}
	
	
	@RequestMapping(value = "cat/person/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id ) {
		Person person = baseService.findById(Person.class , id);
		model.addAttribute("person", person);
		model.addAttribute("address", person.getAddress());
		
        List<State> state = baseService.findAll(State.class);
		HashMap<Long, String> stateMap = new HashMap<Long, String>();
		for (State e : state) {
			stateMap.put(e.getId(),e.getName() );
		}
		model.addAttribute("states", stateMap);
		model.addAttribute("gender",  Gender.values());
		
		
		return SecopreConstans.MV_CAT_PERSON;
	}


}
