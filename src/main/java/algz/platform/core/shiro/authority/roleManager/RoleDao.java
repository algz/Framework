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
    public List<User> findUsernameByRole(Role role);
}
