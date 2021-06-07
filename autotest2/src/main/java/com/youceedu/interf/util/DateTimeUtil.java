package com.youceedu.interf.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @Title:  DateTimeUtil.java   
 * @Package com.youceedu.test   
 * @Description:    ��ȡʱ�䷽��  
 * @author: maxiaokui     
 * @date:   2021��5��5�� ����3:56:11   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */

public class DateTimeUtil {
	/**
	 * 
	 * @Title: getTimeTmp1   
	 * @Description: ��ȡ��ǰ��ʱ���
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public static long getTimeTmp1(){
		Date date = new Date();
		return date.getTime();
	}
	/**
	 * 
	 * @Title: getTime   
	 * @Description: ��ȡ��ǰ��ʱ��
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
    public static String getTime(){
    	return DateFormat.getInstance().format(new Date());	
    }
    /**
     * 
     * @Title: getDate   
     * @Description: ��ȡ��ǰ����
     * @param: @return      
     * @return: String      
     * @throws
     */
    public static String getDate(){
    	return DateFormat.getDateInstance().format(new Date());
    }
    /**
     * 
     * @Title: getDateTime   
     * @Description: ��ȡ��ǰ������ʱ��
     * @param: @return      
     * @return: String      
     * @throws
     */
    public static String getDateTime(){
    	return DateFormat.getDateTimeInstance().format(new Date());
    }
    /**
     * 
     * @Title: getPatternDateTime   
     * @Description: ��ȡ��ǰ�Ķ�������ʱ���ʽ
     * @param: @param pattern
     * @param: @return      
     * @return: String      
     * @throws
     */
    public static String  getPatternDateTime(String pattern){
    	return new SimpleDateFormat(pattern).format(new Date());
    }

	public static void main(String[] args) {
		

	}

}
