package ideasw.secopre.model;

import ideasw.secopre.model.base.AuditEntity;
import ideasw.secopre.model.base.Persistible;
import ideasw.secopre.model.catalog.District;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ENTRYDISTRICT", indexes = { @Index(unique = true, name = "entrydistrict_ix", columnList = "id") })
public class EntryDistrict extends AuditEntity implements Persistible {

	/**
    *
    */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DISTRICT_ID", nullable = false, updatable = false)
	private District district;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ENTRY_ID", nullable = false, updatable = false)
	private Entry entry;
	
	@Column(name = "ANNUAL_AMOUNT", columnDefinition = "Decimal(10,2)")
	private Double annualAmount;

	@Column(name = "BUDGET_AMOUNT", columnDefinition = "Decimal(10,2)")
	private Double budgetAmount;

	@Column(name = "BUDGET_AMOUNT_ASSIGN", columnDefinition = "Decimal(10,2)")
	private Double budgetAmountAssign;

	@Column(name = "COMMITTED_AMOUNT", columnDefinition = "Decimal(10,2)")
	private Double committedAmount;

	
	@Column(name = "MONTH")
	private Long month;

	@Transient
	private boolean validMovement;

	@Transient	
	private String monthString;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
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

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public boolean isValidMovement() {
		return validMovement;
	}

	public void setValidMovement(boolean validMovement) {
		this.validMovement = validMovement;
	}

	public String getMonthString() {
		return monthString;
	}

	public void setMonthString(String monthString) {
		this.monthString = monthString;
	}

}
