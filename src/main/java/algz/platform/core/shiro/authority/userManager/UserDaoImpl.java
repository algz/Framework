package algz.platform.core.shiro.authority.userManager;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import algz.platform.core.shiro.authority.roleManager.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sf;
    
	@Override
    public void saveUser(User user) {
    	if(user.getUserid()!=null&&!user.getUserid().equals("")){
    		User entity=(User)sf.getCurrentSession().get(User.class, user.getUserid());
    		if(user.getUsername()!=null){
    			entity.setUsername(user.getUsername());
    		}
    		if(user.getCname()!=null){
    			entity.setCname(user.getCname());
    		}
    		if(user.getPassword()!=null){
    			entity.setPassword(user.getPassword());
    		}
    		if(user.getDepartment()!=null){
    			entity.setDepartment(user.getDepartment());
    		}
    	}else{
    		sf.getCurrentSession().save(user);
    	}
//        final String sql = "insert into sys_user(organization_id, username, password, salt, role_ids, locked) values(?,?,?,?,?,?)";

//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
//                int count = 1;
//                psst.setLong(count++, user.getOrganizationId());
//                psst.setString(count++, user.getUsername());
//                psst.setString(count++, user.getPassword());
//                psst.setString(count++, user.getSalt());
//                psst.setString(count++, user.getRoleIdsStr());
//                psst.setBoolean(count++, user.getLocked());
//                return psst;
//            }
//        }, keyHolder);
//        user.setUserid(keyHolder.getKey().longValue()+"");
//        return user;
    }

//    public User updateUser(User user) {
//        String sql = "update ALGZ_USER set organization_id=?,username=?, password=?, salt=?, role_ids=?, locked=? where id=?";
////        jdbcTemplate.update(
////                sql,
////                user.getOrganizationId(), user.getUsername(), user.getPassword(), user.getSalt(), user.getRoleIdsStr(), user.getLocked(), user.getId());
//        return user;
//    }

	@Override
    public void deleteUser(String userId) {
        String sql = "delete from ALGZ_USER where id='"+userId+"'";
        sf.getCurrentSession().createSQLQuery(sql).executeUpdate();
//        jdbcTemplate.update(sql, userId);
    }

    @Override
    public User findOne(String userId) {
        //String sql = "select id, organization_id, username, password, salt, role_ids as roleIdsStr, locked from sys_user where id=?";
        return (User)sf.getCurrentSession().get(User.class, userId);
//    	List<User> userList = null;//jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), userId);
//        if(userList.size() == 0) {
//            return null;
//        }
//        return userList.get(0);
    }


	@Override
	public List<User> findAll(User user,Integer start, Integer limit) {
		
    	String hql="from User";
    	Query query= sf.getCurrentSession().createQuery(hql);
    	if(start!=null){
    		query.setFirstResult(start);
    		if(limit!=null){
    			query.setMaxResults(limit);
    		}
    	}
		return query.list();
	}

	@Override
	public Integer countAll(User user) {
		String hql="select count(1) from User";
		return ((Long)sf.getCurrentSession().createQuery(hql).uniqueResult()).intValue();
	}
    @Override
    public User findByUsername(String username) {
        String hql = "from User where username='"+username+"'";
        return (User)sf.getCurrentSession().createQuery(hql)
        		.setMaxResults(1).uniqueResult();
        		//jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), username);
    }

	@Override
	public void updateAUser(User user) {
		Role role=new Role();
		role.setRolecname("++++");
		sf.getCurrentSession().saveOrUpdate(role);
	}




}
