package ideasw.secopre.dto;

import java.util.Date;

public class WorkFlowConfig {

	public Long getWorkFlowConfigId() {
		return workFlowConfigId;
	}
	public void setWorkFlowConfigId(Long workFlowConfigId) {
		this.workFlowConfigId = workFlowConfigId;
	}
	private Long workFlowConfigId;
	private Long workFlowId;
	private Long stageConfigId;
	private String wfConfigCode;
	private Long nextStageConfig;
	private Long statusId;
	private Date lastUpdate;
	private boolean active;
	

	public Long getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(Long workFlowId) {
		this.workFlowId = workFlowId;
	}
	public Long getStageConfigId() {
		return stageConfigId;
	}
	public void setStageConfigId(Long stageConfigId) {
		this.stageConfigId = stageConfigId;
	}
	public String getWfConfigCode() {
		return wfConfigCode;
	}
	public void setWfConfigCode(String wfConfigCode) {
		this.wfConfigCode = wfConfigCode;
	}
	public Long getNextStageConfig() {
		return nextStageConfig;
	}
	public void setNextStageConfig(Long nextStageConfig) {
		this.nextStageConfig = nextStageConfig;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
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
}
