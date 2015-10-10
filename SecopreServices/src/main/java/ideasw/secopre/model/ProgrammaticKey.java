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

}