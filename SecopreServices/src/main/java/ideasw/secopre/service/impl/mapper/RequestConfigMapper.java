package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.dto.RequestConfig;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RequestConfigMapper implements RowMapper<Object> {    
	 
	public RequestConfig mapRow(ResultSet rs, int rowNum) throws SQLException {    
		 RequestConfig requestConfig = new RequestConfig();
		 requestConfig.setRequestId(rs.getLong("REQUEST_ID"));
		 requestConfig.setFormalityId(rs.getLong("FORMALITY_ID"));
		 requestConfig.setWorkFlowId(rs.getLong("WORKFLOW_ID"));
		 requestConfig.setAuthorizationId(rs.getLong("AUTHORIZATION_ID"));
		 requestConfig.setLastUpdate(rs.getDate("LAST_UPDATE"));
		 requestConfig.setActive(rs.getBoolean("ACTIVE"));
		 return requestConfig;
	 }    
} 