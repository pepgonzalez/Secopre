package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ideasw.secopre.model.catalog.Person;
import ideasw.secopre.model.security.User;

public class UserMapper implements RowMapper<Object> {    
	 
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {    
		User user = new User();
		user.setId(rs.getLong("ID"));
		user.setUsername(rs.getString("USERNAME"));
		user.setActive(rs.getBoolean("ACTIVE"));
		Person person = new Person();
		person.setId(rs.getLong("PERSON_ID"));
		user.setPerson(person);
		user.setAvatar(rs.getString("AVATAR"));
		user.setEmail(rs.getString("EMAIL"));
		user.setHasChatActive(rs.getBoolean("HAS_CHAT_ACTIVE"));
		user.setNickname(rs.getString("NICKNAME"));
		user.setPassword(rs.getString("PASSWORD"));
		
		return user;   
	 }    
} 
