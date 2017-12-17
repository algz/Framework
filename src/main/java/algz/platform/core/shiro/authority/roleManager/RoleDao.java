/**
 * 
 */
package algz.platform.core.shiro.authority.roleManager;

import java.util.List;

import algz.platform.core.shiro.authority.userManager.User;

/**
 * @author algz
 *
 */
public interface RoleDao {
    public List<User> findUsernameByRoleName(String... roleName);
    
    public List<Role> findAll(Role role,Integer start,Integer length);
    
    public Integer countAll(Role role);
    
    public void saveRole(Role role);
    
    public void delRole(String roleID);
}
