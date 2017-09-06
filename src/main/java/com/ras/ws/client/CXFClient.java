/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.ws.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.mchange.v1.db.sql.Schema;

public class CXFClient{
	
	public static void main(String[] args) throws Exception {

		  //不依赖服务器端接口来完成调用的，也就是不仅仅能调用Java的接口

		JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
		String url="";
		boolean flag=true;
		Object[] result=null; 
		Client client=null;
		if(flag){
			url="http://localhost:8080/axis2/services/SimpleService?wsdl";
			client = clientFactory.createClient(url);
			result = client.invoke("getGreeting", "D18183E1A19A4FF6B93DE3E7D31DF6B1");
			
			
		}else{
			url="http://localhost:8080/algz/ras/ws/endApproval?wsdl";
			client = clientFactory.createClient(url);
			result = client.invoke("endApproval", "D18183E1A19A4FF6B93DE3E7D31DF6B1","","1","");
		}

		if(result.length!=0){
			System.out.println(result[0]);
		}
		//rj-17-0000-000 
		

	}
}