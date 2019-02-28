package com.jt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.Transaction;

public class TestRedis {
	
	/**
	 * 连接单台redis
	 * 参数介绍:
	 * 	redisIP地址.
	 *  redis:6379
	 */
	@Test
	public void testString() {
		Jedis jedis = new Jedis("192.168.161.138",6379);
		jedis.set("1810","1810班");
		jedis.set("1811","1811班");
		jedis.set("1812","1812班");
		String value = jedis.get("1810");
		System.out.println("从redis中获取数据:"+value);
		//为数据设定超时时间单位秒
		jedis.setex("1804", 100, "1804班");
	}
	//操作hash
	@Test
	public void testhash() {
		Jedis jedis = new Jedis("192.168.161.138",6379);
		jedis.hset("user", "id", "1");
		jedis.hset("user", "name", "tomcat");
		jedis.hset("user", "age", "18");
		System.out.println("操作完成"+jedis.hget("user","id"));
		Map<String, String> map = jedis.hgetAll("user");
		System.out.println(map);
	}
	//操作list
	@Test
	public void testList01() {
		Jedis jedis = new Jedis("192.168.161.138",6379);
		Long number = jedis.lpush("list", "a","b","c","d","e");
		System.out.println("获取数据"+number);
		List<String> list = jedis.lrange("list", 0, -1);
		System.out.println("获取参数"+list);
	}
	
	@Test
	public void testList02() {
		Jedis jedis = new Jedis("192.168.161.138",6379);
		jedis.lpush("list", "1","2","3","4","5");
		for (int i = 0; i < 5; i++) {
			String value = jedis.lpop("list");
			System.out.println(value);
		}
		
	}
	
	//实现redis事务控制
	@Test
	public void testTx() {
		Jedis jedis = new Jedis("192.168.161.138",6379);
		//1.先开启事务
		Transaction transaction = jedis.multi();
		
		try {
			transaction.set("d", "d");
			int a = 1/0;
			//2.事务提交
			transaction.exec();
			
		} catch (Exception e) {
			e.printStackTrace();
			//3.事务回滚
			transaction.discard();
			System.out.println("事务回滚");
		}
		
	}
	
	//实现redis分片操作
	@Test
	public void testShards() {
		String host = "192.168.161.138";
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo(host,6379));
		shards.add(new JedisShardInfo(host,6380));
		shards.add(new JedisShardInfo(host,6381));
		ShardedJedis shardedJedis = new ShardedJedis(shards);
		
		shardedJedis.set("shards", "完成分片操作");
		System.out.println("获取数据:"+shardedJedis.get("shards"));
		
	}
	
	//实现哨兵的操作
	@Test
	public void testSentinel() {
		Set<String> sentinels = new HashSet<>();
		//sentinels.add(new HostAndPort(host,port).toString());
		sentinels.add("192.168.161.138:26379");
		JedisSentinelPool pool = new JedisSentinelPool("mymaster",sentinels);
		//获取redis链接
		Jedis jedis = pool.getResource();
		jedis.set("aa", "redis哨兵配置");
		System.out.println(jedis.get("aa"));
		jedis.close();
		
	}
	
	//实现redis集群操作
	@Test
	public void testCluster() {
		Set<HostAndPort> nodes = new HashSet<>();
		String host = "192.168.161.138";
		nodes.add(new HostAndPort(host,7000));
		nodes.add(new HostAndPort(host,7001));
		nodes.add(new HostAndPort(host,7002));
		nodes.add(new HostAndPort(host,7003));
		nodes.add(new HostAndPort(host,7004));
		nodes.add(new HostAndPort(host,7005));
		nodes.add(new HostAndPort(host,7006));
		nodes.add(new HostAndPort(host,7007));
		nodes.add(new HostAndPort(host,7008));
		JedisCluster cluster = new JedisCluster(nodes);
		
		cluster.set("redis", "学习集群使用");
		System.out.println("获取数据:"+cluster.get("redis"));
	}
	
	
	
	
	
}
