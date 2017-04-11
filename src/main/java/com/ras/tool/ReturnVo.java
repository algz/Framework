package com.ras.tool;

import java.util.List;

/**
 * 分页使用 start,length;
 * JS获取dataTable 数据总数(用于分页) recordsTotal,recordsFiltered
 * 
 * @author algz
 *
 * @param <T>
 */
public class ReturnVo<T> {
	
	private Integer draw;
	
	private Integer recordsTotal;
	
	private Integer recordsFiltered;
	

	private Integer start;
	
	private Integer length;
	
	private  List<T> data;

	
	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Integer getRecordsFiltered() {
		return recordsFiltered==null?this.recordsTotal:this.recordsFiltered;
	}

	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public final void setData(List<T> data) {
		this.data = data;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
	
}
