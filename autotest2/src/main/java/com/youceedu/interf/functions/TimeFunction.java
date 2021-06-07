package com.youceedu.interf.functions;

import com.youceedu.interf.util.DateTimeUtil;

/**
 * 
 * @Title:  TimeFunction.java   
 * @Package com.youceedu.interf.functions   
 * @Description:    ����time��   
 * @author: maxiaokui     
 * @date:   2021��5��15�� ����11:05:24   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */

public class TimeFunction implements Function {

	@Override
	public String getFunc() {

		return "Time";
	}

	@Override
	public String execparm(String[] args) {
		String result = null;
		if(args.length==0){
			//ʱ���
			result = String.valueOf(DateTimeUtil.getTimeTmp1()); 
		}else if(args.length==1 && args[0].equals("YMDHMS")){
			//������ʱ����
			result = DateTimeUtil.getDateTime();
		}else if(args.length==1 && args[0].equals("YMD")){
			//������
			result = DateTimeUtil.getDate();
		}else if(args.length==1 && args[0].equals("HMS")){
			//ʱ����
			result = DateTimeUtil.getTime();
		}else {
			//���Ƹ�ʽ
			result = DateTimeUtil.getPatternDateTime(args[0]);
		}
		return result;

		
	}

}
