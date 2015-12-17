package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.dto.RequestHistory;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RequestHistoryMapper implements RowMapper<Object> {    
	 
	public RequestHistory mapRow(ResultSet rs, int rowNum) throws SQLException {    
		RequestHistory dto = new RequestHistory();
		 	dto.setRequestId(rs.getLong("REQUEST_ID"));
		 	dto.setConsecutive(rs.getLong("CONSECUTIVE"));
		 	dto.setInitialStage(rs.getString("ETAPA_INICIAL"));
		 	dto.setStageConfigId(rs.getLong("STAGE_CONFIG_ID"));
		 	dto.setLastAuthorization(rs.getBoolean("IS_LAST_AUTHORIZATION"));
		 	dto.setFinalStage(rs.getString("SIGUIENTE_ETAPA"));
		 	dto.setNextStageConfig(rs.getLong("NEXT_STAGE_CONFIG"));
		 	dto.setCurrentAuthorization(rs.getBoolean("IS_CURRENT_AUTHORIZATION"));
		 	dto.setStatus(rs.getString("ESTATUS"));
		 	dto.setCreationDate(rs.getDate("FECHA_CREACION"));
		 	dto.setComments(rs.getString("COMMENTS"));
		 	dto.setUsername(rs.getString("USERNAME"));
			return dto;
	 }    
} 