package ideasw.secopre.model;

import ideasw.secopre.enums.IndicatorType;
import ideasw.secopre.model.base.Persistible;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "DASHBOARD", indexes = { @Index(unique = true, name = "dashboard_ix", columnList = "id") })
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

}
