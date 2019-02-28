package com.jt.common.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name="tb_item")//定义表名与对象一一映射

@JsonIgnoreProperties(ignoreUnknown=true)//进行JSON转化时,忽略未知属性

public class Item extends BasePojo{
	@Id//定义主键
	@GeneratedValue(strategy=GenerationType.IDENTITY)//定义主键自增
	private Long id;
	//@Column(name="title")
	private String title;			//标题元素
	//@Column(name="sell_point")    //自定义映射字段
	private String sellPoint;	    //卖点信息
	private Long price;				//价格  将价格扩大100倍保存,页面展现时缩小100倍
	private Integer num;			//数量
	private String barcode;			//二维码信息
	private String image;			//图片信息 1.jpg,2.jpg,3.jpg
	private Long cid;				//商品分类信息 1正常，2下架，3删除
	private Integer status;			//1正常，2下架，3删除
	
	/**
	 * 由于前台需要通过get方法获取第一张图片信息 手动的添加一个get方法
	 * @return
	 */
	public String[] getImages() {
		return image.split(",");
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", price=" + price + ", num=" + num
				+ ", barcode=" + barcode + ", image=" + image + ", cid=" + cid + ", status=" + status + "]";
	}
	
	
}
