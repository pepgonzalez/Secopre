package ideasw.secopre.service.impl;

import ideasw.secopre.constants.PropertyConstants;
import ideasw.secopre.constants.WorkflowConstants;
import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Movement;
import ideasw.secopre.dto.Notification;
import ideasw.secopre.dto.Property;
import ideasw.secopre.dto.Report;
import ideasw.secopre.dto.ReportParameter;
import ideasw.secopre.dto.Request;
import ideasw.secopre.dto.RequestConfig;
import ideasw.secopre.dto.RequestHistory;
import ideasw.secopre.dto.StageConfig;
import ideasw.secopre.dto.UserMovement;
import ideasw.secopre.dto.WorkFlowConfig;
import ideasw.secopre.enums.Month;
import ideasw.secopre.enums.WorkFlowCode;
import ideasw.secopre.exception.EntryDistrictException;
import ideasw.secopre.model.DueDate;
import ideasw.secopre.model.Entry;
import ideasw.secopre.model.EntryDistrict;
import ideasw.secopre.model.ProgrammaticKey;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.catalog.MovementType;
import ideasw.secopre.model.security.Permission;
import ideasw.secopre.model.security.Role;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.BaseService;
import ideasw.secopre.service.impl.mapper.DistrictMapper;
import ideasw.secopre.service.impl.mapper.EntryMapper;
import ideasw.secopre.service.impl.mapper.FormalityMapper;
import ideasw.secopre.service.impl.mapper.FullEntryDistrictMapper;
import ideasw.secopre.service.impl.mapper.FullEntryMapper;
import ideasw.secopre.service.impl.mapper.InboxMapper;
import ideasw.secopre.service.impl.mapper.MovementMapper;
import ideasw.secopre.service.impl.mapper.NotificationMapper;
import ideasw.secopre.service.impl.mapper.PermissionMapper;
import ideasw.secopre.service.impl.mapper.PropertyMapper;
import ideasw.secopre.service.impl.mapper.ReportMapper;
import ideasw.secopre.service.impl.mapper.ReportParameterMapper;
import ideasw.secopre.service.impl.mapper.RequestConfigMapper;
import ideasw.secopre.service.impl.mapper.RequestHistoryMapper;
import ideasw.secopre.service.impl.mapper.RequestMapper;
import ideasw.secopre.service.impl.mapper.RoleMapper;
import ideasw.secopre.service.impl.mapper.StageConfigMapper;
import ideasw.secopre.service.impl.mapper.UserMapper;
import ideasw.secopre.service.impl.mapper.UserMovementMapper;
import ideasw.secopre.service.impl.mapper.WorkFlowConfigMapper;
import ideasw.secopre.sql.QueryContainer;
import ideasw.secopre.sql.SQLConstants;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccessNativeServiceImpl extends AccessNativeServiceBaseImpl implements AccessNativeService{

	@Autowired
	QueryContainer queryContainer;

	@Autowired
	public BaseService baseService;
	
	static final Logger LOG = LoggerFactory.getLogger(AccessNativeServiceImpl.class);
	
	@Override
	/*
	 * Obtiene todos los tramites disponibles que puede iniciar el usuario
	 * */
	public List<Formality> getFormalityAvailableByUser(User user) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("userId", user.getId());	
		return this.queryForList(Formality.class, queryContainer.getSQL(SQLConstants.GET_FORMALITY_FROM_USER_ID), namedParameters, new FormalityMapper());
	}

	/*
	 * Proceso para inicio de tramite, inserta en REQUEST, REQUEST_CONFIG, REQUEST_HISTORY
	 * */
	public Long startFormality(Request request, Long userId) throws Exception {
		
		this.insertOrUpdateRequest(request);
		
		this.insertOrUpdateRequestDetail(request);

		Formality formality = this.getFormalityById(request.getFormalityId());
		this.insertRequestConfig(request.getRequestId(), formality);
		
		this.invokeNextStage(request.getRequestId(), WorkFlowCode.SOLCOMP.name() , -1L,  userId, request.getComments(), 0);
		
		return request.getRequestId();
	}
	
	/*
	 * Proceso para obtener el listado de tramites en proceso por usuario
	 * */
	public List<Inbox> getInboxByUserId(Long userId){
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("userId", userId);	
		return this.queryForList(Inbox.class, queryContainer.getSQL(SQLConstants.GET_FORMALITY_INBOX), namedParameters, new InboxMapper());
	}
	
	public List<Inbox> getMyInboxByUserId(Long userId){
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("userId", userId);	
		return this.queryForList(Inbox.class, queryContainer.getSQL(SQLConstants.GET_MY_FORMALITY_INBOX), namedParameters, new InboxMapper());
	}
	
	public List<RequestHistory> getRequestHistory(Long requestId){
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("requestId", requestId);	
		return this.queryForList(RequestHistory.class, queryContainer.getSQL(SQLConstants.GET_REQUEST_HISTORY), namedParameters, new RequestHistoryMapper());
	}
	
	
	public void invokeNextStage(Request request, Long userId){
		this.invokeNextStage(request.getRequestId(), request.getNextStageValueCode(), request.getStageConfigId(), userId, request.getComments(), 0);
	}
	
	public int updateRequestUploadedFile(Long requestId, String uploadedFilePath){
		SqlParameterSource namedParameters = new MapSqlParameterSource()
		.addValue("requestId", requestId)
		.addValue("uploadedFilePath", uploadedFilePath);
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.UPDATE_UPLOADED_FILE_IN_REQUEST), namedParameters);
	}
	
	
	/*
	 * Proceso para avanzar un tramite de etapa
	 * */
	private void invokeNextStage(Long requestId, String valueCode, Long stageConfigId,  Long userId, String comments, int isAutomatic){
			
		int consecutive = this.getNextConsecutive(requestId);
	
		if(consecutive == 1){
			LOG.info("Insertanto etapa inicial " + consecutive);
			WorkFlowConfig transition = this.getRequestFirstWorkFlowConfig(requestId, valueCode).get(0);
			this.insertTransition(requestId, transition, consecutive, userId, comments);
		}else{
			LOG.info("Insertando consecutivo de etapa: " + consecutive);
			WorkFlowConfig transition = this.getRequestWorkFlowConfig(requestId, valueCode, stageConfigId).get(0);
			
			//se obtiene la etapa actual
			StageConfig currentStage = this.getStageConfigById(stageConfigId);
			LOG.info("Etapa actual: " + currentStage);
			
			//validar si la siguiente etapa es operacion o cancelacion del tramite
			StageConfig next = this.getStageConfigById(transition.getNextStageConfig());
			LOG.info("Siguiente etapa:" + next);
			
			
			//si la siguiente etapa es autorizacion y no es salto automatico, logica para identificar siguiente etapa
			if (next.getIsAuthorization() && isAutomatic == 0 &&  !(currentStage.getIsAuthorization())){
				LOG.info("siguiente etapa es etapa de autorizacion, validando privilegios del usuario");
				//se valida si el usuario tiene el superRol de autorizacion configurado en el tramite
				RequestConfig requestConfig = this.getRequestConfigById(requestId);
				
				RequestConfig config = this.getRequestConfigById(requestId);
				Formality formality = this.getFormalityById(config.getFormalityId());
				
				int isSuperUser = this.isAuthorizationSuperUser(formality.getAuthorizationId(), userId);
				LOG.info("Es usuario super usuario de configuracion: " + isSuperUser);
				if (isSuperUser > 0){
					LOG.info("Finalizando captura y operando movimiento");
					invokeNextStage(requestId, WorkflowConstants.SUPER_ROLE_AUTHORIZATION, stageConfigId, userId, comments, 1);
					return;
				}
				
				//si no es super user, pregunto por el employment
				String automaticAuthorizationCode = getAutomaticAuthorization(config.getAuthorizationId(), userId);
				if(automaticAuthorizationCode.equals(WorkflowConstants.WF_NOT_AUTOMATIC_AUTHORIZATION)){
					LOG.info("usuario no tiene privilegios de autorizacion automatica");
				}else{
					LOG.info("Autorizacion automatica por etapa: " + automaticAuthorizationCode);
					invokeNextStage(requestId, automaticAuthorizationCode, stageConfigId, userId, comments, 1);
					return;
				}
			}else{
				LOG.info("omitiendo validacion de autorizaciones automaticas");
			}
			
			
			//si es etapa de cancelacion, se da rollback a los movimientos a la baja
			if(next.getIsCanceled()){
				//por cada movimiento a la baja, se resta el monto distrito-partida-mes, al saldo comprometido
				this.runCancelation(requestId);
			}
			
			//si la siguiente etapa es de tramite finalizado, opera los movimientos
			if(next.getIsOperated()){
				//por cada movimiento a la alza, se suma el monto al distrito-patida-mes, al saldo acumulado
				//por cada movimiento a la baja, se resta el monto al distrito-partido-mes, al saldo comprometido y al saldo acumulado
				this.runOperation(requestId);
			}
			
			//operar o modificar monto comprometido si aplica
			
			this.inactivateActiveStage(requestId);
			this.insertTransition(requestId, transition, consecutive, userId, comments);
		}
	}
	
	private String getAutomaticAuthorization(Long authorizationId, Long userId){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("authorizationId", authorizationId)
				.addValue("userId", userId);
		return this.queryForObject(String.class, queryContainer.getSQL(SQLConstants.GET_AUTOMATIC_AUTHORIZATION), params);
	}
	
	
	private void runCancelation(Long requestId){
		//se obtiene el detalle del folio
		Request request = this.getRequestAndDetailById(requestId);
		for(Movement mov : request.getDownMovements()){
			//iteracion sobre los meses del movimiento
			for(int i= mov.getInitialMonthId(); i<= mov.getFinalMonthId();i++){
				EntryDistrict entry = this.getEntryBalance(request.getDistrictId(), mov.getEntryId(), new Long(i));
				entry.setCommittedAmount((entry.getCommittedAmount() - mov.getMonthAmount()));
				baseService.update(entry);
			}
		}	
	}
	
	private void runOperation(Long requestId){
		//se obtiene el detalle del folio
		Request request = this.getRequestAndDetailById(requestId);
		
		//se realizan las disminuciones
		for(Movement mov : request.getDownMovements()){
			//iteracion sobre los meses del movimiento
			for(int i= mov.getInitialMonthId(); i<= mov.getFinalMonthId();i++){
				EntryDistrict entry = this.getEntryBalance(request.getDistrictId(), mov.getEntryId(), new Long(i));
				entry.setCommittedAmount((entry.getCommittedAmount() - mov.getMonthAmount()));
				entry.setBudgetAmountAssign((entry.getBudgetAmountAssign() - mov.getMonthAmount()));
				baseService.update(entry);
			}
		}	
		
		// se realizan los movimientos a la alza
		for(Movement mov : request.getUpMovements()){
			//iteracion sobre los meses del movimiento
			for(int i= mov.getInitialMonthId(); i<= mov.getFinalMonthId();i++){
				EntryDistrict entry = this.getEntryBalance(request.getDistrictId(), mov.getEntryId(), new Long(i));
				entry.setBudgetAmountAssign((entry.getBudgetAmountAssign() + mov.getMonthAmount()));
				baseService.update(entry);
			}
		}	
	}
	
	private StageConfig getStageConfigById(Long stageConfigId){
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("stageConfigId", stageConfigId);	
		return this.queryForList(StageConfig.class, queryContainer.getSQL(SQLConstants.GET_STAGE_CONFIG_BY_ID), namedParameters, new StageConfigMapper()).get(0);
	}
	
	
	
	/*----------------------------------------------------------------------------------------------------------
	 * 	Procesos internos del service
	 * ---------------------------------------------------------------------------------------------------------*/
	
	private List<WorkFlowConfig> getRequestFirstWorkFlowConfig(Long requestId, String wfConfigCode) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("requestId", requestId).addValue("wfConfigCode", wfConfigCode);	
		return this.queryForList(WorkFlowConfig.class, queryContainer.getSQL(SQLConstants.GET_REQUEST_FIRST_WORKFLOW_CONFIG), namedParameters, new WorkFlowConfigMapper());
	}
	
	private List<WorkFlowConfig> getRequestWorkFlowConfig(Long requestId, String wfConfigCode, Long stageConfigId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("requestId", requestId).addValue("wfConfigCode", wfConfigCode).addValue("stageConfigId", stageConfigId);	
		return this.queryForList(WorkFlowConfig.class, queryContainer.getSQL(SQLConstants.GET_REQUEST_WORKFLOW_CONFIG), namedParameters, new WorkFlowConfigMapper());
	}
	
	private int inactivateActiveStage(Long requestId){
		SqlParameterSource params = new MapSqlParameterSource().addValue("requestId", requestId);
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.INACTIVATE_ACTIVE_STAGE), params);
	}
	
	private int getNextConsecutive(Long requestId){
		SqlParameterSource params = new MapSqlParameterSource().addValue("requestId", requestId);
		return this.queryForObject(Integer.class, queryContainer.getSQL(SQLConstants.GET_NEXT_CONSECUTIVE), params);
	}
	
	public Long getRequestNextConsecutive(){
		SqlParameterSource params = new MapSqlParameterSource();
		return this.queryForObject(Long.class, queryContainer.getSQL(SQLConstants.GET_REQUEST_NEXT_CONSECUTIVE), params);
	}
	
	private int canUserAuthorize(Long authorizationId, Long stageConfigId, Long userId){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("authorizationId", authorizationId)
				.addValue("stageConfigId", stageConfigId)
				.addValue("userId", userId);
		return this.queryForObject(Integer.class, queryContainer.getSQL(SQLConstants.CAN_USER_AUTHORIZE), params);
	}
	
	private int isAuthorizationSuperUser(Long authorizationId, Long userId){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("authorizationId", authorizationId)
				.addValue("userId", userId);
		return this.queryForObject(Integer.class, queryContainer.getSQL(SQLConstants.IS_USER_AUTHORIZATION_SUPERUSER), params);
	}
	
	private int hasMoreSignatures(Long requestId, Long stageConfigId){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("requestId", requestId)
				.addValue("stageConfigId", stageConfigId);
		return this.queryForObject(Integer.class, queryContainer.getSQL(SQLConstants.HAS_MORE_SIGNATURES), params);
	}
	
	
	private int insertRequestConfig(Long requestId, Formality formality){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("requestId", requestId)
				.addValue("formalityId", formality.getFormalityId())
				.addValue("workFlowId", formality.getWorkFlowId())
				.addValue("authorizationId", formality.getAuthorizationId());
		
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.INSERT_REQUEST_CONFIG), params);
	}
	
	private Formality getFormalityById(Long formalityId){		
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("formalityId", formalityId);
		List<Formality> list = this.queryForList(Formality.class, queryContainer.getSQL(SQLConstants.GET_FORMALITY_BY_ID), namedParameters, new FormalityMapper());
		return list.get(0);
	}
	

	private Long insertRequest(Request request){
		return this.insertAndReturnId(Request.TABLE_NAME, Request.PRIMARY_KEY, request.getParams());
	}
	
	private int insertOrUpdateRequest(Request request){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("requestId", request.getRequestId())
				.addValue("folio", request.getFolio())
				.addValue("districtId", request.getDistrictId())
				.addValue("justification", request.getJustification())
				.addValue("movementTypeId", request.getMovementTypeId())
				.addValue("resourcePath", request.getResourcePath())
				.addValue("certifiedAccount", request.getCertifiedAccount());
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.INSERT_OR_UPDATE_REQUEST), params);
	}
	
	@Transactional(readOnly = false, rollbackFor=EntryDistrictException.class)
	public int insertOrUpdateRequestDetail(Request request) throws Exception{
		
		request = this.insertOrUpdateRequestData(request);
		
		//se limpian los movimientos actuales
		LOG.info("Limpiando detalle de movimientos");
		int clean = this.cleanRequestDetail(request.getRequestId());
		LOG.info("Detalle de movimientos eliminado");
		
		LOG.info("Insertando movimientos de disminucion");
		this.insertMovementList(request.getDownMovements(), request);
		LOG.info("Movimientos de disminucion terminado");
		LOG.info("Insertando movimientos de amplicacion");
		this.insertMovementList(request.getUpMovements(), request);
		LOG.info("Movimientos de ampliacion terminado");
		return 0;
	}
	
	
	public Request insertOrUpdateRequestData(Request request){
		Request baseRequest = this.getRequestById(request.getRequestId());
		
		baseRequest.setMovementTypeId(request.getMovementTypeId());
		baseRequest.setCertifiedAccount(request.getCertifiedAccount());
		
		request.setDistrictId(baseRequest.getDistrictId());
		
		LOG.info("Actualizando request");
		this.insertOrUpdateRequest(baseRequest);
		LOG.info("Request Actualizado");
		
		return request;
	}

	private int cleanRequestDetail(Long requestId){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("requestId", requestId);
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.CLEAN_REQUEST_DETAIL), params);
	}
	
	
	private void insertMovementList(List<Movement> list, Request request) throws Exception{
		
		LOG.info("---------------------------------------------------------------------------");
		LOG.info("Cantidad de movimientos a actualizar: " + list.size());
		LOG.info("Tipo de guardado: " + request.getNextStageValueCode());
		LOG.info("Request id: " + request.getRequestId());
		LOG.info("---------------------------------------------------------------------------");

		
		for(Movement m : list){
			
			//si no es un elemento eliminado
			if(m.getRemovedElement() == 0){
				
				//se afectan a las partidas
				if(m.getMovementTypeId().intValue() < 0){
					
					//en los movimientos de disminucion se compromete el saldo
					for(int i = m.getInitialMonthId(); i <= m.getFinalMonthId(); i++){
						
						//Se obtiene el balance actual de la partida distrito mes
						EntryDistrict entry = this.getEntryBalance(request.getDistrictId(), m.getEntryId(), new Long(i));
						if (entry == null){
							throw new EntryDistrictException(request.getDistrictId(), m.getEntryId(), new Long(i), m.getMonthAmount());
						}
						//TODO validar los montos de nuevo y modificar saldo comprometido si es finalizar captura
						if (request.getNextStageValueCode().equals("SOLCOMP")){
							if((entry.getBudgetAmountAssign() - entry.getCommittedAmount()) < m.getMonthAmount()){
								throw new EntryDistrictException(entry.getDistrict().getNumber(), entry.getEntry().getDescription(), 
																entry.getMonthString(), (entry.getBudgetAmountAssign() - entry.getCommittedAmount()), m.getMonthAmount());
							}	
							
							//se actualiza el movimiento
							LOG.info("Actualizando saldo comprometido a partida");
							entry.setCommittedAmount(entry.getCommittedAmount() + m.getMonthAmount());
							baseService.update(entry);
						}
					}
					
				}
				
				LOG.info("Obteniendo id detalle para request_id: " + request.getRequestId());
				//inserta de nuevo el registro
				//Long id =  this.insertAndReturnId(Movement.TABLE_NAME, Movement.PRIMARY_KEY, m.getParams(request.getRequestId()));
				m.setRequestId(request.getRequestId());
				this.insertMovement(m);
				//LOG.info("Request_detail obtenido: " + id);
				//m.setRequestDetailId(id);
			}
		}
	}
	
	private int insertMovement(Movement m){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("requestId", m.getRequestId())
				.addValue("movementTypeId", m.getMovementTypeId())
				.addValue("programaticKeyId", m.getProgramaticKeyId())
				.addValue("entryId", m.getEntryId())
				.addValue("initialMonth", m.getInitialMonthId())
				.addValue("finalMonth", m.getFinalMonthId())
				.addValue("monthAmount", m.getMonthAmount())
				.addValue("totalAmount", m.getTotalAmount())
				.addValue("active", 1);
		LOG.info("insertando movimiento");
		LOG.info(m.toString());
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.INSERT_REQUEST_DETAIL), params);
	}
	
	private int insertTransition(Long requestId, WorkFlowConfig config, int consecutive, Long userId, String comments){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("requestId", requestId)
				.addValue("consecutive", consecutive)
				.addValue("workFlowConfigId",config.getWorkFlowConfigId())
				.addValue("userId", userId)
				.addValue("comments", comments);
		
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.INSERT_REQUEST_HISTORY), params);
	}

	@Override
	public Request getRequestById(Long requestId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("requestId", requestId);
		List<Request> list = this.queryForList(Request.class, queryContainer.getSQL(SQLConstants.GET_REQUEST_BY_ID), namedParameters, new RequestMapper());
		Request r =  list.get(0);
		return r;
	}
	
	public Request getRequestAndDetailById(Long requestId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("requestId", requestId);
		List<Request> list = this.queryForList(Request.class, queryContainer.getSQL(SQLConstants.GET_REQUEST_BY_ID), namedParameters, new RequestMapper());
		Request r =  list.get(0);
		r.setMovements(this.getRequestDetailByRequestId(requestId));

		//se obtiene requestConfig
		RequestConfig config = this.getRequestConfigById(r.getRequestId());

		Formality formality =  getFormalityById(config.getFormalityId());
		String formalityName = formality.getDescription();

		r.setFormalityName(formalityName);

		return r;
	}
	
	public Request getRequestAndPartialDetailById(Long requestId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("requestId", requestId);
		List<Request> list = this.queryForList(Request.class, queryContainer.getSQL(SQLConstants.GET_REQUEST_BY_ID), namedParameters, new RequestMapper());
		Request r =  list.get(0);
		r.setMovements(this.getRequestPartialDetailByRequestId(requestId));

		//se obtiene requestConfig
		RequestConfig config = this.getRequestConfigById(r.getRequestId());

		Formality formality =  getFormalityById(config.getFormalityId());
		String formalityName = formality.getDescription();

		r.setFormalityName(formalityName);

		return r;
	}

	public List<Movement> getRequestDetailByRequestId(Long requestId){
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("requestId", requestId);
		List<Movement> list = this.queryForList(Movement.class, queryContainer.getSQL(SQLConstants.GET_REQUEST_DETAIL), namedParameters, new MovementMapper());		
		return list;
	}
	
	public List<Movement> getRequestPartialDetailByRequestId(Long requestId){
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("requestId", requestId);
		List<Movement> list = this.queryForList(Movement.class, queryContainer.getSQL(SQLConstants.GET_REQUEST_DETAIL_MIRROR), namedParameters, new MovementMapper());		
		return list;
	}
	
	public RequestConfig getRequestConfigById(Long requestId){
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("requestId", requestId);
		List<RequestConfig> list = this.queryForList(RequestConfig.class, queryContainer.getSQL(SQLConstants.GET_REQUEST_CONFIG_BY_ID), namedParameters, new RequestConfigMapper());
		return list.get(0);
	}


	@Override
	public Authorization getAuthorization(Request request, User user) {
		Authorization authorization = new Authorization();
		//se obtiene el requestConfig y se agrega codigo a authorizacion
		RequestConfig config = this.getRequestConfigById(request.getRequestId());
		Formality formality = this.getFormalityById(config.getFormalityId());
		
		authorization.setFormalityCode(formality.getCode());
		
		//se valida si el usuario puede autorizar la etapa
		authorization.setCanUserAuthorize((this.canUserAuthorize(formality.getAuthorizationId(), request.getStageConfigId(), user.getId())) > 0);
		
		//se valida si el usuario tiene el rol de superusuario configurado para el grupo de authorizacion
		authorization.setSuperUser(this.isAuthorizationSuperUser(formality.getAuthorizationId(), user.getId()) > 0);
		
		//se valida si la etapa tiene mas firmas o es la ultima firma
		authorization.setMoreSignatures(this.hasMoreSignatures(request.getRequestId(), request.getStageConfigId()) > 0);
		
		return authorization;
	}

	@Override
	public Map<Long, String> getProgramaticKeysMap() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		List<ProgrammaticKey> l = baseService.findByProperty(ProgrammaticKey.class, "year", year);
		Map<Long, String> map = new HashMap<Long, String>();
		for(ProgrammaticKey k : l){
			map.put(k.getId(), k.getCode());
		}
		return map;
	}
	
	public Map<Long, String> getEntriesMap(){
		List<Entry> l = new ArrayList<Entry>();
		l = baseService.findAll(Entry.class);
		Map<Long, String> map = new HashMap<Long, String>();
		for (Entry e : l){
			map.put(e.getId(), e.getName());
		}
		return map;
	}
	
	public Map<Long, String> getDistrictsMap(){
		List<District> l = new ArrayList<District>();
		l = baseService.findAll(District.class);
		Map<Long, String> map = new HashMap<Long, String>();
		for (District e : l){
			map.put(e.getId(), e.getNumber());
		}
		return map;
	}

	@Override
	public List<Entry> getEntries(Long programaticKey) {
		List<Entry> l = new ArrayList<Entry>();
		l = baseService.findByProperty(Entry.class, "programmaticKey", baseService.findById(ProgrammaticKey.class, programaticKey));
		return l;
	}

	@Override
	public Map<Long, String> getEntriesMap(Long programaticKey) {
		List<Entry> l = new ArrayList<Entry>();
		if(programaticKey.longValue() > 0){
			l = baseService.findByProperty(Entry.class, "programmaticKey", baseService.findById(ProgrammaticKey.class, programaticKey));
		}else{
			l = baseService.findAll(Entry.class);
		}
		Map<Long, String> map = new HashMap<Long, String>();
		for (Entry e : l){
			map.put(e.getId(), e.getCode() + " - "+e.getName());
		}
		return map;
	}

	@Override
	public Map<Long, String> getMonthsMap() {
		Map<Long, String> map = new HashMap<Long, String>();
		for (Month mes : Month.values()){
			map.put(mes.getId(), mes.name());
		}
		return map;
	}

	@Override
	public Map<Long, String> getMovementTypesMap() {
		List<MovementType> movementTypes = baseService.findAll(MovementType.class);	
		Map<Long, String> map = new HashMap<Long, String>();
		for(MovementType mov : movementTypes){
			map.put(mov.getId(), mov.getDescription());
		}
		return map;
	}

	
	/*Metodo que retorna todas las partidas asociadas a la llave programatica y que tengan presupuesto asignado en el distrito seleccionado*/
	@Override	
	public List<Entry> getEntries(Long districtId, Long programaticKeyId) {

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("programaticKeyId", programaticKeyId)
				.addValue("districtId", districtId);
		List<Entry> list = this.queryForList(Entry.class, queryContainer.getSQL(SQLConstants.GET_VALID_ENTRIES), namedParameters, new EntryMapper());	
		return list;

	}

	/*
	 * Metodo que retorna todas las partidas asociadas a la llave programatica y
	 * que tengan presupuesto asignado en el distrito seleccionado
	 */
	@Override
	public List<Entry> getValidEntriesByDistrict(Long districtId) {

		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("districtId", districtId);

		List<Entry> list = this.queryForList(Entry.class, queryContainer
				.getSQL(SQLConstants.GET_VALID_ENTRIES_BY_DISTRIC),
				namedParameters, new EntryMapper());
		return list;

	}
	
	@Override
	public EntryDistrict getEntryBalance(Long districtId, Long entryId, Long month) {
		
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("district", baseService.findById(District.class, districtId));
		propertiesMap.put("entry", baseService.findById(Entry.class, entryId));
		propertiesMap.put("month", month);
		
		List<EntryDistrict> list = baseService.findByProperties(EntryDistrict.class, propertiesMap);
		EntryDistrict entry = (list.size() > 0 ? list.get(0) : null);
		if(entry != null){
			entry.setMonthString(this.getMonthsMap().get(entry.getMonth() + 1));
		}
		return entry;
	}
	
	public List<EntryDistrict> getAnnualEntryBalance(Long districtId, Long entryId){
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("district", baseService.findById(District.class, districtId));
		propertiesMap.put("entry", baseService.findById(Entry.class, entryId));
		
		List<EntryDistrict> list = baseService.findByProperties(EntryDistrict.class, propertiesMap);
		for(EntryDistrict entry :list){
			entry.setMonthString(this.getMonthsMap().get(entry.getMonth() + 1));
		}
		return list;
	}
	
	public List<District> getValidDistricts(){
		List<District> list = this.queryForList(District.class, queryContainer.getSQL(SQLConstants.GET_VALID_DISTRICTS), new MapSqlParameterSource(), new DistrictMapper());	
		return list;
	}
	
	public List<District> getValidDistrictsByUserId(Long userId){
		SqlParameterSource params = new MapSqlParameterSource().addValue("userId", userId);
		List<District> list = this.queryForList(District.class, queryContainer.getSQL(SQLConstants.GET_VALID_DISTRICTS_BY_USER), params, new DistrictMapper());	
		return list;
	}
	
	public int isUsernameValid(String username){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("username", username);
		
		return this.queryForObject(Integer.class, queryContainer.getSQL(SQLConstants.IS_USERNAME_VALID), params);
				
	}
	
	public List<Report> getReportList(User user) throws Exception{
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("userId", user.getId());
		List<Report> reportList = this.queryForList(Report.class, queryContainer.getSQL(SQLConstants.GET_REPORT_LIST), namedParameters, new ReportMapper());
		for(Report report: reportList){
			report.setReportParameters(this.getReportParameterByReportId(report.getReportId()));
		}
		return reportList;
	}
	
	public Report getReportById(Long reportId) throws Exception{
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("reportId", reportId);
		Report report = this.queryForList(Report.class, queryContainer.getSQL(SQLConstants.GET_REPORT_BY_ID), namedParameters , new ReportMapper()).get(0);
		report.setReportParameters(this.getReportParameterByReportId(report.getReportId()));
		return report;
	}
	
	public List<ReportParameter> getReportParameterByReportId(Long reportId) throws Exception{
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("reportId", reportId);
		List<ReportParameter> reportParameterList = this.queryForList(ReportParameter.class, queryContainer.getSQL(SQLConstants.GET_REPORT_PARAMETERS), namedParameters, new ReportParameterMapper());
		
		for(ReportParameter reportParameter: reportParameterList){
			if(reportParameter.getAjax() != null && reportParameter.getAjax().length() > 0){
				//se obtiene el mapa de opciones
				 Method method = this.getClass().getMethod(reportParameter.getAjax());
				 Map<Long, String> parameterOptions = (Map<Long, String>) method.invoke(this);
				 reportParameter.setParameterOptions(parameterOptions);
			}
		}
		return reportParameterList;
	}
	
	public Connection getSecopreDSConnection() throws SQLException{
		return super.getSecopreConnection();
	}

	@Override
	public Connection getTsadbitntstDataSourceConnection() throws SQLException {
		return super.getTsadbitntstConnection();
	}
	
	
	@Override
	public List<Role> getRolesByUser(Long userId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
		.addValue("userId", userId);
        return this.queryForList(Role.class, queryContainer.getSQL(SQLConstants.GET_ROLE_LIST_BY_USER), namedParameters, new RoleMapper());
	}
	
	
	@Override
	public List<Permission> getPermissionsByRole(Long idRole) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
		.addValue("idRole", idRole);
        return this.queryForList(Permission.class, queryContainer.getSQL(SQLConstants.GET_PERMISSION_LIST_BY_ROLE), namedParameters, new PermissionMapper());
	}
	
	@Override
	public List<Role> getRolesByMenu(Long idMenu) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
		.addValue("idMenu", idMenu);
        return this.queryForList(Role.class, queryContainer.getSQL(SQLConstants.GET_ROLE_LIST_BY_MENU), namedParameters, new RoleMapper());
	}

	@Override
	public List<ReportParameter> getParametersById(Long reportId) throws Exception {
		return this.getReportParameterByReportId(reportId);
	}
	
	public List<UserMovement> getCreatedFormalitiesByUserId(Long userId, int totalMovements){
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("userId", userId).addValue("total", totalMovements);
		return this.queryForList(UserMovement.class, queryContainer.getSQL(SQLConstants.GET_CREATED_FORMALITIES_BY_USER), namedParameters, new UserMovementMapper());
	}
	
	public List<UserMovement> getMovementUserActions(Long userId, int totalMovements){
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("userId", userId).addValue("total", totalMovements);
		return this.queryForList(UserMovement.class, queryContainer.getSQL(SQLConstants.GET_USER_MOVEMENT_ACTIONS), namedParameters, new UserMovementMapper());
	}
	
	@Override
	public List<District> getDistrictsByUser(Long userId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
		.addValue("userId", userId);
        return this.queryForList(District.class, queryContainer.getSQL(SQLConstants.GET_DISTRICT_LIST_BY_USER), namedParameters, new DistrictMapper());
	}
	
	@Override
	public List<User> getUsersByDistrict(Long districtId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
		.addValue("districtId", districtId);
        return this.queryForList(User.class, queryContainer.getSQL(SQLConstants.GET_USER_LIST_BY_DISTRICT), namedParameters, new UserMapper());
	}
	
	@Override
	public int isPasswordExist(String password,Long idUser ){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("password", password).addValue("idUser", idUser);
		
		return this.queryForObject(Integer.class, queryContainer.getSQL(SQLConstants.IS_PASSWORD_EXIST), params);			
	}
	
	@Override
	public List<EntryDistrict> getEntryDistrict() {
		SqlParameterSource namedParameters = new MapSqlParameterSource();
        return this.queryForList(EntryDistrict.class, queryContainer.getSQL(SQLConstants.GET_ENTRY_DISTRICT), namedParameters, new FullEntryDistrictMapper());
	}

	private boolean isValidDateForCapture(Date date){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = df.format(date);
		SqlParameterSource params = new MapSqlParameterSource().addValue("currentDate", currentDate);
		return this.queryForObject(Integer.class, queryContainer.getSQL(SQLConstants.IS_VALID_DATE_FOR_CAPTURE), params) > 0;			
	}
	
	private int getActiveRequestInCapture(Long userId){
		LOG.info("Buscando tramites en etapas de captura para el usuario: " + userId);
		SqlParameterSource params = new MapSqlParameterSource().addValue("userId", userId);
		int result = this.queryForObject(Integer.class, queryContainer.getSQL(SQLConstants.GET_ACTIVE_REQUESTS_IN_CAPTURE), params);			
		LOG.info("Total de tramites en etapa de captura: " + result);
		return result;
	}
	
	public Property getPropertyByCode(String code){
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("code", code);
		Property property = this.queryForList(Property.class, queryContainer.getSQL(SQLConstants.GET_PROPERTY_BY_CODE), namedParameters , new PropertyMapper()).get(0);
		return property;
	}
	
	public boolean hasUserRole(Long userId, Long roleId){
		SqlParameterSource params = new MapSqlParameterSource().addValue("userId", userId).addValue("roleId", roleId);
		return (this.queryForObject(Integer.class, queryContainer.getSQL(SQLConstants.HAS_USER_ROLE), params) > 0);		
	}
	
	
	public List<DueDate> getActiveDueDate(){
		List<DueDate> l = baseService.findAll(DueDate.class);
		return l;
	}
		
	public Map<String, Boolean> canUserCapture(Long userId){
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		
		Property dueDateExceptionRoleProperty = this.getPropertyByCode(PropertyConstants.ROL_SALTO_REGLA_FECHA_CORTE);
		Property requestInCaptureExceptionRoleProperty = this.getPropertyByCode(PropertyConstants.ROL_SALTO_REGLA_TRAMITE_PENDIENTE);
		
		boolean isValidDate = false;
		boolean hasUserRequestInProcess = true;
		
		//pregunto si el usuario tiene el rol de excepcion de fechas de corte
		if(this.hasUserRole(userId, dueDateExceptionRoleProperty.getLongValue())){
			LOG.info("User tiene rol de excepcion de fechas de captura");
			isValidDate = true;
		//pregunto si la fecha de corte es valida
		}else{
			if(this.isValidDateForCapture(new Date())){
				LOG.info("es hoy fecha de captura correcta");
				isValidDate = true;
			}
		}
		
		//pregunto si el usuario tiene el rol de excepcion de tramites en tuberia
		if(this.hasUserRole(userId, requestInCaptureExceptionRoleProperty.getLongValue())){
			LOG.info("Usuario tiene rol de excepcion de tramites en proceso");
			hasUserRequestInProcess = false;
		//pregunto si el usuario tiene tramites en proceso en etapa de captura
		}else{
			if(this.getActiveRequestInCapture(userId) == 0){
				LOG.info("el usuario no tiene tramites en proceso");
				hasUserRequestInProcess = false;
			}
		}
		
		LOG.info("is valid date: " + isValidDate);
		LOG.info("hasUserRequestInProcess: " + hasUserRequestInProcess);
		
		result.put("isValidDate", isValidDate);
		result.put("hasUserRequestInProcess", hasUserRequestInProcess);
		result.put("canUserCapture", (isValidDate && !(hasUserRequestInProcess)) );
		return result;
	}
	
	public Map<Long, String> getValidDistrictsMapByUserId(Long userId) {
		Map<Long, String> validDistrictsMapByUser = new HashMap<Long, String>();
		
		for (District district : getValidDistrictsByUserId(userId)) {
			validDistrictsMapByUser.put(district.getId(), district.getNumber());
		}

		return validDistrictsMapByUser;
	}
	
	
	@Override
	public List<Notification> getNotificationByUserId(Long userId) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("userId", userId);
		return this.queryForList(Notification.class, queryContainer.getSQL(SQLConstants.GET_NOTIFICATIONS_BY_USER), params, new NotificationMapper());
	}
	
	@Override
	public List<District> getDistricts() {
        return this.queryForList(District.class, queryContainer.getSQL(SQLConstants.GET_LIST_DISTRICTS),null, new DistrictMapper());
	}
	
	@Override
	public List<User> getUsers() {
        return this.queryForList(User.class, queryContainer.getSQL(SQLConstants.GET_LIST_USERS), null, new UserMapper());
	}
	
	@Override
	public List<Entry> getEntries() {
        return this.queryForList(Entry.class, queryContainer.getSQL(SQLConstants.GET_LIST_ENTRIES), null, new FullEntryMapper());
	}
	
	@Override
	public List<District> getDistrictsByNotice(Long noticeId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource()
		.addValue("noticeId", noticeId);
        return this.queryForList(District.class, queryContainer.getSQL(SQLConstants.GET_DISTRICT_LIST_BY_NOTICE), namedParameters, new DistrictMapper());
	}

}
