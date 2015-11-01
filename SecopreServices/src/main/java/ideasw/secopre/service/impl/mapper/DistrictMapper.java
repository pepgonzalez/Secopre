package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ideasw.secopre.dto.Movement;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.Entry;
import ideasw.secopre.model.catalog.District;

public class DistrictMapper implements RowMapper<Object> {    
	 
	public District mapRow(ResultSet rs, int rowNum) throws SQLException {    
		District district = new District();
		district.setId(rs.getLong("ID"));
		district.setNumber(rs.getString("DESCRIPTION"));
		 return district;   
	 }    
} 