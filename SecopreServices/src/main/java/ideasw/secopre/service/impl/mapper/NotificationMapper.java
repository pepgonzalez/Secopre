package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.dto.Notification;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class NotificationMapper implements RowMapper<Object> {    
	 
	public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {    
		Notification notification = new Notification();
		
		notification.setUserId(rs.getLong("USER_ID"));
		notification.setNotificationType(rs.getInt("TYPE"));
		notification.setMessage(rs.getString("MESSAGE"));
		notification.setStatus(rs.getInt("STATUS"));
		notification.setTransitionId(rs.getLong("TRANSITION_ID"));
		
		return notification;   
	 }    
} 