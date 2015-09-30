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
	private Long initialMonthId;
	private Long finalMonthId;
	private Double monthAmount;
	private Double totalAmount;
	
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
	public Long getInitialMonthId() {
		return initialMonthId;
	}
	public void setInitialMonthId(Long initialMonthId) {
		this.initialMonthId = initialMonthId;
	}
	public Long getFinalMonthId() {
		return finalMonthId;
	}
	public void setFinalMonthId(Long finalMonthId) {
		this.finalMonthId = finalMonthId;
	}
	public Double getMonthAmount() {
		return monthAmount;
	}
	public void setMonthAmount(Double monthAmount) {
		this.monthAmount = monthAmount;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
