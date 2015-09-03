package ideasw.secopre.model.security;

import ideasw.secopre.model.base.Persistible;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

public class PermissionRole implements Persistible {

	/**
    *
    */
	private static final long serialVersionUID = 1L;

	@Id
	private Long permissionId;

	@Id
	private Long roleId;

	// bi-directional many-to-one association to Role
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "PERMISSIONID", referencedColumnName = "ID")
	private Permission permission;

	// bi-directional many-to-one association to Role
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "PATHID", referencedColumnName = "ID")
	private Role role;

	public PermissionRole() {

	}

	/**
	 * @return the permissionId
	 */
	public Long getPermissionId() {
		return permissionId;
	}

	/**
	 * @param permissionId
	 *            the permissionId to set
	 */
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	/**
	 * @return the permission
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * @param permission
	 *            the permission to set
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

}
