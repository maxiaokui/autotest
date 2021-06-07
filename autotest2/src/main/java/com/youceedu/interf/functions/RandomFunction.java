package com.youceedu.interf.functions;

import com.youceedu.interf.util.TestRandomUtil;

/**
 * 
 * @Title:  RandomFunction.java   
 * @Package com.youceedu.interf.functions   
 * @Description:    生成一个随机数
 * @author: maxiaokui     
 * @date:   2021年5月15日 上午11:01:35   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */

public class RandomFunction implements Function {

	

	@Override
	public String getFunc() {

		return "Random";
	}

	@Override
	public String execparm(String[] args) {
		
		String result = null;
		if(args[0].equals("1")){
			int mix = Integer.valueOf(args[1]);
			int max = Integer.valueOf(args[2]);
			
			result = String.valueOf(TestRandomUtil.getRandom(mix, max));
		}else if(args[0].equals("2")){
			result = String.valueOf(TestRandomUtil.getRandomBoolean());
		}else if(args[0].equals("3")){
			float mix = Float.valueOf(args[1]);
			float max = Float.valueOf(args[2]);
			result = String.valueOf(TestRandomUtil.getRandomFloat(mix, max));
		}else if(args[0].equals("4")){
			double mix = Double.valueOf(args[1]);
			double max = Double.valueOf(args[2]);
			result = String.valueOf(TestRandomUtil.getRandomDouble(mix, max));
		}else if(args[0].equals("5")){
			result = TestRandomUtil.getString(Integer.valueOf(args[1]));
		}
		return result;
	}

}
