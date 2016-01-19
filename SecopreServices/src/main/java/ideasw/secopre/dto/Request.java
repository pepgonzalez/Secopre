package ideasw.secopre.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import ideasw.secopre.model.Entry;

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
	private Long entryId;
	private Long rectificationId;
	private Long requestIdByDistrict;

	//variables correspondientes a REQUEST DETAIL
	private Long movementTypeId;
	private List<Movement> upMovements = new ArrayList<Movement>();
	private List<Movement> downMovements = new ArrayList<Movement>();
	
	//suma de ambos
	private List<Movement> movements = new ArrayList<Movement>();
	
	//historia
	private List<RequestHistory> requestHistory = new ArrayList<RequestHistory>();
	private RequestHistory currentStage;
	
	private String comments;
	

	//variables de transporte y gestion de la forma
	private String nextStageValueCode;
	private Long stageConfigId;
	private String formalityCode;
	private Long formalityId;	
	private boolean authorizationForm;
	
	//variable para gastos
	private String certifiedAccount;
	private String expenseDescription;
	
	//variable para mostrar el nombre del tramite dinamico en capturas completas
	private String formalityName;
		
	//pojo para tramite de gestion de partidas
	private Entry entry;
	
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
		return "{requestId: " + requestId + 
				",formalityId: " + formalityId + 
				",folio: " + folio + 
				",districtId: " + districtId + 
				", justification: "+ justification + 
				", certifiedAccount: " + certifiedAccount + "}";
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
		this.movements = movs;
		for(Movement m : movs){
			if (m.getMovementTypeId() > 0){
				this.upMovements.add(m);
			}else{
				this.downMovements.add(m);
			}
		}
	}
	
	public List<Movement> getMovements(){
		return this.movements;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public List<RequestHistory> getRequestHistory() {
		return requestHistory;
	}
	public void setRequestHistory(List<RequestHistory> requestHistory) {
		this.requestHistory = requestHistory;
	}
	public String getCertifiedAccount() {
		return certifiedAccount;
	}
	public void setCertifiedAccount(String certifiedAccount) {
		this.certifiedAccount = certifiedAccount;
	}
	public String getFormalityName() {
		return formalityName;
	}
	public void setFormalityName(String formalityName) {
		this.formalityName = formalityName;
	}
	public RequestHistory getCurrentStage() {
		return currentStage;
	}
	public void setCurrentStage(RequestHistory currentStage) {
		this.currentStage = currentStage;
	}
	public Entry getEntry() {
		return entry;
	}
	public void setEntry(Entry entry) {
		this.entry = entry;
	}
	public Long getEntryId() {
		return entryId;
	}
	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}
	public Long getRectificationId() {
		return rectificationId;
	}
	public void setRectificationId(Long rectificationId) {
		this.rectificationId = rectificationId;
	}
	public String getExpenseDescription() {
		return expenseDescription;
	}
	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}
	public Long getRequestIdByDistrict() {
		return requestIdByDistrict;
	}
	public void setRequestIdByDistrict(Long requestIdByDistrict) {
		this.requestIdByDistrict = requestIdByDistrict;
	}
}
