package ideasw.secopre.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase de estereotipo DTO para el manejo de informacion base de un tramite
 * {@link Formality}
 * 
 * @author pepgonzalez
 *
 */
public class Request {

	private Long requestId;
	private String firstName;
	private String parentLastName;
	private String motherLastName;
	private Long formalityId;

	//variables de transporte
	private String nextStageValueCode;
	private Long stageConfigId;
	
	//variables de authorizacion
	private boolean authorizationForm;
	
	public boolean getAuthorizationForm() {
		return authorizationForm;
	}
	public void setAuthorizationForm(boolean authorizationForm) {
		this.authorizationForm = authorizationForm;
	}
	public Long getFormalityId() {
		return formalityId;
	}
	public void setFormalityId(Long formalityId) {
		this.formalityId = formalityId;
	}
	
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getParentLastName() {
		return parentLastName;
	}
	public void setParentLastName(String parentLastName) {
		this.parentLastName = parentLastName;
	}
	public String getMotherLastName() {
		return motherLastName;
	}
	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}
	public String getNextStageValueCode() {
		return nextStageValueCode;
	}
	public void setNextStageValueCode(String nextStageValueCode) {
		this.nextStageValueCode = nextStageValueCode;
	}
	public Long getStageConfigId() {
		return stageConfigId;
	}
	public void setStageConfigId(Long stageConfigId) {
		this.stageConfigId = stageConfigId;
	}
	
	@Override
	public String toString(){
		return "{requestId: " + requestId + ",formalityId: " + formalityId + ",firstName: "+firstName+",parentLastName: "+parentLastName+", motherLastName: "+motherLastName;
	}
	
	public static final String TABLE_NAME = "REQUEST";
	public static final String PRIMARY_KEY = "ID";
	
	public Map<String, Object> getParams(){
		Map<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("FIRST_NAME", this.getFirstName());
	    parameters.put("PARENT_LAST_NAME", this.getParentLastName());
	    parameters.put("MOTHER_LAST_NAME", this.getMotherLastName());
	    parameters.put("LAST_UPDATE", new Date());
	    parameters.put("ACTIVE", 1);
	    return parameters;
	}
	
}
