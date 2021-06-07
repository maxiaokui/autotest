package com.youceedu.interf.functions;

import java.util.UUID;

/**
 * 
 * @Title:  UUIDFunction.java   
 * @Package com.youceedu.interf.functions   
 * @Description:    ����һ��uuid�ַ���  
 * @author: maxiaokui     
 * @date:   2021��5��15�� ����11:03:01   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */

public class UUIDFunction implements Function {

	@Override
	public String getFunc() {
		
		return "UUID";
	}

	@Override
	public String execparm(String[] args) {
		
		String result = null;
		
		result = UUID.randomUUID().toString().replace("-", "");
		
		return result;
	}

}
