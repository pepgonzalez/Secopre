package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ideasw.secopre.dto.Request;

public class RequestMapper implements RowMapper<Object> {    
	 
	public Request mapRow(ResultSet rs, int rowNum) throws SQLException {    
		 Request request = new Request();
		 request.setRequestId(rs.getLong("REQUEST_ID"));
		 request.setFolio(rs.getString("FOLIO"));
		 request.setDistrictId(rs.getLong("DISTRICT_ID"));
		 request.setJustification(rs.getString("JUSTIFICATION"));
		 request.setResourcePath(rs.getString("RESOURCE_PATH"));
		 request.setMovementTypeId(rs.getLong("MOVEMENT_TYPE_ID"));
		 //request.setFirstName(rs.getString("FIRST_NAME"));
		 //request.setParentLastName(rs.getString("PARENT_LAST_NAME"));
		 //request.setMotherLastName(rs.getString("MOTHER_LAST_NAME"));
		 //request.setMovementName(rs.getString("MOVEMENT_NAME"));
		 //request.setMovementPrice(rs.getFloat("MOVEMENT_PRICE"));
		 return request;   
	 }    
} 