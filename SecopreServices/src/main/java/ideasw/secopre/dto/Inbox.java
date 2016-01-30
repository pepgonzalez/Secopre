package ideasw.secopre.dto;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Inbox {

	private Long requestId;
	private String folio;
	private String justification;
	private Long districtId;
	private String districtDescription;
	private String resourcePath;
	private Long formalityId;
	private String formalityDescription;
	private String transactionType;
	private Long workFlowConfigId;
	private Long stageConfigId;
	private String description;
	private Long nextStageConfig;
	private Long pathId;
	private String url;
	private boolean isCapture;
	private boolean isAuthorization;
	private boolean requestFinished;
	private String captureForm;
	private Long statusId;
	private String nextDescription;
	private boolean hasComments;
	
	private boolean isCanceled;
	private boolean isOperated;
	
	private Float totalAmount;
	private Date creationDate;
	private String creationDateStr;
	
	@SuppressWarnings("unused")
	private boolean hasDocument;
	
	public Long getFormalityId() {
		return formalityId;
	}
	public void setFormalityId(Long formalityId) {
		this.formalityId = formalityId;
	}
	public String getFormalityDescription() {
		return formalityDescription;
	}
	public void setFormalityDescription(String formalityDescription) {
		this.formalityDescription = formalityDescription;
	}
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public Long getWorkFlowConfigId() {
		return workFlowConfigId;
	}
	public void setWorkFlowConfigId(Long workFlowId) {
		this.workFlowConfigId = workFlowId;
	}
	public Long getStageConfigId() {
		return stageConfigId;
	}
	public void setStageConfigId(Long stageConfigId) {
		this.stageConfigId = stageConfigId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getNextStageConfig() {
		return nextStageConfig;
	}
	public void setNextStageConfig(Long nextStageConfig) {
		this.nextStageConfig = nextStageConfig;
	}
	public Long getPathId() {
		return pathId;
	}
	public void setPathId(Long pathId) {
		this.pathId = pathId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isCapture() {
		return isCapture;
	}
	public void setCapture(boolean isCapture) {
		this.isCapture = isCapture;
	}
	public boolean isAuthorization() {
		return isAuthorization;
	}
	public void setAuthorization(boolean isAuthorization) {
		this.isAuthorization = isAuthorization;
	}
	public String getCaptureForm() {
		return captureForm;
	}
	public void setCaptureForm(String captureForm) {
		this.captureForm = captureForm;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getNextDescription() {
		return nextDescription;
	}
	public void setNextDescription(String nextDescription) {
		this.nextDescription = nextDescription;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	public String getDistrictDescription() {
		return districtDescription;
	}
	public void setDistrictDescription(String districtDescription) {
		this.districtDescription = districtDescription;
	}	

	public String getNextStageURL(){
		String basePath =  (this.isCapture ? this.url + "/" + this.captureForm : this.url);
		basePath = basePath + "/" + this.requestId + "/" + this.nextStageConfig + "/0";
		return basePath;
	}
	
	public String getNextStageJSFunction(){
		return (this.isAuthorization ? "initAuthorization()" : 
					(this.isCapture ?  this.captureForm + "Capture()" : 
						(this.captureForm != null && this.captureForm.equals("upload") ? "initUpload()" :
							(this.requestFinished ? "initFinish()" : "()")
						)
					)
				);
	}

	public boolean getHasDocument() {
		return (this.resourcePath != null && !(this.resourcePath.isEmpty()) && this.resourcePath.length() > 0);
	}
	public void setHasDocument(boolean hasDocument) {
		this.hasDocument = hasDocument;
	}
	public boolean getRequestFinished() {
		return requestFinished;
	}
	public void setRequestFinished(boolean requestFinished) {
		this.requestFinished = requestFinished;
	}
	public Float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getCreationDateStr() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(this.creationDate);
	}
	
	public String getTotalAmountStr(){
		DecimalFormat formatter = new DecimalFormat("$###,###,##0.00");
		String result=  formatter.format(this.totalAmount);
		return result;
	}
	public boolean getIsCanceled() {
		return isCanceled;
	}
	public void setCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}
	public boolean getIsOperated() {
		return isOperated;
	}
	public void setOperated(boolean isOperated) {
		this.isOperated = isOperated;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public boolean getHasComments() {
		return hasComments;
	}
	public void setHasComments(boolean hasComments) {
		this.hasComments = hasComments;
	}
}