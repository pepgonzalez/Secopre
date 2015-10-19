package ideasw.secopre.model;

import java.util.Date;
import java.util.List;

import ideasw.secopre.model.base.AuditEntity;
import ideasw.secopre.model.base.Persistible;
import ideasw.secopre.model.catalog.District;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;



/**
 * Clase que modela las notificaciones dentro de la aplicacion Secopre
 * 
 * @author jesus.gallardos@gmail.com
 *
 */
@Entity
@Table(name = "NOTICE", indexes = { @Index(unique = true, name = "notice_ix", columnList = "id") })
public class Notice extends AuditEntity implements Persistible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "REGISTER_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date registerDate;

	@Column(name = "DISPLAY_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date displayDate;
	
	@Column(name = "NOTICE", nullable = false)
	private String notice;
	
	// bi-directional many-to-one association 

	@NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
    @JoinTable(name = "NOTICE_DISTRICT",
        joinColumns = {@JoinColumn(name="NOTICE_ID")},
        inverseJoinColumns = {@JoinColumn(name="DISTRICT_ID")}
    )
    private List<District> distrs;

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


	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getDisplayDate() {
		return displayDate;
	}

	public void setDisplayDate(Date displayDate) {
		this.displayDate = displayDate;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public List<District> getDistrs() {
		return distrs;
	}

	public void setDistrs(List<District> distrs) {
		this.distrs = distrs;
	}


}
