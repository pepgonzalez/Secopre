package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import ideasw.secopre.model.security.Permission;

public class PermissionMapper implements RowMapper<Object> {    
	 
	public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {    
		Permission permission = new Permission();
		permission.setId(rs.getLong("PERMISSION_ID"));
		permission.setName(rs.getString("NAME"));
		return permission;   
	 }    
} 