package ideasw.secopre.model;

import javax.persistence.Transient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.format.annotation.DateTimeFormat;



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
	
	@Transient
	private String registerDateStr;
	
	@Transient
	private String displayDateStr;
	
	@Column(name = "NOTICE", nullable = false)
	private String noticeInfo;
	
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


	public List<District> getDistrs() {
		return distrs;
	}

	public void setDistrs(List<District> distrs) {
		this.distrs = distrs;
	}

	public String getNoticeInfo() {
		return noticeInfo;
	}

	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo;
	}

	public String getRegisterDateStr() {
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 String registerDateStr = null;
		 if(this.registerDate!=null)
		 {
		 registerDateStr = sdf.format(this.registerDate); 
		 }
		 return registerDateStr;
	}

	public void setRegisterDateStr(String registerDateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date registerDate = sdf.parse(registerDateStr);
			this.setRegisterDate(registerDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getDisplayDateStr() {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String displayDateStr = null;
		if(this.displayDate!=null)
		{displayDateStr = sdf.format(this.displayDate ); }
		return displayDateStr;

	}

	public void setDisplayDateStr(String displayDateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date displayDate = sdf.parse(displayDateStr);
			this.setDisplayDate(displayDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
