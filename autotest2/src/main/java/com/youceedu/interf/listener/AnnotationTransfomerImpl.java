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
 * @Description: 用例失败执行重试，调用的RetryAnalyzerImll类  
 * @author: maxiaokui     
 * @date:   2021年4月26日 下午6:59:44   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */

public class AnnotationTransfomerImpl implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		//这个类必须被实现，实现后才能进行下面的用例重试
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();
		
		annotation.setRetryAnalyzer(RetryAnalyzerImpl.class);
		
	}

}
