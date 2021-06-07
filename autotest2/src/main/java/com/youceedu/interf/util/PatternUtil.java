package com.youceedu.interf.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.alibaba.fastjson.JSONPath;

import junit.framework.Assert;

public class PatternUtil {
	//����һ��������ʽ
	public static String conpareResultreg = "(\\$\\.[\\w-+]+):([\\u4e00-\\u9fa5\\w]+)";
	//�Ƿ������ͱ�������������ʽ
	public static String isDepreg = "([\\w/]+):([\\$\\.\\w]+)";
	public static String Reqdataregx = "([\\w/]+):([\\$\\.\\w]+)";
	private static Map<String,String> map = new HashMap<String,String>();
	
	/**
	 * 
	 * @Title: getMatcher   
	 * @Description: һ��������ʽ����
	 * @param: @param reg
	 * @param: @param data
	 * @param: @return      
	 * @return: Matcher      
	 * @throws
	 */
	public static Matcher getMatcher(String reg,String data){
		//����һ��������ʽ
		Pattern pa = Pattern.compile(reg);
		//����һ������
		return pa.matcher(data);
	}
	
	/**
	 * 
	 * @Title: handlerReadataOfFun   
	 * @Description: readata�������ݴ���
	 * @param: @param parevalue
	 * @param: @param reqdata
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	
	
	//ֵ����ת��
	public static String handlerReadataOfFun(String reqdata) throws Exception{
		
		  Pattern pattern = Pattern.compile(Reqdataregx);
		  Matcher matcher = pattern.matcher(reqdata);
		  while(matcher.find()){
			  String groupName = matcher.group();
			  String funName=matcher.group(1);
			  String[] funparam = matcher.group(2).replace("(", "").replace(")", "").split(",");
			  
			  String value = null;
			  if(FuncMapingClassUtil.isFunc(funName)){
				  value = FuncMapingClassUtil.getvalue(funName, funparam);
			  }
			  
			  //�滻ֵ��ʼ
			  reqdata=StringUtil.replace(reqdata, groupName, value);
		  }
		  return reqdata;
	}
	/**
	 * 
	 * @Title: isDepCase   
	 * @Description: �Ƿ����������������ֵ��ѷ��ص�ֵͨ��������ʽ�����hashmap��
	 * @param: @param Dep_key
	 * @param: @param atresult      
	 * @return: void      
	 * @throws
	 */
	public static void isDepCase(String Dep_key,String atresult){
		//����getmacher
		Matcher matcher = getMatcher(isDepreg,Dep_key);

		  while(matcher.find()){
			  //System.out.println("������"+matcher.group());
			  //System.out.println("������1"+matcher.group(1));
			 // System.out.println("������2"+matcher.group(2));
			  
			  String value = JSONPath.read(atresult, matcher.group(2)).toString(); 
			  //�ŵ�map����Ϊmap��ʽ��key:value
			  map.put(matcher.group(), value);
			  for(Entry<String,String> entry:map.entrySet()){
				  
				  System.out.println("��������patternUtil��������ŵ�mapֵ��"+entry.getKey()+"    "+entry.getValue());
			  }
		  }
	}
	
	
	
	public static void conpareResult(String atresult,String Result_expected){
		
		 //Ԥ�ڽ������ֵ
		//System.out.println("Ԥ�ڽ������ֵ"+Result_expected);
		Matcher matcher=getMatcher(conpareResultreg, Result_expected);
		while(matcher.find()){
			String expjsonPath = matcher.group(1);
			String expvalue = matcher.group(2);
			//ʵ�ʽ������ֵ
		    String  actvalue = JSONPath.read(atresult, expjsonPath).toString();
		    //assert ������ʧ�ܵ���������������������ִ��
		    Assert.assertEquals(expvalue,actvalue);
		  }		
	}
	
	
	public static int conpareResultTodb(String atresult,String Result_expected){
		
		int flag = 0;
		List<Integer> list = new ArrayList<Integer>();
		
		 //Ԥ�ڽ������ֵ
		//System.out.println("Ԥ�ڽ������ֵ"+Result_expected);
		Matcher matcher=getMatcher(conpareResultreg, Result_expected);
		while(matcher.find()){
			String expjsonPath = matcher.group(1);
			String expvalue = matcher.group(2);
			//ʵ�ʽ������ֵ
		    String  actvalue = JSONPath.read(atresult, expjsonPath).toString();
		    int staus = actvalue.equals(expvalue)?1:0;
		    list.add(staus);
		  }	
		if(!list.contains(0)){
			flag = 1;
		}
		return flag;
	}
	
	


			
	public static void  main(){
		}
}
