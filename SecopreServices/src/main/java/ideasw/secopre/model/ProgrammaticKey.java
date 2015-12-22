package ideasw.secopre.model;

import ideasw.secopre.model.base.AuditEntity;
import ideasw.secopre.model.base.Persistible;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Clase que modela los agrupadores de partidas contables dentro de la
 * aplicacion Secopre
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Entity
@Table(name = "PROGRAMMATIC_KEY", indexes = { @Index(unique = true, name = "programmatic_ix", columnList = "id") })
public class ProgrammaticKey extends AuditEntity implements Persistible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "YEAR", nullable = true)
	private Integer year;
	
	@Column(name = "RAMO", nullable = true)
	private String ramo;
	
	@Column(name = "UNIT_RESPONSABLE", nullable = true)
	private String unitResponsable;
	
	@Column(name = "PROGRAM", nullable = true)
	private String program;
	
	@Column(name = "FINANCING_SOURCE", nullable = true)
	private String financingSource;
	
	@Column(name = "FUNCTIONAL_GROUP", nullable = true)
	private String functionalGroup;
	
	@Column(name = "ACTIVITY", nullable = true)
	private String activity;
	
	@Column(name = "FUNCTION", nullable = true)
	private String function;
	
	@Column(name = "PROGRAM_BUDGET", nullable = true)
	private String programBudget;
	
	@Column(name = "SUBFUNCTION", nullable = true)
	private String subfunction;
	
	@Column(name = "EXPENSE_TYPE", nullable = true)
	private String expenseType;
	
	@Column(name = "CODE", nullable = true)
	private String code;
	
	@Column(name = "FINALITY", nullable = true)
	private String finality;
	
	@Transient
	private String description;

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
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getFinality() {
		return finality;
	}

	public void setFinality(String finality) {
		this.finality = finality;
	}

	public String getSubfunction() {
		return subfunction;
	}

	public void setSubfunction(String subfunction) {
		this.subfunction = subfunction;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getProgramBudget() {
		return programBudget;
	}

	public void setProgramBudget(String programBudget) {
		this.programBudget = programBudget;
	}

	public String getUnitResponsable() {
		return unitResponsable;
	}

	public void setUnitResponsable(String unitResponsable) {
		this.unitResponsable = unitResponsable;
	}

	public String getDescription() {
//		description = getFullKey();
		return description;
		//return this.function.concat(" ").concat(this.finality).concat(" ").concat(this.subfunction).concat(" ").concat(activity).concat(" ").concat(programBudget).concat(" ").concat(this.unitResponsable);
	}
	
	public String getFullKey(){
		StringBuffer sb = new StringBuffer();
		
		sb.append(year).append(" ");
		sb.append(ramo).append(" ");
		sb.append(unitResponsable).append(" ");
		sb.append(functionalGroup).append(" ");
		sb.append(function).append(" ");
		sb.append(subfunction).append(" ");
		sb.append(program).append(" ");
		sb.append(activity).append(" ");
		sb.append(programBudget).append(" ");
		sb.append(expenseType).append(" ");
		sb.append(financingSource);
		
		return sb.toString();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRamo() {
		return ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getFinancingSource() {
		return financingSource;
	}

	public void setFinancingSource(String financingSource) {
		this.financingSource = financingSource;
	}

	public String getFunctionalGroup() {
		return functionalGroup;
	}

	public void setFunctionalGroup(String functionalGroup) {
		this.functionalGroup = functionalGroup;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

}
