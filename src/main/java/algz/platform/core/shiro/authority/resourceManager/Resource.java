package algz.platform.core.shiro.authority.resourceManager;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

/**
 * <p>
 * User: algz
 * <p>
 * Date: 17-05-22
 * <p>
 * Version: 1.0
 * 
 * Hibernate 注解 自关联 : menus parentMenu
 * 
 */
@Entity
@Table(name = "ALGZ_RESOURCE")
public class Resource {

	// private ResourceType type = ResourceType.menu; //资源类型
	//
	// private String permission; //权限字符串
	// private Long parentId; //父编号
	// private String parentIds; //父编号列表
	// private Boolean available = Boolean.FALSE;

	@Id
	@GenericGenerator(name = "ALGZGenerator", strategy = "guid")
	@GeneratedValue(generator = "ALGZGenerator")
	private String id;

	/**
	 * 资源名称
	 */
	@Column(name = "NAME")
	private String name;

	/**
	 * 菜单ICON
	 */
	@Column(name = "ICON")
	private String icon;

	/**
	 * 关联的URL,资源路径
	 */
	@Column(name = "URL")
	private String url;

	/**
	 * 菜单顺序
	 */
	@Column(name = "sequence")
	private Integer sequence;

	/**
	 * 是否有效.1有效,0无效.
	 */
	@Column(name = "ISVALID")
	private String isValid;

	/**
	 * 是否公有资源(所有人可看):空或1为公有;0为私有,有权限限制
	 */
	@Column(name = "ISPUBLIC")
	private String isPublic;

	/**
	 * 是否当前菜单
	 */
	@Transient
	private String active;

	@Column(name = "PARENT_ID")
	private String parentID;

	@ManyToOne(fetch = FetchType.LAZY)
	@LazyToOne(LazyToOneOption.PROXY)
	@JoinColumn(name = "PARENT_ID", nullable = true, insertable = false, updatable = false)
	private Resource parentResource;

	// @Transient
	@OrderBy(value = "sequence asc") // OrderBy参数值要对应Bean中的属性名
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "parentResource")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Resource> resources;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public Resource getParentResource() {
		return parentResource;
	}

	public void setParentResource(Resource parentResource) {
		this.parentResource = parentResource;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	// public static enum ResourceType {
	// menu("菜单"), button("按钮");
	//
	// private final String info;
	// private ResourceType(String info) {
	// this.info = info;
	// }
	//
	// public String getInfo() {
	// return info;
	// }
	// }

}