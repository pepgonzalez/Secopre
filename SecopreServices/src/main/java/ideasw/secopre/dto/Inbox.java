package ideasw.secopre.dto;

public class Inbox {

	private Long requestId;
	private String folio;
	private String justification;
	private Long districtId;
	private String districtDescription;
	private String resourcePath;
	private Long formalityId;
	private String formalityDescription;
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
		basePath = basePath + "/" + this.requestId + "/" + this.nextStageConfig;
		return basePath;
	}
	
	public String getNextStageJSFunction(){
		return (this.isAuthorization ? "initAuthorization()" : (this.isCapture ? "initFullCapture()" : (this.captureForm != null && this.captureForm.equals("upload") ? "initUpload()" : "()")));
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
}