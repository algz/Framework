/**
 * 
 */
/**
 * @author algz
 *
 */
package algz.platform.core.shiro.authority.userRoleManager;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import algz.platform.core.shiro.authority.roleManager.Role;

/**
 * <p>User: algz
 * <p>Date: 17-5-22
 * <p>Version: 1.0
 */
@Entity
@Table(name="ALGZ_USER_ROLE")
public class UserRole implements Serializable {
	
	@Id
	@GenericGenerator(name = "ALGZGenerator", strategy = "guid")
	@GeneratedValue(generator="ALGZGenerator")
    private String id; //编号
	
	@Column(name="USERID")
	private String userID;
	
	@Column(name="ROLEID")
	private String roleID;

	
//	private List<Role> roles;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	
	
}