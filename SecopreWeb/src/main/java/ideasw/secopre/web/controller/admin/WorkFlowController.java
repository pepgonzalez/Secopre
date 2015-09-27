package ideasw.secopre.web.controller.admin;

import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WorkFlowController extends AuthController{

	@Autowired
	private AccessNativeService accessNativeService;
	
	@RequestMapping(value = "wf/capture/{formalityCode}/{requestId}/{stageConfigId}", method = { RequestMethod.GET })
	public String showMovementsCapture(@PathVariable("requestId") Long requestId, 
									   @PathVariable("stageConfigId") Long stageConfigId, 
									   @PathVariable("formalityCode") String formalityCode,
									   ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("showMovementsCapture");
		Request requestForm = new Request();
		
		requestForm = accessNativeService.getRequestById(requestId);
		
		requestForm.setStageConfigId(stageConfigId);
		requestForm.setFormalityCode(formalityCode);
		
		model.addAttribute("requestForm", requestForm);
		
		return SecopreConstans.MV_TRAM_CAPTURE;
	}
	
	@RequestMapping(value = "wf/capture/{movementCode}", method = { RequestMethod.POST })
	public String saveMovements(@ModelAttribute("requestForm") Request requestForm, ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("Guardando movimientos");
		System.out.println("tipo de guardado: " + requestForm.getNextStageValueCode());
		System.out.println("request: " + requestForm.getRequestId());
		System.out.println("stageConfigId: " + requestForm.getStageConfigId());
		
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		//TODO implementacion para guardar informacion completa de tramite de movimiento
		accessNativeService.insertOrUpdateRequestDetail(requestForm);
		
		//avanzar de etapa
		accessNativeService.invokeNextStage(requestForm, loggedUser.getId());

		return "redirect:/auth/tram/list";
	}
	
	@RequestMapping(value = "wf/authorization/{requestId}/{stageConfigId}", method = { RequestMethod.GET })
	public String showAuthorizationInfo(@PathVariable("requestId") Long requestId, 
									   @PathVariable("stageConfigId") Long stageConfigId, 
									   ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("showAuthorizationInfo");
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		Request requestForm = new Request();
		requestForm = accessNativeService.getRequestById(requestId);
		requestForm.setStageConfigId(stageConfigId);
		requestForm.setAuthorizationForm(true);
		
		//se obtienen valores de authorizacion
		Authorization authorization = accessNativeService.getAuthorization(requestForm, loggedUser);
		System.out.println(authorization);
		
		model.addAttribute("requestForm", requestForm);
		model.addAttribute("authorization", authorization);
		
		return SecopreConstans.MV_TRAM_AUTH;
	}
	
	@RequestMapping(value = "wf/authorization", method = { RequestMethod.POST })
	public String authorizeCancelOrFinishFormality(@ModelAttribute("requestForm") Request requestForm, ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("authorizeCancelOrFinishFormality");
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		//avanzar de etapa
		accessNativeService.invokeNextStage(requestForm, loggedUser.getId());

		return "redirect:/auth/tram/list";
	}
	
	@RequestMapping(value = "wf/upload/{requestId}/{stageConfigId}", method = { RequestMethod.GET })
	public String showUploadForm(@PathVariable("requestId") Long requestId, 
									   @PathVariable("stageConfigId") Long stageConfigId, 
									   ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("showUploadForm");
		Request requestForm = new Request();
		
		//requestForm = accessNativeService.getRequestById(requestId);
		
		requestForm.setStageConfigId(stageConfigId);
		
		model.addAttribute("requestForm", requestForm);
		
		return SecopreConstans.MV_TRAM_UPLOAD;
	}
	
	@RequestMapping(value = "wf/upload", method = { RequestMethod.POST })
	public String uploadFile(  @RequestParam("file") MultipartFile file, ModelMap model, RedirectAttributes attributes,  Principal principal) {
		
		System.out.println("uploadFile");
				
		boolean isEmpty = file.isEmpty();
		
		System.out.println("archivo vacio: " + isEmpty);
		
		if(isEmpty){
			String fileName = file.getOriginalFilename();
			System.out.println("fileName: " + fileName);
		}		
		//ser loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		//avanzar de etapa
		//accessNativeService.invokeNextStage(requestForm, loggedUser.getId());

		return "redirect:/auth/tram/list";
	}
}
