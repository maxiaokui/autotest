package com.youceedu.interf.functions;

import java.util.UUID;

/**
 * 
 * @Title:  UUIDFunction.java   
 * @Package com.youceedu.interf.functions   
 * @Description:    生成一个uuid字符串  
 * @author: maxiaokui     
 * @date:   2021年5月15日 上午11:03:01   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
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
