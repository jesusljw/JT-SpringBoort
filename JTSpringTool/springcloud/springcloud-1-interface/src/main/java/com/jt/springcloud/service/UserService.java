package com.jt.springcloud.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.springcloud.factory.UserFallBackFactory;
import com.jt.springcloud.pojo.User;
@FeignClient(value="provider-user",fallbackFactory=UserFallBackFactory.class)	//写入微服务名称
public interface UserService {
	
	@RequestMapping(value="/findAll")
	List<User> findAll();

	
	@RequestMapping("/saveUser")
	public String saveUser(@RequestBody User user);
}
