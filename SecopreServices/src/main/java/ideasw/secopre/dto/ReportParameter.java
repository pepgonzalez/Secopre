package ideasw.secopre.dto;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * Clase de estereotipo DTO para el manejo de informacion base de un tramite
 * {@link REPORT_PARAMETER}
 * 
 * @author pepgonzalez
 *
 */
public class ReportParameter {

	//campos base
	private Long reportId;
	private String parameterName;
	private String parameterPath;
	private String parameterType;
	private String ajax;
	private String label;
	private boolean required;
	private Date creationDate;
	private boolean active;
	
	//campos de carga por reflextion
	private String districtId;
	private String year;
	private String initialDateStr;
	private String finalDateStr;
	private String initialMonthId;
	private String finalMonthId;
	private String programaticKeyId;
	private String entryId;
	private String requestId;
	
	//opciones para parametros dinamicos del tipo select
	private Map<Long, String> parameterOptions;
	
	//datos de reporte
	private Blob reportImage;
	private Long reportImageId;
	private String reportImageMethod;
	
	public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getParameterPath() {
		return parameterPath;
	}
	public void setParameterPath(String parameterPath) {
		this.parameterPath = parameterPath;
	}
	public String getParameterType() {
		return parameterType;
	}
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public String toString(){
		return "districtId: "+ districtId + ", year: " + year;
	}
	public String getInitialDateStr() {
		return initialDateStr;
	}
	public void setInitialDateStr(String initialDateStr) {
		this.initialDateStr = initialDateStr;
	}
	public String getFinalDateStr() {
		return finalDateStr;
	}
	public void setFinalDateStr(String finalDateStr) {
		this.finalDateStr = finalDateStr;
	}
	public String getAjax() {
		return ajax;
	}
	public void setAjax(String ajax) {
		this.ajax = ajax;
	}
	public Map<Long, String> getParameterOptions() {
		return parameterOptions;
	}
	public void setParameterOptions(Map<Long, String> parameterOptions) {
		this.parameterOptions = parameterOptions;
	}
	public String getInitialMonthId() {
		return initialMonthId;
	}
	public void setInitialMonthId(String initialMonthId) {
		this.initialMonthId = initialMonthId;
	}
	public String getFinalMonthId() {
		return finalMonthId;
	}
	public void setFinalMonthId(String finalMonthId) {
		this.finalMonthId = finalMonthId;
	}
	public String getProgramaticKeyId() {
		return programaticKeyId;
	}
	public void setProgramaticKeyId(String programaticKeyId) {
		this.programaticKeyId = programaticKeyId;
	}
	public String getEntryId() {
		return entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public Blob getReportImage() {
		return reportImage;
	}
	public void setReportImage(Blob reportImage) {
		this.reportImage = reportImage;
	}
	public Long getReportImageId() {
		return reportImageId;
	}
	public void setReportImageId(Long reportImageId) {
		this.reportImageId = reportImageId;
	}
	public String getReportImageMethod() {
		return reportImageMethod;
	}
	public void setReportImageMethod(String reportImageMethod) {
		this.reportImageMethod = reportImageMethod;
	}
}
