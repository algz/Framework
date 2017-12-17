package com.ras.ws.service;

import javax.xml.ws.Endpoint;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;

public class Main {
	  /**
     * 发布WebService
     * 简单
     */
    public static void main(String[] args) {
        EndpointImpl endpoint = new EndpointImpl(new SpringBus(), new SayHiServiceImp());
        //endpoint.publish("/java-users");
        endpoint.publish("http://localhost:8080/algz/ras/ws/sayHi", new SayHiServiceImp());
    }

}
