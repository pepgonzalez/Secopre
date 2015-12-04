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
public class RequestHistory {

	//variables correspondientes a REQUEST
	private Long requestId;
	private Long consecutive;
	private String initialStage;
	private Long stageConfigId;
	private boolean isLastAuthorization;
	
	private String finalStage;
	private Long nextStageConfig;
	private boolean isCurrentAuthorization;
	
	private String status;
	private Date creationDate;
	private String comments;
	private String username;
	
	
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public Long getConsecutive() {
		return consecutive;
	}
	public void setConsecutive(Long consecutive) {
		this.consecutive = consecutive;
	}
	public String getInitialStage() {
		return initialStage;
	}
	public void setInitialStage(String initalStage) {
		this.initialStage = initalStage;
	}
	public String getFinalStage() {
		return finalStage;
	}
	public void setFinalStage(String finalStage) {
		this.finalStage = finalStage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getStageConfigId() {
		return stageConfigId;
	}
	public void setStageConfigId(Long stageConfigId) {
		this.stageConfigId = stageConfigId;
	}
	public boolean isLastAuthorization() {
		return isLastAuthorization;
	}
	public void setLastAuthorization(boolean isLastAuthorization) {
		this.isLastAuthorization = isLastAuthorization;
	}
	public Long getNextStageConfig() {
		return nextStageConfig;
	}
	public void setNextStageConfig(Long nextStageConfig) {
		this.nextStageConfig = nextStageConfig;
	}
	public boolean isCurrentAuthorization() {
		return isCurrentAuthorization;
	}
	public void setCurrentAuthorization(boolean isCurrentAuthorization) {
		this.isCurrentAuthorization = isCurrentAuthorization;
	}
	
	public String getCompleteComments(){
		return "Usuario: " + this.getUsername() + " - Fecha: " + this.getCreationDate() + " - Observaciones: "  + this.getComments();
	}
}
