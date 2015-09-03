/**
 * 
 */
package ideasw.secopre.model.security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ideasw.secopre.model.base.Persistible;

/**
 * Esta entidad permite almacenar el control de acceso de todos los usuarios de
 * la aplicacion
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Entity
@Table(name = "USER_ACCESS", indexes = { @Index(unique = true, name = "user_access_ix", columnList = "id") })
public class UserAccess implements Persistible {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;

	@Column(name = "LOGIN_DATE")
	private Date loginDate;

	@Column(name = "LOGOUT_DATE")
	private Date logoutDate;

	@Column(name = "REMOTE_IP")
	private String remoteIP;

	@Column(name = "JSESSIONID")
	private String jSessionId;
	
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

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getLogoutDate() {
		return logoutDate;
	}

	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}

	public String getRemoteIP() {
		return remoteIP;
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

	/**
	 * @return the jSessionId
	 */
	public String getjSessionId() {
		return jSessionId;
	}

	/**
	 * @param jSessionId the jSessionId to set
	 */
	public void setjSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}

}
