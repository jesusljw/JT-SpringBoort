package com.jt.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.jt.order.pojo.Order;
import com.jt.order.service.OrderService;
import com.jt.order.vo.SysResult;
import com.jt.util.MapperUtil;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/create")
	public SysResult saveOrder(String orderJSON) {
		try {
			Order order = MapperUtil.toObject(orderJSON, Order.class);
			String orderId = orderService.saveOrder(order);
			if(!StringUtils.isEmpty(orderId)) {
				return SysResult.oK(orderId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return SysResult.build(201, "订单入库失败");
	}
	
	@RequestMapping("/query/{orderId}")
	public Order findOrderById(@PathVariable String orderId) {
		return orderService.findOrderById(orderId);
		
	}
	
	
	
	
	
}
