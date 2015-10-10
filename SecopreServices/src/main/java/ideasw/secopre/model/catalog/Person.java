/**
 * 
 */
package ideasw.secopre.model.catalog;

import ideasw.secopre.enums.PersonType;
import ideasw.secopre.model.base.Persistible;
import ideasw.secopre.model.base.PersonBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/**
 * @author jorge.cano
 *
 */
@Entity
@Audited
@Table(name = "PERSON", indexes = {
		@Index(unique = true, name = "person_ix", columnList = "id"),
		@Index(unique = true, name = "rfc_ix", columnList = "rfc") })
public class Person extends PersonBase implements Persistible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="TYPE")
	@Enumerated(EnumType.STRING)
	private PersonType personType = PersonType.FISICA;

	/**
	 * @return the personType
	 */
	public PersonType getPersonType() {
		return personType;
	}

	/**
	 * @param personType the personType to set
	 */
	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}
	
}
