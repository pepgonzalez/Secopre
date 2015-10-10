package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.dto.WorkFlowConfig;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class WorkFlowConfigMapper implements RowMapper<Object> {    
	 
	public WorkFlowConfig mapRow(ResultSet rs, int rowNum) throws SQLException {    
		WorkFlowConfig workFlowConfig = new WorkFlowConfig();
		 	
		workFlowConfig.setWorkFlowConfigId(rs.getLong("ID"));
		workFlowConfig.setWorkFlowId(rs.getLong("WORKFLOW_ID"));
		workFlowConfig.setStageConfigId(rs.getLong("STAGE_CONFIG_ID"));
		workFlowConfig.setWfConfigCode(rs.getString("WF_CFG_CODE"));
		workFlowConfig.setNextStageConfig(rs.getLong("NEXT_STAGE_CONFIG"));
		workFlowConfig.setStatusId(rs.getLong("STATUS_ID"));
		workFlowConfig.setLastUpdate(rs.getDate("LAST_UPDATE"));
		workFlowConfig.setActive(rs.getBoolean("ACTIVE"));
		 
		 return workFlowConfig;   
	 }    
} 