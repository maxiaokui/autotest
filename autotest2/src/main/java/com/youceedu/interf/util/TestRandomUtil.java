package com.youceedu.interf.util;

import java.util.Random;

public class TestRandomUtil {
	private static Random r = new Random();
	
	private static String str = "avasdewrdfdsafsadjjgjghjghjg";
	
	public static String getString(int length){
		StringBuffer sb = new StringBuffer();
		for(int a=0;a<length;a++){
			
			int b = r.nextInt(str.length());
			sb.append(str.charAt(b));
		}
		
		return sb.toString();
	}
	/**
	 * 
	 * @Title: getRandom   
	 * @Description: ����һ����С��ֵ��������һ��ֵ��������ֵΪmix+max
	 * @param: @param mix
	 * @param: @param max
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public static int getRandom(int mix,int max){
		
		//�������һ��0-max֮�����
		
		int a = r.nextInt(max)+mix;
		return a;
	}
	/**
	 * 
	 * @Title: getRandomLong   
	 * @Description: �õ�long��Χ�ڵ������
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public static long getRandomLong(){
		
		return r.nextLong();
	}
	/**
	 * 
	 * @Title: getRandomBoolean   
	 * @Description: ���һ��true ����false
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean getRandomBoolean(){
		return r.nextBoolean();
	}
	/**
     * 
     * @Title: main   
     * @Description: �õ�mix��mix+max��Χ�ڵ�ֵ
     * @param: @param args      
     * @return: void      
     * @throws
     */
	public static float getRandomFloat(float mix,float max){
		return r.nextFloat()*max+mix;
	}
	/**
	 * 
	 * @Title: getRandomDouble   
	 * @Description: �õ�mix��mix+max��Χ�ڵ�ֵ
	 * @param: @param mix
	 * @param: @param max
	 * @param: @return      
	 * @return: double      
	 * @throws
	 */
    public static double getRandomDouble(double mix,double max){
    	return r.nextDouble()*max+mix;
    }
    /**
     * 
     * @Title: getRandomString   
     * @Description: ����һ���ַ��� �������һ���͵�ǰ�ַ�������һ����ֵ
     * @param: @param string
     * @param: @return      
     * @return: String      
     * @throws
     */
    public static String getRandomString(String string){
    	StringBuffer str = new StringBuffer();
    	for(int i=0;i<string.length();i++){
    		int a = r.nextInt(string.length());
    		str.append(string.charAt(a));
    	}
    	return str.toString();
    }
	public static void main(String[] args) {
		System.out.println(getRandomLong());
		System.out.println(getRandomBoolean());
		System.out.println(getRandomFloat(0.0f,2.0f));
		System.out.println(getRandomDouble(20.0d,21.0d));
		System.out.println(getRandomString("abcdqwe123"));

	}

}
