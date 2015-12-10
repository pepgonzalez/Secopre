package ideasw.secopre.model.catalog;

import ideasw.secopre.model.base.AuditEntity;
import ideasw.secopre.model.base.Persistible;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "POSITION", indexes = { @Index(unique = true, name = "position_ix", columnList = "id") })
public class Position extends AuditEntity implements Persistible {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DESCRIPTION")
	@Size(max = 100)
	private String description;

	@Column(name = "NAME")
	@Size(max = 50)
	private String name;
	
	@Column(name = "ES_DIRECTOR")
	private Long isDirector;

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Long getIsDirector() {
		return isDirector;
	}

	public void setIsDirector(Long isDirector) {
		this.isDirector = isDirector;
	}



}
