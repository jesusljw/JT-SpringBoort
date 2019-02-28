package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Cat;
import com.example.demo.pojo.Dog;
import com.example.demo.pojo.User;

@RestController
public class HelloController {
	
	@Autowired
	private User user;
	@Autowired
	private Dog dog;
	
	@RequestMapping("/hello")
	public String hello() {
		return "您好,SpringBool";
	}
	
	@RequestMapping("/getUser")
	public User getUser() {
		return user;
	}
	@RequestMapping("/getDog")
	public Dog getDog() {
		return dog;
	}
	@RequestMapping("/getCat")
	public Cat getCat() {
		Cat cat = new Cat().setId(100).setAge(19).setName("xiaomao");
		return cat;
	}
	
	
}
