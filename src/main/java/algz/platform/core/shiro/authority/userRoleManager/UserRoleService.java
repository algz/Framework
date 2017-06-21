package algz.platform.core.shiro.authority.userRoleManager;



import java.util.List;
import java.util.Set;

import algz.platform.core.shiro.authority.roleManager.Role;
import algz.platform.core.shiro.authority.userManager.User;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserRoleService {

public List<Role> findRoleByUserID(String userID);

}
