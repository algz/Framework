package algz.platform.core.shiro.authority.resourceManager;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ResourceDaoImpl implements ResourceDao {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    
	@Autowired
	private SessionFactory sf;
	
    public Resource createResource(final Resource resource) {
        final String sql = "insert into sys_resource(name, type, url, permission, parent_id, parent_ids, available) values(?,?,?,?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
//                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
//                int count = 1;
//                psst.setString(count++, resource.getName());
//                psst.setString(count++, resource.getType().name());
//                psst.setString(count++, resource.getUrl());
//                psst.setString(count++, resource.getPermission());
//                psst.setLong(count++, resource.getParentId());
//                psst.setString(count++, resource.getParentIds());
//                psst.setBoolean(count++, resource.getAvailable());
//                return psst;
//            }
//        }, keyHolder);
        resource.setId(keyHolder.getKey().longValue());
        return resource;
    }

    @Override
    public Resource updateResource(Resource resource) {
        final String sql = "update sys_resource set name=?, type=?, url=?, permission=?, parent_id=?, parent_ids=?, available=? where id=?";
//        jdbcTemplate.update(
//                sql,
//                resource.getName(), resource.getType().name(), resource.getUrl(), resource.getPermission(), resource.getParentId(), resource.getParentIds(), resource.getAvailable(), resource.getId());
        return resource;
    }

    public void deleteResource(Long resourceId) {
        Resource resource = findOne(resourceId);
        final String deleteSelfSql = "delete from sys_resource where id=?";
//        jdbcTemplate.update(deleteSelfSql, resourceId);
        final String deleteDescendantsSql = "delete from sys_resource where parent_ids like ?";
//        jdbcTemplate.update(deleteDescendantsSql, resource.makeSelfAsParentIds() + "%");
    }


    @Override
    public Resource findOne(Long resourceId) {
        final String sql = "select id, name, type, url, permission, parent_id, parent_ids, available from sys_resource where id=?";
        List<Resource> resourceList = null;//jdbcTemplate.query(sql, new BeanPropertyRowMapper(Resource.class), resourceId);
        if(resourceList.size() == 0) {
            return null;
        }
        return resourceList.get(0);
    }

    @Override
    public List<Resource> findAll() {
        final String sql = "select * from sys_resource ";
        List<Resource> list= sf.getCurrentSession().createSQLQuery(sql).addEntity(Resource.class).list();
        return list;
        //        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Resource.class));
    }

}
