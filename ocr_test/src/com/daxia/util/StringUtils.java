package com.daxia.util;

import java.util.Random;

/**
 * ClassName:StringUtils
 * Function: String工具类
 *
 * @author   shenwei
 * @version  
 * @since    Ver 1.1
 * @Date	 Feb 11, 2015		4:04:06 PM
 */
public class StringUtils {
	
    
    public static String escapeSQLTags(String input)
	{
		return input;
	}
    
    public static String substring(String str, int max) {
		String tmp = empty2Null(str);
		if (tmp == null || tmp.length() <= max)
			return str;
		else
			return str.substring(0, max);
	}

	public static String randomString(int length) {
		String str = null;
		if (length <= 0)
			return null;
		String charset = "abcdefghijklmnopqrstuvwxyz1234567890!#$@%&*-=+|/ABCDEFGHIJKLMNOQPRSTUVWXYZ";
		Random r = new Random();
		Random r1 = new Random();
		StringBuffer bf = new StringBuffer();
		int ba = Math.abs(r1.nextInt() % length) + 1;
		for (int i = 0; i < ba; i++) {
			int radix = Math.abs(r.nextInt(ba) % charset.length());
			char c = charset.charAt(radix);
			bf.append(c);
		}

		str = bf.toString();
		return str;
	}

	public static String null2Empty(String s) {
		if (s == null)
			s = "";
		return s;
	}

	public static String empty2Null(String s) {
		if (s != null && s.trim().length() == 0)
			s = null;
		return s;
	}

	public static boolean isNumeric(String str) {
		if (str == null)
			return false;
		int sz = str.length();
		for (int i = 0; i < sz; i++)
			if (!Character.isDigit(str.charAt(i)))
				return false;

		return true;
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0)
			return true;
		for (int i = 0; i < strLen; i++)
			if (!Character.isWhitespace(str.charAt(i)))
				return false;

		return true;
	}

	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	public static String toHtml(String s) {
		String html = s;
		if (s == null || s.length() == 0)
			return "&nbsp";
		char symbol[] = { '&', '<', '>', '"', '\r' };
		String obj[] = { "&amp;", "&lt;", "&gt;", "&quot;", "<br>" };
		for (int i = 0; i < symbol.length; i++)
			html = html.replaceAll(String.valueOf(symbol[i]), obj[i]);

		return html;
	}

	public static boolean notEmpty(Object o) {
		return o != null && !"".equals(o.toString())
				&& !"null".equalsIgnoreCase(o.toString())
				&& !"undefined".equalsIgnoreCase(o.toString());
	}

	/**
	 * 转换成sql的查询in条件
	 * 
	 * @param s
	 *            ex. "1,2,3,4,5,6,"
	 * @return '1','2','3','4','5','6'
	 */
	public static String toQueryStr(String s) {
		if (s.indexOf(",") != -1) {
			String[] tmp = s.split(",");
			StringBuffer str = new StringBuffer();
			for (int i = 0; i < tmp.length; i++) {
				str.append("'").append(tmp[i].trim()).append("',");
			}
			return str.toString().substring(0, str.lastIndexOf(","));
		}
		return "'" + s + "'";
	}
	public static String getLineSeparatoByOs() {
		return System.getProperty("line.separator");
	}
}

