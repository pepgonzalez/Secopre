package ideasw.secopre.web.controller.catalog;



import ideasw.secopre.model.catalog.Address;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.catalog.Person;
import ideasw.secopre.model.catalog.State;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.SecopreCache;
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
	
	@Autowired
	private AccessNativeService accessNativeService;

	@RequestMapping(value = "cat/district/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(ModelMap model, RedirectAttributes attributes) {
		District district = new District();
		Person person = new Person();
		Address address = new Address();
		State state = new State();
		
		List<District> districtList  = new ArrayList<District>();
		districtList = accessNativeService.getDistricts();
//		
//		for (District dist : districtList) {
//			if (dist.getAddress().getId() != 0)
//			{Address dir= baseService.findById(Address.class,dist.getAddress().getId());
//			dist.setAddress(dir);
//			}
//		}
		
		model.addAttribute("districtList", districtList);
		//model.addAttribute("districtList", baseService.findAll(District.class));
		model.addAttribute("district", district);
		model.addAttribute("person", person);
		model.addAttribute("address", address);
		model.addAttribute("state", state);
		model.addAttribute("states", secopreCahe.getAllStatesMap());
		//model.addAttribute("usuarios", baseService.findAll(User.class));
		model.addAttribute("usuarios", accessNativeService.getUsers() );
		
		return SecopreConstans.MV_CAT_DISTRICT;
	}
	
	@RequestMapping(value = "cat/district/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("district") District district,@ModelAttribute("address") Address address, @RequestParam("usuarios") String users,@RequestParam("addressid") Long addressid, ModelMap model) {
		try {
			List<User> userList  = new ArrayList<User>();
			List<String> items = Arrays.asList(users.split("\\s*,\\s*"));
			
			for (String userid : items) {
				User user= baseService.findById(User.class, Long.parseLong(userid));
				userList.add(user);	
			}
			
			
			if(district.getId() == null)
			{
				district.setActivo(Boolean.TRUE);
				address.setActivo(Boolean.TRUE);
				baseService.save(address);
				district.setAddress(address);
			}
			else
			{
			   District districtEdit = baseService.findById(District.class , district.getId());	
			   if (addressid != null)
			   {
				   Address addressEdit = baseService.findById(Address.class , addressid);
				   addressEdit.setZipCode(address.getZipCode());
				   addressEdit.setCity(address.getCity());
				   addressEdit.setColony(address.getColony());
				   addressEdit.setInteriorNumber(address.getInteriorNumber());
				   addressEdit.setExteriorNumber(address.getExteriorNumber());
				   addressEdit.setStreet(address.getStreet());
				   addressEdit.setStateDTO(address.getStateDTO() );
				   address = addressEdit;
			   }
			   else
			   {
				   Address addressEdit = new Address();
				   addressEdit.setActivo(Boolean.TRUE);
				   addressEdit.setZipCode(address.getZipCode());
				   addressEdit.setCity(address.getCity());
				   addressEdit.setColony(address.getColony());
				   addressEdit.setInteriorNumber(address.getInteriorNumber());
				   addressEdit.setExteriorNumber(address.getExteriorNumber());
				   addressEdit.setStreet(address.getStreet());
				   addressEdit.setStateDTO(address.getStateDTO() );
//				   addressEdit.setState(address.getState()); 
				   address = addressEdit;
			   }
			   
			   districtEdit.setAddress(address);
			   districtEdit.setEmail(district.getEmail());
			   districtEdit.setNumber(district.getNumber());
			   districtEdit.setState(district.getState());
			   districtEdit.setTelephone(district.getTelephone());
			   districtEdit.setEntity(district.getEntity());

			   district = districtEdit;
			}
			
			
			district.setUsers(userList);
			baseService.save(district);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el Distrito:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_DISTRICT_LIST;
	}
	
	@RequestMapping(value = "cat/district/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id ) {
		District district = baseService.findById(District.class , id);

		model.addAttribute("district", district);
		if (district.getAddress() != null){
			model.addAttribute("address", district.getAddress());			
		}
		else{
			model.addAttribute("address", new Address());	
		}

		model.addAttribute("state", district.getState());

		model.addAttribute("states", secopreCahe.getAllStatesMap());
		
		//model.addAttribute("usuarios", baseService.findAll(User.class));
		
		model.addAttribute("usuarios", accessNativeService.getUsers());
		
		
		return SecopreConstans.MV_CAT_DISTRICT_ADD;
		
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
		return SecopreConstans.MV_CAT_DISTRICT_LIST;
	}
	
	@RequestMapping(value = "cat/district/changeStatus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String changeStatus( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id,@RequestParam("activo") Boolean activo  ) {
		District districtEdit = baseService.findById(District.class , id);
		districtEdit.setActivo(activo);
		baseService.save(districtEdit);
		
//		District district = new District();
//		Person person = new Person();
//		Address address = new Address();
//		State state = new State();
//		model.addAttribute("districtList", baseService.findAll(District.class));
//		model.addAttribute("district", district);
//		model.addAttribute("person", person);
//		model.addAttribute("address", address);
//		model.addAttribute("state", state);
//		
//		model.addAttribute("states", secopreCahe.getAllStatesMap());
		
		return SecopreConstans.MV_CAT_DISTRICT_LIST;
	}
	
	@RequestMapping(value = "adm/usr/getUsersByDistrict/{idDistrict}", method= {RequestMethod.GET})
	public @ResponseBody Map<String, Object> getUsersByDistrict(@PathVariable("idDistrict") Long idDistrict){
		Map<String, Object> returnObject = new HashMap<String, Object>();
		//Listado de Distritos
		List<User> users  = accessNativeService.getUsersByDistrict(idDistrict);
		
		 //List of numbers we want to concatenate
	    List<Long> numbers = new ArrayList<Long>();
	    for (User u : users) {
	    	numbers.add(u.getId());
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
