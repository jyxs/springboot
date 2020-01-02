package com.ai2331.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
	private static final ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
	public static final String DATE_MODEL_1 = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DATE_MODEL_2 = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_MODEL_3 = "yyyy-MM-dd HH:mm";
	public static final String DATE_MODEL_4 = "yyyy-MM-dd HH";
	public static final String DATE_MODEL_5 = "yyyy-MM-dd";
	public static final String DATE_MODEL_6 = "yyyy-MM";

	public static final String DATE_MODEL_7 = "yyyyMMddHHmmss.SSS";
	public static final String DATE_MODEL_8 = "yyyyMMddHHmmss";
	public static final String DATE_MODEL_9 = "yyyyMMddHHmm";
	public static final String DATE_MODEL_10 = "yyyyMMddHH";
	public static final String DATE_MODEL_11 = "yyyyMMdd";
	public static final String DATE_MODEL_12 = "yyyyMM";

	public static final String DATE_MODEL_13 = "yyyy/MM/dd HH:mm:ss.SSS";
	public static final String DATE_MODEL_14 = "yyyy/MM/dd HH:mm:ss";
	public static final String DATE_MODEL_15 = "yyyy/MM/dd HH:mm";
	public static final String DATE_MODEL_16 = "yyyy/MM/dd HH";
	public static final String DATE_MODEL_17 = "yyyy/MM/dd";
	public static final String DATE_MODEL_18 = "yyyy/MM";

	public static final String DATE_MODEL_99 = "yyyy";

	/**
	 * 根据日期字符串，判断日期格式。常用于不知道的日期格式判断。
	 * 
	 * @param date 日期字符串
	 * @return String
	 */
	private static DateType getType(String date) {
		if (date.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d+")) {
			return new DateType(DateUtils.DATE_MODEL_1, false);
		} else if (date.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
			return new DateType(DateUtils.DATE_MODEL_2, false);
		} else if (date.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
			return new DateType(DateUtils.DATE_MODEL_3, false);
		} else if (date.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}")) {
			return new DateType(DateUtils.DATE_MODEL_4, false);
		} else if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
			return new DateType(DateUtils.DATE_MODEL_5, true);
		} else if (date.matches("\\d{4}-\\d{2}")) {
			return new DateType(DateUtils.DATE_MODEL_6, true);
		} else if (date.matches("\\d{14}.\\d+")) {
			return new DateType(DateUtils.DATE_MODEL_7, false);
		} else if (date.matches("\\d{14}")) {
			return new DateType(DateUtils.DATE_MODEL_8, false);
		} else if (date.matches("\\d{12}")) {
			return new DateType(DateUtils.DATE_MODEL_9, false);
		} else if (date.matches("\\d{10}")) {
			return new DateType(DateUtils.DATE_MODEL_10, false);
		} else if (date.matches("\\d{8}")) {
			return new DateType(DateUtils.DATE_MODEL_11, true);
		} else if (date.matches("\\d{6}")) {
			return new DateType(DateUtils.DATE_MODEL_12, true);
		} else if (date.matches("\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d+")) {
			return new DateType(DateUtils.DATE_MODEL_13, false);
		} else if (date.matches("\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
			return new DateType(DateUtils.DATE_MODEL_14, false);
		} else if (date.matches("\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}")) {
			return new DateType(DateUtils.DATE_MODEL_15, false);
		} else if (date.matches("\\d{4}/\\d{2}/\\d{2} \\d{2}")) {
			return new DateType(DateUtils.DATE_MODEL_16, false);
		} else if (date.matches("\\d{4}/\\d{2}/\\d{2}")) {
			return new DateType(DateUtils.DATE_MODEL_17, true);
		} else if (date.matches("\\d{4}/\\d{2}")) {
			return new DateType(DateUtils.DATE_MODEL_18, true);
		}
		return null;
	}

	public static Date getDate(String date) {
		if (null == date || (date = date.trim()).equals("")) {
			return null;
		}
		DateType dt = getType(date);
		if (dt.isDate) {
			return localDate2Date(LocalDate.parse(date, DateTimeFormatter.ofPattern(dt.partten)));
		} else {
			return localDateTime2Date(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(dt.partten)));
		}
	}

	/**
	 * 格式化日期，yyyy-MM-dd
	 * @param date
	 * @return
	 * date: Sep 16, 2019 2:15:30 PM
	 */
	public static String getDefaultDateStr(Date date) {
		if (null == date) {
			return "";
		}
		LocalDate localDate = date2LocalDate(date);
		return localDate.format(DateTimeFormatter.ofPattern(DATE_MODEL_5));
	}

	/**
	 * 格式化日期，yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 * date: Sep 16, 2019 2:15:30 PM
	 */
	public static String getDefaultDateTimeStr(Date date) {
		if (null == date) {
			return "";
		}
		LocalDateTime localDateTime = date2LocalDateTime(date);
		return localDateTime.format(DateTimeFormatter.ofPattern(DATE_MODEL_2));
	}

	/**
	 * 
	 * 格式化指定格式的日期
	 * @param date
	 * @param type
	 * @return
	 * date: Sep 16, 2019 2:16:47 PM
	 */
	public static String getDateStr(Date date, String type) {
		if (null == date) {
			return "";
		}
		LocalDate localDate = date2LocalDate(date);
		return localDate.format(DateTimeFormatter.ofPattern(type));
	}

	/**
	* Date转换为LocalDateTime
	* @param date
	* @return 
	*/
	public static LocalDateTime date2LocalDateTime(Date date) {
		Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
		return instant.atZone(zoneId).toLocalDateTime();
	}

	/**
	 * Date转换为LocalDate
	 * @param date
	 * @return 
	 */
	public static LocalDate date2LocalDate(Date date) {
		Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
		return instant.atZone(zoneId).toLocalDate();
	}

	/**
	 * LocalDateTime转换为Date
	 * @param localDateTime
	 * @return 
	 */
	public static Date localDateTime2Date(LocalDateTime localDateTime) {
		ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
		return Date.from(zdt.toInstant());
	}

	/**
	 * LocalDate转换为Date
	 * @param date
	 * @return 
	 */
	public static Date localDate2Date(LocalDate localDate) {
		ZonedDateTime zdt = localDate.atStartOfDay().atZone(zoneId);
		return Date.from(zdt.toInstant());
	}

	static class DateType {
		private String partten;
		private boolean isDate;

		public DateType(String partten, boolean isDate) {
			this.partten = partten;
			this.isDate = isDate;
		}

		public String getPartten() {
			return partten;
		}

		public boolean isDate() {
			return isDate;
		}
	}
	
	public static void main(String[] args) {
//		for (int i = 0; i < 1000; i++) {
//			new Thread() {
//				@Override
//				public void run() {
//					System.out.println(getDate("2019-01-01 11:11:11"));
//				}
//			}.start();
//		}
//		for (int i = 0; i < 1000; i++) {
//			new Thread() {
//				@Override
//				public void run() {
//					System.out.println(getDefaultDateTimeStr(new Date()));
//				}
//			}.start();
//		}
	}

}
