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

	@Column(name = "CODE", nullable = false)
	private String code;

	@Column(name = "YEAR", nullable = false)
	private Integer year;
	
	@Column(name = "FUNCTION", nullable = true)
	private String function;
	
	@Column(name = "FINALITY", nullable = true)
	private String finality;
	
	@Column(name = "SUBFUNCTION", nullable = true)
	private String subfunction;

	@Column(name = "ACTIVITY", nullable = true)
	private String activity;
	
	@Column(name = "PROGRAM_BUDGET", nullable = true)
	private String programBudget;
	
	@Column(name = "UNIT_RESPONSABLE", nullable = true)
	private String unitResponsable;

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

}
