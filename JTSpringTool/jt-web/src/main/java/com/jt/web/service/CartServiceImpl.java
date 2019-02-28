package com.jt.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.po.Cart;
import com.jt.common.service.HttpClientService;
import com.jt.common.util.MapperUtil;
import com.jt.common.vo.SysResult;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private HttpClientService httpClient;
	@Autowired
	private ObjectMapper objectMapper;


	@Override
	public List<Cart> findCartListByUserId(Long userId) {
		String url = "http://cart.jt.com/cart/query/"+userId;
		String result = httpClient.doGet(url);
		SysResult sysResult = MapperUtil.toObject(result, SysResult.class);
		List<Cart> cartList = (List<Cart>) sysResult.getData();
		return cartList;
	}


	@Override
	public void updateCartNum(Cart cart) {
		String url = "http://cart.jt.com/cart/update/num/"+cart.getUserId()+"/"+cart.getItemId()+"/"+cart.getNum();
		httpClient.doGet(url);
		
	}


	@Override
	public void saveCart(Cart cart) {
		String url = "http://cart.jt.com/cart/save";
		String cartJSON = MapperUtil.toJSON(cart);
		Map<String,String> params = new HashMap<String,String>();
		params.put("cartJSON", cartJSON);
		httpClient.doPost(url, params);
	}


	@Override
	public void deleteCart(Cart cart) {
		String url = "http://cart.jt.com/cart/delete/"+cart.getUserId()+"/"+cart.getItemId();
//		String cartJSON = MapperUtil.toJSON(cart);
//		Map<String, String> params = new HashMap<>();
//		params.put("cartJSON",cartJSON);
//		httpClient.doGet(url, params);
		httpClient.doGet(url);
	}

	
}
