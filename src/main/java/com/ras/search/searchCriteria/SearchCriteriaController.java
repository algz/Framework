package com.ras.search.searchCriteria;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.index.Page;
import com.ras.search.SearchTagService;
import com.ras.search.SearchTagVo;
import com.ras.tool.CommonTool;

import net.sf.json.JSONArray;

@RequestMapping("/ras/search")
@Controller  
public class SearchCriteriaController {

	
	@Autowired
	private SearchTagService searchTagService;
	
	@Autowired
	private SearchCriteriaService service;
	
	   /**
     * 查询关键字
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value={"/searchcriteria"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public ModelAndView  SearchCriteria(HttpServletRequest request, HttpServletResponse response) {
		//ModelAndView("WebContent路径/jsp文件名(扩展名可选）", request作用域的属性名, request作用域的属性值);
//    	JSONArray obj=JSONArray.fromObject("[{text:'menu1'},{text:'menu2'}]");
    	Map<String, Object> map=new HashMap<String, Object>();

    	Page page=new Page();
    	page.setHeader_h1("查询");
    	page.setHeader_small("查询关键字");
    	map.put("page", page);
    	

    	
    	String str=request.getParameter("ids");
    	if(str!=null){
        	String[] ids=str.split(",");
        	map.put("searchTags", searchTagService.findAllByIds(ids));
    	}
    	map.put("tagSelect", searchTagService.findAllParent());
    	return new ModelAndView("ras/search/searchCriteria",map);
    }
    
    /**
     * 查询搜索标签表格
     * @param vo
     * @param request
     * @param response
     */
    @RequestMapping(value={"/searchcriteriagird"})
    public void SearchCriteriaGird(SearchCriteriaVo vo,HttpServletRequest request,HttpServletResponse response){
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Map m=CommonTool.stringFormToJson(s.split("&"),false);
    	vo.setParamMap(m);
    	if(m.keySet().size()!=0){
        	service.SearchCriteriaGird(vo);
    	}else{
    		vo.setData(null);
    	}
		CommonTool.writeJSONToPage(response, vo);
		
    }
}
