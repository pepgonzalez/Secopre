package ideasw.secopre.model;

import ideasw.secopre.model.base.AuditEntity;
import ideasw.secopre.model.base.Persistible;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@Column(name = "MAX_BLOCK_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date maxBlockDate;

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

}
