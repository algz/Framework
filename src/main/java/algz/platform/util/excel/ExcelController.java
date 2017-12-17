package algz.platform.util.excel;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/excel")
public class ExcelController {

	@Autowired
	private ExcelService service;
	
	/**
	 * 
	 * @param model 必须包含: filename,className,methodName,paramValue
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/exportexcel")
	public ModelAndView exportReportToExcel(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		Map m=new HashMap();
		m.put("className", request.getParameter("className"));
		m.put("methodName", request.getParameter("methodName"));
		m.put("reportID", request.getParameter("reportID"));
		ExcelView view=new ExcelView();
//		try {
//			view.buildExcelDocument(m, service.createExcel(m), request, response);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		return new ModelAndView(view,m);
	}
}
