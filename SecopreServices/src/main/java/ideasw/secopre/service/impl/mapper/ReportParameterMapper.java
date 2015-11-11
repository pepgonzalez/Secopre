package ideasw.secopre.service.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ideasw.secopre.dto.Movement;
import ideasw.secopre.dto.Report;
import ideasw.secopre.dto.ReportParameter;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.Entry;
import ideasw.secopre.model.catalog.District;

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