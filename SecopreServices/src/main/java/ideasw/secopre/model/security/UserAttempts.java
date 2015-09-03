/**
 * 
 */
package ideasw.secopre.model.security;

import ideasw.secopre.model.base.Persistible;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Esta entidad permite almacenar la informacion de intentos de acceso fallido
 * por usuario, de esta forma se puede parametrizar el maximo numero de intentos
 * 
 * @author 0jacancue
 *
 */
@Entity
@Table(name = "USER_ATTEMPTS", indexes = { @Index(unique = true, name = "user_attempts_ix", columnList = "id") })
public class UserAttempts implements Persistible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;

	@Column(name = "ATTEMPTS")
	private int attempts;

	@Column(name = "LAST_MODIFIED")
	private Date lastModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

}
