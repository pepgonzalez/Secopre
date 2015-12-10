/**
 * 
 */
package ideasw.secopre.model;

import ideasw.secopre.model.base.AuditEntity;
import ideasw.secopre.model.base.Persistible;
import ideasw.secopre.model.security.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Entidad que permite almacenar la informacion basica de un director, 
 * 
 * @author jesus.gallardos@gmail.com
 *
 */
@Entity
@Audited
@Table(name = "DIRECTOR", indexes = {
		@Index(unique = true, name = "director_ix", columnList = "id") })
public class Director implements Persistible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ACTIVE")
	private boolean active;

	@ManyToOne
	@JoinColumn(name = "USER_ID") 
	private User user;

	@Column(name = "INITIAL_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date initialDate;
	
	@Transient
	private String initialDateStr;

	@Column(name = "FINAL_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date finalDate;

	@Transient
	private String finalDateStr;
	
	@Column(name = "LEGEND",  length = 200)
	private String legend;	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}
	
	public String getInitialDateStr() {
		String initialDateStr=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(this.initialDate!=null){
	    initialDateStr  = sdf.format(this.initialDate ); 
		}
	    return initialDateStr;
		
	}

	public void setInitialDateStr(String initialDateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date initialDate = sdf.parse(initialDateStr);
			this.setInitialDate(initialDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFinalDateStr() {
		String finalDateStr=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(this.finalDate != null)
	    {finalDateStr  = sdf.format(this.finalDate ); 
	    }
		return finalDateStr;
	}

	public void setFinalDateStr(String finalDateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date finalDate = sdf.parse(finalDateStr);
			this.setFinalDate(finalDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getLegend() {
		return legend;
	}

	public void setLegend(String legend) {
		this.legend = legend;
	}





}
