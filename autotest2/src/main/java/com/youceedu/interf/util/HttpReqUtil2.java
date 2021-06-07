package com.youceedu.interf.util;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @Title:  HttpReqUtil.java   
 * @Package com.youceedu.tools.http   
 * @Description:   ������get��post���󷽷�   
 * @author: maxiaokui     
 * @date:   2021��4��13�� ����10:20:48   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */
public class HttpReqUtil2 {
	//ÿ�ε�����cookie�ǹ��õ�
	private static CookieStore cookieStore= new BasicCookieStore();
	
	//����ͷ������Ϣ����������ʱ��Ӧ�Ĳ�������
	public static void httpRequestConfig(HttpRequestBase httpRequestBase,String parm){
		
		httpRequestBase.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:87.0) Gecko/20100101 Firefox/87.0");
		
		if(JsonUtil.isJsonArray(parm)||JsonUtil.isJsonString(parm)){
			httpRequestBase.setHeader("Content-Type", "application/Json; charset=UTF-8");
		}else{
			httpRequestBase.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		}
		
		//��������ʱ��Ӧ�Ĳ�������
		
		RequestConfig requestConfig = RequestConfig.custom()
				                    .setConnectionRequestTimeout(3000)
				                    .build();
		httpRequestBase.setConfig(requestConfig);

	}
	
	
	/**
	 * 
	 * @Title: sendGet   
	 * @Description: ����get����͵õ�String���͵ķ���ֵ
	 * @param: @param url
	 * @param: @param parm
	 * @param: @return
	 * @param: @throws IOException      
	 * @return: String      
	 * @throws
	 */
	public static String sendGet(String url,String parm) throws IOException{
		String result = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse reponse = null;
		String finaurl = url+"?"+parm;
		try{
			//A �õ�httpclient�����cookie�洢����������������
			//httpclient =HttpClients.createDefault();
			httpclient =HttpClients.custom()
					   .setDefaultCookieStore(cookieStore)
					   .build();
			
			
			//B ��������
			HttpGet httpget =new HttpGet(finaurl);
			//��������ͷ��Ϣ����������ʱ��Ӧ�Ĳ�������
			httpRequestConfig(httpget, parm);

			//��õ�����������ֵ
			reponse = httpclient.execute(httpget);
			//�õ�������������
			int statuscode = reponse.getStatusLine().getStatusCode();
			//�жϷ������Ƿ�Ϊ200
			if(statuscode==HttpStatus.SC_OK){
				//C �о�����������ֵ�������صĲ����ַ�������
				HttpEntity entity = reponse.getEntity();
				
				//E �о�EntityUtils��ת�����ַ�������
				 result = EntityUtils.toString(entity, "utf-8");
			}else{
				result="���ķ�����������Ϊ"+statuscode+"�������ĵ�ַ";
			}
			List<Cookie> listCookie =cookieStore.getCookies();
			for(Cookie cookie:listCookie){
				System.out.println(cookie.getName()+cookie.getValue());
				
			}
			}finally{
			reponse.close();
			httpclient.close();
		}
		return result;
	}
	
	
	/**
	 * 
	 * @Title: sendPost   
	 * @Description: ����post����
	 * @param: @param url
	 * @param: @param parm
	 * @param: @return
	 * @param: @throws IOException      
	 * @return: String      
	 * @throws
	 */
	public static String sendPost(String url,String parm) throws IOException{
		String result = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse reponse = null;
		
		try{
			//A �õ�httpclient�����cookie�洢����������������
			//httpclient =HttpClients.createDefault();
			httpclient =HttpClients.custom()
					   .setDefaultCookieStore(cookieStore)
					   .build();
			//B ��������
			HttpPost httpPost =new HttpPost(url);
			//��������ͷ����������ʱ
			httpRequestConfig(httpPost, parm);
			//����ʱ������ 
			httpPost.setEntity(new StringEntity(parm,"utf-8"));
			
			//��õ�����������ֵ
			reponse = httpclient.execute(httpPost);
			//�õ�������������
			int statuscode = reponse.getStatusLine().getStatusCode();
			//�жϷ������Ƿ�Ϊ200
			if(statuscode==HttpStatus.SC_OK){
				//C �о�����������ֵ�������صĲ����ַ�������
				HttpEntity entity = reponse.getEntity();
				
				//E �о�EntityUtils��ת�����ַ�������
				 result = EntityUtils.toString(entity, "utf-8");
			}else{
				result="���ķ�����������Ϊ"+statuscode+"�������ĵ�ַ";	
			}
			List<Cookie> listCookie =cookieStore.getCookies();
			for(Cookie cookie:listCookie){
				System.out.println("cookie�еĸ�ʽΪ"+cookie.getName()+cookie.getValue());
				
			}
		}finally{
			reponse.close();
			httpclient.close();
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		String url = "http://ssotest.bacic5i5j.com/login?service=http://uat-taiyuan.cbsv.bacic5i5j.com/task-web/cas";
		String param = " ";
		String a = sendGet(url,param);
		System.out.println(a);

	}

}
