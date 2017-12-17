package algz.platform.core.configure.WebAppInitializer;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ras.ws.service.RASWebservice;
import com.ras.ws.service.SayHiService;
import com.ras.ws.service.SayHiServiceImp;



//import com.ras.ws.service.SpringDataUsers;
import org.apache.cxf.jaxws.EndpointImpl;//com.sun.xml.internal.ws.transport.http.server.EndpointImpl;


import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:META-INF/cxf/cxf.xml"}) //cxf 3.1 后只需导入这一个XML.
public class CXFConfiguration {

//	@Autowired
//  private ApplicationContext applicationContext;

	/**
	 * 必须注入,即使不使用(其它方法在隐藏使用).
	 */
    @Autowired
    private Bus bus;

    @Autowired
    private SayHiService sayHiService;
    
    @Autowired
    private RASWebservice rasWebservice;
    
    /**
     * url: http://localhost:8080/algz/ras/ws/hello?wsdl
     * 说明:http://localhost:8080/{项目名}/{servletURL}/{endpoint}?wsdl
     * 
     * @return
     */
    @Bean
    public EndpointImpl sayHiService() {
        //Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        
        EndpointImpl endpoint = new EndpointImpl(bus,sayHiService);
        endpoint.publish("/hello");
        return endpoint;
    }
	
    /**
     * url: http://localhost:8080/algz/ras/ws/endApproval?wsdl
     * 说明:http://localhost:8080/{项目名}/{servletURL}/{endpoint}?wsdl
     * 
     * servletURL 在 AppInitializer.java 中CXF配置.
     * 
     * @return
     */
    @Bean
    public EndpointImpl rasWebservice() {
        //Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        
        EndpointImpl endpoint = new EndpointImpl(bus,rasWebservice);
        endpoint.publish("/endApproval");
        return endpoint;
    }
}
