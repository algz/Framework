package algz.platform.core.shiro.authority.userManager;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

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
    
    public User createUser(final User user) {
        final String sql = "insert into sys_user(organization_id, username, password, salt, role_ids, locked) values(?,?,?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
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

        user.setUserid(keyHolder.getKey().longValue()+"");
        return user;
    }

    public User updateUser(User user) {
        String sql = "update sys_user set organization_id=?,username=?, password=?, salt=?, role_ids=?, locked=? where id=?";
//        jdbcTemplate.update(
//                sql,
//                user.getOrganizationId(), user.getUsername(), user.getPassword(), user.getSalt(), user.getRoleIdsStr(), user.getLocked(), user.getId());
        return user;
    }

    public void deleteUser(String userId) {
        String sql = "delete from sys_user where id=?";
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
    public List<User> findAll() {
    	return findAll(null,null);
//        String sql = "select id, organization_id, username, password, salt, role_ids as roleIdsStr, locked from sys_user";
//        return null;//jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
    }

	@Override
	public List<User> findAll(Integer start, Integer limit) {
    	String hql="from User";
    	Query query= sf.getCurrentSession().createQuery(hql);
    	if(start!=null){
    		query.setFirstResult(start).setMaxResults(limit);
    	}
		return query.list();
	}

    @Override
    public User findByUsername(String username) {
        String hql = "from User where username='"+username+"'";
        return (User)sf.getCurrentSession().createQuery(hql)
        		.setMaxResults(1).uniqueResult();
        		//jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class), username);
    }


}
