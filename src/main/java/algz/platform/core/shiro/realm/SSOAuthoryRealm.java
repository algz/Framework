package algz.platform.core.shiro.realm;


import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.relationTable.sysware.user.SyswareUserDao;

import algz.platform.core.shiro.authority.userManager.AUserService;
import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.core.shiro.authority.userManager.UserService;

/**
 * UserRealm父类AuthorizingRealm将获取Subject相关信息分成两步：
 * 获取身份验证信息（doGetAuthenticationInfo）及授权信息（doGetAuthorizationInfo）.
 * @author algz
 *
 *
 *shiro HTML标签
 *html:
 *
 *sec_permission表的permissionName="user:create"
 *<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
 *<shiro:hasPermission name="user:create">
     <a href="admin.jsp">创建用户</a>
</shiro:hasPermission>

 *
 */
@Service  
public class SSOAuthoryRealm extends AuthorizingRealm {

	@Resource
    private UserService userService;
    
//	  @Autowired
//	  private AUserService userService;
    
    @Autowired
    private SyswareUserDao syswareUserDao;


	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

//      //UsernamePasswordToken对象用来存放提交的登录信息  
//      UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;  
		String username = (String)token.getPrincipal();
        User user = syswareUserDao.findByUsername(username);//userService.findByUsername(username);

//        user.getRoles().size();
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if(Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getPassword(), //密码
//                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
//      //通过权限
//        request.getSession().setAttribute("LoginUser",user); //保存登陆成功的用户.
      //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return authenticationInfo; 
	}
	
    /**
     * 
     * 权限认证 
     * 
     * 例:
     * // 只用同时具有user:view和user:create权限才能访问
    	@RequiresPermissions(value={"user:view","user:create"}, logical= Logical.AND)
    	public void xxxx(...){ ... }
    	
    	 // 只有角色为admin的才能访问
    	@RequiresRoles("admin")
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //用户的角色集合  
        authorizationInfo.setRoles(userService.findRoles(username));
      //用户的角色对应的所有权限
        authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
        
//      //获取登录时输入的用户名  
//      String loginName=(String) principalCollection.fromRealm(getName()).iterator().next();  
//      //到数据库查是否有此对象  
//      User user=userService.findByName(loginName);  
//      if(user!=null){  
//          //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）  
//          SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();  
//          //用户的角色集合  
//          info.setRoles(user.getRolesName());  
//          //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要  
//          List<Role> roleList=user.getRoleList();  
//          for (Role role : roleList) {  
//              info.addStringPermissions(role.getPermissionsName());  
//          }  
//          return info;  
//      }  
//      return null;  
	}
}
