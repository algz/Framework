package algz.platform.core.shiro.authority.roleManager;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	@GenericGenerator(name = "ALGZGenerator", strategy = "guid")
	@GeneratedValue(generator="ALGZGenerator")
    private String id; //编号
	
	@Column(name="ROLENAME")
    private String rolename; //角色标识 程序中判断使用,如"admin"
	
	@Column(name="DESCRIPTION")
    private String description; //角色描述,UI界面显示使用
	
	@Transient
    private List<Long> resourceIds; //拥有的资源
	
	@Transient
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户

    public Role() {
    }



    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



    public String getRolename() {
		return rolename;
	}



	public void setRolename(String rolename) {
		this.rolename = rolename;
	}



	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        if (id != null ? !id.equals(role.id) : role.id != null) return false;

        return true;
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
