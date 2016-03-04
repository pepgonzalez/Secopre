package ideasw.secopre.web.controller.admin;

import ideasw.secopre.constants.PropertyConstants;
import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.EntryBalance;
import ideasw.secopre.dto.EntryCurrentTotal;
import ideasw.secopre.dto.EntryFilter;
import ideasw.secopre.dto.Movement;
import ideasw.secopre.dto.Property;
import ideasw.secopre.dto.Report;
import ideasw.secopre.dto.ReportParameter;
import ideasw.secopre.dto.Request;
import ideasw.secopre.dto.RequestHistory;
import ideasw.secopre.enums.AccountingType;
import ideasw.secopre.enums.StatusEntry;
import ideasw.secopre.exception.EntryDistrictException;
import ideasw.secopre.model.DueDate;
import ideasw.secopre.model.Entry;
import ideasw.secopre.model.EntryDistrict;
import ideasw.secopre.model.ProgrammaticKey;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.EntryConfigService;
import ideasw.secopre.service.MovementsService;
import ideasw.secopre.service.ReportService;
import ideasw.secopre.web.SecopreConstans;
import ideasw.secopre.web.controller.base.AuthController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Autowired
	private MovementsService movementsService;
	
	@Autowired
	private EntryConfigService entryConfigService;
	
	@Autowired
	private ReportService reportService;
	
	/*
	 * Metodo para obtener la forma de captura de movimientos
	 * param formalityCode 	- forma que sera cargada
	 * param requestId		- folio en cuestion
	 * param stageConfigId	- etapa actual del folio
	 * */
	@RequestMapping(value = "wf/capture/partial/{formalityCode}/{requestId}/{stageConfigId}/{executeInnerJs}", method = { RequestMethod.GET })
	public String showMovementsPartialCapture(
			@PathVariable("requestId") Long requestId, @PathVariable("stageConfigId") Long stageConfigId, 
			@PathVariable("formalityCode") String formalityCode,
			@PathVariable("executeInnerJs") Integer executeInnerJs,
			ModelMap model, RedirectAttributes attributes, Principal principal) {
		
		LOG.info("Cargando informacion parcial");
		
		Request requestForm = accessNativeService.getRequestAndPartialDetailById(requestId);
		
		if(requestForm.getEntryId() != null && requestForm.getEntryId() > 0){
			Entry e = baseService.findById(Entry.class, requestForm.getEntryId());
			ProgrammaticKey p = baseService.findById(ProgrammaticKey.class, e.getProgrammaticKey().getId());
			e.setProgrammaticKey(p);
			if(e.getConcept() != null){
				Entry concept = baseService.findById(Entry.class, e.getConcept().getId());
				e.setConcept(concept);
			}
			requestForm.setEntry(e);
		}else{
			requestForm.setEntry(new Entry());
		}
		
		requestForm.setStageConfigId(stageConfigId);
		requestForm.setFormalityCode(formalityCode);
				
		if (requestForm.getMovementTypeId() != null && requestForm.getMovementTypeId().intValue() > 0){
			requestForm.setMovementTypeId(requestForm.getMovementTypeId());
		}else{
			requestForm.setMovementTypeId(-1L);
		}

		if(requestForm.getCurrentStage().isLastAuthorization() && requestForm.getCurrentStage().getComments().length() > 0){
			List<String> msgs = new ArrayList<String>();
			msgs.add(requestForm.getCurrentStage().getCompleteComments());
			
			model.addAttribute("messages", msgs);
			model.addAttribute("existMessages", 1);
		}
		
		//informacion requerida en tramite de partidas
		Map<String, String> accountingType = new HashMap<String, String>();
		for (AccountingType account : AccountingType.values()){
			accountingType.put(account.name(), account.name());
		}
		
		//Map<Long, String> conceptMap= accessNativeService.getConceptsMap();
		List<Entry> concepts = accessNativeService.getConceptsMap();
			
		model.addAttribute("accountingTypes", accountingType);
		model.addAttribute("conceptsList", concepts);
		
		model.addAttribute("movementTypes", accessNativeService.getMovementTypesMap());
		model.addAttribute("programaticKeys", accessNativeService.getProgramaticKeysMap());
		model.addAttribute("programaticKeysFull", accessNativeService.getProgramaticKeysFullMap());
		model.addAttribute("entries", accessNativeService.getEntriesMap(-1L));
		model.addAttribute("months", accessNativeService.getMonthsMap());
		model.addAttribute("requestForm", requestForm);
		model.addAttribute("executeInnerJs", executeInnerJs);
		model.addAttribute("folio", requestForm.getFolio());

		return SecopreConstans.MV_TRAM_CAPTURE;
	}
	
	@RequestMapping(value = "wf/capture/delete/{requestDetailId}/{formalityCode}/{requestId}/{stageConfigId}/{ijs}", method = { RequestMethod.POST })
	public String deleteDetailItem(
			@PathVariable("requestDetailId") Long requestDetailId, 
			@PathVariable("requestId") Long requestId, 
			@PathVariable("stageConfigId") Long stageConfigId, 
			@PathVariable("formalityCode") String formalityCode,
			@PathVariable("ijs") Integer ijs,
			ModelMap model, RedirectAttributes attributes, Principal principal) {
		
		LOG.info("Borrando elemento y redirigiendo : " + requestDetailId);
		movementsService.removeMirrorElement(requestDetailId);
		
		LOG.info("Redirigiendo a carga del listado");
		return "redirect:/auth/wf/capture/partial/" + formalityCode + "/" + requestId + "/" + stageConfigId + "/" + ijs;
	}
	
	
	/*
	 * Metodo para guardar el listado de movimientos de forma parcial o permamentemente avanzando de etapa
	 * param requestForm - Objeto con el listado de movimientos capturados 
	 * */
	@RequestMapping(value = "wf/capture/partial/{movementCode}", method = { RequestMethod.POST })
	public String saveMovementsV2(@ModelAttribute("requestForm") Request requestForm, BindingResult result, ModelMap model, 
			RedirectAttributes attributes, Principal principal, HttpServletRequest request,final RedirectAttributes redirectAttributes){
		
		LOG.info("iniciando guardado parcial de movimientos");
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		try{
			requestForm = accessNativeService.insertOrUpdateRequestData(requestForm);
			//se actualiza la informacion de guardado parcial
			movementsService.savePartialRequest(requestForm);
			LOG.info("Redirigiendo a carga del listado");
			return "redirect:/auth/wf/capture/partial/" + requestForm.getFormalityCode() + "/" + requestForm.getRequestId() + "/" + requestForm.getStageConfigId() + "/1";
		
		}catch(Exception ex2){
			ex2.printStackTrace();
			LOG.error(ex2.getMessage());
			List<String> errors = new ArrayList<String>();
			errors.add("Error interno del sistema. Contacte a su administrador por favor.");
			
			redirectAttributes.addFlashAttribute("errors", errors);
			redirectAttributes.addFlashAttribute("existErrors", 1);
			
			return "redirect:/auth/wf/capture/partial/" + requestForm.getFormalityCode() + "/" + requestForm.getRequestId() + "/" + requestForm.getStageConfigId() + "/1";
		}
	}
	
	@RequestMapping(value = "wf/capture/movements2", method = { RequestMethod.POST })
	public String saveMovementsAndAdvance(@ModelAttribute("requestForm") Request requestForm, BindingResult result, ModelMap model, 
			RedirectAttributes attributes, Principal principal, HttpServletRequest request, final RedirectAttributes redirectAttributes) throws Exception{
		
		LOG.info("iniciando guardado de movimientos y validando operacion");
		
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		try{
			requestForm = accessNativeService.insertOrUpdateRequestData(requestForm);
			//se actualiza la informacion de guardado parcial
			movementsService.savePartialRequest(requestForm);
			
			//valida los movimientos (al menos uno y totales compensado)
			movementsService.isValidMovement(requestForm.getRequestId(), requestForm.getMovementTypeId());
			
			//si es valido el movimiento, pasar a tabla de datalle y comprometer segun sea el caso
			accessNativeService.insertOrUpdateRequestDetail(requestForm);
			accessNativeService.invokeNextStage(requestForm, loggedUser.getId());
			
			//se borra la tabla espejo
			movementsService.cleanMirrorMovements(requestForm.getRequestId());
			
			LOG.info("Redirigiendo al listado de movimientos");
			return SecopreConstans.MV_TRAM_LIST_REDIRECT;
		
		}catch(EntryDistrictException ex){
			ex.printStackTrace();
			LOG.error(ex.getMessage());
			List<String> errors = new ArrayList<String>();
			errors.add(ex.getMessage());
			
			redirectAttributes.addFlashAttribute("errors", errors);
			redirectAttributes.addFlashAttribute("existErrors", 1);
			
			return "redirect:/auth/wf/capture/partial/" + requestForm.getFormalityCode() + "/" + requestForm.getRequestId() + "/" + requestForm.getStageConfigId() + "/1";
		}catch(Exception ex2){
			ex2.printStackTrace();
			LOG.error(ex2.getMessage());
			List<String> errors = new ArrayList<String>();
			errors.add("Error interno del sistema. Contacte a su administrador por favor.");
			
			redirectAttributes.addFlashAttribute("errors", errors);
			redirectAttributes.addFlashAttribute("existErrors", 1);
			
			return "redirect:/auth/wf/capture/partial/" + requestForm.getFormalityCode() + "/" + requestForm.getRequestId() + "/" + requestForm.getStageConfigId() + "/1";
		}
	}
	
	@RequestMapping(value = "wf/capture/expense", method = { RequestMethod.POST })
	public String saveExpense(@ModelAttribute("requestForm") Request requestForm, BindingResult result, ModelMap model, 
			RedirectAttributes attributes, Principal principal, HttpServletRequest request, final RedirectAttributes redirectAttributes) throws Exception{
		
		LOG.info("guardando gasto");
		
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		try{
			//se valida si la cuenta por certificar ya esta capturada para el distrito
			Request baseRequest = accessNativeService.getRequestById(requestForm.getRequestId());
			boolean existsAccuountInDistrict = accessNativeService.existCeritifiedAccountInDistrict(baseRequest.getDistrictId(), requestForm.getCertifiedAccount(), requestForm.getRequestId());
			
			if(existsAccuountInDistrict){
				throw new EntryDistrictException("La cuenta certificada capturada ( " + requestForm.getCertifiedAccount() + ") ya fue capturada para este distrito. Verifique");
			}
			
			requestForm = accessNativeService.insertOrUpdateRequestData(requestForm);
			//se actualiza la informacion de guardado parcial
			movementsService.savePartialRequest(requestForm);
			
			//valida los movimientos (al menos uno y totales compensado)
			movementsService.isValidMovement(requestForm.getRequestId(), requestForm.getMovementTypeId());

			
			
			//si es valido el movimiento, pasar a tabla de datalle y comprometer segun sea el caso
			accessNativeService.insertOrUpdateRequestDetail(requestForm);
			accessNativeService.invokeNextStage(requestForm, loggedUser.getId());
			
			//se borra la tabla espejo
			movementsService.cleanMirrorMovements(requestForm.getRequestId());
			
			LOG.info("Redirigiendo al listado de movimientos");
			return SecopreConstans.MV_TRAM_LIST_REDIRECT;
		
		}catch(EntryDistrictException ex){
			ex.printStackTrace();
			LOG.error(ex.getMessage());
			List<String> errors = new ArrayList<String>();
			errors.add(ex.getMessage());
			
			redirectAttributes.addFlashAttribute("errors", errors);
			redirectAttributes.addFlashAttribute("existErrors", 1);
			
			return "redirect:/auth/wf/capture/partial/" + requestForm.getFormalityCode() + "/" + requestForm.getRequestId() + "/" + requestForm.getStageConfigId() + "/1";
		}catch(Exception ex2){
			ex2.printStackTrace();
			LOG.error(ex2.getMessage());
			List<String> errors = new ArrayList<String>();
			errors.add("Error interno del sistema. Contacte a su administrador por favor.");
			
			redirectAttributes.addFlashAttribute("errors", errors);
			redirectAttributes.addFlashAttribute("existErrors", 1);
			
			return "redirect:/auth/wf/capture/partial/" + requestForm.getFormalityCode() + "/" + requestForm.getRequestId() + "/" + requestForm.getStageConfigId() + "/1";
		}
	}
	
	
	//proceso para guardar la partida actual
	
	@RequestMapping(value = "wf/capture/entries", method = { RequestMethod.POST })
	public String saveEntry(@ModelAttribute("requestForm") Request requestForm, BindingResult result, ModelMap model, 
			RedirectAttributes attributes, Principal principal, HttpServletRequest request, final RedirectAttributes redirectAttributes) throws Exception{
		
		LOG.info("iniciando guardado de partida");
		
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		
		try{
			
			Request r = accessNativeService.getRequestById(requestForm.getRequestId());
			Entry entry = new Entry();
			
			if(r.getEntryId() != null && r.getEntryId() > 0){
				
				entry = baseService.findById(Entry.class, r.getEntryId());
				entry.setCode(requestForm.getEntry().getCode());
				entry.setName(requestForm.getEntry().getDescription());
				entry.setDescription(requestForm.getEntry().getDescription());	
				entry.setAccountingType(requestForm.getEntry().getAccountingType());
				
				if (entry.getAccountingType() == AccountingType.PARTIDA){
					entry.setConcept(baseService.findById(Entry.class, requestForm.getEntry().getConcept().getId()));
				}
				
				LOG.info("Clave programatica: " + requestForm.getEntry().getProgrammaticKey().getId());
				ProgrammaticKey pk = baseService.findById(ProgrammaticKey.class, requestForm.getEntry().getProgrammaticKey().getId());
				LOG.info("Clave nueva: " + pk.getCode());
				entry.setProgrammaticKey(pk);
				baseService.update(entry);
				
			}else{
			
				LOG.info("codigo: " + requestForm.getEntry().getCode());
				LOG.info("descipcion: " + requestForm.getEntry().getDescription());
				
				entry = new Entry();
				entry.setCode(requestForm.getEntry().getCode());
				entry.setName(requestForm.getEntry().getDescription());
				entry.setDescription(requestForm.getEntry().getDescription());	
				
				ProgrammaticKey pk = baseService.findById(ProgrammaticKey.class, requestForm.getEntry().getProgrammaticKey().getId());
				entry.setProgrammaticKey(pk);
				
				entry.setAccountingType(requestForm.getEntry().getAccountingType());
				
				if (entry.getAccountingType() == AccountingType.PARTIDA){
					entry.setConcept(baseService.findById(Entry.class, requestForm.getEntry().getConcept().getId()));
				}
				
				entry.setStatus(StatusEntry.INACTIVE);
				entry.setActivo(false);
				entry.setCreatedBy(loggedUser.getUsername());
				
				//si es nuevo, se valida que el codigo no exista ya
				Map<String, Object> propertiesMap = new HashMap<String, Object>();
				propertiesMap.put("code", requestForm.getEntry().getCode());
				List<Entry> currentEntriesWithCode = baseService.findByProperties(Entry.class, propertiesMap);
				
				if(currentEntriesWithCode != null && currentEntriesWithCode.size() > 0){
					for(Entry e : currentEntriesWithCode){
						//si esta activa, significa que alguien ya la opero
						if(e.getStatus() == StatusEntry.ACTIVE){
							throw new EntryDistrictException("El código " + requestForm.getEntry().getCode() + " ya se encuentra actualmente asociado a la partida: " + e.getDescription() + ". Verifique.");
						}
						//si no esta activa, probablemente alguien la creó
						else if(e.getStatus() == StatusEntry.INACTIVE){
							Long requestId = accessNativeService.getRequestByEntryId(e.getId());
							if(requestId > 0 && requestId.longValue() != r.getRequestId()){
								Request requestCurrentEntry = accessNativeService.getRequestById(requestId);
								throw new EntryDistrictException("El código " + requestForm.getEntry().getCode() + " se encuentra asociado a partida: " 
								+ e.getDescription() + " la cual fue creada mediante el folio: "+ requestCurrentEntry.getFolio() + ", "
								+ "mismo que se encuentra en trámite. Verifique.");
							}
						}
					}
				}
				
				//guardando partida
				Long entryId = (Long) baseService.persistAndReturnId(entry);			
				r.setEntryId(entryId);		
				r = accessNativeService.insertOrUpdateRequestData(r);	
		
			}
			
			accessNativeService.invokeNextStage(requestForm, loggedUser.getId());
			LOG.info("Redirigiendo al listado de movimientos");
			return SecopreConstans.MV_TRAM_LIST_REDIRECT;
			
			/*
			Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("code", requestForm.getEntry().getCode());
			List<Entry> el = baseService.findByProperties(Entry.class, propertiesMap);
			
			LOG.info("Total de partidas con codigo: " +  requestForm.getEntry().getCode() + ": " + el.size());
			
			List<Entry> currentEntries = new ArrayList<Entry>();
			
			if(entry.getId() != null && entry.getId().longValue() > 0){
				for(Entry e : el){
					if (e.getId().longValue() != entry.getId().longValue()){
						currentEntries.add(e);
					}
				}
			}
			
			if(currentEntries != null && currentEntries.size() > 0){
				throw new EntryDistrictException("El código " + requestForm.getEntry().getCode() + " ya se encuentra actualmente asociado a la partida: " + currentEntries.get(0).getDescription() + ". Verifique.");
			}else{
				accessNativeService.invokeNextStage(requestForm, loggedUser.getId());
				LOG.info("Redirigiendo al listado de movimientos");
				return SecopreConstans.MV_TRAM_LIST_REDIRECT;
			}
			*/
						
		}catch(EntryDistrictException ex){
			//ex.printStackTrace();
			LOG.error(ex.getMessage());
			List<String> errors = new ArrayList<String>();
			errors.add(ex.getMessage());
			
			redirectAttributes.addFlashAttribute("errors", errors);
			redirectAttributes.addFlashAttribute("existErrors", 1);
			
			return "redirect:/auth/wf/capture/partial/" + requestForm.getFormalityCode() + "/" + requestForm.getRequestId() + "/" + requestForm.getStageConfigId() + "/1";
		}catch(Exception ex2){
			//ex2.printStackTrace();
			LOG.error(ex2.getMessage());
			List<String> errors = new ArrayList<String>();
			errors.add("Error interno del sistema. Contacte a su administrador por favor.");
			
			redirectAttributes.addFlashAttribute("errors", errors);
			redirectAttributes.addFlashAttribute("existErrors", 1);
			
			return "redirect:/auth/wf/capture/partial/" + requestForm.getFormalityCode() + "/" + requestForm.getRequestId() + "/" + requestForm.getStageConfigId() + "/1";
		}
	}
	
	
	/*
	 * Metodo para mostrar la autorizacion del folio correspondiente a la siguiente etapa
	 * param requestId		- Folio en cuestion
	 * param stageConfigId	- Etapa de autorizacion actual
	 * */
	@RequestMapping(value = "wf/authorization/{requestId}/{stageConfigId}/{ijs}", method = { RequestMethod.GET })
	public String showAuthorizationInfo(@PathVariable("requestId") Long requestId, 
			@PathVariable("stageConfigId") Long stageConfigId, 
			@PathVariable("ijs") int ijs, ModelMap model, RedirectAttributes attributes, Principal principal) {

		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);

		Request requestForm = accessNativeService.getRequestAndDetailById(requestId);
		if(requestForm.getEntryId() != null && requestForm.getEntryId() > 0){
			Entry e = baseService.findById(Entry.class, requestForm.getEntryId());
			if(e.getConcept() != null){
				e.setConcept(baseService.findById(Entry.class, e.getConcept().getId()));
			}
			e.setProgrammaticKey(baseService.findById(ProgrammaticKey.class, e.getProgrammaticKey().getId()));
			requestForm.setEntry(e);
		}
		requestForm.setStageConfigId(stageConfigId);
		requestForm.setAuthorizationForm(true);
		
		LOG.info("Request: " + requestForm);

		// se obtienen valores de authorizacion
		Authorization authorization = accessNativeService.getAuthorization(requestForm, loggedUser);
		
		//informacion requerida en tramite de partidas
		Map<String, String> accountingType = new HashMap<String, String>();
		for (AccountingType account : AccountingType.values()){
			accountingType.put(account.name(), account.name());
		}
		
		List<Entry> concepts = accessNativeService.getConceptsMap();
		
		model.addAttribute("conceptsList", concepts);
		model.addAttribute("accountingTypes", accountingType);
		model.addAttribute("programaticKeysFull", accessNativeService.getProgramaticKeysFullMap());

		model.addAttribute("movementTypes", accessNativeService.getMovementTypesMap());
		model.addAttribute("programaticKeys", accessNativeService.getProgramaticKeysMap());
		model.addAttribute("entries", accessNativeService.getEntriesMap(-1L));
		model.addAttribute("months", accessNativeService.getMonthsMap());
		model.addAttribute("requestForm", requestForm);
		model.addAttribute("authorization", authorization);
		model.addAttribute("folio", requestForm.getFolio());
		
		if(requestForm.getFormalityId() != null && requestForm.getFormalityId().longValue() == 4){
			requestForm.setReduccionTotalAmount(requestForm.calculateReducctionTotalAmount());
		}

		return SecopreConstans.MV_TRAM_AUTH;
	}
	
	
	/*
	 * Metodo para mostrar la autorizacion del folio correspondiente a la siguiente etapa
	 * param requestId		- Folio en cuestion
	 * param stageConfigId	- Etapa de autorizacion actual
	 * */
	@RequestMapping(value = "wf/finish/{requestId}/{stageConfigId}/{ijs}", method = { RequestMethod.GET })
	public String showRequestDetail(@PathVariable("requestId") Long requestId, 
			@PathVariable("stageConfigId") Long stageConfigId, 
			@PathVariable("ijs") int ijs, ModelMap model, RedirectAttributes attributes, Principal principal) {

		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);

		Request requestForm = accessNativeService.getRequestAndDetailById(requestId);
		if(requestForm.getEntryId() != null && requestForm.getEntryId() > 0){
			Entry e = baseService.findById(Entry.class, requestForm.getEntryId());
			if(e.getConcept() != null){
				e.setConcept(baseService.findById(Entry.class, e.getConcept().getId()));
			}
			e.setProgrammaticKey(baseService.findById(ProgrammaticKey.class, e.getProgrammaticKey().getId()));
			requestForm.setEntry(e);
		}
		requestForm.setStageConfigId(stageConfigId);
		requestForm.setAuthorizationForm(true);
		
		LOG.info("Request: " + requestForm);

		// se obtienen valores de authorizacion
		Authorization authorization = accessNativeService.getAuthorization(requestForm, loggedUser);
		
		//informacion requerida en tramite de partidas
		Map<String, String> accountingType = new HashMap<String, String>();
		for (AccountingType account : AccountingType.values()){
			accountingType.put(account.name(), account.name());
		}
		
		List<Entry> concepts = accessNativeService.getConceptsMap();
		
		model.addAttribute("conceptsList", concepts);
		model.addAttribute("accountingTypes", accountingType);
		model.addAttribute("programaticKeysFull", accessNativeService.getProgramaticKeysFullMap());

		model.addAttribute("movementTypes", accessNativeService.getMovementTypesMap());
		model.addAttribute("programaticKeys", accessNativeService.getProgramaticKeysMap());
		model.addAttribute("entries", accessNativeService.getEntriesMap(-1L));
		model.addAttribute("months", accessNativeService.getMonthsMap());
		model.addAttribute("requestForm", requestForm);
		model.addAttribute("authorization", authorization);
		model.addAttribute("folio", requestForm.getFolio());

		return SecopreConstans.MV_TRAM_AUTH;
	}

	/*
	 * Metodo para avanzar la etapa correspondiente guardando los comentarios de authorizacion
	 * param requestForm	- Obtiene el codigo de salida de la etapa en funcion del resultado de la autorizacion
	 * */
	@RequestMapping(value = "wf/authorization", method = { RequestMethod.POST })
	public String authorizeCancelOrFinishFormality( @ModelAttribute("requestForm") Request requestForm, ModelMap model, RedirectAttributes attributes, Principal principal) throws Exception {

		//si voy a regresar a captura, muevo la informacion de detalle a mirror
		if("REGRESAR".equals(requestForm.getNextStageValueCode())){
			Request r = accessNativeService.rollbackRequestDetail(requestForm.getRequestId());
			movementsService.savePartialRequest(r);
		}
		
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);
		accessNativeService.invokeNextStage(requestForm, loggedUser.getId());

		return SecopreConstans.MV_TRAM_LIST_REDIRECT;
	}

	/*
	 * Metodo para mostrar el formulario de carga de documentos anexos
	 * param requestId		- Folio en cuestion
	 * param stageConfigId	- Etapa actual
	 * */
	@RequestMapping(value = "wf/upload/{requestId}/{stageConfigId}/{ijs}", method = { RequestMethod.GET, RequestMethod.POST })
	public String showUploadForm(@PathVariable("requestId") Long requestId, 
			@PathVariable("stageConfigId") Long stageConfigId, 
			@PathVariable("ijs") int ijs,
			ModelMap model, RedirectAttributes attributes, Principal principal) {

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
				LOG.info("======> creando directorio de folio: " + requestFolder);
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
	      
	    	String REPORT_TYPE_DOWNLOAD = "application/x-download";
	    	
		  	Request request = accessNativeService.getRequestById(requestId);
		  	File f = new File(request.getResourcePath());
		  	InputStream is = new FileInputStream(request.getResourcePath());
	      	
	      	String resourcePath = request.getResourcePath();
	      	String split[] = resourcePath.split("\\.");
	      	if(split.length > 1){
	      		response.setContentType(getContentTypeFromExtension(split[split.length - 1].toUpperCase()));
	      	}else{
		      	response.setContentType(REPORT_TYPE_DOWNLOAD); 
	      	}
	      	
	      	response.setHeader("Content-disposition", "attachment;filename=" + request.getResourcePath());
	      	response.setContentLength((int) f.length());
	      	org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
	      	
	    } catch (IOException ex) {
	      	LOG.debug("Ocurrio un error al intentar descargar el archivo" + ex.toString());
	    }
	}
	
	/*
	 * Metodo para realizar la descarga del archivo anexo al folio
	 * param requestId
	 * */
	@RequestMapping(value = "wf/downloadFile/{propertyCode}", method = RequestMethod.GET)
	public void downloadFile(@PathVariable("propertyCode") String propertyCode, HttpServletResponse response) {
	    try {
	      
	    	String REPORT_TYPE_DOWNLOAD = "application/x-download";
	    		    	
	    	Property p = accessNativeService.getPropertyByCode(propertyCode);
	    	
		  	File f = new File(p.getDescription());
		  	InputStream is = new FileInputStream(p.getValue());	      	
		  	
	      	String split[] = p.getDescription().split("\\.");
	      	if(split.length > 1){
	      		response.setContentType(getContentTypeFromExtension(split[split.length - 1].toUpperCase()));
	      	}else{
		      	response.setContentType(REPORT_TYPE_DOWNLOAD); 
	      	}
	      	
	      	response.setHeader("Content-disposition", "attachment;filename=" + p.getDescription());
	      	org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
	      	response.flushBuffer();
	     
	      	
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	      	LOG.debug("Ocurrio un error al intentar descargar el archivo" + ex.toString());
	    }
	}
	
	private String getContentTypeFromExtension(String extension){
		String REPORT_TYPE_PDF = "application/pdf";
		String REPORT_TYPE_XLS = "application/vnd.ms-excel";
		String REPORT_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		String REPORT_TYPE_DOC = "application/msword";
		String REPORT_TYPE_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		String REPORT_TYPE_DOWNLOAD = "application/x-download";
		String contentType = REPORT_TYPE_DOWNLOAD;
		
		switch(extension){
			case "PDF":
				contentType = REPORT_TYPE_PDF;
				break;
			case "XLS":
				contentType = REPORT_TYPE_XLS;
				break;
			case "XLSX":
				contentType = REPORT_TYPE_XLSX;
				break;
			case "DOC":
				contentType = REPORT_TYPE_DOC;
				break;
			case "DOCX":
				contentType = REPORT_TYPE_DOCX;
				break;
			default:
				contentType = REPORT_TYPE_DOWNLOAD;
		}
		return contentType;
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
	
	@RequestMapping(value = "wf/getComments/{requestId}", method = { RequestMethod.GET })
	public String getComments(@PathVariable("requestId") Long requestId, ModelMap model, RedirectAttributes attributes, Principal principal) {

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
		
		EntryFilter entryFilter = new EntryFilter();
		entryFilter.setDistrictId(districtId);
		District d = baseService.findById(District.class, districtId);
		entryFilter.setEntryId(entryId);
		entryFilter.setStateId(d.getState().getId());
		
		EntryBalance balance = entryConfigService.getEntryBalance(entryFilter, StatusEntry.ACTIVE);
		
		model.addAttribute("entryDistrictBalance", entryDistrictBalance);
		model.addAttribute("balance", balance);
		return SecopreConstans.MV_TRAM_BALANCE;
	}
	
	/*Metodo para obtener el detalle distrito - llave programatica - mes */
	@RequestMapping(value = "wf/entryTotals/{district}/{entry}", method = { RequestMethod.GET })
	public String getAmountsDetail(@PathVariable("district") Long districtId,
								   @PathVariable("entry") Long entryId, 
								   ModelMap model, RedirectAttributes attributes, Principal principal) {
		
		LOG.info("total de registros para distrito: " + districtId + ", partida: " + entryId);
		
		EntryCurrentTotal totals = accessNativeService.getEntryCurrentTotals(districtId, entryId);
		List<EntryCurrentTotal> l = new ArrayList<EntryCurrentTotal>();
		l.add(totals);
		model.addAttribute("totals", l);
		return SecopreConstans.MV_TRAM_CURRENT_TOTAL;
	}
	
	
	/*Metodo para obtener el detalle de la clave programatica */
	@RequestMapping(value = "wf/pk/{pkId}", method = { RequestMethod.GET })
	public String getProgramaticData(@PathVariable("pkId") Long programaticKeyId,
								   ModelMap model, RedirectAttributes attributes, Principal principal) {

		LOG.info("Buscando llave programatica con id: " + programaticKeyId);
		ProgrammaticKey pk = baseService.findById(ProgrammaticKey.class, programaticKeyId);
		boolean existeKey = false;
		if (pk != null){
			LOG.info("AÑO: " + pk.getYear());
			existeKey = true;
		}else{
			LOG.info("no se encontro la llave");
		}
		
		model.addAttribute("pk", pk);
		model.addAttribute("existeKey", existeKey);
		return SecopreConstans.MV_TRAM_PK;
	}
	
	
	/*
	 * Metodo para obtener el detalle de la historia de un folio
	 * param requestId
	 * */
	@RequestMapping(value = "wf/getDueDates", method = { RequestMethod.GET })
	public String getDueDatesInfo(ModelMap model, RedirectAttributes attributes, Principal principal) {

		List<DueDate> dueDates = accessNativeService.getActiveDueDate();

		model.addAttribute("dueDates", dueDates);
		return SecopreConstans.MV_TRAM_DUE_DATES;
	}
	
	/*Metodo para descargar el formato base del tramite concluido*/
	@RequestMapping(value = "wf/download/format/{requestId}", method = RequestMethod.GET)
	public void getFormatFile(@PathVariable("requestId") Long requestId, Principal principal, HttpServletResponse response) throws Exception {
	    try {
	    	
	    	Property p = accessNativeService.getPropertyByCode(PropertyConstants.OPERATED_MOVEMENT_REPORT_ID);
	    	
	    	ReportParameter params = new ReportParameter();
	    	params.setRequestId(requestId.toString());
	    	
			User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);				    	
	    	Report report = reportService.getReport(p.getLongValue(), loggedUser.getId(), params);
			
	    	super.flushReport(response, report);
	      	
	    } catch (IOException ex) {
	      	LOG.debug("Ocurrio un error al intentar descargar el archivo" + ex.toString());
	    }
	}
	
	

	@RequestMapping(value = "wf/rectification/{requestId}", method = { RequestMethod.GET })
	public String startRectification( @PathVariable("requestId") Long requestId, ModelMap model, RedirectAttributes attributes, Principal principal) throws Exception {

		LOG.info("Iniciando rectification del requestId: " + requestId);

		/*
		 * 1. obtener el detalle de el movimiento
		 * 2. reasignar los saldos a las partidas correspondientes
		 * 3. iniciar nuevo tramite de gastos igual que original, con nuevo id
		 * 4. avanzar folio nuevo de etapa
		 * 5. generar request detail mirror con monto de request id
		 * 6. actualizar folio padre con id de request de rectificacion
		 * */
		
		Request baseRequest = accessNativeService.getRequestAndDetailById(requestId);
		Request rectificationRequest = accessNativeService.getRequestAndDetailById(requestId);
		
		boolean result = accessNativeService.fullRollbackMovement(baseRequest.getDownMovements(), baseRequest);
		
		User loggedUser = baseService.findByProperty(User.class, "username", principal.getName()).get(0);			
		District district= baseService.findById(District.class, baseRequest.getDistrictId());
		Long newRequestId = accessNativeService.getRequestByDistrictNextConsecutive(district.getId());	
		String newFolio = "DTO-" +  district.getNumber() + "/" + newRequestId;
		
		rectificationRequest.setRequestId(newRequestId);
		rectificationRequest.setFolio(newFolio);
		rectificationRequest.setUpMovements(new ArrayList<Movement>());
		rectificationRequest.setDownMovements(new ArrayList<Movement>());
		accessNativeService.startFormality(rectificationRequest, loggedUser.getId());

		rectificationRequest.setDownMovements(baseRequest.getDownMovements());
		movementsService.savePartialRequest(rectificationRequest);

		baseRequest.setRectificationId(newRequestId);
		baseRequest = accessNativeService.insertOrUpdateRequestData(baseRequest);
		
		LOG.info("Rectificacion realizada exitosamente");
		
		return SecopreConstans.MV_TRAM_LIST_REDIRECT;
	}
}

