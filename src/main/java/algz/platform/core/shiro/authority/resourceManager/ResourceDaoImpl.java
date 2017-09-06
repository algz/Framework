package algz.platform.core.shiro.authority.resourceManager;


import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author algz
 *
 */
@Repository
public class ResourceDaoImpl implements ResourceDao {

	@Autowired
	private SessionFactory sf;



	@Override
	public List<Resource> findAll() {
        String hql = "FROM Resource m where parentID=0 and (isValid is null or isValid='1') order by m.sequence";
        
        return sf.getCurrentSession().createQuery(hql).list();
        //jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
	}
	

	@Override
	public List<Resource> findAll(Resource resource, Integer start, Integer length) {
		StringBuilder sql=new StringBuilder("select * from ALGZ_RESOURCE where 1=1 ");
		if(resource!=null){
			if(resource.getName()!=null){
				sql.append(" and name='"+resource.getName()+"'");
			}
		}

		SQLQuery query=sf.getCurrentSession().createSQLQuery(sql.toString());
		if(start!=null){
			query.setFirstResult(start).setMaxResults(length);
		}
		return query.addEntity(Resource.class).list();
	}

	@Override
	public Integer countAll(Resource resource) {
		StringBuilder sql=new StringBuilder("select count(1) from ALGZ_RESOURCE where 1=1 ");
		if(resource!=null){
			if(resource.getName()!=null){
				sql.append(" and name='"+resource.getName()+"'");
			}
		}
		BigDecimal count=(BigDecimal)sf.getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
		return count.intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> findResourceByRole(List<String> roleList) {
		String sql="select * from ( select * from ALGZ_RESOURCE t where ispublic='1' and isvalid='1' "
				+ "union "
				+ "select res.* from ALGZ_RESOURCE res "
				+ "inner join ALGZ_ROLE_RESOURCE rres on res.id=rres.resourceid and isvalid='1' and rres.roleid in (:roleids)"
				+ ") where isvalid='1' order by parent_id,sequence";
		List<Resource> list=null;
		if(roleList.size()==0){
			sql="select * from ALGZ_RESOURCE t where ispublic='1' and isvalid='1' order by parent_id,sequence";
			list= sf.getCurrentSession().createSQLQuery(sql)
					.addEntity(Resource.class)
					.list();
		}else{
			list= sf.getCurrentSession().createSQLQuery(sql)
					.addEntity(Resource.class)
					.setParameterList("roleids", roleList)
					.list();
		}

		//清理所有集成
		for(Resource res:list){
			if(res.getParentID().equals("0")){
				res.getResources().clear();
			}
		}
		
		List<Resource> resList=new ArrayList<Resource>();
		for(Resource res:list){
			if(res.getParentID().equals("0")){
				resList.add(res);
			}else{
			    for(Resource sub:resList){
			    	if(sub.getId().equals(res.getParentID())){
			    		sub.getResources().add(res);
			    	}
			    }
			}
		}
		return resList;
//		sql="select * from ALGZ_ROLE_RESOURCE where roleid in ()";
//		Iterator<Resource> it=list.iterator();
//		while(it.hasNext()){
//			Resource resource=it.next();
//			if(resource.getIsPublic()==null||resource.getIsPublic().equals("1")){
//				
//			}
//		}
//        String hql = "FROM Resource m where parentID=0 and (isValid is null or isValid='1') order by m.sequence";
//        
//        return sf.getCurrentSession().createQuery(hql).list();
        //jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
	}

}
