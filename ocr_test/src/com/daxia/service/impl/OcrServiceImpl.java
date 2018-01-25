package com.daxia.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daxia.common.Common;
import com.daxia.common.Dictionary;
import com.daxia.dto.BotanyDTO;
import com.daxia.dto.FoodDTO;
import com.daxia.dto.Respon;
import com.daxia.entity.OcrAccount;
import com.daxia.mapper.ConfigMapper;
import com.daxia.mapper.OcrAccountMapper;
import com.daxia.service.OcrService;
import com.daxia.util.TimestampTool;
import com.daxia.util.ocr.Base64Util;
import com.daxia.util.ocr.FileUtil;
import com.daxia.util.ocr.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Service
public class OcrServiceImpl implements OcrService{
	private Logger log=Logger.getLogger(OcrServiceImpl.class);

	@Autowired
	private ConfigMapper configMapper;
	@Autowired
	private OcrAccountMapper ocrAccountMapper;
	@Override
	public String savePicture(MultipartFile file) throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		if(file!=null&&!file.isEmpty()){
			String picpath=configMapper.selectByCode(Dictionary.PIC_PATH)+File.separator;
			String picName=file.getOriginalFilename();
			picName=System.currentTimeMillis()+picName.substring(picName.length()-4, picName.length());
			File targetFile = new File(picpath, picName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);//保存文件
			return picpath+picName;
		}else{
			return null;
		}
		
	}
	@Override
	public Respon<List<BotanyDTO>> getBotanyDTOList(MultipartFile file,int num) {
		// TODO Auto-generated method stub
		String path;
		Respon<List<BotanyDTO>> resp=new Respon<List<BotanyDTO>>();
		try {
			path = this.savePicture(file);
//			path="111111";
			if(path!=null){
				//识别
//				OcrAccount ocr=this. getOcrAccount();
				try {
					resp=	botanyDTOOCR(path, num);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resp.setCode(Common.FAIL.getCode());
					resp.setMessage(e.getMessage());
				}
			}
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			resp.setCode(Common.FAIL.getCode());
			resp.setMessage(e.getMessage());
		}
		
		return resp;
	}
	public  String getAuth() {
		
		String authHost=configMapper.selectByCode(Dictionary.TOKEN_PATH);
		String grant_type=configMapper.selectByCode(Dictionary.GRANT_TYPE);
		String client_secret=configMapper.selectByCode(Dictionary.SECRET_KEY);
		String client_id=configMapper.selectByCode(Dictionary.API_KEY);
		
        // 获取token地址
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type="+grant_type
                // 2. 官网获取的 API Key
                + "&client_id=" + client_id
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + client_secret;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

	/**
	 * 从百度获取token
	 * @Title: getTokenFromBaidu
	 * @param record
	 * @return
	 * @throws Exception  
	 * @createDate 2017年10月26日;下午2:55:26
	 * @author luy
	 */
	public   Map<String, Object> getTokenFromBaidu() {
		String authHost=configMapper.selectByCode(Dictionary.TOKEN_PATH);
//		String grant_type=configMapper.selectByCode(Dictionary.GRANT_TYPE);
		String client_secret=configMapper.selectByCode(Dictionary.SECRET_KEY);
		String client_id=configMapper.selectByCode(Dictionary.API_KEY);
	
		String data="";
		try {
			data = HttpUtil.getToken(authHost,  client_id, client_secret);
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
//			log.error("从百度获取token:异常"+e.getMessage());	
			
		}
//		String data="{\"access_token\":\"24.7a5a4a978e12eb6923637b02001ede01.2592000.1511512445.282335-10282185\",\"session_key\":\"9mzdDFQf53WSQjJHHZOkuHFPoCkC07EfkeJ9FkRWeI9G\\/L5q717BWiRGEFoDeCkQHCjQde+e28rUOiVatgw\\/W31P\\/08pNA==\",\"scope\":\"public vis-ocr_ocr brain_ocr_scope brain_ocr_general brain_ocr_general_basic brain_ocr_general_enhanced vis-ocr_business_license brain_ocr_webimage brain_all_scope brain_ocr_idcard brain_ocr_driving_license brain_ocr_vehicle_license vis-ocr_plate_number brain_solution brain_ocr_plate_number brain_ocr_accurate brain_ocr_accurate_basic brain_ocr_receipt brain_ocr_business_license wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian ApsMisTest_Test\\u6743\\u9650 vis-classify_flower\",\"refresh_token\":\"25.7c843346cdbfbf2d0e7535760b1ec43a.315360000.1824280445.282335-10282185\",\"session_secret\":\"c7195044109e29d608ebe2e74606ba5f\",\"expires_in\":2592000}";
//		log.info("从百度获取token:-----"+data);
		return new Gson().fromJson(data, new TypeToken<Map<String, Object>>() {}.getType());  
	}
	@Override
	public OcrAccount getOcrAccount() {
		
		
		OcrAccount account=	ocrAccountMapper.selectByPrimaryKey();
		
		if(account!=null&&account.getEffective_time()!=null){
			
			if(account.getEffective_time()!=null&&account.getExpires_in()!=null){
				//token 生效时间
				long time1=account.getEffective_time().getTime();
				long time2=System.currentTimeMillis();//当前时间
				long expires_in=0L;
				try {
					Double expires_time=Double.parseDouble(account.getExpires_in());
					expires_in=expires_time.longValue();
				} catch (NumberFormatException e) {
					
					
				}
				if(expires_in==0){
					//重新获取token
					
					 Map<String, Object>  tokenmap= this.getTokenFromBaidu();
					 if(tokenmap!=null&&tokenmap.get("access_token")!=null){
						//更新token
						 String token=(String) tokenmap.get("access_token");
						 Object newexpires_in=(Object) tokenmap.get("expires_in");
						 account.setAccess_token(token);
						 account.setExpires_in(String.valueOf(newexpires_in));
						 account.setEffective_time(TimestampTool.crunttime());
						 ocrAccountMapper.updateByPrimaryKey(account);
					 }
					//2592000000     -76662822=	2515337178       720000000				
				}else if(expires_in*1000-(time2-time1)<2*60*60*1000){//有效期-(当前时间-生效时间)<2个小时        30  -2  
					//重新获取token
					//更新token
					Map<String, Object>  tokenmap= this.getTokenFromBaidu();
					 if(tokenmap!=null&&tokenmap.get("access_token")!=null){
						//更新token
						 String token=(String) tokenmap.get("access_token");
						 Object newexpires_in=(Object) tokenmap.get("expires_in");
						 account.setAccess_token(token);
						 account.setExpires_in(String.valueOf(newexpires_in));
						 account.setEffective_time(TimestampTool.crunttime());
						 ocrAccountMapper.updateByPrimaryKey(account);
					 }
				}
			}
			
			
		}
		
		// TODO Auto-generated method stub
		return account;
		
	}
	
	public  Respon< List<BotanyDTO>> botanyDTOOCR(String path,int num) throws Exception{

		long beginTime =System.currentTimeMillis();
        byte[] imgData = FileUtil.readFileByBytes(path);
        String imgStr = Base64Util.encode(imgData);
        String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
      String type="";
        if(num==0){
        	type=Dictionary.BOTANY_PATH;
        }else if(num==1){
        	type=Dictionary.ANIMAL_PATH;
        }else{
        	type=Dictionary.BOTANY_PATH;
        }
       String vehicleLicensePath=configMapper.selectByCode(type);
       log.info("url:"+vehicleLicensePath);
       OcrAccount ocr=    this.getOcrAccount();
        String result = HttpUtil.post(vehicleLicensePath, ocr.getAccess_token(), params);
//        {"log_id": 832502857, "words_result_num": 10, "words_result": {"品牌型号": {"words": "解放牌CA4257P2K2T1EA"}, "发证日期": {"words": "20130720"}, "使用性质": {"words": "货运"}, "发动机号码": {"words": "51676680"}, "号牌号码": {"words": "蒙E76258"}, "所有人": {"words": "呼伦贝尔市晓明运输有限公司"}, "住址": {"words": "内蒙古自治区呼伦贝尔市鄂温克族自治旗巴彦托海镇八居G安居小区5号楼2单元4层2号"}, "注册日期": {"words": "20101207"}, "车辆识别代号": {"words": "LFWSRXNH6AAD38754"}, "车辆类型": {"words": "重型半挂牵引车"}}}
//        log.info("百度识别行驶证:-----"+result);
    	long times =System.currentTimeMillis()-beginTime;
//    	log.info("百度识别行驶证耗时："+times);
        Map<String, Object> resultMap = new Gson().fromJson(result, new TypeToken<Map<String, Object>>() {}.getType());
      
        
        Respon< List<BotanyDTO>> resp=new Respon<List<BotanyDTO>>();
        List<BotanyDTO> list=new ArrayList<BotanyDTO>();
        
        
        if(resultMap!=null){
        	if(resultMap.get("error_code")==null){//成功
        	//result:{"log_id": 343123361707834035, "result": {"score": 0.85536277294159, "name": "非植物"}}
        	//result:{"log_id": 1016645712119177339, "result": [{"score": 0.9872915148735, "name": "西瓜"}, {"score": 0.0042934538796544, "name": "哈密瓜"}, {"score": 0.0031954871956259, "name": "小西瓜"}, {"score": 0.0013184666167945, "name": "地雷瓜"}, {"score": 0.00077596440678462, "name": "西瓜叶子"}]}
//        		if("[".indexOf(resultMap.get("result").toString())>=0){
//        			System.out.println(2313);
//        		}
        		if((resultMap.get("result").toString()).indexOf("[")>=0){
        			list= new Gson().fromJson(resultMap.get("result").toString(), new TypeToken<List<BotanyDTO>>() {}.getType());
        		}else{
        			BotanyDTO	dto=new Gson().fromJson(resultMap.get("result").toString(), new TypeToken<BotanyDTO>() {}.getType());
        			list.add(dto);
        		}
        		
        		resp.setData(list);
        		resp.setCode(Common.SUCCESS.getCode());
        		resp.setMessage(Common.SUCCESS.getMsg());
        	}else{
      		resp.setCode(resultMap.get("error_code").toString());
      		resp.setMessage((String)resultMap.get("error_msg"));
      		
        	}
        }else{
        	resp.setCode(Common.NO_RESPONSE.getCode());
      		resp.setMessage(Common.NO_RESPONSE.getCode());
        	 
 	 }
    	
        return resp;
   

		
		
		
		
	}
	public  Respon< List<FoodDTO>> foodDTOOCR(String path) throws Exception{

		long beginTime =System.currentTimeMillis();
        byte[] imgData = FileUtil.readFileByBytes(path);
        String imgStr = Base64Util.encode(imgData);
        String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
    
       String vehicleLicensePath=configMapper.selectByCode(Dictionary.FOOD_PATH);
       log.info("url:"+vehicleLicensePath);
       OcrAccount ocr=    this.getOcrAccount();
        String result = HttpUtil.post(vehicleLicensePath, ocr.getAccess_token(), params);
//        {"log_id": 832502857, "words_result_num": 10, "words_result": {"品牌型号": {"words": "解放牌CA4257P2K2T1EA"}, "发证日期": {"words": "20130720"}, "使用性质": {"words": "货运"}, "发动机号码": {"words": "51676680"}, "号牌号码": {"words": "蒙E76258"}, "所有人": {"words": "呼伦贝尔市晓明运输有限公司"}, "住址": {"words": "内蒙古自治区呼伦贝尔市鄂温克族自治旗巴彦托海镇八居G安居小区5号楼2单元4层2号"}, "注册日期": {"words": "20101207"}, "车辆识别代号": {"words": "LFWSRXNH6AAD38754"}, "车辆类型": {"words": "重型半挂牵引车"}}}
//        log.info("百度识别行驶证:-----"+result);
    	long times =System.currentTimeMillis()-beginTime;
//    	log.info("百度识别行驶证耗时："+times);
        Map<String, Object> resultMap = new Gson().fromJson(result, new TypeToken<Map<String, Object>>() {}.getType());
      
        
        Respon< List<FoodDTO>> resp=new Respon<List<FoodDTO>>();
        List<FoodDTO> list=new ArrayList<FoodDTO>();
        
        
        if(resultMap!=null){
        	if(resultMap.get("error_code")==null){//成功
        	//result:{"log_id": 343123361707834035, "result": {"score": 0.85536277294159, "name": "非植物"}}
        	//result:{"log_id": 1016645712119177339, "result": [{"score": 0.9872915148735, "name": "西瓜"}, {"score": 0.0042934538796544, "name": "哈密瓜"}, {"score": 0.0031954871956259, "name": "小西瓜"}, {"score": 0.0013184666167945, "name": "地雷瓜"}, {"score": 0.00077596440678462, "name": "西瓜叶子"}]}
//        		if("[".indexOf(resultMap.get("result").toString())>=0){
//        			System.out.println(2313);
//        		}
        		if((resultMap.get("result").toString()).indexOf("[")>=0){
        			list= new Gson().fromJson(resultMap.get("result").toString(), new TypeToken<List<FoodDTO>>() {}.getType());
        		}else{
        			FoodDTO	dto=new Gson().fromJson(resultMap.get("result").toString(), new TypeToken<FoodDTO>() {}.getType());
        			list.add(dto);
        		}
        		
        		resp.setData(list);
        		resp.setCode(Common.SUCCESS.getCode());
        		resp.setMessage(Common.SUCCESS.getMsg());
        	}else{
      		resp.setCode(resultMap.get("error_code").toString());
      		resp.setMessage((String)resultMap.get("error_msg"));
      		
        	}
        }else{
        	resp.setCode(Common.NO_RESPONSE.getCode());
      		resp.setMessage(Common.NO_RESPONSE.getCode());
        	 
 	 }
    	
        return resp;
   

		
		
		
		
	}
	@Override
	public Respon<List<FoodDTO>> getFoodList(MultipartFile file) {

		// TODO Auto-generated method stub
		String path;
		Respon<List<FoodDTO>> resp=new Respon<List<FoodDTO>>();
		try {
			path = this.savePicture(file);
//			path="111111";
			if(path!=null){
				//识别
//				OcrAccount ocr=this. getOcrAccount();
				try {
					resp=	foodDTOOCR(path);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					resp.setCode(Common.FAIL.getCode());
					resp.setMessage(e.getMessage());
				}
			}
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			resp.setCode(Common.FAIL.getCode());
			resp.setMessage(e.getMessage());
		}
		
		return resp;
	
	}

	
}
