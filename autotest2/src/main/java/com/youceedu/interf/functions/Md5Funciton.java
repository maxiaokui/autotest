package com.youceedu.interf.functions;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @Title:  Md5Funciton.java   
 * @Package com.youceedu.interf.functions   
 * @Description:    ����һ��md5 
 * @author: maxiaokui     
 * @date:   2021��5��15�� ����11:04:14   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */

public class Md5Funciton implements Function{

	@Override
	public String getFunc() {

		return "Md5";
	}

	@Override
	public String execparm(String[] args) {
		
		String result = null;
		
		if(args.length ==1){
			result = DigestUtils.md5Hex(args[0]);
		}

		return result;
	}

}
