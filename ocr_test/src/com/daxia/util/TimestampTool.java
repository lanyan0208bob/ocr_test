package com.daxia.util;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * ClassName:TimestampTool
 * Function: 时间工具类
 * Reason:	 TODO ADD REASON
 *
 * @author   shenwei
 * @version  
 * @since    Ver 1.1
 * @Date	 2015	Feb 10, 2015		3:31:42 PM
 */
public class TimestampTool {
	
	// 当前时间（2015-02-10 15:35:09.093）
	public static Timestamp crunttime() {
		
		return new Timestamp(System.currentTimeMillis());
	}

	//获取当前时间的字符串（2015-02-10）
	public static String getCurrentDate() {
		
		Timestamp d=crunttime();
		return d.toString().substring(0,10);
	}
	
	//获取当前时间的字符串（2006-07-07 22:10:10）
	public static String getCurrentDateTime() {

		Timestamp d=crunttime();
		return d.toString().substring(0,19);
	}
	
	//	获取给定时间的字符串,只有日期（2006-07-07）
	public static String getStrDate(Timestamp t) {
		return t.toString().substring(0,10);
	}
	
	//获取给定时间的字符串,只有年月日（20150210）
	public static String getStringDate() {
		Timestamp t=crunttime();
		return t.toString().substring(0,10).replaceAll("-","");
	}
	
	//获取给定时间的字符串（2006-07-07 22:10:10）
	public static String getStrDateTime(Timestamp t) {
		return t.toString().substring(0,19);
	}

	// 返回日期 格式:2006-07-05
	public static Timestamp date(String str) {
		Timestamp tp = null;
		if (str.length() <= 10) {
			String[] string = str.trim().split("-");
			int one = Integer.parseInt(string[0]) - 1900;
			int two = Integer.parseInt(string[1]) - 1;
			int three = Integer.parseInt(string[2]);
			tp = new Timestamp(one, two, three, 0, 0, 0, 0);
		}
		return tp;
	}

	// 返回时间和日期  格式:2006-07-05 22:10:10
	public static Timestamp datetime(String str) {
		Timestamp tp = null;
		if (str.length() > 10) {
			String[] string = str.trim().split(" ");
			String[] date = string[0].split("-");
			String[] time = string[1].split(":");
			int date1 = Integer.parseInt(date[0]) - 1900;
			int date2 = Integer.parseInt(date[1]) - 1;
			int date3 = Integer.parseInt(date[2]);
			int time1 = Integer.parseInt(time[0]);
			int time2 = Integer.parseInt(time[1]);
			if(StringUtils.notEmpty(time)&&time.length>2){
				int time3 = Integer.parseInt(time[2]);
				tp = new Timestamp(date1, date2, date3, time1, time2, time3, 0);
			}else{
				tp = new Timestamp(date1, date2, date3, time1, time2, 0, 0);
			}
		}
		return tp;
	}

	// 返回日期和时间(没有秒)  格式:2006-07-05 22:10
	public static Timestamp datetimeHm(String str) {
		Timestamp tp = null;
		if (str.length() > 10) {
			String[] string = str.trim().split(" ");
			String[] date = string[0].split("-");
			String[] time = string[1].split(":");
			int date1 = Integer.parseInt(date[0]) - 1900;
			int date2 = Integer.parseInt(date[1]) - 1;
			int date3 = Integer.parseInt(date[2]);
			int time1 = Integer.parseInt(time[0]);
			int time2 = Integer.parseInt(time[1]);
			tp = new Timestamp(date1, date2, date3, time1, time2, 0, 0);
		}
		return tp;
	}
	
	//	获取当前日期之前的日期字符串 如 2007-04-15  前5月 就是 2006-11-15 
	public static String getPreviousMonth(int month){
		Calendar   cal1   =   Calendar.getInstance();   
        cal1.setTime(new Date());   
        cal1.add(Calendar.MONTH,-month);               
        SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd");   
        return formatter.format(cal1.getTime());
    }
	
	//获取当前日期之后的日期字符串 如 2007-04-15  后一天 就是 2007-04-16 
	public static String getNextDay(int day){
		Calendar   cal1   =   Calendar.getInstance();   
        cal1.setTime(new Date());   
        cal1.add(Calendar.DAY_OF_MONTH,day);               
        SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd");   
        return formatter.format(cal1.getTime());
    }
	
	//获取指定日期之后的日期字符串 如 2007-04-15  后一天 就是 2007-04-16 
	public static String getNextDay(String strDate,int day){
		Calendar   cal1   =   Calendar.getInstance();   
		String[] string = strDate.trim().split("-");
		int one = Integer.parseInt(string[0]) - 1900;
		int two = Integer.parseInt(string[1]) - 1;
		int three = Integer.parseInt(string[2]);
        cal1.setTime(new Date(one,two,three));   
        cal1.add(Calendar.DAY_OF_MONTH,day);               
        SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd");   
        return formatter.format(cal1.getTime());
    }
	
	//取得当前时间下N分钟时间
	public static String getNextMinute(int minute){
		Calendar   cal1   =   Calendar.getInstance();   
        cal1.setTime(new Date());   
        cal1.add(Calendar.MINUTE,minute);               
        SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        return formatter.format(cal1.getTime());
    }
	
	//获取当前时间字符串 格式：20101102223145
	public static String yyyyMMddHHmmss(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		String now = sdf.format(cal.getTime());
		return now;
	}
	
	//获取当前时间字符串 格式：2010-11-02 22:31
	public static String getDateStr(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	//获取当前时间字符串 格式：2010-11-02 22:31:25
	public static String getDateString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	//取得当前时间下N小时时间
	public static Timestamp getTimestampByHour(int hour){
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, hour);
		
		return new Timestamp(cal.getTimeInMillis());
	}
	
	//取得当前时间后N天时间
	public static Timestamp getTimestampByDay(int day){
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, day);
		
		return new Timestamp(cal.getTimeInMillis());
	}
	
	//取得当前时间后N天时间
	public static Date getDateByDay(int day){
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		
		Date myDate = cal.getTime();
		return myDate;
	}
	
	//将日期字符串转换成Date（yyyy-MM-dd HH:mm:ss）
	public static Date getDateByStr(String dateStr){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = dateStr;
			Date date = sdf.parse(strDate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//将日期转换成字符串
	public static String getStrByDate(Date date){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date);
		
		return dateStr;
	}
	public static int daysBetween(Date smdate,Date bdate) throws ParseException  
    {  
     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM- dd");
     smdate=sdf.parse(sdf.format(smdate));
     bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();  
        cal.setTime(smdate);  
        long time1 = cal.getTimeInMillis ();               
        cal.setTime(bdate);  
        long time2 = cal.getTimeInMillis ();       
        long between_days=(time2-time1)/ (1000*3600*24);

       return Integer.parseInt(String.valueOf (between_days));         
    }
	//获得指定时间后一年
	public static Date getNextYear(Date strDate){
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 String date = sdf.format  (strDate);
		String nowYearString = date.split("-")[0];
		String insureMonthString = date.split("-")[1];
		String insureDayString =  date.split("-")[2];
		String newInsureDateString = (Integer.valueOf(nowYearString)+1) +"-"+insureMonthString+"-"+insureDayString;
		Date newInsureDate = sdf.parse(newInsureDateString);
		 return newInsureDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//yyyyMMdd   ---->>    yyyy-MM-dd
		public static String getStr2Str(String date) throws ParseException{
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date1=sdf.parse(date);
//			String dateStr = sdf.format(date);
			return getStrByDate(date1);
		}
	public static void main(String[] args) throws ParseException{
		System.out.println(getStr2Str(null));
	}
}
