package ideasw.secopre.web.controller.admin;

import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Movement;
import ideasw.secopre.dto.Rectification;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.EntryDistrict;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.security.User;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TramiteController extends AuthController {

	static final Logger LOG = LoggerFactory
			.getLogger(TramiteController.class);
	
	@RequestMapping(value = "tram/add", method = { RequestMethod.GET })
	public String showFormalityForm(ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		List<Formality> formalities = accessNativeService.getFormalityAvailableByUser(loggedUser);
		
		HashMap<Long, String> formalitiesMap = new HashMap<Long, String>();
		for (Formality f : formalities) {
		   formalitiesMap.put(f.getFormalityId(), f.getDescription());
		}
		

		Request requestForm = new Request();
		
		model.addAttribute("formalities", formalitiesMap);
		model.addAttribute("districts", accessNativeService.getValidDistrictsMapByUserId(loggedUser.getId()));
		model.addAttribute("requestForm", requestForm);
		
		return SecopreConstans.MV_TRAM_ADD;
	}
	
	@RequestMapping(value = "tram/list", method = { RequestMethod.GET })
	public String showFormalityList(ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("showFormalityList");
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);

		List<Inbox> inboxList = accessNativeService.getInboxByUserId(loggedUser.getId());
		
		//Map<String,Boolean> canCapture = accessNativeService.canUserCapture(loggedUser.getId());
		
		model.addAttribute("inboxList", inboxList);
		model.addAttribute("canUserCapture", true);
		model.addAttribute("hasUserRequestInProcess", false);
		model.addAttribute("isValidDate", true);
		
				
		return SecopreConstans.MV_TRAM_LIST;
	}

	@RequestMapping(value = "tram/add", method = { RequestMethod.POST })
	public String addFormality(@ModelAttribute("requestForm") Request requestForm, ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		try{
			User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
			
			System.out.println(requestForm);
			
			District district= baseService.findById(District.class, requestForm.getDistrictId());
			Long requestId = accessNativeService.getRequestNextConsecutive();
			
			
			
			Long requestIdByDistrict = accessNativeService.getRequestByDistrictNextConsecutive(requestForm.getDistrictId());
			
			//String folio = "DTO-" +  district.getNumber() + "/" + requestId;
			String folio = "DTO-" +  district.getNumber() + "/" + requestIdByDistrict;
			
			requestForm.setRequestId(requestId);
			requestForm.setFolio(folio);
			requestForm.setRequestIdByDistrict(requestIdByDistrict);
			
			accessNativeService.startFormality(requestForm, loggedUser.getId());
			
			Formality f = accessNativeService.getFormalityById(requestForm.getFormalityId());
			if(f.getProcessAfterCreation() != null && f.getProcessAfterCreation().length() > 0 ){
				LOG.info("Ejecutando metodo complementario");
				this.executeComplementMethod(f.getProcessAfterCreation(), requestForm);
			}else{
				LOG.info("Tramite no requiere de ejecucion complementaria");
			}
			
			return "redirect:/auth/tram/list";
		}catch(Exception ex){
			System.out.println(ex);
			ex.printStackTrace();
			return "redirect:/auth/tram/list";
		}
	}
	
	public void executeComplementMethod(String method, Request requestForm) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class[] paramTypes = new Class[]{Request.class};		
		 String dataSourceMethod = method;
		 Method methodObject = this.getClass().getMethod(dataSourceMethod, paramTypes);
		 methodObject.invoke(this, new Object[] { requestForm });
	}
	
	public void masiveReduction(Request request){
		LOG.info("-- Ejecutando metodo complementario: masiveReduction" );
		
		Long districtId = request.getDistrictId();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int currentMonth = c.get(Calendar.MONTH);
		
		LOG.info("Generando paquete masivo para distrito: " + districtId + ", mes: " + currentMonth);
		
		List<EntryDistrict> entryList = accessNativeService.getEntriesByDistrict(districtId, new Long(currentMonth));
		
		LOG.info("creando listado de movimientos de disminucion");
		
		List<Movement> movements = new ArrayList<Movement>();
		for(EntryDistrict entry : entryList){
			Movement m = new Movement();
			
			m.setRequestId(request.getRequestId());
			m.setMovementTypeId(-1L);
			m.setProgramaticKeyId(entry.getEntry().getProgrammaticKey().getId());
			m.setEntryId(entry.getEntry().getId());
			m.setInitialMonthId(currentMonth);
			m.setFinalMonthId(currentMonth);
			m.setMonthAmount(entry.getBudgetAmountAssign().toString());
			m.setTotalAmount(entry.getBudgetAmountAssign().toString());			
			movements.add(m);
		}
		
		LOG.info("asignando movimientos a tramite, total de movimientos: " + movements);
		request.setDownMovements(movements);
		
		LOG.info("Guardando detalle de movimiento masivo");
		try {
			accessNativeService.insertOrUpdateMasiveDetail(request);
			LOG.info("Fin de la ejecucion complementaria");
		} catch (Exception e) {
			LOG.info("Ocurrio un error durante el guardado de movimientos de tramite masivo: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "tram/mylist", method = { RequestMethod.GET })
	public String showMyFormalityList(ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("showMyFormalityList");
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);

		List<Inbox> inboxList = accessNativeService.getMyInboxByUserId(loggedUser.getId());
		
		model.addAttribute("inboxList", inboxList);	
		return SecopreConstans.MV_TRAM_MY_LIST;
	}
	
	@RequestMapping(value = "tram/rectification", method = { RequestMethod.GET })
	public String showRectificationList(ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("showRectificationList");
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);

		List<Rectification> rectificationList = accessNativeService.getRectificationInbox();
		
		model.addAttribute("inboxList", rectificationList);	
		return SecopreConstans.MV_TRAM_RECTIFICATION;
	}
	
}
