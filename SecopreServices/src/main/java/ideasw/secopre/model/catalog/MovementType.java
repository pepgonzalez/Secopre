package ideasw.secopre.model.catalog;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import ideasw.secopre.model.base.Persistible;

@Entity
@Table(name = "MOVEMENT_TYPE", indexes = { @Index(unique = true, name = "movement_type_ix", columnList = "id") })
public class MovementType implements Persistible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DESCRIPTION", nullable = false, length = 100)
	private String description;

	@Column(name = "CODE", nullable = false, length = 50)
	private String code;
	
	@Column(name = "IS_ELEGIBLE", nullable = false, length = 1)
	private Integer isElegible;

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
	 * @return the code
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the name
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIsElegible() {
		return isElegible;
	}

	public void setIsElegible(Integer isElegible) {
		this.isElegible = isElegible;
	}
}
