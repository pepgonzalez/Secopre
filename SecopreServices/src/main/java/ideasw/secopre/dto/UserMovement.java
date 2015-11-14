package ideasw.secopre.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * Clase de estereotipo DTO para el manejo de informacion base de un tramite
 * {@link UserMovement}
 * 
 * @author pepgonzalez
 *
 */
public class UserMovement {

	private String folio;
	private String movementType;
	private String comments;
	private String justification;
	private String formality;
	private Date creationDate;
	private String currentStatus;
	
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getMovementType() {
		return movementType;
	}
	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public String getFormality() {
		return formality;
	}
	public void setFormality(String formality) {
		this.formality = formality;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String toString(){
		return "{folio:"+folio+",movementTypeId:"+movementType+",justification:"+justification+",formality:"
				+formality+",creationDate:"+creationDate+",currentStatus:"+currentStatus+",comments:"+comments+"}";
	}
}
