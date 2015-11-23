package ideasw.secopre.web.controller.admin;

import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.security.User;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TramiteController extends AuthController {

	
	@RequestMapping(value = "tram/add", method = { RequestMethod.GET })
	public String showFormalityForm(ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("showFormalityForm");
		
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
		
		Map<String,Boolean> canCapture = accessNativeService.canUserCapture(loggedUser.getId());
		
		model.addAttribute("inboxList", inboxList);
		model.addAttribute("canUserCapture", canCapture.get("canUserCapture"));
		model.addAttribute("hasUserRequestInProcess", canCapture.get("hasUserRequestInProcess"));
		model.addAttribute("isValidDate", canCapture.get("isValidDate"));
		
				
		return SecopreConstans.MV_TRAM_LIST;
	}

	@RequestMapping(value = "tram/add", method = { RequestMethod.POST })
	public String addFormality(@ModelAttribute("requestForm") Request requestForm, ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		try{
			User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
			
			System.out.println(requestForm);
			
			District district= baseService.findById(District.class, requestForm.getDistrictId());
			Long requestId = accessNativeService.getRequestNextConsecutive();
			
			String folio = "DTO-" +  district.getNumber() + "/" + requestId;
			
			requestForm.setRequestId(requestId);
			requestForm.setFolio(folio);
			
			accessNativeService.startFormality(requestForm, loggedUser.getId());
			
			return "redirect:/auth/tram/list";
		}catch(Exception ex){
			System.out.println(ex);
			return "redirect:/auth/tram/list";
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
	
}
