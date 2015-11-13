package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import ideasw.secopre.dto.UserMovement;

public class UserMovementMapper implements RowMapper<Object> {    
	 
	public UserMovement mapRow(ResultSet rs, int rowNum) throws SQLException {    
		UserMovement userMovement = new UserMovement();
		
		userMovement.setFolio(rs.getString("FOLIO"));
		userMovement.setMovementType(rs.getString("MOVEMENT_TYPE"));
		userMovement.setJustification(rs.getString("JUSTIFICATION"));
		userMovement.setComments(rs.getString("COMMENTS"));
		userMovement.setFormality(rs.getString("FORMALITY"));
		userMovement.setCreationDate(rs.getDate("CREATION_DATE"));
		userMovement.setCurrentStatus(rs.getString("CURRENT_STATUS"));
		
		return userMovement; 
	 }    
} 