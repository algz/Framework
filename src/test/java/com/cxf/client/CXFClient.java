/**
 * 
 */
/**
 * @author algz
 *
 */
package com.cxf.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CXFClient{
	
	public static void main(String[] args) throws Exception {

		  //不依赖服务器端接口来完成调用的，也就是不仅仅能调用Java的接口

		JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();

		Client client = clientFactory.createClient("http://localhost:8080/algz/ras/ws/hello?wsdl");

		Object[] result = client.invoke("SayHi", "KEVIN");

		if(result.length!=0){
			System.out.println(result[0]);
		}
		

		}
}