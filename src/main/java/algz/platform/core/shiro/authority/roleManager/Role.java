package algz.platform.core.shiro.authority.roleManager;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import algz.platform.core.shiro.authority.resourceManager.Resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p>User: algz
 * <p>Date: 2017-1-28
 * <p>Version: 1.0
 */
@Entity
@Table(name="ALGZ_ROLE")
public class Role implements Serializable {
	
	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "guid")
	@GeneratedValue(generator="ALGZGenerator")
    private String roleid; //编号
	
	@Column(name="ROLENAME")
    private String rolename; //角色标识 程序中判断使用,如"admin"
	
	@Column(name="ROLECNAME")
    private String rolecname; //角色标识 程序中判断使用,如"admin"
	
	@Column(name="DESCRIPTION")
    private String description; //角色描述,UI界面显示使用
	
	/**
	 * 角色类别:0普通角色;1系统角色
	 */
	@Column(name="ROLECATEGORY")
	private String rolecategory;
	
	// 不能设置FetchType.LAZY,因为user保存到session(登陆)后,再使用关联查询(此时原会话关闭,启动的是新会话),会报异常could not initialize proxy - no Session
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(name="ALGZ_ROLE_RESOURCE",
		joinColumns={@JoinColumn(name="ROLEID", nullable = false, updatable = false)},
		inverseJoinColumns={@JoinColumn(name="RESOURCEID", nullable = false, updatable = false)})
	private List<Resource> resources;
	
	@Transient
    private List<Long> resourceIds; //拥有的资源
	
	@Transient
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户

    public Role() {
    }



    public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String id) {
		this.roleid = id;
	}



    public String getRolename() {
		return rolename;
	}



	public void setRolename(String roleName) {
		this.rolename = roleName;
	}

	
	
	public String getRolecname() {
		return rolecname;
	}



	public void setRolecname(String roleCname) {
		this.rolecname = roleCname;
	}



	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    public String getRolecategory() {
		return rolecategory;
	}



	public void setRolecategory(String roleCategory) {
		this.rolecategory = roleCategory;
	}



	public List<Long> getResourceIds() {
        if(resourceIds == null) {
            resourceIds = new ArrayList<Long>();
        }
        return resourceIds;
    }

    public void setResourceIds(List<Long> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getResourceIdsStr() {
        if(CollectionUtils.isEmpty(resourceIds)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(Long resourceId : resourceIds) {
            s.append(resourceId);
            s.append(",");
        }
        return s.toString();
    }

    public void setResourceIdsStr(String resourceIdsStr) {
        if(StringUtils.isEmpty(resourceIdsStr)) {
            return;
        }
        String[] resourceIdStrs = resourceIdsStr.split(",");
        for(String resourceIdStr : resourceIdStrs) {
            if(StringUtils.isEmpty(resourceIdStr)) {
                continue;
            }
            getResourceIds().add(Long.valueOf(resourceIdStr));
        }
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (roleid != null ? !roleid.equals(role.roleid) : role.roleid != null) return false;

        return true;
    }



	public List<Resource> getResources() {
		return resources;
	}



	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

    
    
//    @Override
//    public String toString() {
//        return "Role{" +
//                "id=" + id +
//                ", role='" + role + '\'' +
//                ", description='" + description + '\'' +
//                ", resourceIds=" + resourceIds +
//                ", available=" + available +
//                '}';
//    }
}
