package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ideasw.secopre.dto.Movement;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.Entry;

public class EntryMapper implements RowMapper<Object> {    
	 
	public Entry mapRow(ResultSet rs, int rowNum) throws SQLException {    
		Entry entry = new Entry();
		 entry.setId(rs.getLong("ID"));
		 entry.setName(rs.getString("NAME"));
		 return entry;   
	 }    
} 