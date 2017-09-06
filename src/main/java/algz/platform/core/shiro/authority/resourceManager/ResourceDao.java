package algz.platform.core.shiro.authority.resourceManager;



import java.util.List;

/**
 * 
 * @author algz
 *
 */
public interface ResourceDao {

	/**
	 * 
	 * @return
	 */
    public List<Resource> findAll();
    public List<Resource> findAll(Resource resource,Integer start,Integer length);
    public Integer countAll(Resource resource);
    public List<Resource> findResourceByRole(List<String> roleList);

}
