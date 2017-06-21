package algz.platform.core.shiro.authority.userRoleManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import algz.platform.core.shiro.authority.roleManager.Role;
import algz.platform.core.shiro.authority.userManager.User;

import java.util.*;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service

public class UserRoleServiceImpl implements UserRoleService {

	@Override
	public List<Role> findRoleByUserID(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

//    @Autowired
//    private UserRoleDao userRoleDao;
////    @Autowired
////    private PasswordHelper passwordHelper;
////    @Autowired
////    private RoleService roleService;
//
//    /**
//     * 创建用户
//     * @param user
//     */
//    public User createUser(User user) {
//        //加密密码
////        passwordHelper.encryptPassword(user);
//        return userRoleDao.createUser(user);
//    }
//
//    @Override
//    public User updateUser(User user) {
//        return userRoleDao.updateUser(user);
//    }
//
//    @Override
//    public void deleteUser(Long userId) {
//        userRoleDao.deleteUser(userId);
//    }
//
//    /**
//     * 修改密码
//     * @param userId
//     * @param newPassword
//     */
//    public void changePassword(Long userId, String newPassword) {
//        User user =userRoleDao.findOne(userId);
//        user.setPassword(newPassword);
////        passwordHelper.encryptPassword(user);
//        userRoleDao.updateUser(user);
//    }
//
//    @Override
//    public User findOne(Long userId) {
//        return userRoleDao.findOne(userId);
//    }
//
//    @Override
//    public List<User> findAll() {
//        return userRoleDao.findAll();
//    }
//
//    /**
//     * 根据用户名查找用户
//     * @param username
//     * @return
//     */
//    public User findByUsername(String username) {
//        return userRoleDao.findByUsername(username);
//    }
//
//    /**
//     * 根据用户名查找其角色
//     * @param username
//     * @return
//     */
//    public Set<String> findRoles(String username) {
//        User user =findByUsername(username);
//        if(user == null) {
//            return Collections.EMPTY_SET;
//        }
//        return null;//roleService.findRoles(user.getRoleIds().toArray(new Long[0]));
//    }
//
//    /**
//     * 根据用户名查找其权限
//     * @param username
//     * @return
//     */
//    public Set<String> findPermissions(String username) {
//        User user =findByUsername(username);
//        if(user == null) {
//            return Collections.EMPTY_SET;
//        }
//        return null;//roleService.findPermissions(user.getRoleIds().toArray(new Long[0]));
//    }

}
