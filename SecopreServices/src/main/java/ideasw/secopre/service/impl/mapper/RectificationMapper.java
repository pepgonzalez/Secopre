package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ideasw.secopre.dto.Rectification;
import ideasw.secopre.model.catalog.Person;
import ideasw.secopre.model.security.User;

public class RectificationMapper implements RowMapper<Object> {    
	 
	public Rectification mapRow(ResultSet rs, int rowNum) throws SQLException {    
		Rectification r = new Rectification();
	
		r.setRequestId(rs.getLong("ID"));
		r.setFolio(rs.getString("FOLIO"));
		r.setJustification(rs.getString("JUSTIFICATION"));
		r.setFormalityDescription(rs.getString("DESCRIPTION"));
		r.setCreationDate(rs.getDate("LAST_UPDATE"));
		r.setTotalAmount(rs.getDouble("TOTAL"));
		r.setUserName(rs.getString("USERNAME"));
		r.setHasRectification(rs.getBoolean("HAS_RECTIFICATION"));
		r.setRectificationFolio(rs.getString("RECTIFICATED_FOLIO"));
		r.setCertifiedAccount(rs.getString("CERTIFIED_ACCOUNT"));
		
		return r;   
	 }    
} 
