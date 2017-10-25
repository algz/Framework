package algz.platform.core.shiro.authority.roleManager;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import algz.platform.core.shiro.authority.resourceManager.Resource;
import algz.platform.core.shiro.authority.userManager.User;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<User> findUsernameByRoleName(String... roleNames) {
		StringBuilder sql=new StringBuilder("select distinct au.* from ALGZ_USER_ROLE aur "
				+ "inner join ALGZ_ROLE ar on aur.roleid=ar.id "
				+ "inner join ALGZ_USER au on aur.userid=au.id where 1=1 ");
		String roleName="";
		for(int i=0;i<roleNames.length;i++){
			if(!roleName.equals("")){
				roleName+=",";
			}
			roleName+=("'"+roleNames[i].toLowerCase()+"'");
		}
		if(!roleName.equals("")){
			sql.append(" and LOWER(ar.rolename) in ("+roleName+")");
		}
		return sf.getCurrentSession().createSQLQuery(sql.toString()).addEntity(User.class).list();
	}

	@Override
	public List<Role> findAll(Role role, Integer start, Integer length) {
		StringBuilder hql=new StringBuilder("from Role where 1=1 ");
		if(role!=null){

		}

		Query query=sf.getCurrentSession().createQuery(hql.toString());
		if(start!=null){
			query.setFirstResult(start).setMaxResults(length);
		}
		return query.list();
	}

	@Override
	public Integer countAll(Role role) {
		String hql="select count(1) from Role";
		return ((Long)sf.getCurrentSession().createQuery(hql).uniqueResult()).intValue();
	}


}
