package com.youceedu.interf.util;

public class StringUtil {
	
	public static String replace(String Req_data,String matcher,String value){
		//�鿴Ҫ������ַ����ĳ���
		int matcherlenth = matcher.length();
		//�鿴���ڵ�λ��
		int strint = Req_data.indexOf(matcher);
		//�������ַ�������Ϣ
		String strleft = Req_data.substring(0, strint);
		//����Ҳ��ַ�����Ϣ
		int right = strint + matcherlenth;
		String strright = Req_data.substring(right);
		//������ֵ����ֵ���Ҳ��ֵƴ�������������أ�
		return strleft+value+strright;
		
		
	}

	public static void main(String[] args) {
		
		

	}

}
