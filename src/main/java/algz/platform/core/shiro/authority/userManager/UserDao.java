package algz.platform.core.shiro.authority.userManager;



import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserDao {

    public User createUser(User user);
    public User updateUser(User user);
    public void deleteUser(String userId);

    User findOne(String userId);


    public List<User> findAll(User user,Integer start, Integer limit);
    public Integer countAll(User user);
    
    User findByUsername(String username);

}
