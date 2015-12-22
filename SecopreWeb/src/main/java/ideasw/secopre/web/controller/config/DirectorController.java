package ideasw.secopre.web.controller.config;

import ideasw.secopre.model.Director;
import ideasw.secopre.model.catalog.Person;
import ideasw.secopre.model.security.User;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller principal encargada de administrar el catalogo de Directores
 
 * 
 * @author jesus.gallardos@gmail.com
 *
 */
@Controller
public class DirectorController extends AuthController {

	@RequestMapping(value = "param/director/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(ModelMap model, RedirectAttributes attributes) {
		Director director = new Director();
		model.addAttribute("directorList", baseService.findAll(Director.class));
		model.addAttribute("director", director);
		
		model.addAttribute("directores", accessNativeService.getDirectors());
		String position = null;
		List<User> userList = accessNativeService.getDirectors();
		
		//Lista de Personas
		HashMap<Long, String> directorMap = new HashMap<Long, String>();
		for (User user : userList) {
			User fullUser = baseService.findById(User.class , user.getId());	
			position = fullUser.getPosition().getDescription();
			
			Person person = new Person();
			person = baseService.findById(Person.class, user.getPerson().getId());
			if (position != null)
			{
			directorMap.put(user.getId() ,person.getName().concat(" ").concat(person.getSecondName().concat(" ").
					concat(person.getFatherLastName().concat(" ").concat(person.getMotherLastName().concat(" - ").concat(position) ))) );
			}
			else
			{
				directorMap.put(user.getId() ,person.getName().concat(" ").concat(person.getSecondName().concat(" ").
						concat(person.getFatherLastName().concat(" ").concat(person.getMotherLastName() ))) );	
			}
		}
		model.addAttribute("directors", directorMap);
		
		
		return SecopreConstans.MV_CAT_DIRECTOR;
	}

	@RequestMapping(value = "param/director/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("director") Director director, ModelMap model) {
		try {
			
			if(director.getId() == null)
			{
				director.setActive(Boolean.TRUE);
			}
			else
			{
			   Director directorEdit = baseService.findById(Director.class , director.getId());	
			   directorEdit.setInitialDate(director.getInitialDate());
			   directorEdit.setFinalDate(director.getFinalDate());
			   directorEdit.setActive(director.isActive());
			   directorEdit.setUser(director.getUser());
			   directorEdit.setLegend(director.getLegend());
			   director = directorEdit;
			}

			baseService.save(director);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar el historico de directores:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_DIRECTOR_LIST;
	}

	@RequestMapping(value = "param/director/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit(ModelMap model, RedirectAttributes attributes,
			@RequestParam("id") Long id) {
		
		Director director = baseService.findById(Director.class, id);
		model.addAttribute("director", director);
		List<User> userList = accessNativeService.getDirectors();
		//Lista de Personas
		HashMap<Long, String> directorMap = new HashMap<Long, String>();
		for (User user : userList) {
			Person person = new Person();
			person = baseService.findById(Person.class, user.getPerson().getId());
			directorMap.put(user.getId() ,person.getName().concat(" ").concat(person.getSecondName().concat(" ").concat(person.getFatherLastName().concat(" ").concat(person.getMotherLastName()))) );
		}
		model.addAttribute("directors", directorMap);
		
		

		return SecopreConstans.MV_CAT_DIRECTOR_ADD;
	}

	@RequestMapping(value = "param/director/delete", method = RequestMethod.POST)
	public String delete(ModelMap model, @RequestParam("id") Long id) {
		try {
			Director director = baseService.findById(Director.class, id);
			if (director != null) {
				baseService.remove(director);
			}
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar la fecha de corte:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_DIRECTOR_LIST;
	}

	@RequestMapping(value = "param/director/changeStatus", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String changeStatus(ModelMap model, RedirectAttributes attributes,
			@RequestParam("id") Long id, @RequestParam("activo") Boolean activo) {
		Director directorEdit = baseService.findById(Director.class, id);
		directorEdit.setActive(activo);
		baseService.save(directorEdit);

		return SecopreConstans.MV_CAT_DIRECTOR_LIST;
	}


	
	boolean isWithinRange(Date testDate,Date startDate,Date endDate) {
		return testDate.after(startDate) && testDate.before(endDate);
	}
	
//	@RequestMapping(value = "param/dueDate/isDueDateValid", method= {RequestMethod.GET})
//	public @ResponseBody Map<String, Object> isDueDateValid(@RequestParam("dueDateStr") String dueDateStr, @RequestParam("maxBlockDateStr") String maxBlockDateStr ){
//		Map<String, Object> returnObject = new HashMap<String, Object>();
//        DueDate dueDate = new DueDate();
//        dueDate.setDueDateStr(dueDateStr);
//        dueDate.setMaxBlockDateStr(maxBlockDateStr);
//        String result = "0";
//        try
//        {
//        	
//        	boolean isValid = false;
//        	boolean isValidDueDate = false;
//        	boolean isValidMaxBlockDate = false;
//    		
//    		
//    		if (dueDate.getDueDate().after(dueDate.getMaxBlockDate()))
//    		{
//    			isValid=true;
//				result="2";
//    		}
//    		
//    		if (!isValid)
//        	{
//    		   Map<String, Object> params = new HashMap<String, Object>();
//    		   params.put("activo", Boolean.TRUE);
//    		   List<DueDate> allActives = baseService.findByProperties(DueDate.class,
//    				params);	
//    		
//        
//	    		for (DueDate item : allActives) {
//	    			isValidDueDate = isWithinRange(dueDate.getDueDate(),item.getDueDate(),item.getMaxBlockDate());
//	    			isValidMaxBlockDate = isWithinRange(dueDate.getMaxBlockDate(),item.getDueDate(),item.getMaxBlockDate());
//	    			if(isValidMaxBlockDate || isValidDueDate)
//	    			{
//	    				isValid=true;
//	    				result="1";
//	    				break; 
//	    			}
//	    		}
//        	}
//        }
//    	catch(Exception e)
//    	{}
//
//	    
//		returnObject.put("result", result);
//		return returnObject;
//	}
	
	
	

}
