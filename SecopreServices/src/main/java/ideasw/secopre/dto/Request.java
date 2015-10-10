package ideasw.secopre.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.AutoPopulatingList;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clase de estereotipo DTO para el manejo de informacion base de un tramite
 * {@link Formality}
 * 
 * @author pepgonzalez
 *
 */
public class Request {

	//variables correspondientes a REQUEST
	private Long requestId;
	private Long districtId;
	private String justification;
	private String folio;
	private String resourcePath;

	//variables correspondientes a REQUEST DETAIL
	private Long movementTypeId;
	private List<Movement> upMovements = new ArrayList<Movement>();
	private List<Movement> downMovements = new ArrayList<Movement>();
	

	//variables de transporte y gestion de la forma
	private String nextStageValueCode;
	private Long stageConfigId;
	private String formalityCode;
	private Long formalityId;	
	private boolean authorizationForm;
		
	public String getFormalityCode() {
		return formalityCode;
	}
	public void setFormalityCode(String formalityCode) {
		this.formalityCode = formalityCode;
	}
	
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
		//archivo
	private MultipartFile file;
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	@Override
	public String toString(){
		return "{requestId: " + requestId + ",formalityId: " + formalityId + ",folio: "+folio+",districtId: "+districtId+", justification: "+justification;
	}
	
	public static final String TABLE_NAME = "REQUEST";
	public static final String PRIMARY_KEY = "ID";
	
	public Map<String, Object> getParams(){
		Map<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("FOLIO", this.getFolio());
	    parameters.put("DISTRICT_ID", this.getDistrictId());
	    parameters.put("JUSTIFICATION", this.getJustification());
	    parameters.put("LAST_UPDATE", new Date());
	    parameters.put("ACTIVE", 1);
	    return parameters;
	}
	public Long getMovementTypeId() {
		return movementTypeId;
	}
	public void setMovementTypeId(Long movementTypeId) {
		this.movementTypeId = movementTypeId;
	}
	public List<Movement> getUpMovements() {
		return upMovements;
	}
	public void setUpMovements(List<Movement> upMovements) {
		this.upMovements = upMovements;
	}
	public List<Movement> getDownMovements() {
		return downMovements;
	}
	public void setDownMovements(List<Movement> downMovements) {
		this.downMovements = downMovements;
	}

	public void setMovements(List<Movement> movs){
		for(Movement m : movs){
			if (m.getMovementTypeId() > 0){
				this.upMovements.add(m);
			}else{
				this.downMovements.add(m);
			}
		}
	}
}