
/**
 * @author algz
 *
 */
package com.ras.ws.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ras.documnet.dataManager.DataService;

/**
 * 作为测试的WebService实现类
 * 
 * @author Johness
 * 
 */
@Service //CXF webservice 类也必须添加spring 注解
//@WebService(serviceName = "SayHiService", endpointInterface = "com.ras.ws.service.SayHiService")
//@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SayHiServiceImp implements SayHiService {

	@Autowired
	private DataService service;
	
	@Override
	public void SayHiDefault() {
		System.out.println("Hi, Johness!");
		
	}

	@Override
	public String SayHi(String name) {
		String s="<WsResult><receiveapproval>true</receiveapproval><returnreason>送审成功</returnreason><approvalID>id</approvalID><paramToken_ras>true </paramToken_ras></WsResult>";
		return s;
	}

	@Override
	public String SayHis(String name) {
		return name+"_ok";
	}

	
	@Override
	public int CheckTime(Date clientTime) {
	       // 精确到秒
        String dateServer = new java.sql.Date(System.currentTimeMillis())
                .toString()
                + " "
                + new java.sql.Time(System.currentTimeMillis());
        String dateClient = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(clientTime);
        return dateServer.equals(dateClient) ? 1 : 0;
	}




}