package algz.platform.core.shiro.realm;


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

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.core.shiro.authority.userManager.UserService;

@Service  
//@Transactional  
public class AuthoryRealm extends AuthorizingRealm {

//    @Autowired
//    private UserService userService;
	
    /**
     * 权限认证 
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.setRoles(userService.findRoles(username));
//        authorizationInfo.setStringPermissions(userService.findPermissions(username));
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

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String username = (String)token.getPrincipal();
        User user = null;//userService.findByUsername(username);

        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if(Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
        
//      //UsernamePasswordToken对象用来存放提交的登录信息  
//      UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;  
//      //查出是否有此用户  
//      User user=userService.findByName(token.getUsername());  
//      if(user!=null){  
//          //若存在，将此用户存放到登录认证info中  
//          return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());  
//      }  
//      return null;  
	}
}
