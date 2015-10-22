package ideasw.secopre.dto;

public class UpdateEntry {
	
	private Long entryId; 	

	private Long programmaticKeyId;

	private Integer entryCode;

	private String entryDescription;
	
	private Double annualAmount;

	public Long getEntryId() {
		return entryId;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

	public Long getProgrammaticKeyId() {
		return programmaticKeyId;
	}

	public void setProgrammaticKeyId(Long programmaticKeyId) {
		this.programmaticKeyId = programmaticKeyId;
	}

	public Integer getEntryCode() {
		return entryCode;
	}

	public void setEntryCode(Integer entryCode) {
		this.entryCode = entryCode;
	}

	public String getEntryDescription() {
		return entryDescription;
	}

	public void setEntryDescription(String entryDescription) {
		this.entryDescription = entryDescription;
	}

	public Double getAnnualAmount() {
		return annualAmount;
	}

	public void setAnnualAmount(Double annualAmount) {
		this.annualAmount = annualAmount;
	}


	
}
