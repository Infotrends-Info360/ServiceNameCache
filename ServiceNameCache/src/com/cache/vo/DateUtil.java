package com.cache.vo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author Lin.Tsai
 */
public class DateUtil {
	
	private final static String YYYYMM = "yyyyMM";
	private final static String YYYY_MM_DD = "yyyy-MM-dd";
	private final static String HH_MM_SS = "HH:mm:ss";
	private final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 根据日历格式获取DateFormat
	 */
	private static DateFormat getDateFormat(String formate){
		return new SimpleDateFormat(formate);
	}
	/**
	 * 获取两个时间之间的时间差，时间格式为：yyyy-MM-dd HH:mm:ss
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 单位：毫秒 异常返回默认为-1
	 */
	public static long getTimeGap(String startTime, String endTime) {
		DateFormat sdf = DateUtil.getDateFormat(YYYY_MM_DD_HH_MM_SS);
		long mm = -1;
		try {
			Date s = sdf.parse(startTime);
			Date e = sdf.parse(endTime);
			mm = e.getTime() - s.getTime();
			if(mm < 0)
				mm = -1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mm;
	}
	
	/**
	 * 重新格式化日期
	 * @param time
	 * @return yyyy-MM-dd HH:mm:ss
	 * @throws ParseException
	 * @比如:
	 * 2012-1-1 12:2:1格式化为2012-01-01 12:02:01
	 */
	public static String formatDate(String time) {
		DateFormat sdf = DateUtil.getDateFormat(YYYY_MM_DD_HH_MM_SS);
		String dateResult = null;
		Date date;
		try {
			date = sdf.parse(time);
			dateResult = sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateResult;
	}
	
	public static String getCurrentTime() {
		DateFormat sdf = DateUtil.getDateFormat(YYYY_MM_DD_HH_MM_SS);
		return sdf.format(System.currentTimeMillis());
	}
	
	public static String getYM() {
		DateFormat sdf = DateUtil.getDateFormat(YYYYMM);
		return sdf.format(System.currentTimeMillis());
	}
	
	public static String getYMD() {
		DateFormat sdf = DateUtil.getDateFormat(YYYY_MM_DD);
		return sdf.format(System.currentTimeMillis());
	}
	
	public static String getHMS() {
		DateFormat sdf = DateUtil.getDateFormat(HH_MM_SS);
		return sdf.format(System.currentTimeMillis());
	}
	
	public static String getYesterday() {
		DateFormat sdf = DateUtil.getDateFormat(YYYY_MM_DD_HH_MM_SS);
		Calendar calender = Calendar.getInstance();
		calender.set(Calendar.DATE, calender.get(Calendar.DATE) - 1);
		return sdf.format(calender.getTime());
	}
	
	public static synchronized String getTomorrow(String date) throws ParseException {
		DateFormat sdf = DateUtil.getDateFormat(YYYY_MM_DD);
		sdf.parse(date);
		Calendar calender = sdf.getCalendar();
		calender.set(Calendar.DATE, calender.get(Calendar.DATE) + 1);
		return sdf.format(calender.getTime());
	}
}
