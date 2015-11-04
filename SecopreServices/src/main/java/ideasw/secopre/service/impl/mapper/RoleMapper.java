package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import ideasw.secopre.model.security.Role;

public class RoleMapper implements RowMapper<Object> {    
	 
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {    
		Role role = new Role();
		role.setId(rs.getLong("ROLE_ID"));
		role.setRolename(rs.getString("ROLENAME"));
		return role;   
	 }    
} 