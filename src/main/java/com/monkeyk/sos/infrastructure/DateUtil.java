package com.monkeyk.sos.infrastructure;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/***
 * 日期工具类
 * 
 * 
 */
public class DateUtil {

	/**
	 * 一天中的天数
	 */
	public static long			millionSecondsOfDay			= 86400000;
	/**
	 * 一天中的小时
	 */
	public static long			millionSecondsOfHour		= 3600000;

	/** 日期格式 **/
	public static final String	FORMATTER_OF_DATE			= "yyyy-MM-dd";
	/** 时间格式 **/
	public static final String	FORMATTER_OF_TIME			= "HH:mm:ss";
	/** 日期时间格式 **/
	public static final String	FORMATTER_OF_DATE_AND_TIME	= "yyyy-MM-dd HH:mm:ss";

	/****
	 * 传入具体日期和增加几个月 ，返回具体日期增加几个月。
	 * 
	 * @param date
	 * @return String
	 */
	public static String subMonth(String date, int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = null;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.MONTH, num);
		Date dt1 = rightNow.getTime();
		String reStr = sdf.format(dt1);
		return reStr;
	}

	/***
	 * 日期减一天、加一天
	 * 
	 * @param option
	 *            传入类型 pro：日期减一天，next：日期加一天
	 * @param _date
	 *            2014-11-24
	 * @return 减一天：2014-11-23或(加一天：2014-11-25)
	 */
	public static String checkOption(String option, String _date, int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cl = Calendar.getInstance();
		Date date = null;

		try {
			date = (Date) sdf.parse(_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cl.setTime(date);
		if ("pre".equals(option)) {
			// 时间减
			cl.add(Calendar.DAY_OF_MONTH, -num);

		} else if ("next".equals(option)) {
			// 时间加
			cl.add(Calendar.DAY_OF_YEAR, num);
		} else {
			// do nothing
		}
		date = cl.getTime();
		return sdf.format(date);
	}

	public static String computeMinDate(String date1, String date2) {
		if (date1 == null) {
			return date2;
		}
		int result = calculationDate(date1, date2);
		if (result >= 0)
			return date2;
		else
			return date1;
	}

	public static int calculationDate(String date1, String date2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(sdf.parse(date1));
			c2.setTime(sdf.parse(date2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int result = c1.compareTo(c2);
		return result;
	}

	/**
	 * 今天
	 * 
	 * @return 今天date
	 */
	public static Date now() {
		return new Date();
	}

	/**
	 * 今天
	 * 
	 * @return 今天date
	 */
	public static String getTodayHHandMM(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sf.format(date);
	}

	/**
	 * 今天
	 * 
	 * @return 今天date
	 */
	public static String getTodays() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(new Date());
	}

	/**
	 * 得到两个日期之间的天数,两头不算,取出数据后，可以根据需要再加
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDay(Date date1, Date date2) {
		Long d2 = date2.getTime();
		Long d1 = date1.getTime();
		return (int) ((d2 - d1) / millionSecondsOfDay);
	}

	/**
	 * 
	 * 计算2个时间之间的相差小时和分钟数，返回XX小时XX分 注意date1格式为yyyy-MM-dd 注意date2格式为yyyy-MM-dd
	 * 注意time1格式为HH:mm 注意time2格式为HH:mm date1<date2
	 * 
	 * @param date1
	 * @param time1
	 * @param date2
	 * @param time2
	 * @return
	 */
	public static String getHourAndMinute(String date1, String time1, String date2, String time2) {
		Date dateTime1_tmp = DateUtil.parse(date1 + " " + time1, "yyyy-MM-dd HH:mm");
		Date dateTime2_tmp = DateUtil.parse(date2 + " " + time2, "yyyy-MM-dd HH:mm");
		Long d2 = dateTime2_tmp.getTime();
		Long d1 = dateTime1_tmp.getTime();
		int hours = (int) ((d2 - d1) / millionSecondsOfHour);
		int mins = (int) ((d2 - d1) % millionSecondsOfHour);
		mins = mins / 60000;
		return String.valueOf(hours) + "小时" + String.valueOf(mins) + "分";
	}

	/**
	 * 计算日期加天数
	 * 
	 * @author lihengjun
	 * @param date
	 * @param days
	 * @return
	 */
	public static String addDay(String beginDate, int days) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dbeginDate = null;
		try {
			dbeginDate = formatter.parse(beginDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = new GregorianCalendar();
		c.setTime(dbeginDate);
		c.add(Calendar.DAY_OF_MONTH, days);
		return formatter.format(c.getTime());
	}

	public static Date addMinutes(Date date, int minutes) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutes);
		return c.getTime();
	}

	/**
	 * 根据指定日期格式格式化日期
	 * 
	 * @param date
	 * @param formater
	 * @return
	 */
	public static String format(Date date, String formater) {
		if (date == null)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		return sdf.format(date);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param formater
	 * @return
	 */
	public static Date parse(String date, String formater) {
		if (date == null || "".equals(date))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		Date result = null;
		try {
			result = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param formater
	 * @return
	 */
	public static Date StringToDate(String date) {
		if (date == null || "".equals(date))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date result = null;
		try {
			result = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据日期取出是星期几
	 * 
	 * @param date
	 *            Date
	 * @return int 返回1-7
	 */
	public static int getWeekOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public static String getWeekTextOfDate(Date date) {
		String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		int t = getWeekOfDate(date);
		if (t == 7)
			t = 0;
		return dayNames[t];
	}

	public static String getWeekTextOfDate(String strDate, String format) {
		String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		int t = getWeekOfDate(parse(strDate, format));
		if (t == 7)
			t = 0;
		return dayNames[t];
	}

	/**
	 * format "yyyy-MM-dd HH:mm:ss"
	 */
	public static String dateToString(Date date, String format) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date).trim();

	}

	/**
	 * 获取日期相差月数
	 * 
	 * @param String
	 * @return int
	 */
	public static int getDiffMonth(String beginDate, String endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dbeginDate = null;
		Date dendDate = null;
		try {
			dbeginDate = formatter.parse(beginDate);
			dendDate = formatter.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return getDiffMonth(dbeginDate, dendDate);
	}

	/**
	 * 获取日期相差月数
	 * 
	 * @param Date
	 * @return int
	 */
	public static int getDiffMonth(Date beginDate, Date endDate) {
		Calendar calbegin = Calendar.getInstance();
		Calendar calend = Calendar.getInstance();
		calbegin.setTime(beginDate);
		calend.setTime(endDate);
		int m_begin = calbegin.get(Calendar.MONTH) + 1;
		int m_end = calend.get(Calendar.MONTH) + 1;
		int checkmonth = m_end - m_begin + (calend.get(Calendar.YEAR) - calbegin.get(Calendar.YEAR)) * 12;
		return checkmonth;
	}

	public static int daysBetween(String smdate, int num) {
		String bdate = subMonth(smdate, num);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(smdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time1 = cal.getTimeInMillis();
		try {
			cal.setTime(sdf.parse(bdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	public static void main(String[] stra) {
		System.out.println(subMonth("2015-10-29", 12));
		// System.out.println(daysBetween("2016-10-29",12));
	}
}
