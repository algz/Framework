package algz.platform.core.shiro.authority.resourceManager;



import java.util.List;
import java.util.Set;


public interface ResourceService {

    List<Resource> findAll();
    List<Resource> findAll(String requestPath);

}
