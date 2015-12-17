package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ideasw.secopre.dto.EntryCurrentTotal;
import ideasw.secopre.dto.Formality;

public class EntryCurrentTotalMapper implements RowMapper<Object> {    
	 
	public EntryCurrentTotal mapRow(ResultSet rs, int rowNum) throws SQLException {    
		EntryCurrentTotal t = new EntryCurrentTotal();

		t.setDistrictId(rs.getLong("DISTRICT_ID"));
		t.setDistrictNumber(rs.getString("NUMBER"));
		t.setState(rs.getString("NAME"));
		t.setEntryId(rs.getLong("ENTRY_ID"));
		t.setEntryDescription(rs.getString("DESCRIPTION"));
		t.setTotalAmount(rs.getDouble("ANNUAL_AMOUNT"));
		
		t.setEnero(rs.getDouble("ENERO"));
		t.setFebrero(rs.getDouble("FEBRERO"));
		t.setMarzo(rs.getDouble("MARZO"));
		t.setAbril(rs.getDouble("ABRIL"));
		t.setMayo(rs.getDouble("MAYO"));
		t.setJunio(rs.getDouble("JUNIO"));
		t.setJulio(rs.getDouble("JULIO"));
		t.setAgosto(rs.getDouble("AGOSTO"));
		t.setSeptiembre(rs.getDouble("SEPTIEMBRE"));
		t.setOctubre(rs.getDouble("OCTUBRE"));
		t.setNoviembre(rs.getDouble("NOVIEMBRE"));
		t.setDiciembre(rs.getDouble("DICIEMBRE"));
		
		return t;   
	 }    
} 