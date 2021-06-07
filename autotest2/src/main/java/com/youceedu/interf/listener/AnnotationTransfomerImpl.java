package com.youceedu.interf.listener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
/**
 * 
 * @Title:  AnnotationTransfomerImpl.java   
 * @Package com.youceedu.interf.listener   
 * @Description: ����ʧ��ִ�����ԣ����õ�RetryAnalyzerImll��  
 * @author: maxiaokui     
 * @date:   2021��4��26�� ����6:59:44   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */

public class AnnotationTransfomerImpl implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		//�������뱻ʵ�֣�ʵ�ֺ���ܽ����������������
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();
		
		annotation.setRetryAnalyzer(RetryAnalyzerImpl.class);
		
	}

}
