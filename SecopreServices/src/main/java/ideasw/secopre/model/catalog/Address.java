package ideasw.secopre.model.catalog;

import ideasw.secopre.model.base.AuditEntity;
import ideasw.secopre.model.base.Persistible;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS", indexes = { @Index(unique = true, name = "address_ix", columnList = "id") })
public class Address extends AuditEntity implements Persistible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "STREET", nullable = false, length = 150)
	private String street;
	@Column(name = "NUMBER", nullable = true, length = 50)
	private String exteriorNumber;
	@Column(name = "NUMBER_INT", nullable = true, length = 50)
	private String interiorNumber;
//	@Column(name = "STATE", unique = true, length = 50)
//	private String state;
	@Column(name = "CITY",  length = 50)
	private String city;
	@Column(name = "COLONY", length = 50)
	private String colony;
	@Column(name = "ZIP_CODE", nullable = false, length = 5)
	private String zipCode;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "STATE_ID", referencedColumnName = "ID")
	private State stateDTO;
	

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
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}


	/**
	 * @return the interiorNumber
	 */
	public String getInteriorNumber() {
		return interiorNumber;
	}

	/**
	 * @param interiorNumber
	 *            the interiorNumber to set
	 */
	public void setInteriorNumber(String interiorNumber) {
		this.interiorNumber = interiorNumber;
	}

	/**
	 * @return the state
	 */
//	public String getState() {
//		return state;
//	}
//
//	/**
//	 * @param state
//	 *            the state to set
//	 */
//	public void setState(String state) {
//		this.state = state;
//	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getColony() {
		return colony;
	}

	public void setColony(String colony) {
		this.colony = colony;
	}

	public State getStateDTO() {
		return stateDTO;
	}

	public void setStateDTO(State stateDTO) {
		this.stateDTO = stateDTO;
	}

	public String getExteriorNumber() {
		return exteriorNumber;
	}

	public void setExteriorNumber(String exteriorNumber) {
		this.exteriorNumber = exteriorNumber;
	}

}
