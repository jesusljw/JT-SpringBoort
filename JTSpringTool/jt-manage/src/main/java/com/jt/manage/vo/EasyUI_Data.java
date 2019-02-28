package com.jt.manage.vo;

import java.io.Serializable;
import java.util.List;


import com.jt.common.po.Item;

public class EasyUI_Data implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6758339638635063133L;
	private Integer total;
	private List<Item> rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<Item> getRows() {
		return rows;
	}
	public void setRows(List<Item> rows) {
		this.rows = rows;
	}
	
	
	
	public EasyUI_Data(Integer total, List<Item> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public EasyUI_Data() {
		super();
	}
	@Override
	public String toString() {
		return "EasyUI_Data [total=" + total + ", rows=" + rows + "]";
	}
	
}
