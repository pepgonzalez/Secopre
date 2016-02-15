package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ideasw.secopre.model.Director;
import ideasw.secopre.model.catalog.Person;
import ideasw.secopre.model.catalog.Position;
import ideasw.secopre.model.security.User;

public class DirectorMapper implements RowMapper<Object> {    
	 
	public Director mapRow(ResultSet rs, int rowNum) throws SQLException {    
		Director director = new Director();
		User user = new User();	
		Position position = new Position();
		user.setId(rs.getLong("ID"));
		user.setUsername(rs.getString("USERNAME"));
		user.setActive(rs.getBoolean("ACTIVE"));
		Person person = new Person();
		person.setId(rs.getLong("PERSON_ID"));
		person.setName(rs.getString("NAME"));
		person.setFatherLastName(rs.getString("FATHER_LASTNAME"));
		person.setMotherLastName(rs.getString("MOTHER_LASTNAME"));
		person.setId(rs.getLong("PERSON_ID"));
		user.setPerson(person);
		user.setAvatar(rs.getString("AVATAR"));
		user.setEmail(rs.getString("EMAIL"));
		user.setHasChatActive(rs.getBoolean("HAS_CHAT_ACTIVE"));
		user.setNickname(rs.getString("NICKNAME"));
		user.setPassword(rs.getString("PASSWORD"));
		position.setId(rs.getLong("POSITION_ID"));
		position.setName(rs.getString("PUESTO_DESCRIPCION"));
		user.setPosition(position);
		director.setUser(user);
		
		
		
		return director;   
	 }    
} 
