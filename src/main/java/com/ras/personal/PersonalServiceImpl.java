package com.ras.personal;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ras.aircraftReport.AircraftReport;
import com.ras.aircraftReport.AircraftReportDao;
import com.ras.personal.report.ReportService;

@Service
public class PersonalServiceImpl implements PersonalService {

	@Autowired
	private PersonalDao dao;

	@Autowired
	private AircraftReportDao aircraftReportDao;
	

	@Override
	public void exportReportToExcel(HSSFWorkbook workbook,Map m) {
//		HSSFWorkbook workbook = new HSSFWorkbook();
		// 建立 Sheet
		HSSFSheet sheet = workbook.createSheet("报告名称");
//		workbook.setSheetName(0,sheetName,HSSFWorkbook..ENCODING_UTF_16);
		
		List<List<String>> resultList=aircraftReportDao.findAircraftReportContentGrid(m.get("reportID")+"");
		HSSFRow row;
		HSSFCell cell = null;
		int iRow=0;
		for(List<String> temList:resultList){
			row= sheet.createRow((short)iRow);//创建行
			for(int j=0;j<temList.size();j++) {
				cell = row.createCell(j);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				Object oj =temList.get(j);
				
				if (oj == null ) {
					oj = "";
				}
				
				cell.setCellValue(oj.toString());
			}
			iRow++;
		}
		

		
//		// 建立标题
//		HSSFRow header= sheet.createRow((short)0);
//		HSSFCell cell = null;
//		//ResultSetMetaData md=rs.getMetaData();
//		//int nColumn=md.getColumnCount();
//		
//		//写入各个字段的名称
//		for(int i=1;i<=columns.size();i++) {
//			  cell = header.createCell((i-1));
//		      cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//		      cell.setCellValue(columns.get(i));//md.getColumnLabel(i)
//		}
//	
//		// 输出数据
//		HSSFRow row;
//		int iRow=1;
//		//写入各条记录，每条记录对应Excel中的一行
//		while(rs.next())	{
//			row= sheet.createRow((short)iRow);
//			for(int j=1;j<=columns.size();j++) {
//				cell = row.createCell(j-1);
//				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//				Object oj = rs.getObject(j);
//				
//				if (oj == null ) {
//					oj = "";
//				}
//				
//				cell.setCellValue(oj.toString());
//			}
//			iRow++;
//		}
//		return workbook;
		
	}
	
	
}
