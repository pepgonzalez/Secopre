package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.enums.Month;
import ideasw.secopre.model.Entry;
import ideasw.secopre.model.EntryDistrict;
import ideasw.secopre.model.ProgrammaticKey;
import ideasw.secopre.model.catalog.District;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FullEntryDistrictMapper implements RowMapper<Object> {    
	 
	public EntryDistrict mapRow(ResultSet rs, int rowNum) throws SQLException {    
		
		EntryDistrict district = new EntryDistrict();
		
		District d = new District();
		d.setId(rs.getLong("DISTRICT_ID"));
		d.setNumber(rs.getString("NUMBER"));
		
		district.setDistrict(d);
		
		ProgrammaticKey pk = new ProgrammaticKey();
		pk.setId(rs.getLong("PROGRAMATIC_KEY_ID"));
		pk.setCode(rs.getString("CODE"));
		pk.setYear(rs.getInt("YEAR"));
		
		district.setProgrammaticKey(pk);
		
		Entry e = new Entry();
		e.setId(rs.getLong("ENTRY_ID"));
		e.setDescription(rs.getString("DESCRIPTION"));
		
		district.setEntry(e);
		
		district.setMonth(rs.getLong("MONTH"));
		
		String month = Month.values()[district.getMonth().intValue()].name();
		district.setMonthString(month);
		
		district.setAnnualAmount(rs.getDouble("ANNUAL_AMOUNT"));
		district.setBudgetAmount(rs.getDouble("BUDGET_AMOUNT"));
		district.setBudgetAmountAssign(rs.getDouble("BUDGET_AMOUNT_ASSIGN"));
		district.setCommittedAmount(rs.getDouble("COMMITTED_AMOUNT"));
		
		return district;   
	 }    
} 
