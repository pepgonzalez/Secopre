package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.model.EntryDistrict;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EntryDistrictMapper implements RowMapper<Object> {    
	 
	public EntryDistrict mapRow(ResultSet rs, int rowNum) throws SQLException {    
		EntryDistrict entryD = new EntryDistrict();
		 entryD.setBudgetAmount(rs.getDouble("BUDGET_AMOUNT"));
		 entryD.setBudgetAmountAssign(rs.getDouble("BUDGET_AMOUNT_ASSIGN"));
		 entryD.setCommittedAmount(rs.getDouble("COMMITTED_AMOUNT"));
		 return entryD;   
	 }    
} 