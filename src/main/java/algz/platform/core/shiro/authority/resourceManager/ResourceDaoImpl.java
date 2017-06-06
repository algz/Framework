package algz.platform.core.shiro.authority.resourceManager;


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
}
