package egovframework.MNG.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * <p>
 * 날자 포멧팅 변환등에 사용하는 Calendar 유틸리티 클레스
 * </p>
 *
 * @author
 * @date 2009. 10. 7. $Id: $
 */ 
public final class CalendarUtil {

    /** 프레임 워크 환경설정에 설정된 기본형 포멧 문자열 *//*
    private static String dafaultDateFormat = DaonConfigManager.getConfig().getString("common.date-format");
    *//** 프레임 워크 환경설정에 설정된 기본형 포멧 문자열 *//*
    private static String dafaultTimeFormat = DaonConfigManager.getConfig().getString("common.time-format");*/
    /** 년.월.일 형식의 기본형 포메터 */
    public static final FastDateFormat SHORT_DOT_FORMATTER = FastDateFormat.getInstance("yyyy-MM-dd");
    /** 년.월.일 시:분:초 형식의 기본형 포메터 */
    public static final FastDateFormat FULL_DOT_FORMATTER = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    /** 년-월-일 형식의 기본형 포메터 */
    public static final FastDateFormat SHORT_FORMATTER = FastDateFormat.getInstance("yyyy-MM-dd");
    /** 시:분:초 형식의 기본형 포메터 */
    public static final FastDateFormat HMS_FORMATTER = FastDateFormat.getInstance("HH:mm:ss");
    /** 년-월-일 시:분:초 형식의 기본형 포메터 */
    public static final FastDateFormat FULL_FORMATTER = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    /** 프레임 워크 환경설정에 설정된 년월일 기본형 포메터 */
    /*public static final FastDateFormat DEFAULT_DATE_FORMATTER = FastDateFormat.getInstance(dafaultDateFormat);
    *//** 프레임 워크 환경설정에 설정된 년월일 시분초 기본형 포메터 *//*
    public static final FastDateFormat DEFAULT_TIME_FORMATTER = FastDateFormat.getInstance(dafaultTimeFormat);*/

    /**
     * 오늘의 날짜 항목들을 문자 배열로 반환한다.
     *
     * @return String[6] {년,월,일,시,분,초}
     */
    public static String[] getArrDate() {
        String[] arrDate = new String[6];
        Calendar cal = Calendar.getInstance();
        arrDate[0] = fixLength(cal.get(Calendar.YEAR));
        arrDate[1] = fixLength(cal.get(Calendar.MONTH) + 1);
        arrDate[2] = fixLength(cal.get(Calendar.DATE));
        arrDate[3] = fixLength(cal.get(Calendar.HOUR_OF_DAY));
        arrDate[4] = fixLength(cal.get(Calendar.MINUTE));
        arrDate[5] = fixLength(cal.get(Calendar.SECOND));
        return arrDate;
    }
    
    /**
     * 오늘날짜 반환  (년,월,일,시,분,초)
     * @return 
     */
    public static String toDate() {
    	String returnDate = "";
    	String[] arrDate = new String[6];
    	Calendar cal = Calendar.getInstance();
    	arrDate[0] = fixLength(cal.get(Calendar.YEAR));
    	arrDate[1] = fixLength(cal.get(Calendar.MONTH) + 1);
    	arrDate[2] = fixLength(cal.get(Calendar.DATE));
    	arrDate[3] = fixLength(cal.get(Calendar.HOUR_OF_DAY));
    	arrDate[4] = fixLength(cal.get(Calendar.MINUTE));
    	arrDate[5] = fixLength(cal.get(Calendar.SECOND));
    	
    	returnDate = arrDate[0] + arrDate[1] + arrDate[2] +arrDate[3] +arrDate[4] + arrDate[5]; 
    	return returnDate;
    }

    
    
    /**
     * 전달 받은 인자가 9보다 같거나 작은 경우 "0"을 붙인 후 문자로 반환하고, 10보다 같거나 크면 문자로 변환만 한 후에
     * 반환한다.
     *
     * @param dateAttr
     *            <code>int</code>형 인자
     * @return 길이가 보정된 문자열
     */
    public static String fixLength(int dateAttr) {
        return (dateAttr <= 9 ? "0" + String.valueOf(dateAttr) : String
                .valueOf(dateAttr));
    }

    /**
     * 문자열을 받아 <code>Date</code> 객체로 반환한다.
     *
     * @param dateStr
     *            해석 가능한 날짜 문자열
     * @return <code>Date</code> 객체
     */
    public static Date getStrToDate(String dateStr) {

        try {
            Calendar cal = Calendar.getInstance();

            if (dateStr.length() == 8) {
                cal.set(Calendar.YEAR, Integer
                        .parseInt(dateStr.substring(0, 4)));
                cal.set(Calendar.MONTH, Integer.parseInt(dateStr
                        .substring(4, 6)) - 1);
                cal.set(Calendar.DATE, Integer
                        .parseInt(dateStr.substring(6, 8)));
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);

                return cal.getTime();

            }
            else if (dateStr.length() == 14) {
                cal.set(Calendar.YEAR, Integer
                        .parseInt(dateStr.substring(0, 4)));
                cal.set(Calendar.MONTH, Integer.parseInt(dateStr
                        .substring(4, 6)) - 1);
                cal.set(Calendar.DATE, Integer
                        .parseInt(dateStr.substring(6, 8)));
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateStr
                        .substring(8, 10)));
                cal.set(Calendar.MINUTE, Integer.parseInt(dateStr.substring(10,
                        12)));
                cal.set(Calendar.SECOND, Integer.parseInt(dateStr.substring(12,
                        14)));

                return cal.getTime();
            }

            SimpleDateFormat sfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            return sfmt.parse(dateStr);
        }
        catch (ParseException pe) {
            return null;
        }
    }

    /**
     * <p>14자리 날짜 스트링을 주고 [2002.01.01 12:12:24]의 포맷을 가져옴.</p>
     *
     * @param    14자리로된 숫자로된 날짜대상 값.
     * @return   포맷된 날짜.
     */
    public static String getFormatedDate (String date) {

        if (date == null){
            return "";
        }
        if (date.length() < 8) {
            return "";
        }

        StringBuffer returnDate = new StringBuffer();
        returnDate.append( date.substring(0, 4) );
        returnDate.append( "-" );
        returnDate.append( date.substring(4, 6) );
        returnDate.append( "-" );
        returnDate.append( date.substring(6, 8) );
        if (date.length() >= 10) {
            returnDate.append( " " );
            returnDate.append( date.substring(8, 10) );
        }
        if (date.length() >= 12) {
        returnDate.append( ":" );
        returnDate.append( date.substring(10, 12) );
        }
        if (date.length() == 14) {
            returnDate.append( ":" );
            returnDate.append( date.substring(12, 14) );
        }

        return returnDate.toString();
    }

    /**
     * 문자열을 받아서 <code>Calendar</code> 객체로 반환한다.
     *
     * @param dateStr
     *            해석 가능한 날짜 문자열
     * @return <code>Calendar</code> 객체
     */
    public static Calendar getCalendarObj(String dateStr) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getStrToDate(dateStr));

        return cal;
    }

    /**
     * 오늘의 날짜를 사용한다. "yyyy-MM-dd" 패턴의 날짜를 반환한다. 예 : 2008-01-30
     *
     * @return String "yyyy-MM-dd" 패턴날짜
     */
    public static String getShortDate() {

        return SHORT_FORMATTER.format(Calendar.getInstance());
    }

    /**
     * 지정된 날짜를 사용한다. "yyyy-MM-dd" 패턴의 날짜를 반환한다. 예 : 2008-01-30
     *
     * @param dateStr
     *            해석 가능한 날짜 문자열
     * @return String "yyyy-MM-dd" 패턴날짜
     */
    public static String getShortDate(String dateStr) {

        return SHORT_FORMATTER.format(getCalendarObj(dateStr));
    }

    /**
     * 오늘의 날짜를 사용한다. "yyyy-MM-dd" 패턴의 날짜를 반환한다. 예 : 2008-01-30
     *
     * @return String "yyyy.MM.dd" 패턴날짜
     */
    public static String getShortDotDate() {

        return SHORT_DOT_FORMATTER.format(Calendar.getInstance());
    }

    /**
     * 지정된 날짜를 사용한다. "yyyy-MM-dd" 패턴의 날짜를 반환한다. 예 : 2008-01-30
     *
     * @param dateStr
     *            해석 가능한 날짜 문자열
     * @return String "yyyy.MM.dd" 패턴날짜
     */
    public static String getShortDotDate(String dateStr) {

        return SHORT_DOT_FORMATTER.format(getCalendarObj(dateStr));
    }

    /**
     * 오늘의 날짜를 사용한다. "yyyy-MM-dd HH:mm:ss" 패턴의 날짜를 반환한다.
     *
     * 예 : 2008-01-30 21:10:13
     *
     * @return String "yyyy-MM-dd HH:mm:ss" 패턴날짜
     */
    public static String getFullDate() {

        return FULL_FORMATTER.format(Calendar.getInstance());
    }

    /**
     * 지정된 날짜를 사용한다. "yyyy-MM-dd HH:mm:ss" 패턴의 날짜를 반환한다. <br />
     * 예 : 2008-01-30 21:10:13
     *
     * @param dateStr
     *            해석 가능한 날짜 문자열
     * @return String "yyyy-MM-dd HH:mm:ss" 패턴날짜
     */
    public static String getFullDate(String dateStr) {

        return FULL_FORMATTER.format(getCalendarObj(dateStr));
    }

    /**
     * 오늘의 날짜를 사용한다. "yyyy-MM-dd HH:mm:ss" 패턴의 날짜를 반환한다. <br />
     * 예 : 2008-01-30 21:10:13
     *
     * @return String "yyyy.MM.dd HH:mm:ss" 패턴날짜
     */
    public static String getFullDotDate() {

        return FULL_DOT_FORMATTER.format(Calendar.getInstance());
    }

    /**
     * 지정된 날짜를 사용한다. "yyyy-MM-dd HH:mm:ss" 패턴의 날짜를 반환한다. <br />
     * 예 : 2008-01-30 21:10:13
     *
     * @param dateStr
     *            해석 가능한 날짜 문자열
     * @return String "yyyy.MM.dd HH:mm:ss" 패턴날짜
     */
    public static String getFullDotDate(String dateStr) {

        return FULL_DOT_FORMATTER.format(getCalendarObj(dateStr));
    }

    /**
     * 현재 시간을 사용한다. "HH:mm:ss" 패턴의 시분초를 반환한다. <br />
     * 예 : 22:10:12
     *
     * @return String "HH:mm:ss" 패턴 시분초
     */
    public static String getHMS() {

        return HMS_FORMATTER.format(Calendar.getInstance());
    }

    /**
     * 지정된 날짜를 사용한다. "HH:mm:ss" 패턴의 시분초를 반환한다. <br />
     * 예 : 22:10:12
     *
     * @param dateStr
     *            해석 가능한 날짜 문자열
     * @return String "HH:mm:ss" 패턴 시분초
     */
    public static String getHMS(String dateStr) {

        return HMS_FORMATTER.format(getCalendarObj(dateStr));
    }

    /**
     * 오늘의 날짜를 사용한다. 포멧 패턴을 인자로 받아 패턴 형식의 문자열로 값을 반환한다.
     *
     * @param pattern
     *            사용자 지정 패턴(예:"yyyy-MM-dd HH:mm:ss")
     * @return String 패턴형식으로 변환된 날짜
     */
    public static String getFormatDate(String pattern) {
        Calendar cal = Calendar.getInstance();

        return getAssignFormatDate(cal, pattern);
    }

    /**
     *
     * 지정된 날짜를 사용한다. 포멧 패턴을 인자로 받아 패턴 형식의 문자열로 값을 반환한다.
     *
     * <p />
     * 지정가능 문자열 형식
     *
     * 20080715<br />
     * 20080715184933<br />
     *
     * 2008-07-15<br />
     * 2008-07-15 18:49:33<br />
     * 2008-07-15 18:49:33.0 <br />
     *
     * @param dateStr
     *            해석 가능한 날짜 문자열
     * @param pattern
     *            사용자 지정 패턴(예:"yyyy-MM-dd HH:mm:ss")
     * @return String 패턴형식으로 변환된 날짜
     */
    public static String getFormatDate(String dateStr, String pattern) {

        if (dateStr == null || dateStr.length() == 0)
            return "";

        Calendar cal = Calendar.getInstance();
        cal.setTime(getStrToDate(dateStr));

        return getAssignFormatDate(cal, pattern);
    }

    /**
     * 지정된 날짜를 사용한다. 포멧 패턴을 인자로 받아 패턴 형식의 문자열로 값을 반환한다.
     *
     * @param dateObj
     *            Date 객체
     * @param pattern
     *            사용자 지정 패턴(예:"yyyy-MM-dd HH:mm:ss")
     * @return String 패턴형식으로 변환된 날짜
     */
    public static String getFormatDate(Date dateObj, String pattern) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateObj);

        return getAssignFormatDate(cal, pattern);
    }

    /**
     * 2008-07-15<br />
     * 2008-07-15 18:49:33<br />
     * 2008-07-15 18:49:33.0 <br />
     * <code>Calendar</code> 객체(지정된 날짜)와 포멧 패턴을 받아서 패턴 형식의 문자열로 값을 반환한다.
     *
     * @param cal
     *            특정 날짜가 포함된 <code>Calendar</code> 객체
     * @param pattern
     *            사용자 지정 패턴(예:"yyyy-MM-dd HH:mm:ss")
     * @return String 패턴형식으로 변환된 날짜
     */
    public static String getAssignFormatDate(Calendar cal, String pattern) {
        FastDateFormat fdf = FastDateFormat.getInstance(pattern);

        return fdf.format(cal);
    }

    /**
     *
     * 형식의 지정된 날짜와 포멧 패턴을 받아서 프레임워크 기본 패턴 형식의 문자열(년월일시분초)로 값을 반환한다.
     *
     * <p />
     * 지정가능 문자열 형식
     *
     * 20080715<br />
     * 20080715184933<br />
     *
     * 2008-07-15<br />
     * 2008-07-15 18:49:33<br />
     * 2008-07-15 18:49:33.0 <br />
     *
     * @param dateStr
     *            특정 날짜
     * @return String 패턴형식으로 변환된 날짜(년월일)
     */
   /* public static String getAssignFormatDate(String dateStr) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(getStrToDate(dateStr));

        return DEFAULT_DATE_FORMATTER.format(cal);
    }*/

    /**
     * 형식의 지정된 날짜와 포멧 패턴을 받아서 프레임워크 기본 패턴 형식의 문자열(년월일시분초)로 값을 반환한다.
     * <p />
     *
     * <p />
     * 지정가능 문자열 형식
     *
     * 20080715<br />
     * 20080715184933<br />
     *
     * 2008-07-15<br />
     * 2008-07-15 18:49:33<br />
     * 2008-07-15 18:49:33.0 <br />
     *
     * @param timeStr
     *            특정 날짜
     * @return String 패턴형식으로 변환된 날짜(년월일시분초)
     */
   /* public static String getAssignFormatTime(String timeStr) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(getStrToDate(timeStr));

        return DEFAULT_DATE_FORMATTER.format(cal);
    }*/

    /**
     * 넘겨받은 날짜(<code>Date</code>)에 adding 만큼을 년도를 가감한다.
     *
     * @param date
     *            대상 <code>Date</code> 객체
     * @param adding
     *            가감될 수치
     * @return Date adding 만큼 가감된 년도
     * @throws Exception 
     */
    public static Date addYears(Date date, int adding) throws Exception {
        return add(date, Calendar.YEAR, adding);
    }

    /**
     * 넘겨받은 날짜(<code>Date</code>)에 adding 만큼을 월을 가감한다.
     *
     * @param date
     *            대상 <code>Date</code> 객체
     * @param adding
     *            가감될 수치
     * @return Date adding 만큼 가감된 월
     * @throws Exception 
     */
    public static Date addMonths(Date date, int adding) throws Exception {
        return add(date, Calendar.MONTH, adding);
    }

    /**
     * 넘겨받은 날짜(<code>Date</code>)에 adding 만큼을 주를 가감한다.
     *
     * @param date
     *            대상 <code>Date</code> 객체
     * @param adding
     *            가감될 수치
     * @return Date adding 만큼 가감된 주
     * @throws Exception 
     */
    public static Date addWeeks(Date date, int adding) throws Exception {
        return add(date, Calendar.WEEK_OF_YEAR, adding);
    }

    /**
     * 넘겨받은 날짜(<code>Date</code>)에 adding 만큼을 일자를 가감한다.
     *
     * @param date
     *            대상 <code>Date</code> 객체
     * @param adding
     *            가감될 수치
     * @return Date adding 만큼 가감된 일자
     * @throws Exception 
     */
    public static Date addDays(Date date, int adding) throws Exception {
        return add(date, Calendar.DAY_OF_MONTH, adding);
    }

    /**
     * 넘겨받은 날짜(<code>Date</code>)에 adding 만큼을 시간를 가감한다.
     *
     * @param date
     *            대상 <code>Date</code> 객체
     * @param adding
     *            가감될 수치
     * @return Date adding 만큼 가감된 시간
     * @throws Exception 
     */
    public static Date addHours(Date date, int adding) throws Exception {
        return add(date, Calendar.HOUR_OF_DAY, adding);
    }

    /**
     * 넘겨받은 날짜(<code>Date</code>)에 adding 만큼을 분를 가감한다.
     *
     * @param date
     *            대상 <code>Date</code> 객체
     * @param adding
     *            가감될 수치
     * @return Date adding 만큼 가감된 분
     * @throws Exception 
     */
    public static Date addMinutes(Date date, int adding) throws Exception {
        return add(date, Calendar.MINUTE, adding);
    }

    /**
     * 넘겨받은 날짜(<code>Date</code>)에 adding 만큼을 초를 가감한다.
     *
     * @param date
     *            대상 <code>Date</code> 객체
     * @param adding
     *            가감될 수치
     * @return Date adding 만큼 가감된 초
     * @throws Exception 
     */
    public static Date addSeconds(Date date, int adding) throws Exception {
        return add(date, Calendar.SECOND, adding);
    }

    /**
     * 넘겨받은 날짜(<code>Date</code>)에 adding 만큼을 백분초(MILLISECOND)를 가감한다.
     *
     * @param date
     *            대상 <code>Date</code> 객체
     * @param adding
     *            가감될 수치
     * @return Date adding 만큼 가감된 백분초(MILLISECOND)
     * @throws Exception 
     */
    public static Date addMillis(Date date, int adding) throws Exception {
        return add(date, Calendar.MILLISECOND, adding);
    }

    /**
     * 넘겨받은 날짜(<code>Date</code>)에 field 위치(년월일시분초밀리세컨)에 adding 만큼을 가감한다.
     *
     * @param date
     *            대상 <code>Date</code> 객체
     * @param field
     *            가감 대상 위치(년월일시분초밀리세컨)
     * @param adding
     *            가감될 수치
     * @return Date
     * @throws Exception 
     */
    public static Date add(Date date, int field, int adding) throws Exception {
        AssertUtil.isNotNull(date,
                "[오류] 대상 java.util.Date 객체는 null 이 아니어야 합니다.");

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, adding);

        return c.getTime();
    }

    /**
     * <p>특정 포맷의 문자열을 Date 타입으로 변환. </p>
     *
     * @param    대상날짜.
     * @param    대상날짜의 포맷.
     * @return   변경된 Date타입. 대상날짜의 오류가 있을 경우 null 리턴.
     */
    public  static Date stringToDate(String date, String fmt) {

        if (date != null && fmt != null) {
            SimpleDateFormat sfmt = new SimpleDateFormat(fmt);
            try {
                return  sfmt.parse(date);
            }
            catch (ParseException pe) {
                return null;
            }
        }
        else {
            return null;
        }
    }

    /**
     * 둘 날짜 간에 기간 리턴. [<font color=red>null</font>]
     *
     * @param       String    요청 일의 날짜(yyyyMMdd)
     * @return      String    요청 일의 날짜(yyyyMMdd)
     * @return      String    처리결과 파싱 엔티티
     * @throws      java.lang.Exception
     */
    public static int getDate(String first,String end) throws Exception{
        int r_time=0;
        try{
            Calendar c_first = Calendar.getInstance();
            c_first.setTime(stringToDate(first,"yyyyMMdd"));
            Calendar c_end = Calendar.getInstance();
            c_end.setTime(stringToDate(end,"yyyyMMdd"));
            long l_time=(c_end.getTime()).getTime()-(c_first.getTime()).getTime();
            r_time=(int)((l_time)/86300000);
        }
        catch(Exception ex){
            throw ex;
        }
        return r_time;
    }
   
    /**
     * 넘겨받은 날짜의 요일을 int 값으로 반환.일요일(1)토요일(7)
     *
     */
    public static int getDayOfWeek(String year, String mon, String date) {
    	Calendar cal= Calendar.getInstance ();
	    
	    cal.set(Calendar.YEAR,  Integer.parseInt(year));
	    cal.set(Calendar.MONTH, Integer.parseInt(mon)-1);
	    cal.set(Calendar.DATE, Integer.parseInt(date));
	return cal.get(Calendar.DAY_OF_WEEK);
    }
}
