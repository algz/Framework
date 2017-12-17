package com.ras.personal.report;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ras.index.Page;
import com.ras.tool.CommonTool;

@Controller
@RequestMapping("/ras/personal/report")
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	
	@RequestMapping({"","/"})
	public ModelAndView personalReportIndex(ReportVo vo,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map=new HashMap<String,Object>();
    	Page page=new Page();
    	page.setHeader_h1("个人设置");
    	page.setHeader_small("报告详情");
    	map.put("page", page);
    	map.put("report", service.findReport(vo));
		return new ModelAndView("ras/personal/report/reportDetail",map);
	}
	
    @RequestMapping(value={"/findpersonalreportgrid"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  findPersonalReportGrid(ReportVo vo,HttpServletRequest request,HttpServletResponse response) {
    	service.findReportGrid(vo);
    	CommonTool.writeJSONToPage(response,vo );
    }
    
    @RequestMapping(value={"/report/findpersonalreportcontentgrid"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  findPersonalReportContentGrid(ReportVo vo,HttpServletRequest request,HttpServletResponse response) {
    	if(!vo.getReportID().equals("")){
        	service.findReportContentGrid(vo);
        	CommonTool.writeJSONToPage(response,vo );
    	}
    }
	
	
    /**
     * 生成报告
     * @param request
     * @param response
     */
    @RequestMapping(value={"/savereport"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  saveReport(HttpServletRequest request,HttpServletResponse response) {
    	String reportName=request.getParameter("reportName");
    	String reportDes=request.getParameter("reportDes");
    	String[] reportContent=request.getParameterValues("reportContent[]");
    	service.saveReport(reportName, reportDes, reportContent);

    	CommonTool.writeJSONToPage(response," {\"success\":true}" );
    }
    
    
    @RequestMapping(value={"/delfavorites"}) //@RequestMapping 注解的方法才是真正处理请求的处理器
    public void  delReport(ReportVo vo,HttpServletRequest request,HttpServletResponse response) {
        	service.delReport(vo);
        	CommonTool.writeJSONToPage(response,"{\"success\":true}" );
    }
}
