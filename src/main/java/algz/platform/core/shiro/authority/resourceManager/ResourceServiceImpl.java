package algz.platform.core.shiro.authority.resourceManager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.*;

/**
 * <p>User: algz
 * <p>Date: 2016-07-22
 * <p>Version: 1.0
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;
//    @Autowired
//    private PasswordHelper passwordHelper;
//    @Autowired
//    private RoleService roleService;


    @Override
    public List<Resource> findAll() {
        return resourceDao.findAll();
    }


    /**
     * 查询所有菜单,匹配URL,指定激活菜单.
     */
    public List<Resource> findAll(String requestPath) {
    	List<Resource> menuList=findAll();
    	setMenuActive(menuList,requestPath);
//    	for(Resource m:menuList){
//    		if(setMenuActive(menuList,requestPath)){
//    			m.setActive("1");
//    		}
//    		if(m.getUrl()!=null&&matcher.match(m.getUrl()+"**", requestPath)){
//    			m.setActive("1");
//    			break;
//    		}
//    	}
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
