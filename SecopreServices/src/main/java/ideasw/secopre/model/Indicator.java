package ideasw.secopre.model;

import ideasw.secopre.enums.Alignment;
import ideasw.secopre.enums.IndicatorSignus;
import ideasw.secopre.enums.IndicatorType;
import ideasw.secopre.model.base.Persistible;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "INDICATOR", indexes = { @Index(unique = true, name = "indicator_ix", columnList = "id") })
public class Indicator implements Persistible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "INDICATOR_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private IndicatorType indicatorType;

	@Column(name = "SQL_SENTENCE", nullable = false, length = 1000)
	private String sqlSentence;

	@Column(name = "TITLE", nullable = false, length = 100)
	private String title;

	@Column(name = "INDICATOR_SIGNUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private IndicatorSignus signus;

	@Column(name = "ALIGNMENT_SIGNUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private Alignment alignment;

	@Column(name = "ICON", nullable = false, length = 100)
	private String icon;
	
	// bi-directional many-to-one association to permissionPath
	@NotAudited
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "INDICATOR_PARAMETER", joinColumns = { @JoinColumn(name = "INDICATOR_ID") }, inverseJoinColumns = { @JoinColumn(name = "PARAMETER_ID") })
	private List<Parameter> parameters;

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
	 * @return the indicatorType
	 */
	public IndicatorType getIndicatorType() {
		return indicatorType;
	}

	/**
	 * @param indicatorType
	 *            the indicatorType to set
	 */
	public void setIndicatorType(IndicatorType indicatorType) {
		this.indicatorType = indicatorType;
	}

	/**
	 * @return the sqlSentence
	 */
	public String getSqlSentence() {
		return sqlSentence;
	}

	/**
	 * @param sqlSentence
	 *            the sqlSentence to set
	 */
	public void setSqlSentence(String sqlSentence) {
		this.sqlSentence = sqlSentence;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the signus
	 */
	public IndicatorSignus getSignus() {
		return signus;
	}

	/**
	 * @param signus
	 *            the signus to set
	 */
	public void setSignus(IndicatorSignus signus) {
		this.signus = signus;
	}

	/**
	 * @return the alignment
	 */
	public Alignment getAlignment() {
		return alignment;
	}

	/**
	 * @param alignment
	 *            the alignment to set
	 */
	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	/**
	 * @return the parameters
	 */
	public List<Parameter> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

}
