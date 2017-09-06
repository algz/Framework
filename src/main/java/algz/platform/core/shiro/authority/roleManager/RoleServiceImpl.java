/**
 * 
 */
package algz.platform.core.shiro.authority.roleManager;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import algz.platform.core.shiro.authority.userManager.User;

/**
 * @author algz
 *
 */

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao dao;
	
	@Override
	public List<User> findUsernameByRole(Role role) {
		return dao.findUsernameByRole(role);
	}

	@Override
	public List<Role> findAll(Role role, Integer start, Integer length) {
		return dao.findAll(role, start, length);
	}

	@Override
	public Integer countAll(Role role) {
		return dao.countAll(role);
	}

}
