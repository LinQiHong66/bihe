package com.inesv.digiccy.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 时间工具类
 * 
 * @author lumia
 * 
 */
public class TimeUtil {
	/**
	 * 获取当前时间多少分钟之前时间
	 * 
	 * @param limitTime
	 *            分钟数
	 * @return
	 */
	public static String getStartTime(int limitTime) {
		long currentTime = System.currentTimeMillis();
		long startTime = currentTime - limitTime * 60000;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(startTime);
		return time;
	}

	public static void main(String[] args) {
		System.out.println(getStartTime(10));
	}

}
