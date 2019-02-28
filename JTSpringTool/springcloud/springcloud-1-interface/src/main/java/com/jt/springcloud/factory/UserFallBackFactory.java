package com.jt.springcloud.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jt.springcloud.pojo.User;
import com.jt.springcloud.service.UserService;

import feign.hystrix.FallbackFactory;
@Component //交给spring容器管理
public class UserFallBackFactory implements FallbackFactory<UserService>{

	@Override
	public UserService create(Throwable cause) {
		// 该接口
		return new UserService() {
			
			@Override
			public String saveUser(User user) {
				// TODO Auto-generated method stub
				return "后台服务器异常";
			}
			
			@Override
			public List<User> findAll() {
				
					User user = new User();
					user.setId(0);
					user.setName("后台服务器异常");
					user.setAge(0);
					user.setSex("");
					List<User> userList = new ArrayList<User>();
					userList.add(user);
					return userList; 
				
			}
		};
	}

}
