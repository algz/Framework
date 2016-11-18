package algz.platform.system.menu;


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
public class MenuDaoImpl implements MenuDao {

	@Autowired
	private SessionFactory sf;



	@Override
	public List<Menu> findAll() {
        String sql = "SELECT * FROM algz_nav_menu m where m.parent_id=0 order by m.order";
        return sf.getCurrentSession().createSQLQuery(sql).addEntity(Menu.class).list();
        //jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
	}
}
