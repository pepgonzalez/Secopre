package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.model.Notice;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class NoticeMapper implements RowMapper<Object> {    
	 
	public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {    
		Notice notice = new Notice();
		notice.setNoticeInfo(rs.getString("NOTICE"));
		 return notice;   
	 }    
} 
