package com.ras.ws.client;


import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.11
 * 2017-06-23T15:11:43.898+08:00
 * Generated source version: 3.1.11
 * 
 */
@WebServiceClient(name = "SimpleService", 
                  wsdlLocation = "http://localhost:8080/axis2/services/SimpleService?wsdl",
                  targetNamespace = "http://ws.apache.org/axis2") 
public class SimpleService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws.apache.org/axis2", "SimpleService");
    public final static QName SimpleServiceHttpEndpoint = new QName("http://ws.apache.org/axis2", "SimpleServiceHttpEndpoint");
    public final static QName SimpleServiceHttpSoap12Endpoint = new QName("http://ws.apache.org/axis2", "SimpleServiceHttpSoap12Endpoint");
    public final static QName SimpleServiceHttpSoap11Endpoint = new QName("http://ws.apache.org/axis2", "SimpleServiceHttpSoap11Endpoint");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/axis2/services/SimpleService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SimpleService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/axis2/services/SimpleService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SimpleService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SimpleService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SimpleService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public SimpleService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public SimpleService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public SimpleService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




//    /**
//     *
//     * @return
//     *     returns SimpleServicePortType
//     */
//    @WebEndpoint(name = "SimpleServiceHttpEndpoint")
//    public SimpleServicePortType getSimpleServiceHttpEndpoint() {
//        return super.getPort(SimpleServiceHttpEndpoint, SimpleServicePortType.class);
//    }
//
//    /**
//     * 
//     * @param features
//     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
//     * @return
//     *     returns SimpleServicePortType
//     */
//    @WebEndpoint(name = "SimpleServiceHttpEndpoint")
//    public SimpleServicePortType getSimpleServiceHttpEndpoint(WebServiceFeature... features) {
//        return super.getPort(SimpleServiceHttpEndpoint, SimpleServicePortType.class, features);
//    }
//
//
//    /**
//     *
//     * @return
//     *     returns SimpleServicePortType
//     */
//    @WebEndpoint(name = "SimpleServiceHttpSoap12Endpoint")
//    public SimpleServicePortType getSimpleServiceHttpSoap12Endpoint() {
//        return super.getPort(SimpleServiceHttpSoap12Endpoint, SimpleServicePortType.class);
//    }
//
//    /**
//     * 
//     * @param features
//     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
//     * @return
//     *     returns SimpleServicePortType
//     */
//    @WebEndpoint(name = "SimpleServiceHttpSoap12Endpoint")
//    public SimpleServicePortType getSimpleServiceHttpSoap12Endpoint(WebServiceFeature... features) {
//        return super.getPort(SimpleServiceHttpSoap12Endpoint, SimpleServicePortType.class, features);
//    }
//
//
//    /**
//     *
//     * @return
//     *     returns SimpleServicePortType
//     */
//    @WebEndpoint(name = "SimpleServiceHttpSoap11Endpoint")
//    public SimpleServicePortType getSimpleServiceHttpSoap11Endpoint() {
//        return super.getPort(SimpleServiceHttpSoap11Endpoint, SimpleServicePortType.class);
//    }
//
//    /**
//     * 
//     * @param features
//     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
//     * @return
//     *     returns SimpleServicePortType
//     */
//    @WebEndpoint(name = "SimpleServiceHttpSoap11Endpoint")
//    public SimpleServicePortType getSimpleServiceHttpSoap11Endpoint(WebServiceFeature... features) {
//        return super.getPort(SimpleServiceHttpSoap11Endpoint, SimpleServicePortType.class, features);
//    }

}