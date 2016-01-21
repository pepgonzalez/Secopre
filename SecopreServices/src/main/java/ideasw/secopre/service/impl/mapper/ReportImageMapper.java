package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.dto.ReportParameter;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ReportImageMapper implements RowMapper<Object> {    
	 
	public ReportParameter mapRow(ResultSet rs, int rowNum) throws SQLException {    
		ReportParameter reportParameter = new ReportParameter();
		
		reportParameter.setParameterName(rs.getString("PARAMETER_NAME"));
		reportParameter.setReportImage(rs.getBlob("IMAGE"));
		reportParameter.setReportImageMethod(rs.getString("METHOD"));
		return reportParameter;
	 }    
} 
