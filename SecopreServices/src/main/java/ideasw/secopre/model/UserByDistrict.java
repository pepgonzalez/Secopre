package ideasw.secopre.model;

import ideasw.secopre.model.base.Persistible;
import ideasw.secopre.model.catalog.District;
import ideasw.secopre.model.security.User;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DISTRICT", indexes = { @Index(unique = true, name = "user_district_ix", columnList = "id") })
public class UserByDistrict implements Persistible {

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
	@JoinColumn(name = "USER_ID", nullable = false, updatable = false)
	private User user;

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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
