package ideasw.secopre.web.controller.config;

import ideasw.secopre.model.DueDate;
import ideasw.secopre.utils.time.TimeUtils;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller principal encargada de administrar el catalogo de Claves
 * Programaticas,
 * 
 * El {@link RequestMapping} se compone de 3 paths principales que son
 * <ul>
 * <li>oper: Indica que el controller es operativo</li>
 * <li>pk: Indica que la configuracion pertenece a Clave Programatica</li>
 * <li>auth: Indica que el modulo esta protegido por autorizacion</li>
 * </ul>
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Controller
public class DueDateController extends AuthController {

	@RequestMapping(value = "param/dueDate/list", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String getList(ModelMap model, RedirectAttributes attributes) {
		DueDate dueDate = new DueDate();
		model.addAttribute("dueDateList", baseService.findAll(DueDate.class));
		model.addAttribute("dueDate", dueDate);
		return SecopreConstans.MV_CAT_DUEDATE;
	}

	@RequestMapping(value = "param/dueDate/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("dueDate") DueDate dueDate, ModelMap model) {
		try {
			dueDate.setActivo(Boolean.TRUE);
			
			
			
			baseService.save(dueDate);
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar la fecha de corte:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_DUEDATE_LIST;
	}

	@RequestMapping(value = "param/dueDate/edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String edit(ModelMap model, RedirectAttributes attributes,
			@RequestParam("id") Long id) {
		DueDate dueDate = baseService.findById(DueDate.class, id);
		// String dueDateStr = dueDate.getDueDateStr();
		// dueDate.setDueDateStr(dueDateStr);
		model.addAttribute("dueDate", dueDate);
		return SecopreConstans.MV_CAT_DUEDATE_ADD;
	}

	@RequestMapping(value = "param/dueDate/delete", method = RequestMethod.POST)
	public String delete(ModelMap model, @RequestParam("id") Long id) {
		try {
			DueDate dueDate = baseService.findById(DueDate.class, id);
			if (dueDate != null) {
				baseService.remove(dueDate);
			}
		} catch (Exception e) {
			model.addAttribute(
					"errors",
					initErrors("Ocurrio un error al insertar la fecha de corte:"
							+ e.getMessage()));
		}
		return SecopreConstans.MV_CAT_DUEDATE_LIST;
	}

	@RequestMapping(value = "param/dueDate/changeStatus", method = {
			RequestMethod.GET, RequestMethod.POST })
	public String changeStatus(ModelMap model, RedirectAttributes attributes,
			@RequestParam("id") Long id, @RequestParam("activo") Boolean activo) {
		DueDate dueDateEdit = baseService.findById(DueDate.class, id);
		dueDateEdit.setActivo(activo);
		baseService.save(dueDateEdit);

		// DueDate dueDate = new DueDate();
		// model.addAttribute("dueDateList",
		// baseService.findAll(DueDate.class));
		// model.addAttribute("dueDate", dueDate);
		return SecopreConstans.MV_CAT_DUEDATE_LIST;
	}

	public boolean isValidDueDate(DueDate dueDate) {
		boolean isValid = false;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("activo", Boolean.TRUE);
		List<DueDate> allActives = baseService.findByProperties(DueDate.class,
				params);

		if (allActives == null || allActives.isEmpty()) {
			return true;
		}

		List<Interval> allIntervals = new ArrayList<Interval>(0);
		DateTime now = DateTime.now();
		Interval interval = null;
		for (DueDate item : allActives) {
			// Si la fecha actual es anterior a la fecha de inicio de la fecha
			// corte no realiza nada
			if (now.isBefore(item.getDueDate().getTime())) {
				continue;
			}

			// Se asume que todas las fechas que pasan por aqui son futuras

			// Se crea el intervalo con la fecha inicio y fecha fin
			interval = new Interval(item.getDueDate().getTime(), item
					.getMaxBlockDate().getTime());
			allIntervals.add(interval);
		}

		if (allIntervals.isEmpty()) {
			return true;
		}
		isValid = TimeUtils.isOverlaps(new Interval(dueDate.getDueDate()
				.getTime(), dueDate.getMaxBlockDate().getTime()), allIntervals);

		return isValid;
	}
	
	boolean isWithinRange(Date testDate,Date startDate,Date endDate) {
		return testDate.after(startDate) && testDate.before(endDate);
	}
	
	@RequestMapping(value = "param/dueDate/isDueDateValid", method= {RequestMethod.GET})
	public @ResponseBody Map<String, Object> isDueDateValid(@RequestParam("dueDateStr") String dueDateStr, @RequestParam("maxBlockDateStr") String maxBlockDateStr ){
		Map<String, Object> returnObject = new HashMap<String, Object>();
        DueDate dueDate = new DueDate();
        dueDate.setDueDateStr(dueDateStr);
        dueDate.setMaxBlockDateStr(maxBlockDateStr);
        String result = "0";
        try
        {
        	
        	boolean isValid = false;
        	boolean isValidDueDate = false;
        	boolean isValidMaxBlockDate = false;
    		
    		
    		if (dueDate.getDueDate().after(dueDate.getMaxBlockDate()))
    		{
    			isValid=true;
				result="2";
    		}
    		
    		if (!isValid)
        	{
    		   Map<String, Object> params = new HashMap<String, Object>();
    		   params.put("activo", Boolean.TRUE);
    		   List<DueDate> allActives = baseService.findByProperties(DueDate.class,
    				params);	
    		
        
	    		for (DueDate item : allActives) {
	    			isValidDueDate = isWithinRange(dueDate.getDueDate(),item.getDueDate(),item.getMaxBlockDate());
	    			isValidMaxBlockDate = isWithinRange(dueDate.getMaxBlockDate(),item.getDueDate(),item.getMaxBlockDate());
	    			if(isValidMaxBlockDate || isValidDueDate)
	    			{
	    				isValid=true;
	    				result="1";
	    				break; 
	    			}
	    		}
        	}
        }
    	catch(Exception e)
    	{}
//		
//		
//		
//	    if (isValid) 
//	    {result="1";}
//	    else
//	    {result="0";}
//        }
//        catch(Exception e)
//        {result="1";}
	    
		returnObject.put("result", result);
		return returnObject;
	}
	
	
	

}
