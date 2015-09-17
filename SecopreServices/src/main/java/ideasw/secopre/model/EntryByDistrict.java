package ideasw.secopre.model;

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

@Entity
@Table(name = "ENTRY_DISTRICT", indexes = { @Index(unique = true, name = "entry_district_ix", columnList = "id") })
public class EntryByDistrict implements Persistible {

	/**
    *
    */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DISTRICT_ID", nullable = false, updatable = false)
	private District district;

	@Column(name = "ANNUAL_AMOUNT", columnDefinition = "Decimal(10,2)")
	private Double annualAmount;

	@Column(name = "BUDGET_MONTH1", columnDefinition = "Decimal(10,2)")
	private Double budgetMonth1;

	@Column(name = "BUDGET_MONTH2", columnDefinition = "Decimal(10,2)")
	private Double budgetMonth2;

	@Column(name = "BUDGET_MONTH3", columnDefinition = "Decimal(10,2)")
	private Double budgetMonth3;

	@Column(name = "BUDGET_MONTH4", columnDefinition = "Decimal(10,2)")
	private Double budgetMonth4;

	@Column(name = "BUDGET_MONTH5", columnDefinition = "Decimal(10,2)")
	private Double budgetMonth5;

	@Column(name = "BUDGET_MONTH6", columnDefinition = "Decimal(10,2)")
	private Double budgetMonth6;

	@Column(name = "BUDGET_MONTH7", columnDefinition = "Decimal(10,2)")
	private Double budgetMonth7;

	@Column(name = "BUDGET_MONTH8", columnDefinition = "Decimal(10,2)")
	private Double budgetMonth8;

	@Column(name = "BUDGET_MONTH9", columnDefinition = "Decimal(10,2)")
	private Double budgetMonth9;

	@Column(name = "BUDGET_MONTH10", columnDefinition = "Decimal(10,2)")
	private Double budgetMonth10;

	@Column(name = "BUDGET_MONTH11", columnDefinition = "Decimal(10,2)")
	private Double budgetMonth11;

	@Column(name = "BUDGET_MONTH12", columnDefinition = "Decimal(10,2)")
	private Double budgetMonth12;

	@Column(name = "BUDGET_MONTH_ASSIGN1", columnDefinition = "Decimal(10,2)")
	private Double budgetMonthAssing1;

	@Column(name = "BUDGET_MONTH_ASSIGN2", columnDefinition = "Decimal(10,2)")
	private Double budgetMonthAssing2;

	@Column(name = "BUDGET_MONTH_ASSIGN3", columnDefinition = "Decimal(10,2)")
	private Double budgetMonthAssing3;

	@Column(name = "BUDGET_MONTH_ASSIGN4", columnDefinition = "Decimal(10,2)")
	private Double budgetMonthAssing4;

	@Column(name = "BUDGET_MONTH_ASSIGN5", columnDefinition = "Decimal(10,2)")
	private Double budgetMonthAssing5;

	@Column(name = "BUDGET_MONTH_ASSIGN6", columnDefinition = "Decimal(10,2)")
	private Double budgetMonthAssing6;

	@Column(name = "BUDGET_MONTH_ASSIGN7", columnDefinition = "Decimal(10,2)")
	private Double budgetMonthAssing7;

	@Column(name = "BUDGET_MONTH_ASSIGN8", columnDefinition = "Decimal(10,2)")
	private Double budgetMonthAssing8;

	@Column(name = "BUDGET_MONTH_ASSIGN9", columnDefinition = "Decimal(10,2)")
	private Double budgetMonthAssing9;

	@Column(name = "BUDGET_MONTH_ASSIGN10", columnDefinition = "Decimal(10,2)")
	private Double budgetMonthAssing10;

	@Column(name = "BUDGET_MONTH_ASSIGN11", columnDefinition = "Decimal(10,2)")
	private Double budgetMonthAssing11;

	@Column(name = "BUDGET_MONTH_ASSIGN12", columnDefinition = "Decimal(10,2)")
	private Double budgetMonthAssing12;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the district
	 */
	public District getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(District district) {
		this.district = district;
	}

	/**
	 * @return the annualAmount
	 */
	public Double getAnnualAmount() {
		return annualAmount;
	}

	/**
	 * @param annualAmount
	 *            the annualAmount to set
	 */
	public void setAnnualAmount(Double annualAmount) {
		this.annualAmount = annualAmount;
	}

	/**
	 * @return the budgetMonth1
	 */
	public Double getBudgetMonth1() {
		return budgetMonth1;
	}

	/**
	 * @param budgetMonth1
	 *            the budgetMonth1 to set
	 */
	public void setBudgetMonth1(Double budgetMonth1) {
		this.budgetMonth1 = budgetMonth1;
	}

	/**
	 * @return the budgetMonth2
	 */
	public Double getBudgetMonth2() {
		return budgetMonth2;
	}

	/**
	 * @param budgetMonth2
	 *            the budgetMonth2 to set
	 */
	public void setBudgetMonth2(Double budgetMonth2) {
		this.budgetMonth2 = budgetMonth2;
	}

	/**
	 * @return the budgetMonth3
	 */
	public Double getBudgetMonth3() {
		return budgetMonth3;
	}

	/**
	 * @param budgetMonth3
	 *            the budgetMonth3 to set
	 */
	public void setBudgetMonth3(Double budgetMonth3) {
		this.budgetMonth3 = budgetMonth3;
	}

	/**
	 * @return the budgetMonth4
	 */
	public Double getBudgetMonth4() {
		return budgetMonth4;
	}

	/**
	 * @param budgetMonth4
	 *            the budgetMonth4 to set
	 */
	public void setBudgetMonth4(Double budgetMonth4) {
		this.budgetMonth4 = budgetMonth4;
	}

	/**
	 * @return the budgetMonth5
	 */
	public Double getBudgetMonth5() {
		return budgetMonth5;
	}

	/**
	 * @param budgetMonth5
	 *            the budgetMonth5 to set
	 */
	public void setBudgetMonth5(Double budgetMonth5) {
		this.budgetMonth5 = budgetMonth5;
	}

	/**
	 * @return the budgetMonth6
	 */
	public Double getBudgetMonth6() {
		return budgetMonth6;
	}

	/**
	 * @param budgetMonth6
	 *            the budgetMonth6 to set
	 */
	public void setBudgetMonth6(Double budgetMonth6) {
		this.budgetMonth6 = budgetMonth6;
	}

	/**
	 * @return the budgetMonth7
	 */
	public Double getBudgetMonth7() {
		return budgetMonth7;
	}

	/**
	 * @param budgetMonth7
	 *            the budgetMonth7 to set
	 */
	public void setBudgetMonth7(Double budgetMonth7) {
		this.budgetMonth7 = budgetMonth7;
	}

	/**
	 * @return the budgetMonth8
	 */
	public Double getBudgetMonth8() {
		return budgetMonth8;
	}

	/**
	 * @param budgetMonth8
	 *            the budgetMonth8 to set
	 */
	public void setBudgetMonth8(Double budgetMonth8) {
		this.budgetMonth8 = budgetMonth8;
	}

	/**
	 * @return the budgetMonth9
	 */
	public Double getBudgetMonth9() {
		return budgetMonth9;
	}

	/**
	 * @param budgetMonth9
	 *            the budgetMonth9 to set
	 */
	public void setBudgetMonth9(Double budgetMonth9) {
		this.budgetMonth9 = budgetMonth9;
	}

	/**
	 * @return the budgetMonth10
	 */
	public Double getBudgetMonth10() {
		return budgetMonth10;
	}

	/**
	 * @param budgetMonth10
	 *            the budgetMonth10 to set
	 */
	public void setBudgetMonth10(Double budgetMonth10) {
		this.budgetMonth10 = budgetMonth10;
	}

	/**
	 * @return the budgetMonth11
	 */
	public Double getBudgetMonth11() {
		return budgetMonth11;
	}

	/**
	 * @param budgetMonth11
	 *            the budgetMonth11 to set
	 */
	public void setBudgetMonth11(Double budgetMonth11) {
		this.budgetMonth11 = budgetMonth11;
	}

	/**
	 * @return the budgetMonth12
	 */
	public Double getBudgetMonth12() {
		return budgetMonth12;
	}

	/**
	 * @param budgetMonth12
	 *            the budgetMonth12 to set
	 */
	public void setBudgetMonth12(Double budgetMonth12) {
		this.budgetMonth12 = budgetMonth12;
	}

	/**
	 * @return the budgetMonthAssing1
	 */
	public Double getBudgetMonthAssing1() {
		return budgetMonthAssing1;
	}

	/**
	 * @param budgetMonthAssing1
	 *            the budgetMonthAssing1 to set
	 */
	public void setBudgetMonthAssing1(Double budgetMonthAssing1) {
		this.budgetMonthAssing1 = budgetMonthAssing1;
	}

	/**
	 * @return the budgetMonthAssing2
	 */
	public Double getBudgetMonthAssing2() {
		return budgetMonthAssing2;
	}

	/**
	 * @param budgetMonthAssing2
	 *            the budgetMonthAssing2 to set
	 */
	public void setBudgetMonthAssing2(Double budgetMonthAssing2) {
		this.budgetMonthAssing2 = budgetMonthAssing2;
	}

	/**
	 * @return the budgetMonthAssing3
	 */
	public Double getBudgetMonthAssing3() {
		return budgetMonthAssing3;
	}

	/**
	 * @param budgetMonthAssing3
	 *            the budgetMonthAssing3 to set
	 */
	public void setBudgetMonthAssing3(Double budgetMonthAssing3) {
		this.budgetMonthAssing3 = budgetMonthAssing3;
	}

	/**
	 * @return the budgetMonthAssing4
	 */
	public Double getBudgetMonthAssing4() {
		return budgetMonthAssing4;
	}

	/**
	 * @param budgetMonthAssing4
	 *            the budgetMonthAssing4 to set
	 */
	public void setBudgetMonthAssing4(Double budgetMonthAssing4) {
		this.budgetMonthAssing4 = budgetMonthAssing4;
	}

	/**
	 * @return the budgetMonthAssing5
	 */
	public Double getBudgetMonthAssing5() {
		return budgetMonthAssing5;
	}

	/**
	 * @param budgetMonthAssing5
	 *            the budgetMonthAssing5 to set
	 */
	public void setBudgetMonthAssing5(Double budgetMonthAssing5) {
		this.budgetMonthAssing5 = budgetMonthAssing5;
	}

	/**
	 * @return the budgetMonthAssing6
	 */
	public Double getBudgetMonthAssing6() {
		return budgetMonthAssing6;
	}

	/**
	 * @param budgetMonthAssing6
	 *            the budgetMonthAssing6 to set
	 */
	public void setBudgetMonthAssing6(Double budgetMonthAssing6) {
		this.budgetMonthAssing6 = budgetMonthAssing6;
	}

	/**
	 * @return the budgetMonthAssing7
	 */
	public Double getBudgetMonthAssing7() {
		return budgetMonthAssing7;
	}

	/**
	 * @param budgetMonthAssing7
	 *            the budgetMonthAssing7 to set
	 */
	public void setBudgetMonthAssing7(Double budgetMonthAssing7) {
		this.budgetMonthAssing7 = budgetMonthAssing7;
	}

	/**
	 * @return the budgetMonthAssing8
	 */
	public Double getBudgetMonthAssing8() {
		return budgetMonthAssing8;
	}

	/**
	 * @param budgetMonthAssing8
	 *            the budgetMonthAssing8 to set
	 */
	public void setBudgetMonthAssing8(Double budgetMonthAssing8) {
		this.budgetMonthAssing8 = budgetMonthAssing8;
	}

	/**
	 * @return the budgetMonthAssing9
	 */
	public Double getBudgetMonthAssing9() {
		return budgetMonthAssing9;
	}

	/**
	 * @param budgetMonthAssing9
	 *            the budgetMonthAssing9 to set
	 */
	public void setBudgetMonthAssing9(Double budgetMonthAssing9) {
		this.budgetMonthAssing9 = budgetMonthAssing9;
	}

	/**
	 * @return the budgetMonthAssing10
	 */
	public Double getBudgetMonthAssing10() {
		return budgetMonthAssing10;
	}

	/**
	 * @param budgetMonthAssing10
	 *            the budgetMonthAssing10 to set
	 */
	public void setBudgetMonthAssing10(Double budgetMonthAssing10) {
		this.budgetMonthAssing10 = budgetMonthAssing10;
	}

	/**
	 * @return the budgetMonthAssing11
	 */
	public Double getBudgetMonthAssing11() {
		return budgetMonthAssing11;
	}

	/**
	 * @param budgetMonthAssing11
	 *            the budgetMonthAssing11 to set
	 */
	public void setBudgetMonthAssing11(Double budgetMonthAssing11) {
		this.budgetMonthAssing11 = budgetMonthAssing11;
	}

	/**
	 * @return the budgetMonthAssing12
	 */
	public Double getBudgetMonthAssing12() {
		return budgetMonthAssing12;
	}

	/**
	 * @param budgetMonthAssing12
	 *            the budgetMonthAssing12 to set
	 */
	public void setBudgetMonthAssing12(Double budgetMonthAssing12) {
		this.budgetMonthAssing12 = budgetMonthAssing12;
	}

}
