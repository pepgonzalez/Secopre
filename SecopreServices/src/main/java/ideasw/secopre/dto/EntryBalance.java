package ideasw.secopre.dto;

import java.util.List;

public class EntryBalance {

	private Double annualAmount = 0D;
	private Double budgetAmount = 0D;
	private Double budgetAsing = 0D;
	private Double budgetCommit = 0D;

	private List<EntryDistrictDetail> entries;

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

	public Double getBudgetAsing() {
		return budgetAsing;
	}

	public void setBudgetAsing(Double budgetAsing) {
		this.budgetAsing = budgetAsing;
	}

	public Double getBudgetCommit() {
		return budgetCommit;
	}

	public void setBudgetCommit(Double budgetCommit) {
		this.budgetCommit = budgetCommit;
	}

	public List<EntryDistrictDetail> getEntries() {
		return entries;
	}

	public void setEntries(List<EntryDistrictDetail> entries) {
		this.entries = entries;
	}

}
