package ideasw.secopre.model.security;

import ideasw.secopre.model.base.Persistible;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Entidad que permite almacenar los paths definidos para la aplicacion, estos
 * paths a su ves se pueden relacionar a los diferentes permisos del sistema
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Entity
@Table(name = "PATH", indexes = { @Index(unique = true, name = "path_ix", columnList = "id") })
public class Path implements Persistible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ACTIVE")
	private boolean active;

	@Column(name = "PUBLIC_ACCESS")
	private boolean publicAccess;

	@Column(name = "URL", unique = true)
	@Size(max = 50)
	private String url;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Menu menu;	

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
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the publicAccess
	 */
	public boolean isPublicAccess() {
		return publicAccess;
	}

	/**
	 * @param publicAccess
	 *            the publicAccess to set
	 */
	public void setPublicAccess(boolean publicAccess) {
		this.publicAccess = publicAccess;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
