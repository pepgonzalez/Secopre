package ideasw.secopre.dto;

import java.util.Date;

public class StageConfig {

	private Long stageConfigId;
	private Long stageId;
	private Long pathId;
	private boolean isCapture;
	private boolean isAuthorization;
	private boolean isCanceled;
	private boolean isOperated;
	private String captureForm;
	private Date lastUpdate;
	private boolean active;
	
	public Long getStageConfigId() {
		return stageConfigId;
	}
	public void setStageConfigId(Long stageConfigId) {
		this.stageConfigId = stageConfigId;
	}
	public Long getStageId() {
		return stageId;
	}
	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}
	public Long getPathId() {
		return pathId;
	}
	public void setPathId(Long pathId) {
		this.pathId = pathId;
	}
	public boolean getIsCapture() {
		return isCapture;
	}
	public void setCapture(boolean isCapture) {
		this.isCapture = isCapture;
	}
	public boolean getIsAuthorization() {
		return isAuthorization;
	}
	public void setAuthorization(boolean isAuthorization) {
		this.isAuthorization = isAuthorization;
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
	public String getCaptureForm() {
		return captureForm;
	}
	public void setCaptureForm(String captureForm) {
		this.captureForm = captureForm;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public boolean getIsActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String toString(){
		return "id:"+this.stageConfigId+",capture:"+this.isCapture+",authorization:"+this.isAuthorization+",isCanceled:"
			+this.isCanceled+",isOperated:"+this.isOperated;
	}
}
