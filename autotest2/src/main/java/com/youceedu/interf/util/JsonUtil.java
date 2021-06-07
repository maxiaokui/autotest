package com.youceedu.interf.util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Title:  JsonUtil.java   
 * @Package com.youceedu.interf.util   
 * @Description: json������ 
 * @author: wangyanzhao     
 * @date:   2021��03��24�� ����10:54:38   
 * @version V1.0 
 * @Copyright: 2020 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */
public class JsonUtil {
	
	/**
	 * @Title: isJsonString   
	 * @Description: �ж�jsonStr�Ƿ�Ϊjson�ַ���
	 * @param: @param jsonStr
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean isJsonString(String jsonStr) {
		//��ʼ��
		boolean flag = false;
		try {
			JSONObject json = JSON.parseObject(jsonStr);
			flag = true;
		}catch(Exception e) {
		}
		return flag;
	}
	
	/**
	 * @Title: isJsonArray   
	 * @Description: �ж�jsonArray�Ƿ�Ϊjson����
	 * @param: @param jsonArray
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean isJsonArray(String jsonArray) {
		//��ʼ��
		boolean flag = false;
		try {
			JSONArray jSONArray = JSONArray.parseArray(jsonArray);
			flag = true;
		}catch(Exception e) {
		}
		return flag;
	}

	public static void main(String[] args) {
		//��ˮ�ߴ���
		//String jsonStr = "{\"status\":\"true\",\"msg\":\"��¼�ɹ�!\"}";
		//System.out.println("jsonStr�ַ����Ƿ�Ϊjson��ʽ :" + isJsonString(jsonStr));
		
		String jsonArray = "[{\"status\":\"true\",\"msg\":\"��¼�ɹ�!\"},{\"name\":\"zhangsan\",\"score\":90}]";
		System.out.println("jsonArray�ַ����Ƿ�Ϊjson�����ʽ :" + isJsonArray(jsonArray));
	}
}