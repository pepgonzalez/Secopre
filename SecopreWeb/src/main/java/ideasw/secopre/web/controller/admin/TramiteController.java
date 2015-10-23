package ideasw.secopre.web.controller.admin;

import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.BaseService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TramiteController extends AuthController {

	@Autowired
	private AccessNativeService accessNativeService;
	
	@Autowired
	private BaseService baseService;
	
	@RequestMapping(value = "tram/add", method = { RequestMethod.GET })
	public String showFormalityForm(ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("showFormalityForm");
		
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		List<Formality> formalities = accessNativeService.getFormalityAvailableByUser(loggedUser);
		
		HashMap<Long, String> formalitiesMap = new HashMap<Long, String>();
		for (Formality f : formalities) {
		   formalitiesMap.put(f.getFormalityId(), f.getDescription());
		}
		
		//obtener los distritos
		List<District> districtList = accessNativeService.getValidDistricts();
		HashMap<Long, String> districtsMap = new HashMap<Long, String>();
		for(District district : districtList){
			districtsMap.put(district.getId(), district.getNumber());
		}
		
		Request requestForm = new Request();
		
		model.addAttribute("formalities", formalitiesMap);
		model.addAttribute("districts", districtsMap);
		model.addAttribute("requestForm", requestForm);
		
		return SecopreConstans.MV_TRAM_ADD;
	}
	
	@RequestMapping(value = "tram/list", method = { RequestMethod.GET })
	public String showFormalityList(ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("showFormalityList");
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);

		List<Inbox> inboxList = accessNativeService.getInboxByUserId(loggedUser.getId());
		
		model.addAttribute("inboxList", inboxList);
				
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
	
}
