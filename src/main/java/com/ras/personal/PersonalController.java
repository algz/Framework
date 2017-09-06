/**
 * 
 */
/**
 * @author algz
 *
 */
package com.ras.personal;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.comparison.ComparisonVo;
import com.ras.index.Page;
import com.ras.tool.CommonTool;

@Controller
@RequestMapping("/ras/personal")
public class PersonalController{
	
	@Autowired
	private PersonalService service;
	
	@RequestMapping({"","/"})
	public ModelAndView PersonalIndex(){
		Map<String,Object> map=new HashMap<String,Object>();
    	Page page=new Page();
    	page.setHeader_h1("首页");
    	page.setHeader_small("个人设置");
    	map.put("page", page);
		return new ModelAndView("ras/personal/personal",map);
	}
	
	@RequestMapping({"/report/"})
	public ModelAndView personalReportIndex(PersonalVo vo,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String,Object>();
    	Page page=new Page();
    	page.setHeader_h1("个人设置");
    	page.setHeader_small("报告详情");
    	map.put("page", page);
    	map.put("report", service.findPersonalReport(vo));
		return new ModelAndView("ras/personal/report/reportDetail",map);
	}
	
    @RequestMapping(value={"/findpersonalreportgrid"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  findPersonalReportGrid(PersonalVo vo,HttpServletRequest request,HttpServletResponse response) {
    	service.findPersonalReportGrid(vo);
    	CommonTool.writeJSONToPage(response,vo );
    }
    
    @RequestMapping(value={"/report/findpersonalreportcontentgrid"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  findPersonalReportContentGrid(PersonalVo vo,HttpServletRequest request,HttpServletResponse response) {
    	if(!vo.getReportID().equals("")){
        	service.findPersonalReportContentGrid(vo);
        	CommonTool.writeJSONToPage(response,vo );
    	}

    }
}