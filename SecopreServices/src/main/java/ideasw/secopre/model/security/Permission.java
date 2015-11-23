package ideasw.secopre.model.security;

import ideasw.secopre.model.base.Persistible;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Entidad que permite almacenar los permisos definidos para la aplicacion,
 * estos permisos a su ves se pueden relacionar a los diferentes roles del
 * sistema
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Entity
@Table(name = "PERMISSION", indexes = { @Index(unique = true, name = "permission_ix", columnList = "id") })
public class Permission implements Persistible {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ACTIVE")
	private boolean active;

	@Column(name = "NAME", unique = true)
	@Size(max = 50)
	private String name;	
		
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "PATH_ID", referencedColumnName = "ID")
	private Path path;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the path
	 */
	public Path getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(Path path) {
		this.path = path;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
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


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Permission [name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
