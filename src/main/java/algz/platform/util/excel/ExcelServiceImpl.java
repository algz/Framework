package algz.platform.util.excel;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.View;

import algz.platform.util.ApplicationContextFactoryUtil;



@Service
public class ExcelServiceImpl implements ExcelService {
	
	/**
	 * 通过反射调用Bean的方法,在存储进EXCEL对象下载类中.
	 */
	@Override
	public HSSFWorkbook createExcel(Map map) {
		Object bean=ApplicationContextFactoryUtil.getBean(map.get("className")+"");
		//依次需要传入 class对象、方法名、参数类型
		Method  mh = ReflectionUtils.findMethod(bean.getClass(), map.get("methodName")+"",null );  
//		Class[] cs=mh.getParameterTypes();
//		for(Class c:cs){
//			if(c.isAssignableFrom(Map.class)){
//				Map m=new HashMap();
//				m
//			}
//		}
		return (HSSFWorkbook)ReflectionUtils.invokeMethod(mh,  bean,map);  

//		ExcelView view=new ExcelView();
//		view.buildExcelDocument(map, wb/*generateWorkbook(model)*/, request, response);
//		return view;
	}
	
	
}
