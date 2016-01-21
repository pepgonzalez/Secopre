package ideasw.secopre.dto;

import java.text.DecimalFormat;
import java.util.List;

public class EntryBalance {

	private Double annualAmount = 0D;
	private Double budgetAmount = 0D;
	private Double budgetAsing = 0D;
	private Double budgetCommit = 0D;
	DecimalFormat formatter = new DecimalFormat("###,###,###.00");
	private List<EntryDistrictDetail> entries;

	public Double getAnnualAmount() {
		return annualAmount;
	}

	public String getAnnualAmountStr() {
		return annualAmount == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(annualAmount);
	}

	public void setAnnualAmount(Double annualAmount) {
		this.annualAmount = annualAmount;
	}

	public Double getBudgetAmount() {

		return budgetAmount == null ? 0D : budgetAmount;
	}

	public String getBudgetAmountStr() {
		return budgetAmount == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(budgetAmount);
	}

	public void setBudgetAmount(Double budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public Double getBudgetAsing() {
		return budgetAsing == null ? 0D : budgetAsing;
	}

	public String getBudgetAsingStr() {
		return budgetAsing == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(budgetAsing);
	}	
	public void setBudgetAsing(Double budgetAsing) {
		this.budgetAsing = budgetAsing;
	}

	public Double getBudgetCommit() {
		return budgetCommit == null ? 0D : budgetCommit;
	}

	public String getBudgetCommitStr() {
		return budgetCommit == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(budgetCommit);
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

	public String getAvailableStr() {
		return budgetAsing == null  && budgetCommit == null ? "$ " + formatter.format(0D) : "$ "
				+ formatter.format(budgetAsing - budgetCommit);
	}	
	
}
