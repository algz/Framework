package algz.platform.core.shiro.authority.resourceManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.util.Common;

import java.util.*;

/**
 * <p>User: algz
 * <p>Date: 2016-07-22
 * <p>Version: 1.0
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao dao;
    
//    @Autowired
//    private PasswordHelper passwordHelper;
//    @Autowired
//    private RoleService roleService;


    /**
     * 查询所有资源
     */
    @Override
    public List<Resource> findAll() {	
    	return dao.findAll();
    }

	@Override
	public List<Resource> findAll(Resource resource, Integer start, Integer length) {
		return dao.findAll(resource, start, length);
	}

	@Override
	public Integer countAll(Resource resource) {
		return dao.countAll(resource);
	}
    /**
     * 查询指定权限的所有资源
     * @param roleList
     * @return
     */
    @Override
    public List<Resource> findAllByRoleids(List<String> roleList) {  	
    	return dao.findResourceByRole(roleList);
    }

    /**
     * 查询当前用户的所有菜单,匹配URL,指定激活菜单.
     */
    public List<Resource> findAllByUser(User user,String requestPath) {
    	List<Resource> menuList=findAllByRoleids(user.getRoleIds());
    	setMenuActive(menuList,requestPath);
        return menuList;
    }
    
    private boolean setMenuActive(Collection<Resource> resources,String requestPath){
    	PathMatcher matcher = new AntPathMatcher(); 
    	for(Resource m:resources){
    		if(m.getResources().size()!=0){
    			if(setMenuActive(m.getResources(),requestPath)){
    				m.setActive("1");
    				return true;
    			}
    		}else if(m.getUrl()!=null){ 
    			String[] url=m.getUrl().split(",");
    			for(String u:url){
        			if(url.equals(requestPath)||matcher.match(u+"**", requestPath)){//matcher.match(u+"**", requestPath)
        				m.setActive("1");
        				return true;
        			}
    			}

    		}
    	}
    	return false;
    }

}
