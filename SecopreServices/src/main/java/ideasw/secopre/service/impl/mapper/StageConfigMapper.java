package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.dto.StageConfig;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StageConfigMapper implements RowMapper<Object> {    
	 
	public StageConfig mapRow(ResultSet rs, int rowNum) throws SQLException {    
		StageConfig dto = new StageConfig();
		 
		 dto.setStageConfigId(rs.getLong("ID"));
		 dto.setStageId(rs.getLong("STAGE_ID"));
		 dto.setPathId(rs.getLong("PATH_ID"));
		 dto.setCapture(rs.getBoolean("IS_CAPTURE"));
		 dto.setAuthorization(rs.getBoolean("IS_AUTHORIZATION"));
		 dto.setCaptureForm(rs.getString("CAPTURE_FORM"));
		 dto.setCanceled(rs.getBoolean("IS_CANCELED"));
		 dto.setOperated(rs.getBoolean("IS_OPERATED"));
		 dto.setLastUpdate(rs.getDate("LAST_UPDATE"));
		 dto.setActive(rs.getBoolean("ACTIVE"));
		
		 return dto;   
	 }    
} 