package algz.platform.core.shiro.authority.userManager;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import javax.transaction.Transactional;

@Service
public class AUserServiceImpl implements AUserService {

    @Autowired
    private UserDao dao;
    
	@Autowired
	private SessionFactory sf;
    
//    @Autowired
//    private PasswordHelper passwordHelper;
//    @Autowired
//    private RoleService roleService;

    /**
     * 创建用户
     * @param user
     */
    @Transactional
    @Override
    public void saveUser(User user) {
        //加密密码
//        passwordHelper.encryptPassword(user);
         dao.saveUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(String userId) {
    	User user=new User();
    	user.setUsername("aaaaaaaa");
    	dao.saveUser(user);
        dao.deleteUser(userId);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    @Override
    public void changePassword(String userId, String newPassword) {
        User user =dao.findOne(userId);
        user.setPassword(newPassword);
//        passwordHelper.encryptPassword(user);
        dao.saveUser(user);
    }

    @Override
    public User findOne(String userId) {
        return dao.findOne(userId);
    }

    @Override
    public List<User> findAll() {
        return findAll();
    }

	@Override
	public List<User> findAll(User user,Integer start, Integer limit) {
		return dao.findAll(user,start, limit);
	}
    
	@Override
	public Integer countAll(User user) {
		return dao.countAll(user);
	}
	
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
	@Override
    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
	@Override
    public Set<String> findRoles(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        return null;//roleService.findRoles(user.getRoleIds().toArray(new Long[0]));
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
	@Override
    public Set<String> findPermissions(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        return null;//roleService.findPermissions(user.getRoleIds().toArray(new Long[0]));
    }
}
