package com.ras.relationTable.sysware.user;

import java.math.BigDecimal;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import algz.platform.core.shiro.authority.userManager.User;

@Repository
public class SyswareUserDaoImpl implements SyswareUserDao {

	@Autowired
	private SessionFactory sf;
	
	@Transactional
	@Override
	public User findByUsername(String loginName) {
		User user=new User();
		String sql="select * from SYSWARE_USER_VIEW where login_Name='"+loginName+"'";
		Map<String,String> map=(Map<String,String>)sf.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
		if(map==null){
			return null;
		}else{
			sql="select count(1) from ALGZ_USER t where t.username='"+loginName+"'";
			BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery(sql).uniqueResult();
			if(count.intValue()==0){
				sql="insert into ALGZ_USER (id,Username,Password) "
						+ "values('"+map.get("USER_ID")+"','"+map.get("LOGIN_NAME")+"','"+map.get("LOGIN_PASSWORD")+"')";
				sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
			}
			
		}
		
		return user;
	}

}
