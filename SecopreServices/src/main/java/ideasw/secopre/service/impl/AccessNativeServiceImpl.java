package ideasw.secopre.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import ideasw.secopre.dao.impl.SecopreJdbcTemplate;
import ideasw.secopre.dto.Formality;
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

}
