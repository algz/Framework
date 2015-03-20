package com.hello.example.utils;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;




 
/**
 * TODO
 * Copyright    :www.sysware.com.cn
 * Company      :sysware Info. Ltd.
 * Created      :2012-2-18上午10:10:31
 * @author      :杨怀
*/
public class SyswareDateUtils {
	

	/**
	 * @return 返回当前的系统时间戳
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * @return 返回java.util.Date的当前日期
	 */
	public static java.util.Date getCurrentDate() {
		return new java.util.Date();
	}

	/**
	 * @return 返回java.sql.Date的当前日期
	 */
	public static java.sql.Date getCurrentSQLDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	/**
	 * @return 返回字符串形式的当前日期时间 年月日时分秒 如：（2008-12-22 09:43:23)
	 */
	public static String getCurrentDatetimeAsString() {
		return FORMAT_DATETIME.format(new java.util.Date());
	}
	
	/**
	 * @return 返回字符串形式的当前日期时间 年月日时分秒毫秒 如：（2008-12-22 09:43:23 222)
	 */
	public static String getCurrentMilliSecondAsString() {
		return FORMAT_MILLISECOND_DATETIME.format(new java.util.Date());
	}
	
	/**
	 * 日期时间编号（ID）格式化  
	 */
	private static final SimpleDateFormat s_sdfDatetimeMilSecondID = new SimpleDateFormat("yyyyMMddHHmmssS");
	
	/**
	 * 返回当前系统时间的年月日时分秒毫秒（共17位） 如：200812231232456
	 * @return 返回当前系统时间的年月日时分秒毫秒
	 */
	public static String getCurrentDatetimeMillionID(){
		return s_sdfDatetimeMilSecondID.format(new Date());
	}
	
	
	/**
	 * 日期时间编号（ID）格式化  
	 */
	private static final SimpleDateFormat s_sdfDatetimeID = new SimpleDateFormat("yyyyMMddHHmmss");
	
	
	/**
	 * 返回当前系统时间的年月日时分秒毫秒（共17位） 如：200812231232456
	 * @return 返回当前系统时间的年月日时分秒毫秒
	 */
	public static String getCurrentDatetimeID(){
		return s_sdfDatetimeID.format(new Date());
	}
	

	/**
	 * @return 返回字符串形式的当前日期 年月日 如：（2008-12-22）
	 */
	public static String getCurrentDateAsString() {
		return FORMAT_DATE.format(new java.util.Date());
	}
	
	
	/**
	 * 日期格式化
	 */
	public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 格式化给定日期时间 为 ：yyyy-MM-dd<BR>
	 * 
	 * @param pDate 给定需要格式化的日期对象
	 * @return 格式化给定的日期对象，如果给定 pDate为 null，返回空字符串
	 */
	public static String formatDate(Date pDate){
		if(pDate == null)
			return SyswareUtil.EMPTY_STRING;
		return FORMAT_DATE.format(pDate);
	}
	
	public static String formatDate(Date pDate, String format){
		if(pDate == null || SyswareUtil.isEmpty(format))
			return SyswareUtil.EMPTY_STRING;
		SimpleDateFormat format_string = new SimpleDateFormat(format);
		return format_string.format(pDate);
	}
	
	
	/**
	 * 描述	   : 格式化Timestamp 为 ：yyyy-MM-dd
	 * @since  : 2013-6-8:下午02:44:42
	 * @author : suny
	 * @param timestamp
	 * @return
	 */
	public static String formatDate(Timestamp timestamp){
		if(timestamp==null)return SyswareUtil.EMPTY_STRING;
		return FORMAT_DATE.format(timestamp);
	}
	/**
	 * 描述	   : 格式化Timestamp 为 format格式，如果format为null or empty ,返回empty
	 * @since  : 2013-6-8:下午02:45:07
	 * @author : suny
	 * @param timestamp
	 * @param format
	 * @return
	 */
	public static String formatDate(Timestamp timestamp,String format){
		if(timestamp==null || SyswareUtil.isEmpty(format))return SyswareUtil.EMPTY_STRING;
		SimpleDateFormat format_string = new SimpleDateFormat(format);
		return format_string.format(timestamp);
	}
	/**
	 * 描述	   : 格式化Timestamp为 ：yyyy-MM-dd HH:mm:ss
	 * @since  : 2013-6-8:下午02:47:28
	 * @author : suny
	 * @param timestamp
	 * @return
	 */
	public static String formatDatetime(Timestamp timestamp){
		if(timestamp==null)return SyswareUtil.EMPTY_STRING;
		return FORMAT_DATETIME.format(timestamp);
	}
	
	
		
	
	
	/**
	 * 日期时间格式化
	 */
	public static final SimpleDateFormat FORMAT_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * 日期时间格式化
	 */
	public static final SimpleDateFormat FORMAT_MILLISECOND_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SS");
	
	
	/**
	 * 格式化给定日期时间 为 ：yyyy-MM-dd HH:mm:ss <BR>
	 * 
	 * @param pDatetime 给定需要格式化的日期对象
	 * @return 格式化给定的日期对象，如果给定 pDate为 null，返回空字符串
	 */
	public static String formatDatetime(Date pDatetime){
		if(pDatetime == null)
			return SyswareUtil.EMPTY_STRING;
		return FORMAT_DATETIME.format(pDatetime);
	}
	
	/**
	 * 将给定的日期时间转换为 java.util.Date<BR>
	 * @param psDatetime 给定日期时间  yyyy-MM-dd HH:mm:ss
	 * @return 如果 psDatetime 为空返回null，  如果转换成功返回给定日期时间的 java.util.Date，转换失败抛出异常
	 * @throws SWRuntimeException 日期格式转换失败抛出异常
	 */
	public static java.util.Date parseDatetime(String psDatetime){
		if(SyswareUtil.isEmpty(psDatetime))
			return null;
		try {
			return FORMAT_DATETIME.parse(psDatetime);
		} catch (ParseException e) {
			throw new SyswareRuntimeException("日期格式错误，请确认日期格式是否为 yyyy-MM-dd HH:mm:ss ， " + psDatetime);
		}
	}
	
	/**
	 * 将给定的日期转换为 java.util.Date <BR>
	 * 
	 * @param psDate psDate 给定日期时间  yyyy-MM-dd
	 * @return 如果给定的 psDate为null，返回null， 如果转换成功返回给定日期的 java.util.Date，转换失败抛出异常
	 * @throws SWRuntimeException 日期格式转换失败抛出异常
	 */
	public static java.util.Date parseDate(String psDate) throws SyswareRuntimeException{
		
		if(SyswareUtil.isEmpty(psDate))
			return null;
		try {
			return FORMAT_DATE.parse(psDate);
		} catch (ParseException e) {
			throw new SyswareRuntimeException("日期格式错误，请确认日期格式是否为 yyyy-MM-dd ， " + psDate);
		}
	}
	
	public static java.sql.Timestamp convertToTimestamp (String psValue ) {
		
		java.util.Date tValue = null ;
		try{
			tValue = parseDatetime( psValue );
		} catch (Exception e){}
		
		if ( tValue == null ){
			return null;
		}
		return new java.sql.Timestamp ( tValue.getTime() ) ;
	}
	
	public static java.sql.Date convertToSqlDate (String psValue ) {
		
		java.util.Date tValue = null ;
		
		try{
			tValue = parseDate( psValue );
		} catch (Exception e){}
		
		if ( tValue == null ){
			return null;
		}
		
		return new java.sql.Date ( tValue.getTime() ) ;
		
	}
	
	
	/**计算月差,不足一个月的，按照一个月处理
	 * @param pdateBegin 开始日期
	 * @param pdateEnd 结束日期
	 * @return 返回两个日期间的月差，如果开始日期大于结束日期会返回负值，如果两个日期是同一个月份，返回1（不足一个月按照一个月处理）
	 */
	@SuppressWarnings("deprecation")
	public static int getMonthDiff(Date pdateBegin,  Date pdateEnd ) {
		if(pdateBegin ==null)
			throw new SyswareRuntimeException("给定的日期为null，请给定有效值!" );
		if(pdateEnd ==null)
			throw new SyswareRuntimeException("给定的日期为null，请给定有效值!" );
		int iBeginYear = pdateBegin.getYear();
		int iEndYear = pdateEnd.getYear();
		int iBeginMonth = pdateBegin.getMonth();
		int iEndMonth = pdateEnd.getMonth();
		
		int iResult =  12 * (iEndYear - iBeginYear) + iEndMonth - iBeginMonth;
		
		return iResult==0?1:iResult;
	}
	
	
	 
	
	/**计算月差,不足一个月的，按照一个月处理
	 * @param psBegin 开始日期  (日期格式:yyyy-MM-dd)
	 * @param psEnd 结束日期
	 * @return 返回两个日期间的月差，如果开始日期大于结束日期会返回负值，如果两个日期是同一个月份，返回1（不足一个月按照一个月处理）
	 */
	public static int getMonthDiff(String psDateBegin,  String psDateEnd ) {
		
		Date dateBegin = parseDate(psDateBegin);
		
		Date dateEnd = parseDate(psDateEnd);
		
		return getMonthDiff(dateBegin, dateEnd);
	
	}
	

	/**
	 * 将String型的日期转换为Timestamp类型的日期
	 * 
	 * @param psStringTime
	 * @return
	 */
	public static Timestamp convertStringToTimestamp(String psStringTime) {

		try {
			Timestamp tTS = new Timestamp(FORMAT_DATETIME.parse(psStringTime).getTime());
			return tTS;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 计算年数
	 * 
	 * @param psDate
	 * @return
	 */
	public static int getAgeFromDate(String psDate) {
		GregorianCalendar gc = new GregorianCalendar();
		Integer year = gc.get(Calendar.YEAR);
		Integer birth = Integer.parseInt(psDate.substring(0, 4));
		return year - birth;
	}

	
	private static final SimpleDateFormat sdf_month = new SimpleDateFormat("yyyy-MM");
	
	/**
	 * 判断原日期是否早于或等于目标日期年月
	 * @param psStringOriDate 原日期
	 * @param psStringDestDate  目标日期
	 * @return
	 */
	public static boolean isHardBeforeThisMonth(String psStringOriDate,String psStringDestDate) {
		try {
			return sdf_month.parse(psStringOriDate).getTime() <=sdf_month.parse(psStringDestDate).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new SyswareRuntimeException("日期比较时出错");
	}
	
	
	/**
	 * 判断指定的日期是否早于系统日期 年月
	 * @param psStringDate
	 * @return 如果早于系统时间，返回true
	 */
	public static boolean isHardBeforeNowMonth(String psStringDate) {
		try {
			return sdf_month.parse(psStringDate).getTime() < System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new SyswareRuntimeException("日期比较时出错");
	}
	
	/**
	 * 判断指定的时间是否早于或等于系统时间
	 * @param psStringTime
	 * @return 如果早于系统时间，返回true
	 */
	public static boolean isBeforeNowTime(String psStringTime) {
		try {
			return FORMAT_DATETIME.parse(psStringTime).getTime() <= System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new SyswareRuntimeException("时间比较时出错");
	}
	

	
	/**
	 * 判断原时间是否早于或等于目标时间
	 * @param psStringOriDate  原时间
	 * @param psStringDestDate 目标时间
	 * @return
	 */
	public static boolean isBeforeThisTime(String psStringOriTime, String psStringDestTime) {
		try {
			return FORMAT_DATETIME.parse(psStringOriTime).getTime() <= FORMAT_DATETIME.parse(psStringDestTime).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new SyswareRuntimeException("时间比较时出错");
	}

	
	
	/**
	 * 判断指定的日期是否早于或等于系统日期
	 * 
	 * @param psStringDate
	 * @return 如果早于系统时间，返回true
	 */
	public static boolean isBeforeNowDate(String psStringDate) {
		try {
			return FORMAT_DATE.parse(psStringDate).getTime() <= System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new SyswareRuntimeException("日期比较时出错");
	}

	/**
	 * 判断原日期是否早于或等于目标日期
	 * @param psStringOriDate  原日期
	 * @param psStringDestDate 目标日期
	 * @return
	 */
	public static boolean isBeforeThisDate(String psStringOriDate, String psStringDestDate) {
		try {
			return FORMAT_DATE.parse(psStringOriDate).getTime() <= FORMAT_DATE.parse(psStringDestDate).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new SyswareRuntimeException("日期比较时出错");
	}
	
	/**
	 * 判断原日期是否早于目标日期
	 * @param psStringOriDate    原日期
	 * @param psStringDestDate   目标日期
	 * @return
	 */
	public static boolean isHardBeforeThisDate(String psStringOriDate, String psStringDestDate) {
		try {
			return FORMAT_DATE.parse(psStringOriDate).getTime() < FORMAT_DATE.parse(psStringDestDate).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new SyswareRuntimeException("日期比较时出错");
	}
}
