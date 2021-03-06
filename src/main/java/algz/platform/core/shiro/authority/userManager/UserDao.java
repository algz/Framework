package algz.platform.core.shiro.authority.userManager;



import java.util.List;

/**
 * <p>User: a_lgz
 * <p>Date: 2017-12-15
 * <p>Version: 1.0
 */
public interface UserDao {

    public void saveUser(User user);
    
    public void updateAUser(User user);
    public void deleteUser(String userId);

    User findOne(String userId);


    public List<User> findAll(User user,Integer start, Integer limit);
    public Integer countAll(User user);
    
    User findByUsername(String username);

}
