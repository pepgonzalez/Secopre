package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.model.Entry;
import ideasw.secopre.model.ProgrammaticKey;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EntryMapper implements RowMapper<Object> {    
	 
	public Entry mapRow(ResultSet rs, int rowNum) throws SQLException {    
		Entry entry = new Entry();
		 entry.setId(rs.getLong("ID"));
		 entry.setName(rs.getString("NAME"));
		 entry.setCode(rs.getInt("CODE"));
		 
		 ProgrammaticKey pk = new ProgrammaticKey();
		 pk.setId(rs.getLong("PROGRAMMATIC_ID"));
		 
		 entry.setProgrammaticKey(pk);
		 
		 Entry concept = new Entry();
		 concept.setId(rs.getLong("ENTRY_CONCEPT_ID"));
		 
		 return entry;   
	 }    
} 