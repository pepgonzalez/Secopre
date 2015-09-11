package ideasw.secopre.dto;

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
	
	@Override
	public String toString(){
		return "{requestId: " + requestId + ",formalityId: " + formalityId + ",firstName: "+firstName+",parentLastName: "+parentLastName+", motherLastName: "+motherLastName;
	}
	
}
