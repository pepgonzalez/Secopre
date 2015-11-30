package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.model.Entry;
import ideasw.secopre.model.ProgrammaticKey;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FullEntryMapper implements RowMapper<Object> {    
	 
	public Entry mapRow(ResultSet rs, int rowNum) throws SQLException {    
		Entry entry = new Entry();
		entry.setId(rs.getLong("ID"));
	    entry.setName(rs.getString("NAME"));
		entry.setCode(rs.getInt("CODE"));
		entry.setActivo(rs.getBoolean("ACTIVE"));
		entry.setDescription(rs.getString("DESCRIPTION"));
		ProgrammaticKey pk = new ProgrammaticKey();
		pk.setId(rs.getLong("PROGRAMMATIC_ID"));
		pk.setCode(rs.getString("CODE"));
	    pk.setActivity(rs.getString("ACTIVITY"));
        pk.setFinality(rs.getString("FINALITY"));	
        pk.setFunction(rs.getString("FUNCTION"));
        pk.setSubfunction(rs.getString("SUBFUNCTION"));
        pk.setProgramBudget(rs.getString("PROGRAM_BUDGET"));
        pk.setUnitResponsable(rs.getString("UNIT_RESPONSABLE"));
        pk.setDescription(rs.getString("DESCRIPTION_PK"));
        entry.setProgrammaticKey(pk);

		 return entry;   
	 }    
} 