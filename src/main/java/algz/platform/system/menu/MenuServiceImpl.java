package algz.platform.system.menu;


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
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;
//    @Autowired
//    private PasswordHelper passwordHelper;
//    @Autowired
//    private RoleService roleService;


    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }


    /**
     * 查询所有菜单,匹配URL,指定激活菜单.
     */
    public List<Menu> findAll(String requestPath) {
    	List<Menu> menuList=findAll();
    	PathMatcher matcher = new AntPathMatcher();  
    	for(Menu m:menuList){
    		if(m.getUrl()!=null&&matcher.match(m.getUrl()+"**", requestPath)){
    			m.setActive("1");
    			break;
    		}
    	}
        return menuList;
    }
}
