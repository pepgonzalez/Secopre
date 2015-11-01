package ideasw.secopre.web.controller.admin;

import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.Movement;
import ideasw.secopre.dto.Request;
import ideasw.secopre.dto.RequestHistory;
import ideasw.secopre.model.EntryDistrict;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WorkFlowController extends AuthController {

	static final Logger LOG = LoggerFactory
			.getLogger(WorkFlowController.class);

	@Autowired
	private AccessNativeService accessNativeService;

	/*
	 * Metodo para obtener la forma de captura de movimientos
	 * param formalityCode 	- forma que sera cargada
	 * param requestId		- folio en cuestion
	 * param stageConfigId	- etapa actual del folio
	 * */
	@RequestMapping(value = "wf/capture/{formalityCode}/{requestId}/{stageConfigId}", method = { RequestMethod.GET })
	public String showMovementsCapture(
			@PathVariable("requestId") Long requestId, @PathVariable("stageConfigId") Long stageConfigId, @PathVariable("formalityCode") String formalityCode,
			ModelMap model, RedirectAttributes attributes, Principal principal) {

		Request requestForm = accessNativeService.getRequestAndDetailById(requestId);

		requestForm.setStageConfigId(stageConfigId);
		requestForm.setFormalityCode(formalityCode);
				
		if (requestForm.getMovementTypeId() != null && requestForm.getMovementTypeId().intValue() > 0){
			requestForm.setMovementTypeId(requestForm.getMovementTypeId());
		}else{
			requestForm.setMovementTypeId(-1L);
		}

		model.addAttribute("movementTypes", accessNativeService.getMovementTypesMap());
		model.addAttribute("programaticKeys", accessNativeService.getProgramaticKeysMap());
		model.addAttribute("entries", accessNativeService.getEntriesMap(-1L));
		model.addAttribute("months", accessNativeService.getMonthsMap());
		model.addAttribute("requestForm", requestForm);

		return SecopreConstans.MV_TRAM_CAPTURE;
	}

	/*
	 * Metodo para guardar el listado de movimientos y avanzar a la etapa correspondiente
	 * param requestForm - Objeto con el listado de movimientos capturados 
	 * */
	@RequestMapping(value = "wf/capture/{movementCode}", method = { RequestMethod.POST })
	public String saveMovements(@ModelAttribute("requestForm") Request requestForm, BindingResult result, ModelMap model, 
			RedirectAttributes attributes, Principal principal, HttpServletRequest request) throws Exception{

		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		LOG.info("Alza");
		for(Movement m : requestForm.getUpMovements()){
			LOG.info(m.toString());
		}
		
		LOG.info("baja");
		for(Movement m : requestForm.getDownMovements()){
			LOG.info(m.toString());
		}
		
		//try{
			accessNativeService.insertOrUpdateRequestDetail(requestForm);
			accessNativeService.invokeNextStage(requestForm, loggedUser.getId());
			return SecopreConstans.MV_TRAM_LIST_REDIRECT;
		
		/*
		}catch(Exception ex){
			LOG.error(ex.getMessage());
			List<String> errors = new ArrayList<String>();
			errors.add(ex.getMessage());
			
			model.addAttribute("errors", errors);
			model.addAttribute("nextAction", "sendRequestJQ('auth/tram/list','dashboard','initTramiteListPage()','GET');");
			return SecopreConstans.MV_TRAM_EXCEPTION;
		}
		*/
	}

	/*
	 * Metodo para mostrar la autorizacion del folio correspondiente a la siguiente etapa
	 * param requestId		- Folio en cuestion
	 * param stageConfigId	- Etapa de autorizacion actual
	 * */
	@RequestMapping(value = "wf/authorization/{requestId}/{stageConfigId}", method = { RequestMethod.GET })
	public String showAuthorizationInfo(@PathVariable("requestId") Long requestId, @PathVariable("stageConfigId") Long stageConfigId, ModelMap model, RedirectAttributes attributes, Principal principal) {

		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);

		Request requestForm = accessNativeService.getRequestAndDetailById(requestId);
		requestForm.setStageConfigId(stageConfigId);
		requestForm.setAuthorizationForm(true);
		
		LOG.info("Request: " + requestForm);

		// se obtienen valores de authorizacion
		Authorization authorization = accessNativeService.getAuthorization(requestForm, loggedUser);

		model.addAttribute("movementTypes", accessNativeService.getMovementTypesMap());
		model.addAttribute("programaticKeys", accessNativeService.getProgramaticKeysMap());
		model.addAttribute("entries", accessNativeService.getEntriesMap(-1L));
		model.addAttribute("months", accessNativeService.getMonthsMap());
		model.addAttribute("requestForm", requestForm);
		model.addAttribute("authorization", authorization);

		return SecopreConstans.MV_TRAM_AUTH;
	}

	/*
	 * Metodo para avanzar la etapa correspondiente guardando los comentarios de authorizacion
	 * param requestForm	- Obtiene el codigo de salida de la etapa en funcion del resultado de la autorizacion
	 * */
	@RequestMapping(value = "wf/authorization", method = { RequestMethod.POST })
	public String authorizeCancelOrFinishFormality( @ModelAttribute("requestForm") Request requestForm, ModelMap model, RedirectAttributes attributes, Principal principal) {

		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		accessNativeService.invokeNextStage(requestForm, loggedUser.getId());

		return SecopreConstans.MV_TRAM_LIST_REDIRECT;
	}

	/*
	 * Metodo para mostrar el formulario de carga de documentos anexos
	 * param requestId		- Folio en cuestion
	 * param stageConfigId	- Etapa actual
	 * */
	@RequestMapping(value = "wf/upload/{requestId}/{stageConfigId}", method = { RequestMethod.GET })
	public String showUploadForm(@PathVariable("requestId") Long requestId, @PathVariable("stageConfigId") Long stageConfigId, ModelMap model, RedirectAttributes attributes, Principal principal) {

		Request requestForm = accessNativeService.getRequestById(requestId);
		requestForm.setStageConfigId(stageConfigId);

		model.addAttribute("requestForm", requestForm);
		return SecopreConstans.MV_TRAM_UPLOAD;
	}

	/*
	 * Metodo para almacenar el documento anexo y avanzar el folio de etapa
	 * param requestId		- Folio en cuestion
	 * param stageConfigIf	- Etapa actual del folio
	 * param attachment		- Archivo seleccionado por el usuario
	 * */
	@RequestMapping(value = "wf/upload", method = { RequestMethod.POST })
	public String uploadFile(@RequestParam("requestId") Long requestId, 
							 @RequestParam("stageConfigId") Long stageConfigId,
							 @RequestParam("attachment") MultipartFile attachment, 
							 ModelMap model, RedirectAttributes attributes, Principal principal) throws IOException {
		
		if (attachment != null && attachment.getBytes() != null && attachment.getOriginalFilename() != null && !StringUtils.isEmpty(attachment.getOriginalFilename())) {
			
			try{
				
				String operativeSystem = System.getProperty("os.name").toLowerCase();				
				String rootPath = "";
				
				if (operativeSystem.indexOf("nix") >= 0 || operativeSystem.indexOf("nux") >= 0 || operativeSystem.indexOf("aix") > 0 ){
					rootPath = SecopreConstans.SECOPRE_RESOURCES_LINUX_PATH;
				}else{
					rootPath = SecopreConstans.SECOPRE_RESOURCES_WINDOWS_PATH;
				}
	
				File baseDirectory = new File(rootPath);
				if (!baseDirectory.exists()) {
					baseDirectory.mkdir();
					baseDirectory.setExecutable(true);
					baseDirectory.setWritable(true);
					baseDirectory.setReadable(true);
				}
				
				String requestFolder = rootPath + File.separator + requestId;
				
				LOG.debug("creando directorio de folio: " + requestFolder);
				File requestDirectory = new File(requestFolder);
				if (!requestDirectory.exists()) {
					requestDirectory.mkdir();
					requestDirectory.setExecutable(true);
					requestDirectory.setWritable(true);
					requestDirectory.setReadable(true);
				}
			
				//se guarda el archivo
				String documentPath = rootPath + File.separator + requestId + File.separator + attachment.getOriginalFilename();
					
				File file = new File(requestDirectory, attachment.getOriginalFilename());
				if (!file.exists()) {
					file.createNewFile();
				}
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
				LOG.error("Ocurrio un error durante el guardado del documento",ex);
				LOG.debug("Ocurrio un error durante el guardado del documento: " + ex.getMessage());
			}

		}
		return SecopreConstans.MV_TRAM_LIST_REDIRECT;
	}

	/*
	 * Metodo para realizar la descarga del archivo anexo al folio
	 * param requestId
	 * */
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
      	LOG.debug("Ocurrio un error al intentar descargar el archivo" + ex.toString());
    }

	}
	
	/*
	 * Metodo para obtener el detalle de la historia de un folio
	 * param requestId
	 * */
	@RequestMapping(value = "wf/requestDetail/{requestId}", method = { RequestMethod.GET })
	public String getRequestDetail(@PathVariable("requestId") Long requestId, ModelMap model, RedirectAttributes attributes, Principal principal) {

		Request requestForm = new Request();
		List<RequestHistory> requestHistory = accessNativeService.getRequestHistory(requestId);
		requestForm.setRequestHistory(requestHistory);

		model.addAttribute("requestForm", requestForm);
		return SecopreConstans.MV_TRAM_HISTORY;
	}
	
	/*Metodo para obtener el detalle distrito - llave programatica - mes */
	@RequestMapping(value = "wf/entryAmounts/{district}/{programaticKey}/{entry}", method = { RequestMethod.GET })
	public String getAmountsDetail(@PathVariable("district") Long districtId,
								   @PathVariable("programaticKey") Long programaticKeyId, 
								   @PathVariable("entry") Long entryId, 
								   ModelMap model, RedirectAttributes attributes, Principal principal) {

		List<EntryDistrict> entryDistrictBalance = accessNativeService.getAnnualEntryBalance(districtId, entryId);
		
		LOG.info("total de registros para distrito: " + districtId + ", partida: " + entryId + ": " + entryDistrictBalance.size());
		model.addAttribute("entryDistrictBalance", entryDistrictBalance);
		return SecopreConstans.MV_TRAM_BALANCE;
	}
}
