package com.daxia.dto;

/**
 * 植物
* @ClassName: BotanyDTO 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 王继波 
* @date 2018年1月19日 下午3:26:30 
*
 */
public class FoodDTO {

	
/*
 * 名称	
 */
private String name;
/*
 * 卡路里	
 */
private String calorie;

/**
 * 相似度
 */
private String probability;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}



public FoodDTO(String name, String calorie, String probability) {
	super();
	this.name = name;
	this.calorie = calorie;
	this.probability = probability;
}
public String getCalorie() {
	return calorie;
}
public void setCalorie(String calorie) {
	this.calorie = calorie;
}
public String getProbability() {
	return probability;
}
public void setProbability(String probability) {
	this.probability = probability;
}
public FoodDTO() {
	super();
}


}
