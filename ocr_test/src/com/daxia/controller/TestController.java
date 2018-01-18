package com.daxia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
* @ClassName: TestController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 王继波 
* @date 2018年1月18日 下午3:19:36 
*
 */
@Controller
public class TestController {

	/**
	 * 
	* @Title: test 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author 王继波
	* @date 2018年1月18日 下午3:20:42
	 */
	@RequestMapping("/test")
	@ResponseBody
	public String  test(){
		return "1111111111";
	}
}
