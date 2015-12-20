package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ideasw.secopre.dto.Formality;

public class FormalityMapper implements RowMapper<Object> {    
	 
	public Formality mapRow(ResultSet rs, int rowNum) throws SQLException {    
		 Formality formality = new Formality();
			formality.setFormalityId(rs.getLong("FORMALITY_ID"));
			formality.setDescription(rs.getString("DESCRIPTION"));
			formality.setWorkFlowId(rs.getLong("WORKFLOW_ID"));
			formality.setAuthorizationId(rs.getLong("AUTHORIZATION_ID"));
			formality.setCode(rs.getString("CODE"));
			formality.setCreateValidation(rs.getBoolean("CREATE_VALIDATION"));
			formality.setProcessAfterCreation(rs.getString("PROCESS_AFTER_CREATION"));
			return formality;   
	 }    
} 