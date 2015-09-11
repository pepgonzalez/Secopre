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
import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.security.User;
import ideasw.secopre.service.AccessNativeService;
import ideasw.secopre.sql.QueryContainer;
import ideasw.secopre.sql.SQLConstants;

@Service
public class AccessNativeServiceImpl implements AccessNativeService{

	@Autowired
	QueryContainer queryContainer;
	
	@Autowired
	SecopreJdbcTemplate sql;

	@Override
	public List<Formality> getFormalityAvailableByUser(User user) {
		final List<Formality> result = new ArrayList<Formality>();
		
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("userId", user.getId());
	
		sql.getNamedParameterJdbcTemplate().query(queryContainer.getSQL(SQLConstants.GET_FORMALITY_FROM_USER_ID), namedParameters,
				new RowMapper<Object>(){
					
					public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
						Formality formality = new Formality();
						formality.setFormalityId(rs.getLong("FORMALITY_ID"));
						formality.setDescription(rs.getString("DESCRIPTION"));
						formality.setWorkFlowId(rs.getLong("WORKFLOW_ID"));
						
						result.add(formality);
						
						return formality;
					}
					
				});
	
		return result;
	}

	public Long startFormality(Request request) {
		Long requestId = this.insertRequest(request);
		request.setRequestId(requestId);
		//se obtiene la configuracion del tramite
		Formality formality = this.getFormalityById(request.getFormalityId());
		
		//se guarda la configuracion del request
		
		//se inicia la historia del tramite
		
		return requestId;
	}
	
	
	public Formality getFormalityById(Long formalityId){
		
		final List<Formality> formalityList = new ArrayList<Formality>();
		
		SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("formalityId", formalityId);
		
		sql.getNamedParameterJdbcTemplate().query(queryContainer.getSQL(SQLConstants.GET_FORMALITY_BY_ID), namedParameters,
				new RowMapper<Object>(){
					
					public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
						Formality formality = new Formality();
						formality.setFormalityId(rs.getLong("ID"));
						formality.setDescription(rs.getString("DESCRIPTION"));
						formality.setWorkFlowId(rs.getLong("WORKFLOW_ID"));
						
						formalityList.add(formality);
						
						return formality;
					}
					
				});
	
		return formalityList.get(0);
	}
	
	//privados mientras son requeridos en otra capa
	private Long insertRequest(Request request){
		
		Map<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("FIRST_NAME", request.getFirstName());
	    parameters.put("PARENT_LAST_NAME", request.getParentLastName());
	    parameters.put("MOTHER_LAST_NAME", request.getMotherLastName());
	    parameters.put("LAST_UPDATE", new Date());
	    parameters.put("ACTIVE", 1);
	    
		Number id = sql.getSimpleJdbcInsert("REQUEST", "ID").executeAndReturnKey(parameters);
		return new Long(id.longValue());
	}

}
