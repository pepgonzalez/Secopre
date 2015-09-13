package ideasw.secopre.web.controller.admin;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

@Controller
public class WorkFlowController extends AuthController{

	@Autowired
	private AccessNativeService accessNativeService;
	
	@RequestMapping(value = "wf/capture/basic", method = { RequestMethod.GET })
	public String showBasicCapture(ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("showBasicCapture");
		
		return SecopreConstans.MV_TRAM_ADD;
	}
	
	@RequestMapping(value = "wf/capture/movements/{requestId}/{stageConfigId}", method = { RequestMethod.GET })
	public String showMovementsCapture(@PathVariable("requestId") Long requestId, 
									   @PathVariable("stageConfigId") Long stageConfigId, 
									   ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("showMovementsCapture");
		Request requestForm = new Request();
		requestForm.setRequestId(requestId);
		requestForm.setStageConfigId(stageConfigId);
		
		model.addAttribute("requestForm", requestForm);
		
		return SecopreConstans.MV_TRAM_MOVS;
	}
	
	@RequestMapping(value = "wf/capture/movements", method = { RequestMethod.POST })
	public String saveMovements(@ModelAttribute("requestForm") Request requestForm, ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("Guardando movimientos");
		System.out.println("tipo de guardado: " + requestForm.getNextStageValueCode());
		System.out.println("request: " + requestForm.getRequestId());
		System.out.println("stageConfigId: " + requestForm.getStageConfigId());
		
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		//TODO implementacion para guardar informacion completa de tramite de movimiento
		
		//avanzar de etapa
		accessNativeService.invokeNextStage(requestForm, loggedUser.getId());

		return "redirect:/auth/tram/list";
	}
}
