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
	//定义一个正则表达式
	public static String conpareResultreg = "(\\$\\.[\\w-+]+):([\\u4e00-\\u9fa5\\w]+)";
	//是否依赖和被依赖的正则表达式
	public static String isDepreg = "([\\w/]+):([\\$\\.\\w]+)";
	public static String Reqdataregx = "([\\w/]+):([\\$\\.\\w]+)";
	private static Map<String,String> map = new HashMap<String,String>();
	
	/**
	 * 
	 * @Title: getMatcher   
	 * @Description: 一个正则表达式方法
	 * @param: @param reg
	 * @param: @param data
	 * @param: @return      
	 * @return: Matcher      
	 * @throws
	 */
	public static Matcher getMatcher(String reg,String data){
		//传入一个正则表达式
		Pattern pa = Pattern.compile(reg);
		//传入一个数据
		return pa.matcher(data);
	}
	
	/**
	 * 
	 * @Title: handlerReadataOfFun   
	 * @Description: readata依赖数据处理
	 * @param: @param parevalue
	 * @param: @param reqdata
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	
	
	//值进行转换
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
			  
			  //替换值开始
			  reqdata=StringUtil.replace(reqdata, groupName, value);
		  }
		  return reqdata;
	}
	/**
	 * 
	 * @Title: isDepCase   
	 * @Description: 是否被依赖，如果被依赖值则把返回的值通过正则表达式存放在hashmap里
	 * @param: @param Dep_key
	 * @param: @param atresult      
	 * @return: void      
	 * @throws
	 */
	public static void isDepCase(String Dep_key,String atresult){
		//调用getmacher
		Matcher matcher = getMatcher(isDepreg,Dep_key);

		  while(matcher.find()){
			  //System.out.println("这是组"+matcher.group());
			  //System.out.println("这是组1"+matcher.group(1));
			 // System.out.println("这是组2"+matcher.group(2));
			  
			  String value = JSONPath.read(atresult, matcher.group(2)).toString(); 
			  //放到map里因为map格式是key:value
			  map.put(matcher.group(), value);
			  for(Entry<String,String> entry:map.entrySet()){
				  
				  System.out.println("我现在是patternUtil里的输出存放的map值："+entry.getKey()+"    "+entry.getValue());
			  }
		  }
	}
	
	
	
	public static void conpareResult(String atresult,String Result_expected){
		
		 //预期结果返回值
		//System.out.println("预期结果返回值"+Result_expected);
		Matcher matcher=getMatcher(conpareResultreg, Result_expected);
		while(matcher.find()){
			String expjsonPath = matcher.group(1);
			String expvalue = matcher.group(2);
			//实际结果返回值
		    String  actvalue = JSONPath.read(atresult, expjsonPath).toString();
		    //assert 发现有失败的用例后续的用例都不再执行
		    Assert.assertEquals(expvalue,actvalue);
		  }		
	}
	
	
	public static int conpareResultTodb(String atresult,String Result_expected){
		
		int flag = 0;
		List<Integer> list = new ArrayList<Integer>();
		
		 //预期结果返回值
		//System.out.println("预期结果返回值"+Result_expected);
		Matcher matcher=getMatcher(conpareResultreg, Result_expected);
		while(matcher.find()){
			String expjsonPath = matcher.group(1);
			String expvalue = matcher.group(2);
			//实际结果返回值
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
