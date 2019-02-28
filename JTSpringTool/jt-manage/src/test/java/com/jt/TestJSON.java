package com.jt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.manage.pojo.User;

public class TestJSON {

	@Test 
	public void ObjectToJSON() throws IOException {
		User user1 = new User();
		user1.setId(100);
		user1.setName("redis转化");
		user1.setAge(18);
		user1.setSex("男");
		
		ObjectMapper mapper = new ObjectMapper();
		//将对象转化为JSON串
		String JSON = mapper.writeValueAsString(user1);
		System.out.println(JSON);
		//将JSON转化为对象  src 表述需要转化的JSON数据   ValueType表述 转化的数据类型
		User tempUser = mapper.readValue(JSON, User.class);
	}
	@Test
	public void JSONToObject() throws IOException {
		
		User user1 = new User();
		user1.setId(100);
		user1.setName("redis转化");
		user1.setAge(18);
		user1.setSex("男");
		
		User user2 = new User();
		user2.setId(200);
		user2.setName("redis转化");
		user2.setAge(19);
		user2.setSex("男");
		
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String listJSON = objectMapper.writeValueAsString(userList);
		System.out.println(listJSON);
		//将listJSON转化为对象List<User>
		
//		@SuppressWarnings("unchecked")
//		List<User> list = objectMapper.readValue(listJSON,userList.getClass());
		
		User[] users = objectMapper.readValue(listJSON, User[].class);
		System.out.println(Arrays.asList(users));
	}
	
}
