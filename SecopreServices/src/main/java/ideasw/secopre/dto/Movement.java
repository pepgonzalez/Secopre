package ideasw.secopre.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * Clase de estereotipo DTO para el manejo de informacion base de un tramite
 * {@link REQUEST_DETAIL}
 * 
 * @author pepgonzalez
 *
 */
public class Movement {
	
	//variables correspondientes al tipo de tramite de movimientos
	private Long movementTypeId;
	private String description;
	private Long programaticKeyId;
	private Long entryId;
	private Integer initialMonthId;
	private Integer finalMonthId;
	private Double monthAmount;
	private Double totalAmount;
	
	private Integer removedElement = 0;
	
	
	public Long getMovementTypeId() {
		return movementTypeId;
	}
	public void setMovementTypeId(Long movementTypeId) {
		this.movementTypeId = movementTypeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getProgramaticKeyId() {
		return programaticKeyId;
	}
	public void setProgramaticKeyId(Long programaticKeyId) {
		this.programaticKeyId = programaticKeyId;
	}
	public Long getEntryId() {
		return entryId;
	}
	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}
	public Integer getInitialMonthId() {
		return initialMonthId;
	}
	public void setInitialMonthId(Integer initialMonthId) {
		this.initialMonthId = initialMonthId;
	}
	public Integer getFinalMonthId() {
		return finalMonthId;
	}
	public void setFinalMonthId(Integer finalMonthId) {
		this.finalMonthId = finalMonthId;
	}
	public Double getMonthAmount() {
		return monthAmount;
	}
	public void setMonthAmount(Double monthAmount) {
		this.monthAmount = monthAmount;
	}
	public Double getTotalAmount() {
		//int months = (this.finalMonthId.intValue() - this.initialMonthId.intValue()) + 1;
		return 0D;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getRemovedElement() {
		return removedElement;
	}
	public void setRemovedElement(Integer removedElement) {
		this.removedElement = removedElement;
	}

	public String toString(){
		return "{movementTypeId: " + this.movementTypeId +
		", removedElement: "  + this.removedElement +
		", programaticKeyId: " + this.programaticKeyId + 
		", entryId: " + this.entryId + 
		", initialMonthId: " + this.initialMonthId + 
		", finalMonthId: " + this.finalMonthId + 
		", monthAmount: " + this.monthAmount + 
		", totalAmount: " + this.getTotalAmount() + "}";
	}

}
