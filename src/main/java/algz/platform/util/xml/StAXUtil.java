/**
 * 
 */
/**
 * @author algz
 *
 */
package algz.platform.util.xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/*

String getLocalName();  
String getAttributeValue(String namespaceURI, String localName);  
QName getName();  


这三个方法牵扯到XML的namespace（命名空间）、localName（本地名称）、QName（Qualified Name，限定名称）三个概念，我们顺便解释一下： 

命名空间是为了支持相同名称不同含义的XML标签而产生的，它可以这么定义： 
Xml代码  收藏代码
<com:company xmlns:com="http://www.zangweiren.com/company">  
    <!-- here is other tags -->  
</com:company>  

其中，com是命名空间的前缀，company是命名空间的标签，http://www.zangweiren.com/company是命名空间的标识，相同的标识被认为是同一个命名空间。标识又叫URI，是唯一的，有URL（统一资源定位器）和URN（统一资源名称）两种。前缀是命名空间的简写，目的是为了使用方便。命名空间被声明后就可以被使用： 

Xml代码  收藏代码
<com:company xmlns:com="http://www.zangweiren.com/company">  
    <com:depart name="Develop Group" />  
</com:company>  


在上例的<com:depart />标签中，前缀com是命名空间，depart是localName，这两个合起来就是QName。 

在明白了这三个XML基本概念之后，也就明白了getLocalName()和getAttributeValue(String namespaceURI, String localName)方法的含义。 */
public class StAXUtil{
//	现在我们介绍完了StAX的两种解析XML文档的方式，大家也可能对它的使用有了自己的认识。我们最后总结一下：XMLStreamReader和XMLEventReader都允许应用程序迭代底层的XML流，区别在于它们如何对外提供解析后的XML信息片段。前者像个指针，指在刚刚解析过的XML标记的后面，并提供获得关于该标记更多信息的方法。因为不用创建新的对象，所以更节约内存。后者具有更多的面向对象特征，就是个标准的Java迭代器，解析器的当前状态反映在事件对象中，应用程序在处理事件对象的时候不需要访问解析器/读取器。 
	
	
	
	//	(1)StAX的基于指针的拉分析API
//	基于指针的StAX API。这种方式尽管效率高，但是没有提供XML结构的抽象，因此是一种低层API。 
	// 列出所有用户的名称和年龄  
	public static void listNamesAndAges(String xmlFile) {  
		XMLInputFactory factory = XMLInputFactory.newFactory();  

	    try {  
	        XMLStreamReader reader = factory  
	                .createXMLStreamReader(new FileReader(xmlFile));  
	        while (reader.hasNext()) {  
	            // 跳过所有空白、注释或处理指令，到下一个START_ELEMENT  
	            int event = reader.nextTag();  
	            if (event == XMLStreamConstants.START_ELEMENT) {  
	                if ("user".equalsIgnoreCase(reader.getLocalName())) {  
	                    System.out.println("Name:"  
	                            + reader.getAttributeValue(null, "name")  
	                            + ";Age:"  
	                            + reader.getAttributeValue(null, "age"));  
	                }  
	            }  
	        }  
	        reader.close();  
	    } catch (XMLStreamException e) {  
	        e.printStackTrace();  
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}  
	
	//(2)基于迭代器的拉分析API
//	对于基于指针的XMLStreamReader来说，虽然API文档说的是“事件”，但是我们把它看成“标记”更易于理解，而且不会与另一套基于事件的API相混淆。 

	// 列出所有信息  
    @SuppressWarnings("unchecked")  
    public static void listAllByXMLEventReader(String xmlFile) {  
//        String xmlFile = ListUsers.class.getResource("/").getFile()  
//                + "users.xml";  
        XMLInputFactory factory = XMLInputFactory.newInstance();  
        try {  
            // 创建基于迭代器的事件读取器对象  
            XMLEventReader reader = factory  
                    .createXMLEventReader(new FileReader(xmlFile));  
            // 遍历XML文档  
            while (reader.hasNext()) {  
                XMLEvent event = reader.nextEvent();  
                // 如果事件对象是元素的开始  
                if (event.isStartElement()) {  
                    // 转换成开始元素事件对象  
                    StartElement start = event.asStartElement();  
                    // 打印元素标签的本地名称  
                    System.out.print(start.getName().getLocalPart());  
                    // 取得所有属性  
                    Iterator attrs = start.getAttributes();  
                    while (attrs.hasNext()) {  
                        // 打印所有属性信息  
                        Attribute attr = (Attribute) attrs.next();  
                        System.out.print(":" + attr.getName().getLocalPart()  
                                + "=" + attr.getValue());  
                    }  
                    System.out.println();  
                }  
            }  
            reader.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (XMLStreamException e) {  
            e.printStackTrace();  
        }  
    }  
    
    
    /**
     * <?xml version="1.0" ?>  
<group name="mygroup">  
  <student studentname="张三"></student>  
  <student studentname="李四"></student>  
</group>  
     * 
     * @param m Object[] 0:value;1:attribute List<MAP>.
     * @return
     */
    public static String writeToXmlString(Map<String,Object[]> m){
    	XMLOutputFactory factory = XMLOutputFactory.newInstance();  
    	StringWriter sw=new StringWriter();
        try {
        	
        	XMLStreamWriter  writer = factory.createXMLStreamWriter(sw);
        	
	        writer.writeStartDocument();
	        
	        for(String key:m.keySet()){
	        	writer.writeStartElement(key); 
	        	
	        	if(m.get(key)==null){
	        		;
	        	}if(m.get(key)[0]!=null){
	        		writer.writeCharacters(m.get(key)[0].toString());
	        	}else if(m.get(key)[1]!=null){
	        		Map<String,String> tem=(Map<String,String>)m.get(key)[1];
	        		for(String temKey:tem.keySet()){
	        			writer.writeAttribute(temKey, tem.get(temKey));
	        		}
	        	}
	        	
	        	writer.writeEndElement();
	        }
	          
	        writer.writeEndDocument();  
	          
	        writer.flush();  
	        writer.close();
	        sw.flush();
	        sw.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        
    	return sw.toString();
    }
    
    private void writeElement(){
    	
    }
}