package ideasw.secopre.model.base;

import ideasw.secopre.enums.Gender;
import ideasw.secopre.model.catalog.Address;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * 
 * @author jorge.cano
 * 
 */
@MappedSuperclass
public class PersonBase extends AuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "RFC",nullable = true, length = 13)
	private String rfc;

	@Column(name = "CURP",nullable = true, length = 19)
	private String curp;

	@Column(name = "NAME",nullable = false, length = 30)
	private String name;

	@Column(name = "SECOND_NAME",nullable = true, length = 30)
	private String secondName;

	@Column(name = "FATHER_LASTNAME",nullable = false, length = 30)
	private String fatherLastName;

	@Column(name = "MOTHER_LASTNAME",nullable = true, length = 30)
	private String motherLastName;

	@Column(name = "GENDER",nullable = true)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "TELEPHONE",nullable = true, length = 30)
	private String telephone;
	
	@Column(name = "EXTENSION",nullable = true, length = 30)
	private String extension;

	@Column(name = "MOBILE_TELEPHONE",nullable = true, length = 30)
	private String mobileTelepone;

	@Column(name = "TWITTER",nullable = true, length = 50)
	private String twitter;

	@Column(name = "FACEBOOK",nullable = true, length = 50)
	private String facebook;

	@Column(name = "WEB_SITE",nullable = true, length = 300)
	private String webSite;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
	private Address address;
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
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @param rfc
	 *            the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
	 * @return the curp
	 */
	public String getCurp() {
		return curp;
	}

	/**
	 * @param curp
	 *            the curp to set
	 */
	public void setCurp(String curp) {
		this.curp = curp;
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
	 * @return the secondName
	 */
	public String getSecondName() {
		return secondName;
	}

	/**
	 * @param secondName
	 *            the secondName to set
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	/**
	 * @return the fatherLastName
	 */
	public String getFatherLastName() {
		return fatherLastName;
	}

	/**
	 * @param fatherLastName
	 *            the fatherLastName to set
	 */
	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}

	/**
	 * @return the motherLastName
	 */
	public String getMotherLastName() {
		return motherLastName;
	}

	/**
	 * @param motherLastName
	 *            the motherLastName to set
	 */
	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the mobileTelepone
	 */
	public String getMobileTelepone() {
		return mobileTelepone;
	}

	/**
	 * @param mobileTelepone
	 *            the mobileTelepone to set
	 */
	public void setMobileTelepone(String mobileTelepone) {
		this.mobileTelepone = mobileTelepone;
	}

	/**
	 * @return the twitter
	 */
	public String getTwitter() {
		return twitter;
	}

	/**
	 * @param twitter
	 *            the twitter to set
	 */
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	/**
	 * @return the facebook
	 */
	public String getFacebook() {
		return facebook;
	}

	/**
	 * @param facebook
	 *            the facebook to set
	 */
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	/**
	 * @return the webSite
	 */
	public String getWebSite() {
		return webSite;
	}

	/**
	 * @param webSite
	 *            the webSite to set
	 */
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}


}
