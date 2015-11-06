package ideasw.secopre.model.security;

import java.util.List;

import ideasw.secopre.model.base.Persistible;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Table(name = "MENU", indexes = { @Index(unique = true, name = "menu_ix", columnList = "id") })
public class Menu implements Persistible {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ACTIVE")
	private boolean active;

	@Column(name = "DESCRIPTION")
	@Size(max = 100)
	private String description;

	@Column(name = "NAME")
	@Size(max = 50)
	private String name;

	@Column(name = "ICON")
	private String icon;

	@Column(name = "CSSCLASS")
	private String cssClass;

	@Column(name = "JSID")
	private String jsId;
	
	@Column(name = "JSFUNCTION")
	private String jsFunction;

	@Column(name = "PARENT_ID")
	private Long parentId;

	@Column(name = "MENU_ORDER")
	private int order;
	
    // bi-directional many-to-one association to permissionPath
    @NotAudited
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "MENU_ROLE",
        joinColumns = {@JoinColumn(name="MENU_ID")},
        inverseJoinColumns = {@JoinColumn(name="ROLE_ID")}
    )
	private List<Role> roles;

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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the cssClass
	 */
	public String getCssClass() {
		return cssClass;
	}

	/**
	 * @param cssClass
	 *            the cssClass to set
	 */
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	/**
	 * @return the jsFunction
	 */
	public String getJsFunction() {
		return jsFunction;
	}

	/**
	 * @param jsFunction
	 *            the jsFunction to set
	 */
	public void setJsFunction(String jsFunction) {
		this.jsFunction = jsFunction;
	}

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", active=" + active + ", description="
				+ description + ", name=" + name + ", icon=" + icon
				+ ", cssClass=" + cssClass + ", jsFunction=" + jsFunction
				+ ", parentId=" + parentId + ", order=" + order + ", roles="
				+ roles + "]";
	}

	public String getJsId() {
		return jsId;
	}

	public void setJsId(String jsId) {
		this.jsId = jsId;
	}

}
