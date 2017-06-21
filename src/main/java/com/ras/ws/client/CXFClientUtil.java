package com.ras.ws.client;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CXFClientUtil {
	
	public static Object[] invoke(String url,String methodName,String... param){

		  //不依赖服务器端接口来完成调用的，也就是不仅仅能调用Java的接口

		JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
		//"http://localhost:8080/algz/ras/ws/hello?wsdl"
		Client client = clientFactory.createClient(url);

//		Object[] result = client.invoke("SayHi", "KEVIN");
		Object[] result=null;
		try {
			result = client.invoke(methodName, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}
}
