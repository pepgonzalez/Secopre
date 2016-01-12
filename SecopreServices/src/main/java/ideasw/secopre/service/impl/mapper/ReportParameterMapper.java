package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.dto.ReportParameter;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ReportParameterMapper implements RowMapper<Object> {    
	 
	public ReportParameter mapRow(ResultSet rs, int rowNum) throws SQLException {    
		ReportParameter reportParameter = new ReportParameter();
		reportParameter.setReportId(rs.getLong("REPORT_ID"));
		reportParameter.setParameterName(rs.getString("PARAMETER_NAME"));
		reportParameter.setParameterPath(rs.getString("PARAMETER_PATH"));
		reportParameter.setParameterType(rs.getString("PARAMETER_TYPE"));
		reportParameter.setAjax(rs.getString("AJAX"));
		reportParameter.setLabel(rs.getString("LABEL"));
		reportParameter.setRequired(rs.getBoolean("REQUIRED"));
		reportParameter.setCreationDate(rs.getDate("CREATION_DATE"));
		reportParameter.setActive(rs.getBoolean("ACTIVE"));
		return reportParameter;
		
	 }    
} 
