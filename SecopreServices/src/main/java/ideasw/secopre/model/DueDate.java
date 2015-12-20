package ideasw.secopre.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ideasw.secopre.model.base.AuditEntity;
import ideasw.secopre.model.base.Persistible;
import ideasw.secopre.model.catalog.District;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;


/**
 * Clase que modela las fechas corte dentro de la aplicacion Secopre
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Entity
@Table(name = "DUE_DATE", indexes = { @Index(unique = true, name = "due_ix", columnList = "id") })
public class DueDate extends AuditEntity implements Persistible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DUE_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	
	@Transient
	private String dueDateStr;

	@Column(name = "MAX_BLOCK_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date maxBlockDate;

	@Transient
	private String maxBlockDateStr;
	
	
	@NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
    @JoinTable(name = "DUEDATE_DISTRICT",
        joinColumns = {@JoinColumn(name="DUEDATE_ID")},
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

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the maxBlockDate
	 */
	public Date getMaxBlockDate() {
		return maxBlockDate;
	}

	/**
	 * @param maxBlockDate
	 *            the maxBlockDate to set
	 */
	public void setMaxBlockDate(Date maxBlockDate) {
      this.maxBlockDate = maxBlockDate;
	}

	public String getDueDateStr() {
		String dueDateStr=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(this.dueDate!=null){
	    dueDateStr  = sdf.format(this.dueDate ); 
		}
	    return dueDateStr;
		
	}

	public void setDueDateStr(String dueDateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dueDate = sdf.parse(dueDateStr);
			this.setDueDate(dueDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getMaxBlockDateStr() {
		String maxBlockDateStr=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(this.maxBlockDate != null)
	    {maxBlockDateStr  = sdf.format(this.maxBlockDate ); 
	    }
		return maxBlockDateStr;
	}

	public void setMaxBlockDateStr(String maxBlockDateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date maxBlockDate = sdf.parse(maxBlockDateStr);
			this.setMaxBlockDate(maxBlockDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<District> getDistrs() {
		return distrs;
	}

	public void setDistrs(List<District> distrs) {
		this.distrs = distrs;
	}
}
