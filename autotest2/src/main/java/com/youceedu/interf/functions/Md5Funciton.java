package com.youceedu.interf.functions;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @Title:  Md5Funciton.java   
 * @Package com.youceedu.interf.functions   
 * @Description:    生成一个md5 
 * @author: maxiaokui     
 * @date:   2021年5月15日 上午11:04:14   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
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
