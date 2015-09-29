package ideasw.secopre.web.controller.admin;

import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WorkFlowController extends AuthController {

	@Autowired
	private AccessNativeService accessNativeService;

	private String basePath = "C:/SecopreResources/";

	@RequestMapping(value = "wf/capture/{formalityCode}/{requestId}/{stageConfigId}", method = { RequestMethod.GET })
	public String showMovementsCapture(
			@PathVariable("requestId") Long requestId,
			@PathVariable("stageConfigId") Long stageConfigId,
			@PathVariable("formalityCode") String formalityCode,
			ModelMap model, RedirectAttributes attributes, Principal principal) {

		System.out.println("showMovementsCapture");
		Request requestForm = new Request();

		requestForm = accessNativeService.getRequestById(requestId);

		requestForm.setStageConfigId(stageConfigId);
		requestForm.setFormalityCode(formalityCode);

		model.addAttribute("requestForm", requestForm);

		return SecopreConstans.MV_TRAM_CAPTURE;
	}

	@RequestMapping(value = "wf/capture/{movementCode}", method = { RequestMethod.POST })
	public String saveMovements(
			@ModelAttribute("requestForm") Request requestForm, ModelMap model,
			RedirectAttributes attributes, Principal principal) {

		System.out.println("Guardando movimientos");
		System.out.println("tipo de guardado: "
				+ requestForm.getNextStageValueCode());
		System.out.println("request: " + requestForm.getRequestId());
		System.out.println("stageConfigId: " + requestForm.getStageConfigId());

		User loggedUser = baseService.findByProperty(User.class, "username",
				principal.getName()).get(0);

		// TODO implementacion para guardar informacion completa de tramite de
		// movimiento
		accessNativeService.insertOrUpdateRequestDetail(requestForm);

		// avanzar de etapa
		accessNativeService.invokeNextStage(requestForm, loggedUser.getId());

		return "redirect:/auth/tram/list";
	}

	@RequestMapping(value = "wf/authorization/{requestId}/{stageConfigId}", method = { RequestMethod.GET })
	public String showAuthorizationInfo(
			@PathVariable("requestId") Long requestId,
			@PathVariable("stageConfigId") Long stageConfigId, ModelMap model,
			RedirectAttributes attributes, Principal principal) {

		System.out.println("showAuthorizationInfo");
		User loggedUser = baseService.findByProperty(User.class, "username",
				principal.getName()).get(0);

		Request requestForm = new Request();
		requestForm = accessNativeService.getRequestById(requestId);
		requestForm.setStageConfigId(stageConfigId);
		requestForm.setAuthorizationForm(true);

		// se obtienen valores de authorizacion
		Authorization authorization = accessNativeService.getAuthorization(
				requestForm, loggedUser);
		System.out.println(authorization);

		model.addAttribute("requestForm", requestForm);
		model.addAttribute("authorization", authorization);

		return SecopreConstans.MV_TRAM_AUTH;
	}

	@RequestMapping(value = "wf/authorization", method = { RequestMethod.POST })
	public String authorizeCancelOrFinishFormality(
			@ModelAttribute("requestForm") Request requestForm, ModelMap model,
			RedirectAttributes attributes, Principal principal) {

		System.out.println("authorizeCancelOrFinishFormality");
		User loggedUser = baseService.findByProperty(User.class, "username",
				principal.getName()).get(0);

		// avanzar de etapa
		accessNativeService.invokeNextStage(requestForm, loggedUser.getId());

		return "redirect:/auth/tram/list";
	}

	@RequestMapping(value = "wf/upload/{requestId}/{stageConfigId}", method = { RequestMethod.GET })
	public String showUploadForm(@PathVariable("requestId") Long requestId,
			@PathVariable("stageConfigId") Long stageConfigId, ModelMap model,
			RedirectAttributes attributes, Principal principal) {

		System.out.println("showUploadForm");
		Request requestForm = new Request();

		// requestForm = accessNativeService.getRequestById(requestId);

		requestForm.setStageConfigId(stageConfigId);

		model.addAttribute("requestForm", requestForm);

		return SecopreConstans.MV_TRAM_UPLOAD;
	}

	@RequestMapping(value = "wf/upload", method = { RequestMethod.POST })
	public String uploadFile(
			@RequestParam("requestId") Long requestId,
			@RequestParam("stageConfigId") Long stageConfigId,
			@RequestParam("attachment") MultipartFile attachment,
			ModelMap model, RedirectAttributes attributes, Principal principal) throws IOException {

		System.out.println("uploadFile");

		if (attachment != null && attachment.getBytes() != null && attachment.getOriginalFilename() != null && !StringUtils.isEmpty(attachment.getOriginalFilename())) {
			
			String fileName = attachment.getOriginalFilename();
			String finalPath = basePath + requestId + "/";

			try{
				attachment.transferTo(new File(finalPath));
				finalPath += fileName;

				int res = accessNativeService.updateRequestUploadedFile(requestId, finalPath);

				//accessNativeService.invokeNextStage(requestForm, loggedUser.getId());				

			}catch(Exception e){
				System.out.println("Excepcion al guardar el archivo");
			}
		}
		// ser loggedUser = baseService.findByProperty(User.class, "username",
		// principal.getName()).get(0);

		// avanzar de etapa
		// accessNativeService.invokeNextStage(requestForm, loggedUser.getId());

		return "redirect:/auth/tram/list";
	}

	@RequestMapping(value = "wf/download/{requestId}", method = RequestMethod.GET)
	public void getFile(@PathVariable("requestId") Long requestId, HttpServletResponse response) {
    try {
      
	  	Request request = accessNativeService.getRequestById(requestId);
      	InputStream is = new FileInputStream(request.getResourcePath());
      	org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
      	response.flushBuffer();
      	
    } catch (IOException ex) {
      	System.out.println("Ocurrio un error al intentar descargar el archivo" + ex.toString());
    }

}
}
