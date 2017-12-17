package com.ras.approval;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ras.tool.CommonTool;
import com.ras.ws.client.CXFClientUtil;

import algz.platform.core.shiro.authority.roleManager.RoleService;
import algz.platform.core.shiro.authority.userManager.User;
import algz.platform.core.shiro.authority.userManager.UserService;
import algz.platform.util.Common;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ApprovalServiceImpl implements ApprovalService {

	@Autowired
	private ApprovalDao dao;
	
	@Autowired
	private RoleService roleservice;
	
	@Autowired
	private UserService userService;
	
	@Value("${P2M_webservice}")
	private String P2M_webservice;
	
	@Value("${P2M_method}")
	private String P2M_method;
	
	@Value("${P2M_dataCheckURL}")
	private String P2M_dataCheckURL;
	
	@Value("${P2M_dataApprovalURL}")
	private String P2M_dataApprovalURL;
	

	
	/**
	 * 提交审批
	 * @param approval
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	@Override
	public String submitApproval(Approval approval)  {
		approval.setSubmitter(Common.getLoginUser().getUserid()); //提交人
		approval.setApprovalStatus("1");
		return dao.saveApproval(approval);
	}

	public String submitToP2M(Approval approval)throws Exception{
		//准备提交其它系统的webservice数据
		Map<String,String> m=new LinkedHashMap<String,String>();
		m.put("paramToken_ras", approval.getApprovalID());
		m.put("taskName", "数据审批");//任务名称
		//url1=数据校验员;url=数据审核员.
		String url1=P2M_dataCheckURL+"?approvalID="+approval.getApprovalID();
		String url2=P2M_dataApprovalURL+"?approvalID="+approval.getApprovalID();
		m.put("url", url1+","+url2);
		m.put("startUserId", Common.getLoginUser().getUsername());//发起人id,p2m login_name
		
		//数据校验员,数据审核员
		//List<User> users=roleservice.findUsernameByRoleNames("dataCheck","dataApproval");
		String username1=approval.getDataCheck();//users.get(0).getUsername();//数据校验员
		String username2=approval.getDataApproval();//users.get(1).getUsername();//数据审核员
		m.put("approvalUserIds", username1+","+username2); //审批人id=其它系统loginname
		
		String param=writeToXmlString(m);
		Object[] s=CXFClientUtil.invoke(P2M_webservice, P2M_method, param);
		Map<String,String> retMap=readerXmlToObject(s[0].toString().replace("\n  ", ""));
		if(retMap.get("result")!=null&&retMap.get("result").equals("true")){
			approval.setP2mApprovalID(retMap.get("approvalId"));
		}else{
			throw new Exception(retMap.get("errorInfo"));
		}
		return retMap.get("errorInfo");
	}
	
	private static String writeToXmlString(Map<String,String> m){
    	XMLOutputFactory factory = XMLOutputFactory.newInstance();  
    	StringWriter sw=new StringWriter();
        try {
        	
        	XMLStreamWriter  writer = factory.createXMLStreamWriter(sw);
	        writer.writeStartDocument();  
	        writer.writeStartElement("PlaneApproval"); 
	        
	        for(String key:m.keySet()){
	        	writer.writeStartElement(key); 
	        	
	        	if(m.get(key)!=null){
	        		writer.writeCharacters(m.get(key));
	        	}
	        	
	        	writer.writeEndElement();
	        }
	        

	        writer.writeEndElement();  
	        writer.writeEndDocument();  
	          
	        writer.flush();  
	        writer.close();
	        sw.flush();
	        sw.close();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
        
    	return sw.toString();
    }
	
	// 列出所有信息  
    @SuppressWarnings("unchecked")  
    private static Map<String,String> readerXmlToObject(String xmlString) {  
    	Map<String,String> m=new HashMap<String,String>();
        XMLInputFactory factory = XMLInputFactory.newInstance();  
        try {  
            // 创建基于迭代器的事件读取器对象  
            XMLEventReader reader = factory.createXMLEventReader(new StringReader(xmlString));  
            // 遍历XML文档  
            StartElement start=null;
            while (reader.hasNext()) {  
            	XMLEvent event = reader.nextEvent(); 
                // 如果事件对象是元素的开始  
                if (event.isStartElement()) {  
                    // 转换成开始元素事件对象  
                	start=event.asStartElement();
                	m.put(start.getName().getLocalPart(), "");

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
                }else if(event.isCharacters()){
            		Characters charact = event.asCharacters();
            		m.put(start.getName().getLocalPart(), charact.getData());
            	}   
            }  
            reader.close();  
        } catch (XMLStreamException e) {  
            e.printStackTrace();  
        }  
        return m;
    }


	@Override
	public void findApprovalGrid(ApprovalVo vo) {
		dao.findApprovalGrid(vo);
	}


	@Override
	public Approval findOne(Approval approval) {
		return dao.findOne(approval);
	}


	@Override
	public JSONObject getAllUser() {
		JSONObject jo=new JSONObject();
		List<User> userList=userService.findAll(null,0,null);
		jo.put("user", userListToJSONArray(userList));
		//数据校验员,
		userList=roleservice.findUsernameByRoleNames("dataCheck");
		jo.put("dataCheck", userListToJSONArray(userList));
		//数据审核员
		userList=roleservice.findUsernameByRoleNames("dataApproval");
		jo.put("dataApproval", userListToJSONArray(userList));
		return jo;
	}


	@Override
	public JSONObject getUserRole() {
		JSONObject jo=new JSONObject();
		//数据校验员,
		List<User> userList=roleservice.findUsernameByRoleNames("dataCheck");
		jo.put("dataCheck", userListToJSONArray(userList));
		//数据审核员
		userList=roleservice.findUsernameByRoleNames("dataApproval");
		jo.put("dataApproval", userListToJSONArray(userList));
		return jo;
	}
	
	private JSONArray userListToJSONArray(List<User> userList){
		JSONArray ja=new JSONArray();
		for(User u:userList){
			if(u.getCname()!=null){
				JSONObject jo=new JSONObject();
				jo.put("userid", u.getUserid());
				jo.put("cname", u.getCname());
				ja.add(jo);
			}
		}
		return ja;
	}
}
