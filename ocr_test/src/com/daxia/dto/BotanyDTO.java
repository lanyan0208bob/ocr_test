package com.daxia.dto;

/**
 * 植物
* @ClassName: BotanyDTO 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 王继波 
* @date 2018年1月19日 下午3:26:30 
*
 */
public class BotanyDTO {

	
/*
 * 名称	
 */
private String name;
/**
 * 相似度
 */
private double score;

private String year;

public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public double getScore() {
	return score;
}
public void setScore(double score) {
	this.score = score;
}
public BotanyDTO(String name, double score) {
	super();
	this.name = name;
	this.score = score;
}
public BotanyDTO() {
	super();
}


}
