package algz.platform.core.shiro.authority.userManager;



import java.util.List;
import java.util.Set;


public interface AUserService {
	
    /**
     * 保存用户
     * @param user
     */
    public void saveUser(User user);

    /**
     * 删除用户
     * @param userId
     */
    public void deleteUser(String userId);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(String userId, String newPassword);


    public User findOne(String userId);

    public List<User> findAll();
    
    public List<User> findAll(User user,Integer start, Integer limit);
    
    public Integer countAll(User user);
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);

}
