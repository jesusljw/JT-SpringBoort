package com.jt;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

import redis.clients.jedis.JedisCluster;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMybatis {
	//该测试类,相当于直接获取了spring容器对象
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private User user;
	@Autowired
	private JedisCluster jedisCluster;
	
	@Test
	public void testFind() {
		List<User> userList = userMapper.findAll();
		System.out.println(userList);
	}
	//实现查询操作  查询age=3000岁
	//要求0<age<150     等于:eq    大于:gt    小于:lt
	@Test
	public void testFind2() {
//		User user = new User().setAge(3000);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//		queryWrapper.eq("age", 3000);
		queryWrapper.gt("age", 0).lt("age", 150);
		List<User> userList = userMapper.selectList(queryWrapper);
		System.out.println(userList);
	}
	
	
	@Test
	public void testInster() {
		User user = new User().setName("流浪地球").setAge(1).setSex("男");
		int rows = userMapper.insert(user);
		System.out.println("入库成功影响了"+rows+"条");
	}
	
	@Test
	public void testDelet() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.eq("name", "流浪地球");
		userMapper.delete(queryWrapper);
		System.out.println("删除成功");
	}
	/**
	 * 修改操作
	 * 1.entity 修改后的值使用entity封装
	 * 2.updateWrapper 定义where条件的值
	 */
	@Test
	public void testUpdate() {
		User user = new User().setName("汤圆").setAge(18).setSex("女");
		UpdateWrapper<User>  updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("id", 52);
		userMapper.update(user, updateWrapper);
		System.out.println("修改成功");
	}
	
	
	@Test
	public void getUser() {
		System.out.println(user);
	}
	
	
	@Test
	public void testRedis() {
		jedisCluster.set("1810", "spring整合redis集群");
		System.out.println(jedisCluster.get("1810"));
	}
}
