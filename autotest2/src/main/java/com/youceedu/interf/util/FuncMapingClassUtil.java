package com.youceedu.interf.util;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.youceedu.interf.functions.Function;
/**
 * 
 * @Title:  FuncMapingClassUtil.java   
 * @Package com.youceedu.autotest2.http   
 * @Description:    �õ����������   
 * @author: maxiaokui     
 * @date:   2021��5��15�� ����5:43:51   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */
public class FuncMapingClassUtil {
	
	private static Map<String,Class<? extends Function>> funcmap = new HashMap<String,Class<? extends Function >>();
	
	static{
		try{
			//1.�õ��������
		Class<?> function = Class.forName("com.youceedu.interf.functions.Function");
		//2.�õ��������
		String pk = function.getPackage().getName();
		
		String pkpath = pk.replace(".", "/");
		//System.out.println(pk+"\t"+pkpath);
		//�ȵõ�������������Ȼ��õ�����pkpath·������Դ���ٵõ�����·��
		String classpath = function.getClassLoader().getResource(pkpath).getPath();
		//System.out.println("classpath:   "+classpath);
		
		//
		File file = new File(classpath);
		File[] files = file.listFiles();
		for(File f:files){
			String filename = f.getName();
			//�ж���׺���ǲ���class
			if(filename.endsWith(".class")){
 
				String className =pk+"."+filename.substring(0,filename.length()-6);
				Class<?> c = Class.forName(className);
				//c����function������� ���ǲ���
				if(!function.equals(c)&&function.isAssignableFrom(c)){
					Function funobject = (Function)c.newInstance();
					String funname = funobject.getFunc();
					funcmap.put(funname, funobject.getClass());
				}
			}
		}
		//System.out.println(funcmap.get("Md5"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static boolean isFunc(String funname){
		//У���㴫��ķ��������ڲ���map��
		return funcmap.containsKey(funname);
	}
	
	public static String getvalue(String funname,String[] args) throws Exception{
		return funcmap.get(funname).newInstance().execparm(args);
	}

	public static void main(String[] args) throws Exception {
		
		

	}

}
