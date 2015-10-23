package ideasw.secopre.service.impl;

import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Movement;
import ideasw.secopre.dto.Request;
import ideasw.secopre.dto.RequestConfig;
import ideasw.secopre.dto.RequestHistory;
import ideasw.secopre.dto.StageConfig;
import ideasw.secopre.dto.WorkFlowConfig;
import ideasw.secopre.enums.Month;
import ideasw.secopre.enums.WorkFlowCode;
import ideasw.secopre.exception.EntryDistrictException;
import ideasw.secopre.model.Entry;
import ideasw.secopre.model.EntryDistrict;
import ideasw.secopre.model.ProgrammaticKey;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.catalog.MovementType;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.AccessService;
import ideasw.secopre.service.BaseService;
import ideasw.secopre.service.impl.mapper.EntryMapper;
import ideasw.secopre.service.impl.mapper.FormalityMapper;
import ideasw.secopre.service.impl.mapper.InboxMapper;
import ideasw.secopre.service.impl.mapper.RequestConfigMapper;
import ideasw.secopre.service.impl.mapper.RequestHistoryMapper;
import ideasw.secopre.service.impl.mapper.RequestMapper;
import ideasw.secopre.service.impl.mapper.StageConfigMapper;
import ideasw.secopre.service.impl.mapper.MovementMapper;
import ideasw.secopre.service.impl.mapper.WorkFlowConfigMapper;
import ideasw.secopre.sql.QueryContainer;
import ideasw.secopre.sql.SQLConstants;
import sun.util.calendar.BaseCalendar.Date;

import java.util.ArrayList;
import java.util.Calendar;
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
		
		this.invokeNextStage(request.getRequestId(), WorkFlowCode.SOLCOMP.name() , -1L,  userId, request.getComments());
		
		return request.getRequestId();
	}
	
	/*
	 * Proceso para obtener el listado de tramites en proceso por usuario
	 * */
	public List<Inbox> getInboxByUserId(Long userId){
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("userId", userId);	
		return this.queryForList(Inbox.class, queryContainer.getSQL(SQLConstants.GET_FORMALITY_INBOX), namedParameters, new InboxMapper());
	}
	
	public List<RequestHistory> getRequestHistory(Long requestId){
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("requestId", requestId);	
		return this.queryForList(RequestHistory.class, queryContainer.getSQL(SQLConstants.GET_REQUEST_HISTORY), namedParameters, new RequestHistoryMapper());
	}
	
	
	public void invokeNextStage(Request request, Long userId){
		this.invokeNextStage(request.getRequestId(), request.getNextStageValueCode(), request.getStageConfigId(), userId, request.getComments());
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
	private void invokeNextStage(Long requestId, String valueCode, Long stageConfigId,  Long userId, String comments){
			
		int consecutive = this.getNextConsecutive(requestId);
	
		if(consecutive == 1){
			WorkFlowConfig transition = this.getRequestFirstWorkFlowConfig(requestId, valueCode).get(0);
			this.insertTransition(requestId, transition, consecutive, userId, comments);
		}else{
			WorkFlowConfig transition = this.getRequestWorkFlowConfig(requestId, valueCode, stageConfigId).get(0);
			
			//validar si la siguiente etapa es operacion o cancelacion del tramite
			StageConfig next = this.getStageConfigById(transition.getNextStageConfig());
			LOG.info("Siguiente etapa:" + next);
			
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
				.addValue("resourcePath", request.getResourcePath());
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.INSERT_OR_UPDATE_REQUEST), params);
	}
	
	@Transactional(readOnly = false, rollbackFor=EntryDistrictException.class)
	public int insertOrUpdateRequestDetail(Request request) throws Exception{

		Request baseRequest = this.getRequestById(request.getRequestId());
		baseRequest.setMovementTypeId(request.getMovementTypeId());
		request.setDistrictId(baseRequest.getDistrictId());
		
		LOG.info("Actualizando request");
		this.insertOrUpdateRequest(baseRequest);
		LOG.info("Request Actualizado");
		
		
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

	private int cleanRequestDetail(Long requestId){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("requestId", requestId);
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.CLEAN_REQUEST_DETAIL), params);
	}
	
	
	private void insertMovementList(List<Movement> list, Request request) throws Exception{
				
		LOG.info("Cantidad de movimientos a actualizar: " + list.size());
		LOG.info("Tipo de guardado: " + request.getNextStageValueCode());
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
				
				
				//inserta de nuevo el registro
				Long id =  this.insertAndReturnId(Movement.TABLE_NAME, Movement.PRIMARY_KEY, m.getParams(request.getRequestId()));
				m.setRequestDetailId(id);
			}
		}
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
		return r;
	}

	public List<Movement> getRequestDetailByRequestId(Long requestId){
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("requestId", requestId);
		List<Movement> list = this.queryForList(Movement.class, queryContainer.getSQL(SQLConstants.GET_REQUEST_DETAIL), namedParameters, new MovementMapper());	
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
			map.put(e.getId(), e.getName());
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
		return list;
	}

}
