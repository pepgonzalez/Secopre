package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.model.EntryDistrict;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EntryDistrictMapper implements RowMapper<Object> {    
	 
	public EntryDistrict mapRow(ResultSet rs, int rowNum) throws SQLException {    
		EntryDistrict entryD = new EntryDistrict();
		 Double amount = rs.getDouble("BUDGET_AMOUNT");
		 entryD.setBudgetAmount(amount== null?0D:amount);
		 Double amount2 = rs.getDouble("BUDGET_AMOUNT_ASSIGN");
		 entryD.setBudgetAmountAssign(amount2== null?0D:amount2);
		 Double amount3 = rs.getDouble("COMMITTED_AMOUNT");
		 entryD.setCommittedAmount(amount3== null?0D:amount3);
		 return entryD;   
	 }    
} 