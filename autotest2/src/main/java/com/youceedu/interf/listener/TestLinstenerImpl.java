package com.youceedu.interf.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class TestLinstenerImpl implements ITestListener {
	
	ArrayList<ITestResult> testBeRemove = new ArrayList<ITestResult>();
	
	@Override	
	public void onFinish(ITestContext context) {
		
		//�ɹ��������
		Set<ITestResult> allPassTests = context.getPassedTests().getAllResults();
		for(ITestResult allpasstests:allPassTests){
			System.out.println("�ɹ��������ϣ�\n"+allpasstests);
		}
		//ʧ������set���ϣ���Ϊset�������ظ�����
		Set<Integer> failedTestIds=new HashSet<Integer>();
		//ʧ���������
		Set<ITestResult> allfaileTests = context.getFailedTests().getAllResults();
		for(ITestResult allfailetests:allfaileTests){
			int failedHashcodeId  = allfailetests.toString().hashCode();//id����ʧ����������
			if(failedTestIds.contains(failedHashcodeId)){
				//testBeRemove.add(allfailetests);
				//ɾ���ظ���ʧ������
				allfaileTests.remove(allfailetests);
			}else{
				failedTestIds.add(failedHashcodeId);
				System.out.println("ʧ����������"+allfailetests);
			}
		}
		for(ITestResult case1:testBeRemove){
			System.out.println("�ظ���������="+case1);
			//allfaileTests.remove(case1);
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		//System.out.println("������test����ִ��ǰ���У�onTestStart");	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//System.out.println("������test����ִ�гɹ������У�onTestSuccess");	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//System.out.println("������test����ִ��ʧ�ܺ����У�onTestFailure");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//System.out.println("������test����ִ�����������У�onTestSkipped");	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	//System.out.println("������test����ִ��ʧ�ܺ����ڳɹ��ٷֱȷ�Χ�����У�onTestFailedButWithinSuccessPercentage");
	}

	@Override
	public void onStart(ITestContext context) {
		//System.out.println("������ʵ����ǰ���У�onStart");	
	}


//	public void onFinish(ITestContext context) {
//		System.out.println("����������case��ɺ����У�onFinish");	
//		System.out.println("�õ�������"+context.getName());	
//		System.out.println("�õ���ʼʱ��"+context.getStartDate());
//		System.out.println("�õ�����ʱ��"+context.getEndDate());
//		System.out.println("������ɶ�����п���"+context.getOutputDirectory());
//		System.out.println("�õ�host"+context.getHost());
//		System.out.println("�õ�xml��suite����"+context.getSuite().getName());
//		ITestNGMethod[] methodall =context.getAllTestMethods();
//		for(ITestNGMethod a:methodall){
//			System.out.println("�鿴����"+a);
//		}	
//	}


}
