package algz.platform.util.excel.test;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ExcelTestDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	/** 
	* @Title: resultSetToExcel 
	* @Description: 根据结果集生成excel
	* @param  rs 数据集
	* @param  sheetName 工作表名称
	* @param  columns 数据列集
	* @return HSSFWorkbook    返回类型 
	* @throws 
	*/
	public HSSFWorkbook resultSetToExcel(ResultSet rs, String sheetName,List<String> columns ) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 建立 Sheet
		HSSFSheet sheet = workbook.createSheet("参与抽奖活动人员名单");
		//workbook.setSheetName(0,sheetName,HSSFWorkbook..ENCODING_UTF_16);
		
		// 建立标题
		HSSFRow header= sheet.createRow((short)0);
		HSSFCell cell = null;
		//ResultSetMetaData md=rs.getMetaData();
		//int nColumn=md.getColumnCount();
		
		//写入各个字段的名称
		for(int i=1;i<=columns.size();i++) {
			  cell = header.createCell((i-1));
		      cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		      cell.setCellValue(columns.get(i));//md.getColumnLabel(i)
		}
	
		// 输出数据
		HSSFRow row;
		int iRow=1;
		//写入各条记录，每条记录对应Excel中的一行
		while(rs.next())	{
			row= sheet.createRow((short)iRow);
			for(int j=1;j<=columns.size();j++) {
				cell = row.createCell(j-1);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				Object oj = rs.getObject(j);
				
				if (oj == null ) {
					oj = "";
				}
				
				cell.setCellValue(oj.toString());
			}
			iRow++;
		}
		return workbook;
	}
	
	/** 
	* @Title: generateWorkbook 
	* @Description: 根据条件生成excel文本对象
	* @param  condition
	* @return HSSFWorkbook    返回类型 
	* @throws 
	*/
	public HSSFWorkbook generateWorkbook(Map<String, Object> condition) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		String sql = "select u.id,e.name, u.phone, u.iswin, u.winlevel, u.prize, u.inputtime, u.wintime from yiya_eventtipsuser u, yiya_xms_flexible e where u.tipid = e.id";
		List<Object[]> objs=sessionFactory.openSession().createSQLQuery(sql).list();
		ResultSet rs = null;//baseDao.getResultSet(sql);
		if (rs != null) {
			//excel文件名
			String sheetName = "人员名单";
			try {
				workbook = this.resultSetToExcel(rs, sheetName,null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return workbook;
	}
}
