package com.jt.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtil {

	/**
	 * 该工具类实现objectMapper的对象的转化,优化了try-cathch
	 */
	
	public static String toJSON(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return json;
		
	}
	//将json数据转化为对象
		public static <T> T toObject(String json,Class<?> targetClass){
			ObjectMapper mapper = new ObjectMapper();
			T object = null;
			try {
				object = (T) mapper.readValue(json, targetClass);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			return object;
		}
	
	
}
