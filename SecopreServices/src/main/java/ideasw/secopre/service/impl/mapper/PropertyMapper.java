package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.dto.Property;
import ideasw.secopre.dto.Report;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PropertyMapper implements RowMapper<Object> {    
	 
	public Property mapRow(ResultSet rs, int rowNum) throws SQLException {    
		Property p = new Property();
		
		p.setId(rs.getLong("ID"));
		p.setCode(rs.getString("CODE"));
		p.setValue(rs.getString("VALUE"));
		p.setDescription(rs.getString("DESCRIPTION"));
		
		return p;   
	 }    
} 