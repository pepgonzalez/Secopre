package ideasw.secopre.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import ideasw.secopre.dao.impl.SecopreJdbcTemplate;
import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Request;
import ideasw.secopre.dto.RequestConfig;
import ideasw.secopre.dto.WorkFlowConfig;
import ideasw.secopre.enums.WorkFlowCode;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.service.impl.mapper.FormalityMapper;
import ideasw.secopre.service.impl.mapper.InboxMapper;
import ideasw.secopre.service.impl.mapper.RequestConfigMapper;
import ideasw.secopre.service.impl.mapper.RequestMapper;
import ideasw.secopre.service.impl.mapper.WorkFlowConfigMapper;
import ideasw.secopre.sql.QueryContainer;
import ideasw.secopre.sql.SQLConstants;

@Service
public class AccessNativeServiceImpl extends AccessNativeServiceBaseImpl implements AccessNativeService{

	@Autowired
	QueryContainer queryContainer;

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
	public Long startFormality(Request request, Long userId) {
		request.setRequestId(this.insertRequest(request));
		this.insertOrUpdateRequestDetail(request);

		Formality formality = this.getFormalityById(request.getFormalityId());
		this.insertRequestConfig(request.getRequestId(), formality);
		
		this.invokeNextStage(request.getRequestId(), WorkFlowCode.SOLCOMP.name() , -1L,  userId);
		
		return request.getRequestId();
	}
	
	/*
	 * Proceso para obtener el listado de tramites en proceso por usuario
	 * */
	public List<Inbox> getInboxByUserId(Long userId){
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("userId", userId);	
		return this.queryForList(Inbox.class, queryContainer.getSQL(SQLConstants.GET_FORMALITY_INBOX), namedParameters, new InboxMapper());
	}
	
	
	public void invokeNextStage(Request request, Long userId){
		this.invokeNextStage(request.getRequestId(), request.getNextStageValueCode(), request.getStageConfigId(), userId);
	}
	
	
	
	/*
	 * Proceso para avanzar un tramite de etapa
	 * */
	private void invokeNextStage(Long requestId, String valueCode, Long stageConfigId,  Long userId){
			
		int consecutive = this.getNextConsecutive(requestId);
	
		if(consecutive == 1){
			WorkFlowConfig transition = this.getRequestFirstWorkFlowConfig(requestId, valueCode).get(0);
			this.insertTransition(requestId, transition, consecutive, userId);
		}else{
			WorkFlowConfig transition = this.getRequestWorkFlowConfig(requestId, valueCode, stageConfigId).get(0);
			this.inactivateActiveStage(requestId);
			this.insertTransition(requestId, transition, consecutive, userId);
		}
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
	
	public int insertOrUpdateRequestDetail(Request request){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("requestId", request.getRequestId())
				.addValue("movementName", request.getMovementName())
				.addValue("movementPrice", request.getMovementPrice());
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.INSERT_OR_UPDATE_REQUEST_DETAIL), params);
	}
	
	private int insertTransition(Long requestId, WorkFlowConfig config, int consecutive, Long userId){
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("requestId", requestId)
				.addValue("consecutive", consecutive)
				.addValue("workFlowConfigId",config.getWorkFlowConfigId())
				.addValue("userId", userId);
		
		return this.insertOrUpdate(queryContainer.getSQL(SQLConstants.INSERT_REQUEST_HISTORY), params);
	}

	@Override
	public Request getRequestById(Long requestId) {
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("requestId", requestId);
		List<Request> list = this.queryForList(Request.class, queryContainer.getSQL(SQLConstants.GET_REQUEST_BY_ID), namedParameters, new RequestMapper());
		return list.get(0);
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

}
