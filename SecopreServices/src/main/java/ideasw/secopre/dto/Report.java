package ideasw.secopre.dto;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * Clase de estereotipo DTO para el manejo de informacion de reportes
 * {@link REPORT}
 * 
 * @author pepgonzalez
 *
 */
public class Report {

	private Long reportId;
	private String description;
	private String reportType;
	private Blob resource;
	private String parameterQuery;
	private Long roleOwner;
	private Date lastUpdate;
	private boolean active;
	
	//fuente del reporte
	private String reportCode;
	private String reportSourceDescription;
	private String dataSource;
	
	//listado de parametros del reporte
	private List<ReportParameter> reportParameters;
	
	//blob
	private byte[] report;
	
	public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public Blob getResource() {
		return resource;
	}
	public void setResource(Blob resource) {
		this.resource = resource;
	}
	public String getParameterQuery() {
		return parameterQuery;
	}
	public void setParameterQuery(String parameterQuery) {
		this.parameterQuery = parameterQuery;
	}
	public Long getRoleOwner() {
		return roleOwner;
	}
	public void setRoleOwner(Long roleOwner) {
		this.roleOwner = roleOwner;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public byte[] getReport() {
		return report;
	}
	public void setReport(byte[] report) {
		this.report = report;
	}
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public String getReportSourceDescription() {
		return reportSourceDescription;
	}
	public void setReportSourceDescription(String reportSourceDescription) {
		this.reportSourceDescription = reportSourceDescription;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public List<ReportParameter> getReportParameters() {
		return reportParameters;
	}
	public void setReportParameters(List<ReportParameter> reportParameters) {
		this.reportParameters = reportParameters;
	}
	
	public boolean getHasReportParameters(){
		return this.reportParameters.size() > 0;
	}

}
