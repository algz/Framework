package com.ras.relationTable.sysware.user;

import java.math.BigDecimal;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.authority.user.RASUserDao;
import com.ras.authority.user.RASUserDao.Operation;
import com.ras.authority.user.RASUserService;

import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.core.shiro.authority.userManager.UserDao;

@Repository
public class SyswareUserDaoImpl implements SyswareUserDao {

	@Autowired
	private RASUserDao rasuserDao;
	
    @Autowired
    private UserDao userDao;
	
	@Autowired
	private SessionFactory sf;
	
	@Transactional
	@Override
	public User findByUsername(String loginName) {
		User user=userDao.findByUsername(loginName);
		if(user==null){
			String sql="select * from SYSWARE_USER_VIEW where login_Name='"+loginName+"'";
			Map<String,String> map=(Map<String,String>)sf.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
			if(map==null){
				return null;
			}
			user=new User();
			user.setUsername(map.get("LOGIN_NAME"));
			user.setPassword("111111");
			user.setCname(map.get("USER_NAME"));
			userDao.saveUser(user);
			
			sql="select t.id from ALGZ_ROLE t where t.rolename='generalUser'";
			String roleid=(String)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
			rasuserDao.saveUserRole(user.getUserid(), roleid, Operation.add);
			
//			sql="insert into ALGZ_USER (id,Username,Password) "
//					+ "values('"+map.get("USER_ID")+"','"+map.get("LOGIN_NAME")+"','111111')";
//			sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
		}
		
		return user;
	}

}
