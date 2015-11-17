package ideasw.secopre.dto;

import ideasw.secopre.model.EntryDistrict;

import java.util.List;

public class EntryBalance {

	public Double annualAmount = 0D;
	public Double budgetAmount = 0D;
	public Double budgetAsing = 0D;
	public Double budgetCommit = 0D;
	
	public List<EntryDistrict> entries;

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

	public List<EntryDistrict> getEntries() {
		return entries;
	}

	public void setEntries(List<EntryDistrict> entries) {
		this.entries = entries;
	}		
	
}
