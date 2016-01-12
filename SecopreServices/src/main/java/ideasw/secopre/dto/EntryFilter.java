package ideasw.secopre.dto;

public class EntryFilter {

	private Long stateId;

	private Long districtId;

	private Long entryId;

	private Integer[] months;
	
	private Boolean type;

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getEntryId() {
		return entryId;
	}

	public Integer[] getMonths() {
		return months;
	}

	public void setMonths(Integer[] months) {
		this.months = months;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
}
