package ideasw.secopre.dto;

/**
 * Clase de estereotipo DTO para el manejo de configuracion de los tramites
 * {@link Formality}
 * 
 * @author pepgonzalez
 *
 */
public class Formality {
	
	private Long formalityId;
	private String description;
	private Long workFlowId;
	private Long authorizationId;
	private String code;
	private Boolean createValidation;
	private String processAfterCreation;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public Long getAuthorizationId() {
		return authorizationId;
	}
	public void setAuthorizationId(Long authorizationId) {
		this.authorizationId = authorizationId;
	}
	public Long getFormalityId() {
		return formalityId;
	}
	public void setFormalityId(Long formalityId) {
		this.formalityId = formalityId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(Long workFlowId) {
		this.workFlowId = workFlowId;
	}
	
	@Override
	public String toString(){
		return "{"+
			   "formalityId: " + formalityId +
			   ", description: " + description + 
			   ", workFlowId: " + workFlowId + "}";
	}
	public Boolean getCreateValidation() {
		return createValidation;
	}
	public void setCreateValidation(Boolean createValidation) {
		this.createValidation = createValidation;
	}
	public String getProcessAfterCreation() {
		return processAfterCreation;
	}
	public void setProcessAfterCreation(String processAfterCreation) {
		this.processAfterCreation = processAfterCreation;
	}
}
