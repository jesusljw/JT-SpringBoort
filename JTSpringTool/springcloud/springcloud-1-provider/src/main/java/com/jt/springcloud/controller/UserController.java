package com.jt.springcloud.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jt.springcloud.pojo.User;
import com.jt.springcloud.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	//查询用户列表数据
	@HystrixCommand(fallbackMethod="hystrix_findAll")
	@RequestMapping("/findAll")
	public List<User> findAll(){
		System.out.println("第一个提供者");
//		throw new RuntimeException();
		return userService.findAll();
	}
	
	
	public List<User> hystrix_findAll(){
		User user = new User();
		user.setId(0);
		user.setName("后台服务器异常");
		user.setAge(0);
		user.setSex("");
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		return userList; 
	}
	
	/*
	 * 服务提供者 用户新增
	 * @ResponseBody 将对象转化为JSON
	 * @RequestBody  将json串转化为对象
	 */
	@RequestMapping("/saveUser")
	public String saveUser(@RequestBody User user) {
		
		return userService.saveUser(user);
	}
	
	@RequestMapping("/delUser/{id}")
	public String delUser(@PathVariable Integer id) {
		
		return userService.delUser(id);
	}
	
	
	
	
	
	
	
	
	
}
