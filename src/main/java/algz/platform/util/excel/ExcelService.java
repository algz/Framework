package algz.platform.util.excel;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.View;

public interface ExcelService {

	public HSSFWorkbook createExcel(Map model);

}