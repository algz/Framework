package algz.platform.core.configure.WebAppInitializer;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @PropertySource导入基于properties配置文件。
 * @author algz
 *
 */

@Configuration
//启用注解事务管理，使用CGLib代理  
@EnableTransactionManagement(proxyTargetClass = true)  //声明式事务管理，通过spring root application context扫描包septem.config.app：
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfig {

	@Value("${jdbc.driverClass}")
	private String driverClass;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}") 
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	
	/**
	 * @PropertySource 必须依赖此配置才有效。
	 */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
	
	@Bean
	public DataSource dataSource() throws PropertyVetoException {
//	      //JDBC连接
		  ComboPooledDataSource  dataSource=new ComboPooledDataSource (); //c3p0 连接池
		//BasicDataSource ds = new BasicDataSource(); //bdpm （apatch）连接池
		  dataSource.setDriverClass(driverClass);
		  dataSource.setJdbcUrl(url);
		  dataSource.setUser(username);
		  dataSource.setPassword(password);
	  	
	  	return dataSource;
	  }
	  
	  @Bean
	  public LocalSessionFactoryBean sessionFactory(DataSource dataSource) throws PropertyVetoException{
	    // more configuration...
	    Properties properties=new Properties();
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

//	    properties.put("hibernate.connection.driver_class", "org.sqlite.JDBC");
//	    properties.put("hibernate.dialect", "algz.platform.core.configure.database.dialect.SQLiteDialect");
	   // properties.setProperty("hibernate.max_fetch_depth", "3");
	    //properties.setProperty("hibernate.show_sql", "false");
	    //    properties.put("hibernate.connection.url", "jdbc:sqlite:c:/sample.db");
//	    properties.put("hibernate.connection.username", "");
//	    properties.put("hibernate.connection.password", "");
	    
	      LocalSessionFactoryBean factoryBean=new LocalSessionFactoryBean();
	      factoryBean.setDataSource(dataSource);
//	      factoryBean.setPackagesToScan(new String[]{"algz.platform.test"});
	      //  sessionFactoryBean.setPackagesToScan("com.coderli.shurnim.*.model");
	      factoryBean.setHibernateProperties(properties);
	      
	    //扫描model类,不然报Caused by: org.hibernate.MappingException: Unknown entity:
	      factoryBean.setPackagesToScan(new String[] {"algz.platform.core.shiro.*","com.hello.*"});
	      return factoryBean;
	  }

		@Bean
		public HibernateTransactionManager txManager(LocalSessionFactoryBean sessionFactory) throws PropertyVetoException {
			HibernateTransactionManager txManager = new HibernateTransactionManager();
			txManager.setSessionFactory(sessionFactory.getObject());
			return txManager;
		}  
}
