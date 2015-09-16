package ideasw.secopre.dto;

import java.util.Date;

public class RequestConfig {

	private Long requestId;
	private Long formalityId;
	private Long workFlowId;
	private Long authorizationId;
	private Date lastUpdate;
	private boolean active;
	
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public Long getFormalityId() {
		return formalityId;
	}
	public void setFormalityId(Long formalityId) {
		this.formalityId = formalityId;
	}
	public Long getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(Long workFlowId) {
		this.workFlowId = workFlowId;
	}
	public Long getAuthorizationId() {
		return authorizationId;
	}
	public void setAuthorizationId(Long authorizationId) {
		this.authorizationId = authorizationId;
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
