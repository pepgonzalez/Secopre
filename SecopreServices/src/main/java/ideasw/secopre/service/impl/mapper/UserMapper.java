package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import ideasw.secopre.model.security.User;

public class UserMapper implements RowMapper<Object> {    
	 
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {    
		User user = new User();
		user.setId(rs.getLong("ID"));
		user.setUsername(rs.getString("USERNAME"));
		return user;   
	 }    
} 