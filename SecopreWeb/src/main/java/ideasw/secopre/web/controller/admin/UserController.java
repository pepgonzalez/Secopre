package ideasw.secopre.web.controller.admin;

import ideasw.secopre.constants.PropertyConstants;
import ideasw.secopre.dto.Notification;
import ideasw.secopre.dto.Property;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.catalog.Person;
import ideasw.secopre.model.catalog.Position;
import ideasw.secopre.model.security.Permission;
import ideasw.secopre.model.security.Role;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.utils.encryption.Encryption;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
 * usuarios,dentro de este controller se podran configurar los usuarios de la
 * aplicacion
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>adm: Indica que esta en el modulo de administracion</li>
 * <li>usr: Indica que la configuracion pertenece a Usuarios</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Controller
public class UserController extends AuthController {

	@Autowired
	private AccessService accessService;
	
	@Autowired
	private AccessNativeService accessNativeService;

	@RequestMapping(value = "adm/usr/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getUserList(ModelMap model, RedirectAttributes attributes) {
		User user = new User();
		model.addAttribute("userList", baseService.findAll(User.class));
		model.addAttribute("user", user);
		model.addAttribute("roles", baseService.findAll(Role.class));
		//model.addAttribute("permissions", baseService.findAll(Permission.class));
		
		
		List<Person> person = baseService.findAll(Person.class);
		//Lista de Personas
		HashMap<Long, String> personMap = new HashMap<Long, String>();
		for (Person p : person) {
			personMap.put(p.getId(),p.getName().concat(" ").concat(p.getSecondName().concat(" ").concat(p.getFatherLastName().concat(" ").concat(p.getMotherLastName()))) );
		}
		model.addAttribute("persons", personMap);
		//Lista de Position
		List<Position> position = baseService.findAll(Position.class);
		HashMap<Long, String> positionMap = new HashMap<Long, String>();
		for (Position p : position) {
			positionMap.put(p.getId(),p.getName());
		}
		model.addAttribute("positions", positionMap);
		
		//Lista de Distritos
		//model.addAttribute("districts", secopreCache.getAlldistricts());
		
		model.addAttribute("districts", secopreCache.getValidDistricts());
		
		return SecopreConstans.MV_ADM_USR;
	}

	@RequestMapping(value = "adm/directory", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getDirectory(ModelMap model, Principal principal) {
		return SecopreConstans.MV_ADM_DIR;
	}

	@RequestMapping(value = "adm/usr/", method = RequestMethod.PUT)
	public String getUser(ModelMap model, Principal principal) {
		return null;
	}
	
	@RequestMapping(value = "adm/usr/checkUsername", method = RequestMethod.GET, produces="text/plain")
		public String checkUsername(ModelMap model,Principal principal) {
        return Boolean.TRUE.toString();
	}

	@RequestMapping(value = "adm/usr/{username}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable String username, ModelMap model,
			Principal principal) {
		return null;
	}

	@RequestMapping(value = "adm/usr/add", method = RequestMethod.POST)
	//public String add(@ModelAttribute("user") User user, @RequestParam("roles") String role,@RequestParam("permissions") String permission,ModelMap model, RedirectAttributes attributes) {
	public String add(@ModelAttribute("user") User user, @RequestParam("roles") String role,@RequestParam("distrs") String districts,ModelMap model, RedirectAttributes attributes) {
	
		try {
			if(user.getId()==null)
			{
			   user.setPassword(Encryption.encrypByBCrypt(user.getPassword()));
			   user.setActive(Boolean.TRUE);
			}
			else
			{
			   User userEdit = baseService.findById(User.class , user.getId());
			   userEdit.setNickname(user.getNickname());
			   userEdit.setEmail(user.getEmail());
			   userEdit.setHasChatActive(user.getHasChatActive());
			   userEdit.setPerson(user.getPerson());
			   userEdit.setPosition(user.getPosition());

			   user=userEdit;
			}
            // Roles
			List<Role> authorities  = new ArrayList<Role>();
			List<String> items = Arrays.asList(role.split("\\s*,\\s*"));
			
			for (String rolid : items) {
				Role rol= baseService.findById(Role.class, Long.parseLong(rolid));
				authorities.add(rol);	
			}
			user.setAuthorities(authorities);
				
			baseService.save(user);
			
			//Distritos
			List<User> userList  = new ArrayList<User>();
			List<String> itemsDist = Arrays.asList(districts.split("\\s*,\\s*"));
			//userList.add(user);
			
			//Se obtiene la lista original de distritos que tiene el usuario
			List<District> originalDistrictByUser = accessNativeService.getDistrictsByUser(user.getId()) ;
			
			for (String districtId : itemsDist) {
				District district= baseService.findById(District.class, Long.parseLong(districtId));
				//Obtener Todos los usuario de el distrito em curso
				List<User> originalUserByDistrictList = accessNativeService.getUsersByDistrict(district.getId());
				
				//Buscamos el distrito de la lista definitiva en la lista que existe actualmente
				if (!originalDistrictByUser.contains(district))
					{
					   originalUserByDistrictList.add(user);
					}
				district.setUsers(originalUserByDistrictList);
				baseService.save(district);
			}
			
			for (District district : originalDistrictByUser) {
				District dist = baseService.findById(District.class, district.getId());
				if (!itemsDist.contains(dist.getId().toString()))
				{
					//TReigo la lista de ususarios de este distrito
					List<User> originalUserByDistrictList = accessNativeService.getUsersByDistrict(dist.getId());
					originalUserByDistrictList.remove(user);
					dist.setUsers(originalUserByDistrictList);
					baseService.save(dist);
					
				}

			}
			
			return SecopreConstans.MV_ADM_USR_LIST;

		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el usuario:"
							+ e.getMessage()));
			e.getStackTrace();
			e.printStackTrace();
			e.getCause();
		}
		return SecopreConstans.MV_ADM_USR_LIST;
	}

	@RequestMapping(value = "start" , method = RequestMethod.GET)
	public String home(ModelMap model, Principal principal,
			HttpServletRequest request) {
		
		String name = principal.getName(); // get logged in username
		model.addAttribute("username", name);
		// Se colocan los menus del usuario en session
		request.getSession().setAttribute("menus", accessService.getMenuByUserName(name));
		
		//User loggedUser = baseService.findByProperty(User.class, "username", name).get(0);
		User loggedUser = accessNativeService.getUserByUsename(name);
	    List<Role> authorities = accessNativeService.getRolesByUser(loggedUser.getId());
	    loggedUser.setAuthorities(authorities);
		
		model.addAttribute("loggedUser", loggedUser);

		//se obtienen las notificaciones
		List<Notification> notifications = accessNativeService.getNotificationByUserId(loggedUser.getId());
		
		model.addAttribute("notifications", notifications);
		model.addAttribute("totalNotifications", notifications.size());
		
		return SecopreConstans.AUTH_INDEX;
	}
	
	@RequestMapping(value = "adm/usr/delete", method = RequestMethod.POST)
	public String delete(ModelMap model,  @RequestParam("id") Long id ) {
		try {
			User user = baseService.findById(User.class , id);
			if (user!=null){
				baseService.remove(user);
			}
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al eliminar el Usuario:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_ADM_USR;
	}
	
	@RequestMapping(value = "adm/usr/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id ) {
		User user = baseService.findById(User.class , id);
		model.addAttribute("user", user);
		
		model.addAttribute("userList", baseService.findAll(User.class));
	
		//model.addAttribute("permissions", baseService.findAll(Permission.class));
	
		//Listado de Personas
		List<Person> person = baseService.findAll(Person.class);
		HashMap<Long, String> personMap = new HashMap<Long, String>();
		for (Person p : person) {
			personMap.put(p.getId(),p.getName().concat(" ").concat(p.getSecondName().concat(" ").concat(p.getFatherLastName().concat(" ").concat(p.getMotherLastName()))) );
		}
		model.addAttribute("persons", personMap);
		
		//Lista de Roles
		model.addAttribute("roles", baseService.findAll(Role.class));
		
		//Lista de Distritos
		//model.addAttribute("districts", secopreCache.getAlldistricts());
		model.addAttribute("districts", secopreCache.getValidDistricts());
		
		//Lista de Position
		List<Position> position = baseService.findAll(Position.class);
		HashMap<Long, String> positionMap = new HashMap<Long, String>();
		for (Position p : position) {
			positionMap.put(p.getId(), p.getName());
		}
		model.addAttribute("positions", positionMap);

		return SecopreConstans.MV_ADM_USR_EDIT;
	}
	
	@RequestMapping(value = "adm/usr/changeStatus", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String changeStatus( ModelMap model, RedirectAttributes attributes, @RequestParam("id") Long id,@RequestParam("activo") Boolean activo  ) {
		User userEdit = baseService.findById(User.class , id);
		userEdit.setActive(activo);
		baseService.save(userEdit);
		User user = new User();
		model.addAttribute("userList", baseService.findAll(User.class));
		model.addAttribute("user", user);
		model.addAttribute("roles", baseService.findAll(Role.class));
		model.addAttribute("permissions", baseService.findAll(Permission.class));
		
		List<Person> person = baseService.findAll(Person.class);
		
		HashMap<Long, String> personMap = new HashMap<Long, String>();
		for (Person p : person) {
			personMap.put(p.getId(),p.getName() );
		}
		model.addAttribute("persons", personMap);
		return SecopreConstans.MV_ADM_USR_LIST;
	}
	
	@RequestMapping(value = "adm/usr/checkUsername2/{username}", method= {RequestMethod.GET})
	public @ResponseBody Map<String, Object> checkUsername2(@PathVariable("username") String username){
		Map<String, Object> returnObject = new HashMap<String, Object>();
		
		int result = accessNativeService.isUsernameValid(username);
		returnObject.put("result", result);
		return returnObject;
	}
	
	@RequestMapping(value = "adm/usr/getRoles/{idUser}", method= {RequestMethod.GET})
	public @ResponseBody Map<String, Object> getRoles(@PathVariable("idUser") Long idUser){
		Map<String, Object> returnObject = new HashMap<String, Object>();
		//Listado de Roles
		List<Role> authorities  = accessNativeService.getRolesByUser(idUser);
		
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
	
	@RequestMapping(value = "adm/usr/getDistrictsByUser/{idUser}", method= {RequestMethod.GET})
	public @ResponseBody Map<String, Object> getDistrictsByUser(@PathVariable("idUser") Long idUser){
		Map<String, Object> returnObject = new HashMap<String, Object>();
		//Listado de Distritos
		List<District> districts  = accessNativeService.getDistrictsByUser(idUser);
		
		 //List of numbers we want to concatenate
	    List<Long> numbers = new ArrayList<Long>();
	    for (District r : districts) {
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
	
	@RequestMapping(value = "adm/usr/getDistrictsByUserRole/{roles}/{distrs}", method= {RequestMethod.GET})
	public @ResponseBody Map<String, Object> getDistrictsByUserRole(@PathVariable("roles") String roles, @PathVariable("distrs") String distritos){
		Map<String, Object> returnObject = new HashMap<String, Object>();
		
		List<String> itemsDist = Arrays.asList(distritos.split("\\s*,\\s*"));
		List<String> itemsRoles = Arrays.asList(roles.split("\\s*,\\s*"));
	    String result ="valido";
		for (String roleId : itemsRoles) {		
			for (String districtId : itemsDist) {
				
				District district= baseService.findById(District.class, Long.parseLong(districtId));
				Role role= baseService.findById(Role.class, Long.parseLong(roleId));
				
				if (accessNativeService.hasDistrictRole(Long.parseLong(districtId), Long.parseLong(roleId)))
				{  
					 result = "El distrito " + "DTO-" +district.getNumber() + " ya tiene asignado una persona con el rol " + role.getName() + "."; 
						
					 returnObject.put("result", result);
					 return returnObject;
					
				}
				
			}
		}
		
		returnObject.put("result", result);
		return returnObject;
		

	}
	@RequestMapping(value = "adm/usr/resetPass", method = RequestMethod.POST)
	public String resetPass(ModelMap model, @RequestParam("id") Long id) {
		try {
			User user = baseService.findById(User.class, id);
			if (user != null) {
				Property p = accessNativeService
						.getPropertyByCode(PropertyConstants.PASSWORD_DEFAULT);
				user.setPassword(Encryption.encrypByBCrypt(p.getValue()));
				baseService.update(user);
			}
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al resetear el Usuario:"
							+ e.getMessage()));
		}
		return "redirect:/auth/adm/usr/list";
	}	
}
