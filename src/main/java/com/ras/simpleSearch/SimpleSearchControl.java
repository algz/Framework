/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.simpleSearch;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.index.Page;
import com.ras.searchParam.SearchParamService;
import com.ras.searchParam.searchCriteria.SearchCriteriaVo;
import com.ras.tool.CommonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller   
@RequestMapping(value="/ras/simplesearch")
public class SimpleSearchControl{
	
	@Autowired
	private SimpleSearchService service;
	
	@Autowired
	private SearchParamService searchParamService;
	
	@RequestMapping(value={"","/"})
	public ModelAndView indexPage(){
    	Map<String, Object> map=new HashMap<String, Object>();
//    	Iterator it=SecurityUtils.getSubject().getSession().getAttributeKeys().iterator();
//    	while(it.hasNext()){
//    		Object key=it.next();
//    		Object val=SecurityUtils.getSubject().getSession().getAttribute(key);
//    		System.out.println(key+"="+val);
//    	}
    	
    	Page page=new Page();
    	page.setHeader_h1("普通查询");
    	page.setHeader_small("查询");
    	map.put("page", page);
    	map.put("searchTags", searchParamService.findAllParent());
        return new ModelAndView("ras/simplesearch/simpleSearch",map);
	}
	
    /**
     * 查询搜索标签表格
     * @param vo
     * @param request
     * @param response
     */
    @RequestMapping(value={"/simplesearchgird"})
    public void simpleSearchGird(SimpleSearchVo vo,HttpServletRequest request,HttpServletResponse response){
    	Map<String,String[]> map=request.getParameterMap();
//    	vo.setDraw(Integer.parseInt(request.getParameter("draw")));
//    	vo.setRecordsTotal(2);
//    	rtvo.setRecordsFiltered(2);
    	if(map.get("data")==null){
    		CommonTool.writeJSONToPage(response, vo);
    		return ;
    	}
    	String s="";
		try {
			s = java.net.URLDecoder.decode(map.get("data")[0],"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	Map m=JSONObject.fromObject(s);//CommonTool.stringFormToJson(s.split("&"),false);
    	vo.setParamMap(m);
    	if(m.keySet().size()!=0){
        	service.simpleSearchGird(vo);
    	}else{
    		vo.setData(null);
    	}
		CommonTool.writeJSONToPage(response, vo);
		
    }
    
    /**
     * 查询搜索标签树
     * @param vo
     * @param request
     * @param response
     */
    @RequestMapping(value={"/getsearchtreeviewnode"})
    public void getSearchTreeNode(HttpServletRequest request,HttpServletResponse response){
    	//String s="[{text:'Parent 1',nodes: [{text: 'Child 1'}]}]";
                
    	//JSONArray ja=JSONArray.fromObject(s);
//    	JSONObject jo=new JSONObject();
//    	jo.put("success", true);
		CommonTool.writeJSONToPage(response, service.getSearchTreeNode(null));
		
    }
    
    @RequestMapping(value={"/addnotefortaginput"})
    public void addNoteForTagInput(HttpServletRequest request,HttpServletResponse response){
    	String overviewID=request.getParameter("overviewID");
    	String inputNames=request.getParameter("inputName");
    	if(overviewID==null||inputNames==null){
    		//return "{\"success\":false}";
    	}
    	JSONObject jo=service.addNoteForTagInput(overviewID, inputNames.split(","));
    	jo.put("\"success\"", true);
//    	String msg="{\"success\":true,"
//    			+ "inputs:[{name:'aircraftType',vals:[{dataSource:'飞机手册1',inputValue:'美国1'},{dataSource:'飞机手册2',inputValue:'美国2'}]}]}";
		CommonTool.writeJSONToPage(response, jo);
    	//return msg;//"{\"success\":true}";
    }
    
    /**
     * 查询搜索标签表格
     * @param vo
     * @param request
     * @param response
     */
    @RequestMapping(value={"/searchsubmodelgrid"})
    public void searchSubModelGrid(HttpServletRequest request,HttpServletResponse response){
    	String overviewID=request.getParameter("overviewID");
    	if(overviewID!=null){
    		JSONObject jo=service.searchSubModelGrid(overviewID);
    		jo.put("success", true);
    		CommonTool.writeJSONToPage(response, jo);
    	}
    }
    
}