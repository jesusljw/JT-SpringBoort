package com.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.manage.service.ItemService;

@RestController  //@Controller+@ResponseBody  类中的所有方法的返回值必须为JSON
//@Controller
@RequestMapping("/web/item")
public class WebItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/findItemById")
//	@ResponseBody//表示返回的数据为JSON串
	public Item findItemById(Long itemId) {
		return itemService.findItemById(itemId);
	}
	
	@RequestMapping("/findItemDescById")
	public ItemDesc findItemDescById(Long itemId) {
		
		return itemService.findItemDescById(itemId);
	}
}
