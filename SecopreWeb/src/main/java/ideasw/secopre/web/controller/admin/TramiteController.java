package ideasw.secopre.web.controller.admin;

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

import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

@Controller
public class TramiteController extends AuthController {
	
	@Autowired
	private AccessService accessService;

	@Autowired
	private AccessNativeService accessNativeService;
	
	@RequestMapping(value = "tram/add", method = { RequestMethod.GET })
	public String showFormalityForm(ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		List<Formality> formalities = accessNativeService.getFormalityAvailableByUser(loggedUser);
		model.addAttribute("formalities", formalities);
		
		//modelo de user
		Request requestForm = new Request();
		model.addAttribute("requestForm", requestForm);
		
		return SecopreConstans.MV_TRAM_ADD;
	}

	@RequestMapping(value = "tram/add", method = { RequestMethod.POST })
	public String addFormality(ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("NUEVA PETICION___________________");
		
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		List<Formality> formalities = accessNativeService.getFormalityAvailableByUser(loggedUser);
		
		HashMap<Long, String> formalitiesMap = new HashMap<Long, String>();
		for (Formality f : formalities) {
		   formalitiesMap.put(f.getFormalityId(), f.getDescription());
		}
		
		model.addAttribute("formalities", formalitiesMap);
		//modelo de user
		Request requestForm = new Request();
		model.addAttribute("requestForm", requestForm);
		
		return SecopreConstans.MV_TRAM_ADD;
	}
	
	
	
}
