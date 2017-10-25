package com.ras.personal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ras.aircraftReport.AircraftReport;
import com.ras.searchParam.SearchParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository
public class PersonalDaoImpl implements PersonalDao {

	@Autowired
	private SessionFactory sf;

	

	@Override
	public HSSFWorkbook exportReportToExcel(Map m) {
//		m.get("reportID");
//		Report report=(Report)sf.getCurrentSession().get(Report.class, m.get("reportID")+"");
//		this.findPersonalReportContentGrid();
//		
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		// 建立 Sheet
//		HSSFSheet sheet = workbook.createSheet("参与抽奖活动人员名单");
//		//workbook.setSheetName(0,sheetName,HSSFWorkbook..ENCODING_UTF_16);
//		
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
		return null;
	}

}
