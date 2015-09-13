package ideasw.secopre.dto;

public class Inbox {

	private Long requestId;
	private String name;
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
	private String captureForm;
	private Long statusId;
	private String nextDescription;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	

	public String getNextStageURL(){
		String basePath =  (this.isCapture ? this.url + "/" + this.captureForm : this.url);
		basePath = basePath + "/" + this.requestId + "/" + this.nextStageConfig;
		return basePath;
	}
}