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



public class TestRun {
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
		  
		  System.out.println("原始数据"+Req_data);
		  String str = "\\$\\{\\_\\_(\\w+)(\\([\\w,]+\\))\\}";
		
		  Req_data = PatternUtil.handlerReadataOfFun( Req_data);
		  System.out.println("计算后"+Req_data);
		  
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
		  //判断是否依赖
		  if("YES".equals(Is_Dep)){
			  //
			  PatternUtil.isDepCase(Dep_key, atresult);
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
