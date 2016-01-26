package ideasw.secopre.dto;

import java.text.DecimalFormat;
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
	private Long requestDetailId;
	private Long requestId;
	private Long movementTypeId;
	private String description;
	private Long programaticKeyId;
	private Long entryId;
	private Integer initialMonthId;
	private Integer finalMonthId;
	private String monthAmount;
	private String totalAmount;
	
	private Double monthAmountValue;
	private Double totalAmountValue;
	
	private boolean isSaved;
	
	private Integer removedElement = 0;
	
	public Long getRequestDetailId() {
		return requestDetailId;
	}
	public void setRequestDetailId(Long requestDetailId) {
		this.requestDetailId = requestDetailId;
	}
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
	public String getMonthAmount() {
		DecimalFormat formatter = new DecimalFormat("###,###,###.00");
		String result =  formatter.format((this.monthAmountValue == null ? 0 : this.monthAmountValue));
		return result;
	}
	public void setMonthAmount(String monthAmount) {
		this.monthAmount = monthAmount;
		this.monthAmountValue = new Double(monthAmount.replace(",",""));
	}
	public String getTotalAmount() {
		int months = (this.finalMonthId.intValue() - this.initialMonthId.intValue()) + 1;
		Double total =  this.monthAmountValue * months;
		DecimalFormat formatter = new DecimalFormat("###,###,###.00");
		String result=  formatter.format((total == null ? 0 : total));
		return result;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
		this.totalAmountValue = new Double(totalAmount.replace(",",""));
	}
	public Integer getRemovedElement() {
		return removedElement;
	}
	public void setRemovedElement(Integer removedElement) {
		this.removedElement = removedElement;
	}

	public String toString(){
		return "{requestId: "+ requestId + 
		", movementTypeId: " + this.movementTypeId +
		", removedElement: "  + this.removedElement +
		", programaticKeyId: " + this.programaticKeyId + 
		", entryId: " + this.entryId + 
		", initialMonthId: " + this.initialMonthId + 
		", finalMonthId: " + this.finalMonthId + 
		", monthAmount: " + this.monthAmount + 
		", totalAmount: " + this.getTotalAmount() + 
		", monthAmountValue: " + this.monthAmountValue + 
		", totalAmountValue: " + this.totalAmountValue + 
		", isSaved: " + this.isSaved + "}";
	}

	public static final String TABLE_NAME = "REQUEST_DETAIL";
	public static final String PRIMARY_KEY = "ID";

	public Map<String, Object> getParams(Long requestId){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("REQUEST_ID", requestId);
		parameters.put("MOVEMENT_TYPE_ID", this.movementTypeId);
	    parameters.put("PROGRAMATIC_KEY_ID", this.programaticKeyId);
	    parameters.put("ENTRY_ID", this.entryId);
	    parameters.put("INITIAL_MONTH", this.initialMonthId);
	    parameters.put("FINAL_MONTH", this.finalMonthId);
	    parameters.put("MONTH_AMOUNT", this.monthAmount);
	    parameters.put("TOTAL_AMOUNT", this.getTotalAmount());
	    parameters.put("CREATION_DATE", new Date());
	    parameters.put("ACTIVE", 1);
	    return parameters;
	}
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public boolean getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}
	public Double getMonthAmountValue() {
		return monthAmountValue;
	}
	public void setMonthAmountValue(Double monthAmountValue) {
		this.monthAmountValue = monthAmountValue;
	}
	public Double getTotalAmountValue() {
		return totalAmountValue;
	}
	public void setTotalAmountValue(Double totalAmountValue) {
		this.totalAmountValue = totalAmountValue;
	}

}
