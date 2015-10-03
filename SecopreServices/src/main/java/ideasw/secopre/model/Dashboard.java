package ideasw.secopre.model;

import ideasw.secopre.model.base.AuditEntity;
import ideasw.secopre.model.base.Persistible;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Table(name = "DASHBOARD", indexes = { @Index(unique = true, name = "dashboard_ix", columnList = "id") })
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Dashboard extends AuditEntity implements Persistible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	// bi-directional many-to-one association to permissionPath
	@NotAudited
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "DASHBOARD_INDICATOR", joinColumns = { @JoinColumn(name = "DASHBOARD_ID") }, inverseJoinColumns = { @JoinColumn(name = "INDICATOR_ID") })
	private List<Indicator> indicators;

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
	 * @return the indicators
	 */
	public List<Indicator> getIndicators() {
		return indicators;
	}

	/**
	 * @param indicators
	 *            the indicators to set
	 */
	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}

}
