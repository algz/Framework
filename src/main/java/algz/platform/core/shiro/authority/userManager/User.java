package algz.platform.core.shiro.authority.userManager;



import org.hibernate.annotations.GenericGenerator;

import algz.platform.core.shiro.authority.roleManager.Role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

/**
 * <p>User: algz
 * <p>Date: 16-7-18
 * <p>Version: 1.0
 */
@Entity
@Table(name="ALGZ_USER")
public class User implements Serializable {
	
	@Id
	@Column(name = "ID")
	@GenericGenerator(name = "ALGZGenerator", strategy = "guid")
	@GeneratedValue(generator="ALGZGenerator")
    private String userid; //编号
	
	@Column(name="USERNAME")
    private String username; //用户名
	
	@Column(name="PASSWORD")
    private String password; //密码
	
	/**
	 * 部门
	 */
	@Column(name="DEPARTMENT")
	private String department;
	
	@Column(name="PHOTO")
    private String photo; //用户图片
	
	@Column(name="CNAME")
	private String cname;
    
	// 不能设置FetchType.LAZY,因为user保存到session(登陆)后,再使用关联查询(此时原会话关闭,启动的是新会话),会报异常could not initialize proxy - no Session
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(name="ALGZ_USER_ROLE",
		joinColumns={@JoinColumn(name="USERID")},
		inverseJoinColumns={@JoinColumn(name="ROLEID")})
	private List<Role> roles;
	
	//@Column(name="organization_Id")
	@Transient
    private Long organizationId; //所属公司


	@Transient
    private String salt; //加密密码的盐
    
    

    @Transient
    private Boolean locked = Boolean.FALSE;
    
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * 获取当前用户所有的Roles的ID
     * @return
     */
	public List<String> getRoleIds() {
		List<String> list=new ArrayList<String>();
		if(this.roles!=null){
			for(Role role:this.roles){
				list.add(role.getRoleid());
			}
		}
		return list;
	}

    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }
    
    public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

   


    



}
