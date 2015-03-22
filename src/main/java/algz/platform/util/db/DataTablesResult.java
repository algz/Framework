package algz.platform.util.db;

import java.io.Serializable;
import java.util.List;

/**
 * 查询结果的封装
 *
 * @version     : 1.0 
 * @since       : 
 * @team	    : 
 * @author      : 
*/
public class DataTablesResult<T>  implements Serializable{
	
	/**
	 * 
	 */
//	private static final long serialVersionUID = -1237251956038976694L;

	/**数据结果*/
	private List<T> data;
	
	/** 数据条数 */
	private long recordtotal;

	public DataTablesResult(){
		
	}
	
	public List<T> getData() {
		return data;
	}
	
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public long getRecordtotal() {
		return recordtotal;
	}
	
	public void setRecordtotal(long recordtotal) {
		this.recordtotal = recordtotal;
	}
	
}
