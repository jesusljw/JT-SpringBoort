package com.jt.sso.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;
import com.jt.sso.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private JedisCluster jedisCluster;
	
	/**
	 * 实现用户信息校验
	 * URL: http://sso.jt.com/user/check/admin123/1?r=0.12653915941955485&callback=jsonp1550651786889&_=1550651807696
	 */
	@RequestMapping("/check/{param}/{type}")
	public JSONPObject findCheckUser(String callback,@PathVariable String param,@PathVariable Integer type) {
		boolean flag = userService.findCheckUser(param,type);
		SysResult result = SysResult.oK(flag);
		return new JSONPObject(callback, result);
		
	}
	//http://sso.jt.com/user/register
	@RequestMapping("/register")
	public SysResult saveUser(User user) {
		try {
			userService.saveUser(user);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "用户新增失败");
	}
	
	//实现用户信息校验
	@RequestMapping("/login")
	public SysResult findUserByUP(User user) {
		try {
			String token = userService.findUserByUP(user);
			if(!StringUtils.isEmpty(token)) {
				return SysResult.oK(token);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "用户登录失败");
		
	}
	
	//通过跨域,实现用户的信息获取
	@RequestMapping("/query/{token}")
	public JSONPObject findUserByToken(@PathVariable String token,String callback) {
		String userJSON = jedisCluster.get(token);
		return new JSONPObject(callback, SysResult.oK(userJSON));
		
	}
	
	
	
}
