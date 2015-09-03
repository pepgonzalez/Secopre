package ideasw.secopre.model.security;

import ideasw.secopre.model.base.Persistible;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

public class RolePermission implements Persistible {

	/**
    *
    */
	private static final long serialVersionUID = 1L;

	@Id
	private Long roleId;

	@Id
	private Long permissionId;

	// bi-directional many-to-one association to Permission
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "PERMISSIONID", referencedColumnName = "ID")
	private Permission permission;

	// bi-directional many-to-one association to Role
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "ROLEID", referencedColumnName = "ID")
	private Role role;
	
	public RolePermission(){
		
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
	 * @return the permissionId
	 */
	public Long getPermissionId() {
		return permissionId;
	}

	/**
	 * @param permissionId the permissionId to set
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
	 * @param permission the permission to set
	 */
	public void setPermission(Permission permission) {
		this.permission = permission;
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

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
