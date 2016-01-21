package ideasw.secopre.dto;

import java.text.DecimalFormat;


public class EntryDistrictDetail {

	DecimalFormat formatter = new DecimalFormat("###,###,###.00");

	private Long stateId;
	private String stateName;
	private Long districtId;
	private String districtNumber;
	private Long entryId;
	private String entryCode;
	private String entryDescription;

	private Double annualAmount;
	private Double budgetAmount;
	private Double budgetAmountAssign;
	private Double committedAmount;

	private Double january;
	private Double february;
	private Double march;
	private Double april;
	private Double may;
	private Double june;
	private Double july;
	private Double august;
	private Double september;
	private Double october;
	private Double november;
	private Double december;

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictNumber() {
		return districtNumber;
	}

	public void setDistrictNumber(String districtNumber) {
		this.districtNumber = districtNumber;
	}

	public Long getEntryId() {
		return entryId;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
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

	public Double getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(Double budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public Double getBudgetAmountAssign() {
		return budgetAmountAssign;
	}

	public void setBudgetAmountAssign(Double budgetAmountAssign) {
		this.budgetAmountAssign = budgetAmountAssign;
	}

	public Double getCommittedAmount() {
		return committedAmount;
	}

	public void setCommittedAmount(Double committedAmount) {
		this.committedAmount = committedAmount;
	}

	public Double getJanuary() {
		return january;
	}
	public String getJanuaryStr() {
		return january == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(january);
	}

	public void setJanuary(Double january) {
		this.january = january;
	}

	public Double getFebruary() {
		return february;
	}
	public String getFebruaryStr() {
		return february == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(february);
	}
	public void setFebruary(Double february) {
		this.february = february;
	}

	public Double getMarch() {
		return march;
	}
	public String getMarchStr() {
		return march == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(march);
	}
	public void setMarch(Double march) {
		this.march = march;
	}

	public Double getApril() {
		return april;
	}
	public String getAprilStr() {
		return april == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(april);
	}
	public void setApril(Double april) {
		this.april = april;
	}

	public Double getMay() {
		return may;
	}
	public String getMayStr() {
		return may == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(may);
	}
	public void setMay(Double may) {
		this.may = may;
	}
	public String getJuneStr() {
		return june == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(june);
	}
	public Double getJune() {
		return june;
	}

	public void setJune(Double june) {
		this.june = june;
	}

	public Double getJuly() {
		return july;
	}

	public String getJulyStr() {
		return july == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(july);
	}	
	public void setJuly(Double july) {
		this.july = july;
	}

	public Double getAugust() {
		return august;
	}
	public String getAugustStr() {
		return august == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(august);
	}	
	public void setAugust(Double august) {
		this.august = august;
	}

	public Double getSeptember() {
		return september;
	}

	public String getSeptemberStr() {
		return september == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(september);
	}		
	
	public void setSeptember(Double september) {
		this.september = september;
	}

	public Double getOctober() {
		return october;
	}

	public String getOctoberStr() {
		return october == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(october);
	}		
	
	public void setOctober(Double october) {
		this.october = october;
	}

	public Double getNovember() {
		return november;
	}

	public String getNovemberStr() {
		return november == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(november);
	}		
	public void setNovember(Double november) {
		this.november = november;
	}

	public Double getDecember() {
		return december;
	}

	public String getDecemberStr() {
		return december == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(december);
	}
	
	public void setDecember(Double december) {
		this.december = december;
	}
	public String getEntryCode() {
		return entryCode;
	}

	public void setEntryCode(String entryCode) {
		this.entryCode = entryCode;
	}

}
