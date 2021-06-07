package com.youceedu.interf.test;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONPath;
import com.beust.jcommander.Parameter;
import com.youceedu.interf.model.AutoLog;
import com.youceedu.interf.util.DBCPUtil;
import com.youceedu.interf.util.DateTimeUtil;
import com.youceedu.interf.util.ExcelUtil6;
import com.youceedu.interf.util.HttpReqUtil2;
import com.youceedu.interf.util.PatternUtil;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;



public class TestRun2 {
	private String file=null;
	private static List<AutoLog> list= new ArrayList<AutoLog>();
	
	
//	@Parameters({"filePath"})
//	@BeforeClass
//	public void beforeClass(String a){
//		this.file=a;
//	}

		  
	  
	  @Test(dataProvider="dp")
	  public void httpReq(Object Id,Object Test_is_exec,String TestCase,String Req_Type,String Req_host,String Req_interface,String Req_data,String Result_expected,String Is_Dep,String Dep_key) throws Exception {
		  Reporter.log("用例编号为"+Id);
		  Reporter.log("用例是否执行"+Test_is_exec);
		  Reporter.log("请求host:"+Req_host);
		  
		  
		  String requrl = Req_host+Req_interface;
		  String atresult = null;
		  Map<String,String> map = new HashMap<String,String>();
		  
		  //检索是否需要替换
		  String regx1 = "([\\w/]+):([\\$\\.\\w]+)";
		  //请求数据 Req_data
		  Pattern p1 = Pattern.compile(regx1);
		  Matcher matcher1 = p1.matcher(Req_data);
		  
		  while(matcher1.find()){
			  //去处理某个值
		  }
		  
		  
		  
		  if("Yes".equals(Test_is_exec)){
			  if("Get".equals(Req_Type)){
				  //sendGet方法
				  atresult = HttpReqUtil2.sendGet(requrl, Req_data);
			  }else{
				  //sendPost方法
				  atresult = HttpReqUtil2.sendPost(requrl, Req_data);
			  }
		  }else{
			  System.out.println("Test_is_exec为"+Test_is_exec);
		  }
		  if("YES".equals(Is_Dep)){
			  //Dep_key=/api/loginCheck:$.stateCode
			  String regx = "([\\w/]+):([\\$\\.\\w]+)";
			  Pattern p =Pattern.compile(regx);
			 Matcher matcher = p.matcher(Dep_key);
			 while(matcher.find()){
				 System.out.println("group() = "+matcher.group());
				 System.out.println("group(1) = "+matcher.group(1));
				 System.out.println("group(2) = "+matcher.group(2));
				 
				 String value = JSONPath.read(atresult, matcher.group(2)).toString();
				 map.put(matcher.group(), value);
				 for(Entry<String,String> entry:map.entrySet()){
					 System.out.println(entry.getKey()+"****"+entry.getValue());
				 }
			 }
		  }
		  
		  int result = PatternUtil.conpareResultTodb(atresult, Result_expected);
		  list.add(new AutoLog(TestCase,Req_Type,requrl,Req_data,Result_expected,atresult,result,DateTimeUtil.getDateTime()));
		  //预期值与实际值对比
		  PatternUtil.conpareResult(atresult, Result_expected);

 
	  }
	  
	  
	  
	  @DataProvider
	  public Object[][] dp() throws Exception {
		  ExcelUtil6 excel =new ExcelUtil6("E:\\test.xlsx");
		  return excel.fromCellTypeGetCellValue(0);

	  }
	  
	  @AfterTest
	  public void afterTest() throws Exception{
		  System.out.println(DBCPUtil.paramSqlUpdata(list));
	  }
	  


}
