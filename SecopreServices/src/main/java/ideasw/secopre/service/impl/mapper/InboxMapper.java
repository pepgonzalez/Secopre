package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;

public class InboxMapper implements RowMapper<Object> {    
	 
	public Inbox mapRow(ResultSet rs, int rowNum) throws SQLException {    
		 Inbox inbox = new Inbox();
		 	inbox.setRequestId(rs.getLong("ID"));
		 	inbox.setName(rs.getString("NAME"));
		 	inbox.setFormalityId(rs.getLong("FORMALITY_ID"));
		 	inbox.setFormalityDescription(rs.getString("FORMALITY_DESCRIPTION"));
		 	inbox.setWorkFlowConfigId(rs.getLong("WORKFLOW_CONFIG_ID"));
		 	inbox.setStageConfigId(rs.getLong("STAGE_CONFIG_ID"));
		 	inbox.setDescription(rs.getString("DESCRIPTION"));
		 	inbox.setNextStageConfig(rs.getLong("NEXT_STAGE_CONFIG"));
		 	inbox.setPathId(rs.getLong("PATH_ID"));
		 	inbox.setUrl(rs.getString("URL"));
		 	inbox.setCapture(rs.getBoolean("IS_CAPTURE"));
		 	inbox.setAuthorization(rs.getBoolean("IS_AUTHORIZATION"));
		 	inbox.setCaptureForm(rs.getString("CAPTURE_FORM"));
		 	inbox.setStatusId(rs.getLong("STATUS_ID"));
		 	inbox.setNextDescription(rs.getString("NEXT_DESCRIPTION"));
			return inbox;
	 }    
} 