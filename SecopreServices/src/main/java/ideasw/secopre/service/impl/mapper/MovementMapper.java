package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.dto.Movement;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MovementMapper implements RowMapper<Object> {    
	 
	public Movement mapRow(ResultSet rs, int rowNum) throws SQLException {    
		 Movement mov = new Movement();
		 mov.setRequestDetailId(rs.getLong("ID"));
		 mov.setMovementTypeId(rs.getLong("MOVEMENT_TYPE_ID"));
		 mov.setRequestId(rs.getLong("REQUEST_ID"));
		 mov.setProgramaticKeyId(rs.getLong("PROGRAMATIC_KEY_ID"));
		 mov.setEntryId(rs.getLong("ENTRY_ID"));
		 mov.setInitialMonthId(rs.getInt("INITIAL_MONTH"));
		 mov.setFinalMonthId(rs.getInt("FINAL_MONTH"));
		 mov.setMonthAmountValue(rs.getDouble("MONTH_AMOUNT"));
		 mov.setTotalAmountValue(rs.getDouble("TOTAL_AMOUNT"));
		 mov.setIsSaved(true);
		 return mov;   
	 }    
} 