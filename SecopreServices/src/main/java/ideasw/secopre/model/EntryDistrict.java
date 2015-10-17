package ideasw.secopre.model;

import ideasw.secopre.model.base.Persistible;
import ideasw.secopre.model.catalog.District;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ENTRYDISTRICT", indexes = { @Index(unique = true, name = "entrydistrict_ix", columnList = "id") })
public class EntryDistrict implements Persistible {

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

	@Column(name = "MONTH", columnDefinition = "Decimal(10,2)")
	private Double month;

	@Column(name = "BUDGET_AMOUNT", columnDefinition = "Decimal(10,2)")
	private Double budgetAmount;

	@Column(name = "BUDGET_AMOUNT_ASSIGN", columnDefinition = "Decimal(10,2)")
	private Double budgetAmountAssign;
	
	@Column(name = "LAST_UPDATE_DATE", columnDefinition = "Datetime")
	private Date lastUpdateDate;

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

	public Double getMonth() {
		return month;
	}

	public void setMonth(Double month) {
		this.month = month;
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

}
