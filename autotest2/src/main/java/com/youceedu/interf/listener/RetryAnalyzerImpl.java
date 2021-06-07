package com.youceedu.interf.listener;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
/**
 * 
 * @Title:  RetryAnalyzerImpl.java   
 * @Package com.youceedu.interf.listener   
 * @Description: 用例失败为是（true）,调用retry方法，最多再执行3次，可配置次数 
 * @author: maxiaokui     
 * @date:   2021年4月26日 下午7:00:33   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */
public class RetryAnalyzerImpl implements IRetryAnalyzer{
	
	private int retryCount= 1;
	private int retryMaxCount = 4;
	
	

	@Override
	//用例是否执行失败，是(true)则执行下面的方法，否(false)则不执行
	public boolean retry(ITestResult result) {
		if(retryCount<retryMaxCount){
			retryCount++;
			return true;
		}
		retryCount= 1;
		return false;
		
		
	}

}
