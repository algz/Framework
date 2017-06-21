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

    List<User> findAll();

    public List<User> findAll(Integer start, Integer limit);
    
    User findByUsername(String username);

}
