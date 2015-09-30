package ideasw.secopre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import ideasw.secopre.model.base.AuditEntity;
import ideasw.secopre.model.base.Persistible;

@Entity
@Table(name = "DASHBOARD", indexes = { @Index(unique = true, name = "dashboard_ix", columnList = "id") })
public class Dashboard extends AuditEntity implements Persistible {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
