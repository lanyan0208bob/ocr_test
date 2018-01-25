package com.daxia.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.daxia.dto.BotanyDTO;
import com.daxia.dto.FoodDTO;
import com.daxia.dto.Respon;
import com.daxia.entity.OcrAccount;

public interface OcrService {

	
	/**
	 * 图片保存
	* @Title: savePicture 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param file    设定文件 
	* @return String    返回类型  path
	* @throws 
	* @author 王继波
	* @date 2018年1月19日 下午2:44:00
	 */
	String savePicture(MultipartFile file) throws IllegalStateException, IOException;
	
	Respon<List<BotanyDTO>> getBotanyDTOList(MultipartFile file,int num);
	Respon<List<FoodDTO>> getFoodList(MultipartFile file,int num);
	
	/**
	 * 
	* @Title: getAuth 
	* @Description: 获取API访问token  该token有一定的有效期，需要自行管理，当失效时需重新获取.
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author 王继波
	* @date 2018年1月19日 下午3:30:21
	 */
	 String getAuth();
		/**
		 * 从百度获取token
		 * @Title: getTokenFromBaidu
		 * @param record
		 * @return
		 * @throws Exception  
		 * @createDate 2017年10月26日;下午2:55:26
		 * @author luy
		 */
	Map<String, Object> getTokenFromBaidu();
	public OcrAccount getOcrAccount()  ;
}
