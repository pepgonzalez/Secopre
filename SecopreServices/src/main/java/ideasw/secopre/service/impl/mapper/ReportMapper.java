package ideasw.secopre.service.impl.mapper;

import ideasw.secopre.dto.Report;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ReportMapper implements RowMapper<Object> {    
	 
	public Report mapRow(ResultSet rs, int rowNum) throws SQLException {    
		Report report = new Report();
		
		report.setReportId(rs.getLong("ID"));
		report.setDescription(rs.getString("DESCRIPTION"));
		report.setReportType(rs.getString("REPORT_TYPE"));
		report.setParameterQuery(rs.getString("PARAMETER_QUERY"));
		report.setRoleOwner(rs.getLong("ROLE_OWNER"));
		report.setLastUpdate(rs.getDate("LAST_UPDATE"));
		report.setActive(rs.getBoolean("ACTIVE"));
		report.setReportCode(rs.getString("REPORT_CODE"));
		report.setReportSourceDescription(rs.getString("REPORT_SOURCE"));
		report.setDataSource(rs.getString("DATASOURCE"));
		return report;   
	 }    
} 