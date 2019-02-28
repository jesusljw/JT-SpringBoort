package com.jt.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Item;
import com.jt.common.po.ItemDesc;
import com.jt.common.service.HttpClientService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private HttpClientService httpClient;

	@Override
	public Item findItemById(Long itemId) {
		//1.定义远程访问后台的url地址
		String url = "http://manage.jt.com/web/item/findItemById";
		//2.定义参数
		Map<String,String> params = new HashMap<>();
		params.put("itemId", itemId+"");
		//3发起请求,获取返回值结果json串
		String result = httpClient.doGet(url, params);
		System.out.println(result);
		Item item = null;
		try {
			item = objectMapper.readValue(result, Item.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public ItemDesc findItemDescById(Long itemId) {
		String url = "http://manage.jt.com/web/item/findItemDescById";
		Map<String,String> params = new HashMap<>();
		params.put("itemId", itemId+"");
		String result = httpClient.doGet(url, params);
		System.out.println(result);
		ItemDesc itemDesc = null;
		try {
			itemDesc = objectMapper.readValue(result, ItemDesc.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return itemDesc;
	}
	
}
