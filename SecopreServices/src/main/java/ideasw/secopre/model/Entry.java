/**
 * 
 */
package ideasw.secopre.model;

import ideasw.secopre.enums.AccountingType;
import ideasw.secopre.enums.StatusEntry;
import ideasw.secopre.model.base.AuditEntity;
import ideasw.secopre.model.base.Persistible;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que modela las distintas partidas contables dentro de la aplicacion
 * Secopre
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Entity
@Table(name = "ENTRY", indexes = { @Index(unique = true, name = "entry_ix", columnList = "id") })
public class Entry extends AuditEntity implements Persistible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CODE", nullable = false)
	private Integer code;

	@Column(name = "NAME", nullable = false, length = 250)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false, length = 250)
	private String description;

	//(cascade = CascadeType.ALL)
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "PROGRAMMATIC_ID", nullable = false, updatable = true)
	private ProgrammaticKey programmaticKey;
	

	@Column(name = "ACCOUNTING_TYPE")
	@Enumerated(EnumType.STRING)
	private AccountingType accountingType;

	@ManyToOne
	@JoinColumn(name = "ENTRY_CONCEPT_ID")
	private Entry concept;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private StatusEntry status;
	
	public StatusEntry getStatus() {
		return status;
	}

	public void setStatus(StatusEntry status) {
		this.status = status;
	}

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
	 * @return the programmaticKey
	 */
	public ProgrammaticKey getProgrammaticKey() {
		return programmaticKey;
	}

	/**
	 * @param programmaticKey
	 *            the programmaticKey to set
	 */
	public void setProgrammaticKey(ProgrammaticKey programmaticKey) {
		this.programmaticKey = programmaticKey;
	}

	/**
	 * @return the accountingType
	 */
	public AccountingType getAccountingType() {
		return accountingType;
	}

	/**
	 * @param accountingType
	 *            the accountingType to set
	 */
	public void setAccountingType(AccountingType accountingType) {
		this.accountingType = accountingType;
	}

	/**
	 * @return the concept
	 */
	public Entry getConcept() {
		return concept;
	}

	/**
	 * @param concept
	 *            the concept to set
	 */
	public void setConcept(Entry concept) {
		this.concept = concept;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	
	public String getCodeAndDescription(){
		return this.code + " - " + this.name;
	}
}
