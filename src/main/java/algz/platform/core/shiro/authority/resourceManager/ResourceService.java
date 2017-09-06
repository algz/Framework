package algz.platform.core.shiro.authority.resourceManager;



import java.util.List;
import java.util.Set;

import algz.platform.core.shiro.authority.userManager.User;


public interface ResourceService {

    List<Resource> findAll();
    List<Resource> findAll(Resource resource,Integer start,Integer length);
    Integer countAll(Resource resource);
    List<Resource> findAllByRoleids(List<String> roleList);
    List<Resource> findAllByUser(User user,String requestPath);

}
