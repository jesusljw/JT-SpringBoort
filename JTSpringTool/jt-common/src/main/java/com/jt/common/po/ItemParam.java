package com.jt.common.po;

public class ItemParam extends BasePojo{

	private Long id;
	private Long itemCatId;
	private String paramData;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemCatId() {
		return itemCatId;
	}
	public void setItemCatId(Long itemCatId) {
		this.itemCatId = itemCatId;
	}
	public String getParamData() {
		return paramData;
	}
	public void setParamData(String paramData) {
		this.paramData = paramData;
	}
	public ItemParam(Long id, Long itemCatId, String paramData) {
		super();
		this.id = id;
		this.itemCatId = itemCatId;
		this.paramData = paramData;
	}
	public ItemParam() {
		super();
	}
	
}
