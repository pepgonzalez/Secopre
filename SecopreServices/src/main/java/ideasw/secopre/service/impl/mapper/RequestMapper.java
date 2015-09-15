package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ideasw.secopre.dto.Request;

public class RequestMapper implements RowMapper<Object> {    
	 
	public Request mapRow(ResultSet rs, int rowNum) throws SQLException {    
		 Request request = new Request();
		 request.setRequestId(rs.getLong("REQUEST_ID"));
		 request.setFirstName(rs.getString("FIRST_NAME"));
		 request.setParentLastName(rs.getString("PARENT_LAST_NAME"));
		 request.setMotherLastName(rs.getString("MOTHER_LAST_NAME"));
		 return request;   
	 }    
} 