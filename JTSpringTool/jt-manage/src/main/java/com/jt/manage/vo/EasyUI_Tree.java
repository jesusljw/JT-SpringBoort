package com.jt.manage.vo;

import java.io.Serializable;

public class EasyUI_Tree implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3916874758801358890L;
	private Long id;
	private String text;
	private String state;//open开  close关
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public EasyUI_Tree(Long id, String text, String state) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
	}
	public EasyUI_Tree() {
		super();
	}
	
}
