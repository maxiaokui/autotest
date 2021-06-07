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
		  Reporter.log("�������Ϊ"+Id);
		  Reporter.log("�����Ƿ�ִ��"+Test_is_exec);
		  Reporter.log("����host:"+Req_host);
		  
		  
		  String requrl = Req_host+Req_interface;
		  String atresult = null;
		  
		  System.out.println("ԭʼ����"+Req_data);
		  String str = "\\$\\{\\_\\_(\\w+)(\\([\\w,]+\\))\\}";
		
		  Req_data = PatternUtil.handlerReadataOfFun( Req_data);
		  System.out.println("�����"+Req_data);
		  
		  if("Yes".equals(Test_is_exec)){
			  if("Get".equals(Req_Type)){
				  //sendGet����
				  atresult = HttpReqUtil2.sendGet(requrl, Req_data);
			  }else{
				  //sendPost����
				  atresult = HttpReqUtil2.sendPost(requrl, Req_data);
			  }
		  }else{
			  System.out.println("Test_is_execΪ"+Test_is_exec);
		  }
		  //�ж��Ƿ�����
		  if("YES".equals(Is_Dep)){
			  //
			  PatternUtil.isDepCase(Dep_key, atresult);
		  }
		  int result = PatternUtil.conpareResultTodb(atresult, Result_expected);
		  list.add(new AutoLog(TestCase,Req_Type,requrl,Req_data,Result_expected,atresult,result,DateTimeUtil.getDateTime()));
		  //Ԥ��ֵ��ʵ��ֵ�Ա�
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
