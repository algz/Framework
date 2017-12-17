package algz.platform.util.sql;

public class SQLUtil {


	
	public static String createTable(String tableName,Column[] cols){
		StringBuilder sql=new StringBuilder();
		sql.append("CREATE TABLE "+tableName+" (");
		for(int i=0;i<cols.length;i++){
			if(i!=0){
				sql.append(",");
			}
			sql.append(cols[i].getName()+" "+cols[i].getDataType());
		}
		return sql.toString()+")";
	}
	
	
}
