package ideasw.secopre.web.controller.admin;

import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.Movement;
import ideasw.secopre.dto.Request;
import ideasw.secopre.dto.RequestHistory;
import ideasw.secopre.model.catalog.MovementType;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

	private String basePath = "C:" + File.separator+ "SecopreResources" + File.separator;

	@RequestMapping(value = "wf/capture/{formalityCode}/{requestId}/{stageConfigId}", method = { RequestMethod.GET })
	public String showMovementsCapture(
			@PathVariable("requestId") Long requestId,
			@PathVariable("stageConfigId") Long stageConfigId,
			@PathVariable("formalityCode") String formalityCode,
			ModelMap model, RedirectAttributes attributes, Principal principal) {

		System.out.println("showMovementsCapture");
		Request requestForm = new Request();

		requestForm = accessNativeService.getRequestAndDetailById(requestId);

		requestForm.setStageConfigId(stageConfigId);
		requestForm.setFormalityCode(formalityCode);
				
		if (requestForm.getMovementTypeId() != null && requestForm.getMovementTypeId().intValue() > 0){
			requestForm.setMovementTypeId(requestForm.getMovementTypeId());
		}else{
			requestForm.setMovementTypeId(-1L);
		}
		
		List<MovementType> movementTypes = baseService.findAll(MovementType.class);
		
		Map<Long, String> map = new HashMap<Long, String>();
		for(MovementType mov : movementTypes){
			map.put(mov.getId(), mov.getDescription());
		}

		model.addAttribute("movementTypes", map);
		model.addAttribute("programaticKeys", accessNativeService.getProgramaticKeysMap());
		model.addAttribute("entries", accessNativeService.getEntriesMap(-1L));
		model.addAttribute("months", accessNativeService.getMonthsMap());
		model.addAttribute("requestForm", requestForm);

		return SecopreConstans.MV_TRAM_CAPTURE;
	}

	@RequestMapping(value = "wf/capture/{movementCode}", method = { RequestMethod.POST })
	public String saveMovements(
			@ModelAttribute("requestForm") Request requestForm, 
			BindingResult result,
			ModelMap model,
			RedirectAttributes attributes, Principal principal) {

		
		
		System.out.println("Guardando movimientos");
		System.out.println("tipo de guardado: " + requestForm.getNextStageValueCode());
		System.out.println("request: " + requestForm.getRequestId());
		System.out.println("stageConfigId: " + requestForm.getStageConfigId());

		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);

		//System.out.println("total de movimientos capturados: " + requestForm.getUpMovements().size());
		
		for(ObjectError e :result.getAllErrors()){
			System.out.println("Error: " + e.getDefaultMessage());
		};
		
		for(Movement m : requestForm.getUpMovements()){
			System.out.println(m);
		}

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
		
		requestForm = accessNativeService.getRequestAndDetailById(requestId);
		requestForm.setStageConfigId(stageConfigId);
		requestForm.setAuthorizationForm(true);

		// se obtienen valores de authorizacion
		Authorization authorization = accessNativeService.getAuthorization(
				requestForm, loggedUser);
		System.out.println(authorization);

		List<MovementType> movementTypes = baseService.findAll(MovementType.class);
		
		Map<Long, String> map = new HashMap<Long, String>();
		for(MovementType mov : movementTypes){
			map.put(mov.getId(), mov.getDescription());
		}

		model.addAttribute("movementTypes", map);
		model.addAttribute("programaticKeys", accessNativeService.getProgramaticKeysMap());
		model.addAttribute("entries", accessNativeService.getEntriesMap(-1L));
		model.addAttribute("months", accessNativeService.getMonthsMap());
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

		requestForm = accessNativeService.getRequestById(requestId);

		requestForm.setStageConfigId(stageConfigId);

		model.addAttribute("requestForm", requestForm);
		System.out.println("mostrando formulario de carga de archivo");
		return SecopreConstans.MV_TRAM_UPLOAD;
	}

	@RequestMapping(value = "wf/upload", method = { RequestMethod.POST })
	public String uploadFile(@RequestParam("requestId") Long requestId, @RequestParam("stageConfigId") Long stageConfigId,
			@RequestParam("attachment") MultipartFile attachment, ModelMap model, RedirectAttributes attributes, Principal principal) throws IOException {

		System.out.println("uploadFile");

		if (attachment != null && attachment.getBytes() != null && attachment.getOriginalFilename() != null && !StringUtils.isEmpty(attachment.getOriginalFilename())) {
			
			try{
				
			String operativeSystem = System.getProperty("os.name").toLowerCase();
			System.out.println("Sistema operativo actual: " + operativeSystem);
			
			String rootPath = "";
			
			if (operativeSystem.indexOf("nix") >= 0 || operativeSystem.indexOf("nux") >= 0 || operativeSystem.indexOf("aix") > 0 ){
				rootPath = SecopreConstans.SECOPRE_RESOURCES_LINUX_PATH;
			}else{
				rootPath = SecopreConstans.SECOPRE_RESOURCES_WINDOWS_PATH;
			}
			System.out.println("root seleccionado: " + rootPath);

			//se valida si el directorio base existe
			File baseDirectory = new File(rootPath);
			if (!baseDirectory.exists()) {
				System.out.println("creando directorio base");
				baseDirectory.mkdir();
				baseDirectory.setExecutable(true);
				baseDirectory.setWritable(true);
				baseDirectory.setReadable(true);
			}else{
				System.out.println("Directorio ya existe");
			}
			
			//se valida si existe el folder del request
			String requestFolder = rootPath + File.separator + requestId;
			
			System.out.println("creando directorio de folio: " + requestFolder);
			File requestDirectory = new File(requestFolder);
			if (!requestDirectory.exists()) {
				System.out.println("creando directorio de folio");
				requestDirectory.mkdir();
				requestDirectory.setExecutable(true);
				requestDirectory.setWritable(true);
				requestDirectory.setReadable(true);
			}
		
			//se guarda el archivo
			String documentPath = rootPath + File.separator + requestId + File.separator + attachment.getOriginalFilename();
			
			System.out.println("creando documento: " + documentPath);

			File file = new File(requestDirectory, attachment.getOriginalFilename());
			if (!file.exists()) {
				file.createNewFile();
			}
			System.out.println("directorio creado");
			FileOutputStream outputStream = new FileOutputStream(file);
			
			outputStream.write(attachment.getBytes());
			outputStream.flush();
			outputStream.close();
		
			accessNativeService.updateRequestUploadedFile(requestId, documentPath);
			
			Request request = accessNativeService.getRequestById(requestId);
			request.setStageConfigId(stageConfigId);
			request.setNextStageValueCode(SecopreConstans.DEFAULT_UPLOAD_FILE_NEXT_STAGE);

			User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
			
			accessNativeService.invokeNextStage(request, loggedUser.getId());	
			}catch(Exception ex){
				System.out.println("Ocurrio un error durante el guardado del documento: " + ex.getMessage());
				System.out.println(ex);
			}

		}
		return "redirect:/auth/tram/list";
	}

	@RequestMapping(value = "wf/download/{requestId}", method = RequestMethod.GET)
	public void getFile(@PathVariable("requestId") Long requestId, HttpServletResponse response) {
    try {
      
	  	Request request = accessNativeService.getRequestById(requestId);
      	InputStream is = new FileInputStream(request.getResourcePath());
      	org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
      	response.setContentType("application/x-download"); 
      	response.setHeader("Content-disposition", "attachment; filename=" + request.getResourcePath());
      	response.flushBuffer();
      	
    } catch (IOException ex) {
      	System.out.println("Ocurrio un error al intentar descargar el archivo" + ex.toString());
    }

	}
	
	@RequestMapping(value = "wf/requestDetail/{requestId}", method = { RequestMethod.GET })
	public String getRequestDetail(@PathVariable("requestId") Long requestId,
			ModelMap model,
			RedirectAttributes attributes, Principal principal) {

		System.out.println("getRequestDetail");
		Request requestForm = new Request();

		List<RequestHistory> requestHistory = accessNativeService.getRequestHistory(requestId);
		
		requestForm.setRequestHistory(requestHistory);


		model.addAttribute("requestForm", requestForm);
		System.out.println("mostrando historial del tramite");
		return SecopreConstans.MV_TRAM_HISTORY;
	}
}
