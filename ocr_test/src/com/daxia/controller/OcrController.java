package com.daxia.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.daxia.dto.BotanyDTO;
import com.daxia.dto.FoodDTO;
import com.daxia.dto.Respon;
import com.daxia.service.OcrService;

/**
 * 图片识别
* @ClassName: OcrController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 王继波 
* @date 2018年1月19日 下午2:35:56 
*
 */

@Controller
@RequestMapping("/ocr")
public class OcrController {
	private Logger log=Logger.getLogger(OcrController.class);
@Autowired
private  OcrService ocrService;
	
	
	
//	@RequestMapping("/botany")
//	public Respon<BotanyDTO> botanyOcr(@RequestParam(value = "file", required = false) MultipartFile file){
//		
//		return null;
//		
//	}

	@RequestMapping("/botany")
	@ResponseBody
	public Respon<List<BotanyDTO>> botanyOcr(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		log.info("植物识别-----");
		return ocrService.getBotanyDTOList(file,0);
		
	}
	@RequestMapping("/animal")
	@ResponseBody
	public Respon<List<BotanyDTO>> animalOcr(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		log.info("动物识别-----");
		return ocrService.getBotanyDTOList(file,1);
		
	}
	@RequestMapping("/car")
	@ResponseBody
	public Respon<List<BotanyDTO>> carOcr(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		log.info("车型识别-----");
		return ocrService.getBotanyDTOList(file,2);
		
	}
	@RequestMapping("/food")
	@ResponseBody
	public Respon<List<FoodDTO>> foodOcr(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		log.info("食材识别-----");
		return ocrService.getFoodList(file,0);
		
	}
	@RequestMapping("/logo")
	@ResponseBody
	public Respon<List<FoodDTO>> logoOcr(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		log.info("商标识别-----");
		return ocrService.getFoodList(file,1);
		
	}
	
	
//	@RequestMapping("/botany")
//	@ResponseBody
//	public Respon<List<BotanyDTO>> botanyOcr(String base64){
//		System.err.println(base64);
//		return ocrService.getBotanyDTOList(null);
		
//	}
	
	
}
