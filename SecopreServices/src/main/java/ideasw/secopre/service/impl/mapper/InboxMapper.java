package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.dto.Inbox;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class InboxMapper implements RowMapper<Object> {    
	 
	public Inbox mapRow(ResultSet rs, int rowNum) throws SQLException {    
		 Inbox inbox = new Inbox();
		 	inbox.setRequestId(rs.getLong("ID"));
		 	inbox.setFolio(rs.getString("FOLIO"));
		 	inbox.setJustification(rs.getString("JUSTIFICATION"));
		 	inbox.setResourcePath(rs.getString("RESOURCE_PATH"));
		 	inbox.setDistrictId(rs.getLong("DISTRICT"));
		 	inbox.setDistrictDescription(rs.getString("DISTRICT_DESCRIPTION"));
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
		 	inbox.setRequestFinished((rs.getInt("IS_END_WORKFLOW") == 1));
		 	inbox.setCaptureForm(rs.getString("CAPTURE_FORM"));
		 	inbox.setStatusId(rs.getLong("STATUS_ID"));
		 	inbox.setNextDescription(rs.getString("NEXT_DESCRIPTION"));
		 	inbox.setTotalAmount(rs.getFloat("TOTAL_AMOUNT"));
		 	inbox.setCreationDate(rs.getDate("CREATION_DATE"));
		 	inbox.setCanceled(rs.getBoolean("IS_CANCELED"));
		 	inbox.setOperated(rs.getBoolean("IS_OPERATED"));
			return inbox;
	 }    
} 