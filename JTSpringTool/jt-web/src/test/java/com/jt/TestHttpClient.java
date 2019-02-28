package com.jt;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpClient {
	
	/**模拟发起get请求
	 * 1.创建httpClient对象
	 * 2.定义url路径
	 * 3.定义请求方式get/POST
	 * 4.发起request请求,获取response响应
	 * 5.判断状态码是否正确200; 400:请求参数异常; 406:报错:返回结果与页面要求不匹配; 404找不到请求路径; 500:服务器异常
	 * 6.获取响应结果
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Test
	public void get() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String url = "https://item.jd.com/40117351979.html";
		HttpGet get = new HttpGet(url);
		HttpPost post = new HttpPost(url);
		CloseableHttpResponse response = httpClient.execute(get);
		if(response.getStatusLine().getStatusCode()==200) {
			System.out.println("跨域系统访问成功");
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		}
	}
}
