/**
 * 
 */
package algz.platform.core.shiro.authority.roleManager;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import algz.platform.core.shiro.authority.resourceManager.Resource;
import algz.platform.core.shiro.authority.userManager.User;

/**
 * @author algz
 *
 */
public interface RoleService {
	
    /**
     * 根据角色查找用名名
     * @param username
     * @return
     */
    public List<User> findUsernameByRoleNames(String... roleName);
    
    public List<Role> findAll(Role role,Integer start,Integer length);
    public Integer countAll(Role role);
}
