package ideasw.secopre.model.security;

import ideasw.secopre.model.base.Persistible;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

public class UserRole implements Persistible {

	/**
    *
    */
	private static final long serialVersionUID = 1L;

	@Id
	private Long roleId;

	@Id
	private Long userId;

	// bi-directional many-to-one association to Role
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "ROLEID", referencedColumnName = "ID")
	private Role role;

	// bi-directional many-to-one association to User
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "USERID", referencedColumnName = "ID")
	private User user;

	public UserRole() {
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public Long getId() {
		return null;
	}

}
