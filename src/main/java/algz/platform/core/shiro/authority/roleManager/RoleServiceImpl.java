/**
 * 
 */
package algz.platform.core.shiro.authority.roleManager;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

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
	public List<User> findUsernameByRoleNames(String... roleName) {
		return dao.findUsernameByRoleName(roleName);
	}

	@Override
	public List<Role> findAll(Role role, Integer start, Integer length) {
		return dao.findAll(role, start, length);
	}

	@Override
	public Integer countAll(Role role) {
		return dao.countAll(role);
	}

	@Transactional
	@Override
	public void saveRole(Role role) {
		dao.saveRole(role);
	}

	@Transactional
	@Override
	public void delRole(String roleID) {
		dao.delRole(roleID);
	}

	
	
}
