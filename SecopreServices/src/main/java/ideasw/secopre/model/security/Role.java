package ideasw.secopre.model.security;

import ideasw.secopre.model.base.Persistible;

import java.util.List;

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
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.security.core.GrantedAuthority;

/**
 * Entidad que permite almacenar los roles definidos para la aplicacion, estos
 * roles a su ves se pueden relacionar a los diferentes usuarios del sistema
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Table(name = "ROLE", indexes = {
		@Index(unique = true, name = "role_ix", columnList = "id"),
		@Index(unique = true, name = "rolename_ix", columnList = "rolename") })
public class Role implements Persistible, GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ACTIVE")
	private boolean active;

	@Column(name = "ROLENAME", unique = true)
	@Size(max = 50)
	private String rolename;
	
	@Column(name = "DESCRIPTION")
	@Size(max = 100)
	private String description;
	
	@Column(name = "NAME")
	@Size(max = 100)
	private String name;
	

    // bi-directional many-to-one association to permissionPath
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "ROLE_PERMISSION",
        joinColumns = {@JoinColumn(name="ROLE_ID")},
        inverseJoinColumns = {@JoinColumn(name="PERMISSION_ID")}
    )
	private List<Permission> permissions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Override
	public String getAuthority() {
		return this.rolename;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [name=");
		builder.append(rolename);
		builder.append(", permissions=");
		builder.append(permissions);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the permissions
	 */
	public List<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
