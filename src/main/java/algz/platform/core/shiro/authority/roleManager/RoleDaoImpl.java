package algz.platform.core.shiro.authority.roleManager;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.tool.CommonTool;

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

		Query query=sf.getCurrentSession().createQuery(hql.toString()+" order by rolecategory");
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

	@Override
	public void saveRole(Role role) {
		role.setRolecategory("0");
		if(role.getRoleid()!=null&&!role.getRoleid().equals("")){
			Role entity=(Role)sf.getCurrentSession().get(Role.class, role.getRoleid());
			if(role.getRolename()!=null){
				entity.setRolename(role.getRolename());
			}
			if(role.getRolecname()!=null){
				entity.setRolecname(role.getRolecname());
			}
			if(role.getDescription()!=null){
				entity.setDescription(role.getDescription());
			}
			//update,saveOrupdate 跨Session时才用到.比如get()后离开session当作VO使用,再然后把VO放到session保存.
//			sf.getCurrentSession().update(role);
//			CommonTool.updateSingleEntityForProperty(sf, role);
		}else{
			sf.getCurrentSession().save(role);
		}
		
	}

	@Override
	public void delRole(String roleID) {
        String sql = "delete from ALGZ_ROLE where id='"+roleID+"'";
        sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}


}
