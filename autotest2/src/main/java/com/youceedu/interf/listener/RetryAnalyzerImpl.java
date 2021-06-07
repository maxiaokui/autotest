package com.youceedu.interf.listener;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
/**
 * 
 * @Title:  RetryAnalyzerImpl.java   
 * @Package com.youceedu.interf.listener   
 * @Description: ����ʧ��Ϊ�ǣ�true��,����retry�����������ִ��3�Σ������ô��� 
 * @author: maxiaokui     
 * @date:   2021��4��26�� ����7:00:33   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */
public class RetryAnalyzerImpl implements IRetryAnalyzer{
	
	private int retryCount= 1;
	private int retryMaxCount = 4;
	
	

	@Override
	//�����Ƿ�ִ��ʧ�ܣ���(true)��ִ������ķ�������(false)��ִ��
	public boolean retry(ITestResult result) {
		if(retryCount<retryMaxCount){
			retryCount++;
			return true;
		}
		retryCount= 1;
		return false;
		
		
	}

}
