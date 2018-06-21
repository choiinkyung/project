package egovframework.MNG.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.internal.org.bouncycastle.util.Arrays;


/**
 * <p>
 * 문자열 관련  유틸리티 클레스
 * </p>
 *
 * @author
 * @date 2009. 10. 7. $Id: $
 */
public class StringUtil extends StringUtils{


	public static String getReqParameter(String strValue) {

		String tmpStr = getParameter(strValue, "");

		return replaceHtmlEscape(tmpStr);
	}

	public static String getReqParameter(String strValue, String retValue) {

		String tmpStr = getParameter(strValue, retValue);

		return replaceHtmlEscape(tmpStr);
    }


	/**
	 * 인자가 <code>null</code>이면 빈 문자열을 반환한다.
	 * @param strValue
	 * @return String
	 */
	public static String getParameter(String strValue) {
		return getParameter(strValue, "");
	}

	/**
	 * 인자가 <code>null</code>이면 지정한 문자열을 반환한다.
	 * @param strValue
	 * @param retValue
	 * @return String
	 */
	public static String getParameter(String strValue, String retValue) {

		String retString = (strValue == null || "".equals(strValue) ? retValue : strValue);

		return retString.trim();
	}

    /**
     * 문자열 내의 모든 White Space를 제거한다. 만약 대상 문자열이 <code>null</code> 이라면 빈문자열("")을
     * 반환한다.
     *
     * @param str
     *            대상 문자열
     * @return White Space가 제거된 문자열
     */
    public static String deleteWhitespace(String str) {
        if (null == str)
            return "";
        StringBuffer buf = new StringBuffer(str);
        int index = 0;
        while (buf.length() > index) {
            if (Character.isWhitespace(buf.charAt(index))) {
                buf.deleteCharAt(index);
            }
            else {
                index++;
            }
        }
        return buf.toString();
    }

    /**
     * 문자열의 길이를 구한다.
     *
     * @param str
     *            대상 문자열
     * @return 문자열의 길이
     * @throws Exception 
     */
    public static int getLength(String str) throws Exception {
        AssertUtil.isNotNull(str, "[오류] 길이를 구하려는 문자열은 null 일수 없습니다.");
        return str.length();
    }

    /**
     * 모든 White Space를 제거한 후 빈 문자열인지를 확인한다.
     *
     * @param str
     *            대상 문자열
     * @return 비었다면 <code>true</code>, 비어있지 않다면 <code>false</code>를 반환한다.
     * @throws Exception 
     *//*
    public static boolean isEmpty(String str) throws Exception {
        AssertUtil.isNotNull(str, "[오류] 비어 있는지를 확인하려는 문자열은  null 일수 없습니다.");
        String tmpStr = deleteWhitespace(str);
        return (getLength(tmpStr) <= 0 ? true : false);
    }*/

    /**
     * 인자가 <code>null</code>이거나 White Space를 제거한 후 결과가 빈 문자열인지 확인한다.
     *
     * @param str
     *            대상 문자열
     * @return <code>null</code> 또는 빈 문자열인 경우 <code>true</code>, 값이 있는 경우
     *         <code>false</code>를 반환한다.
     * @throws Exception 
     */
    public static boolean isNullOrEmpty(String str) throws Exception {
        return (str == null || isEmpty(str) ? true : false);
    }

    /**
     * 원본 문자열 전체에 대하여 대하여 변경 대상 문자를 변경될 문자로 바꾼다.
     *
     * @param sourceStr
     *            원본 문자열
     * @param targetStr
     *            대상 문자
     * @param replaceStr
     *            변경될 문자
     * @return 변경된 문자열
     */
    public static String replaceAll(String sourceStr, String targetStr,
            String replaceStr) {
        if (null == sourceStr)
            return null;
        if (null == targetStr || null == replaceStr)
            return sourceStr;

        StringBuffer sbuf = new StringBuffer();
        int pos = 0;
        int index = sourceStr.indexOf(targetStr);

        int patLen = targetStr.length();
        while (index >= 0) {
            sbuf.append(sourceStr.substring(pos, index));
            sbuf.append(replaceStr);
            pos = index + patLen;
            index = sourceStr.indexOf(targetStr, pos);
        }
        sbuf.append(sourceStr.substring(pos));

        return sbuf.toString();
    }

    /**
     * 원본 문자열 전체에 대하여 복수개의 변경 대상 문자를 각각 대응하는 변경될 문자로 바꾼다.
     *
     * @param sourceStr
     *            원본 문자열
     * @param targetStr
     *            대상 문자 배열
     * @param replaceStr
     *            변경될 문자 배열
     * @return 변경된 문자열
     * @throws Exception 
     */
    public static String replaceAllArr(String sourceStr, String[] targetStr,
            String[] replaceStr) throws Exception {
        AssertUtil.isEqualArrLength(targetStr, replaceStr);

        int tCnt = targetStr.length;
        String tmpStr = sourceStr;

        for (int i = 0; i < tCnt; i++) {
            tmpStr = replaceAll(tmpStr, targetStr[i], replaceStr[i]);
        }

        return tmpStr;
    }

    /**
     * 스트링을 지정한 길이만큼 자른 뒤 지정한 문자열을 추가한다.
     *
     * @param str
     *            대상 문자열
     * @param limit
     *            자를 길이의 최대값
     * @param postFix
     *            뒤에 추가할 문자열
     * @return 지정한 길이만큼 잘려진 문자열
     */
    public static String setMaxLength(String str, int limit, String postFix) {

        if (str == null)
            return "";

        if (limit <= 0)
            return str;

        byte[] strbyte = str.getBytes();

        if (strbyte.length <= limit) {
            return str;
        }

        char[] charArray = str.toCharArray();

        int checkLimit = limit;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] < 256) {
                checkLimit -= 1;
            }
            else {
                checkLimit -= 2;
            }

            if (checkLimit <= 0) {
                break;
            }
        }

        // 대상 문자열 마지막 자리가 2바이트의 중간일 경우 제거함
        byte[] newByte = new byte[limit + checkLimit];

        for (int i = 0; i < newByte.length; i++) {
            newByte[i] = strbyte[i];
        }

        if (postFix == null)
            return new String(newByte);
        else
            return new String(newByte) + postFix;
    }

    /**
     * 스트링을 지정한 길이만큼 자른다.
     * <p/>
     * 2Byte 문자열 처리로직 추가 (fixed 2006-08-22)
     *
     * @param str
     *            대상 문자열
     * @param limit
     *            자를 길이의 최대값
     * @return 지정한 길이만큼 잘려진 문자열
     */
    public static String setMaxLength(String str, int limit) {
        return setMaxLength(str, limit, null);
    }

    /**
     * 스트링을 지정한 길이만큼 자른다.
     *
     * @param str
     *            대상 문자열
     * @param limit
     *            자를 길이의 최대값
     * @return 지정한 길이만큼 잘려진 문자열
     */
    public static String fixLength(String str, int limit) {
        return setMaxLength(str, limit);
    }

    /**
     * 스트링을 지정한 길이만큼 자른 뒤 지정한 문자열을 추가한다.
     *
     * @param str
     *            대상 문자열
     * @param limit
     *            자를 길이의 최대값
     * @param postFix
     *            뒤에 추가할 문자열
     * @return 지정한 길이만큼 잘려진 문자열
     */
    public static String fixLength(String str, int limit, String postFix) {
        return setMaxLength(str, limit, postFix);
    }

    /**
     * 문자열을 지정한 패턴에 따라 특정 문자열로 대체한다.<br />
     * <code>StringUtil.replace(String, String, String)</code> 함수에 비해 약 20% 정도
     * 빠르다.
     *
     * @param s
     *            대상 문자열
     * @param sub
     *            대체 대상 문자열 패턴
     * @param with
     *            대체 문자열
     * @return 대체된 문자열
     */
    public static String fastReplace(String s, String sub, String with) {
        if ((s == null) || (sub == null) || (with == null)) {
            return s;
        }
        int c = 0;
        int i = s.indexOf(sub, c);
        if (i == -1) {
            return s;
        }
        StringBuffer buf = new StringBuffer(s.length() + with.length());
        do {
            buf.append(s.substring(c, i));
            buf.append(with);
            c = i + sub.length();
        }
        while ((i = s.indexOf(sub, c)) != -1);
        if (c < s.length()) {
            buf.append(s.substring(c, s.length()));
        }
        return buf.toString();
    }

    /**
     *
     * @param src
     * @param delimeter
     * @return split 결과를 배열로 반환
     */
    public static String[] fastSplit(String src, String delimeter) {
        if (src == null)
            return null;
        if (delimeter == null)
            return (new String[] { src });
        int maxparts = src.length() / delimeter.length() + 2;
        int positions[] = new int[maxparts];
        int dellen = delimeter.length();
        int i = 0;
        int j = 0;
        int count = 0;
        positions[0] = -dellen;
        while ((i = src.indexOf(delimeter, j)) != -1) {
            count++;
            positions[count] = i;
            j = i + dellen;
        }
        count++;
        positions[count] = src.length();
        String result[] = new String[count];
        for (i = 0; i < count; i++)
            result[i] = src.substring(positions[i] + dellen, positions[i + 1]);

        return result;
    }


    /**
	 * HTML 문법의 코드를 치환한다. QuoteHTMLEncoding
     * 치환 대상 : & , <, >, ", '
     * 예 : & > &amp;
     * @param str 대상 문자열
     * @return 치환된 문자열
	 */
	public static String replaceHtmlEscape(String str) {

		if("".equals(str) || str == null) {
			return "";
		}

	    StringBuffer stringbuffer = new StringBuffer();
	    char ac[] = str.toCharArray();
	    int i = ac.length;
	    for(int j = 0; j < i; j++) {
	    	if(ac[j] == '&')
	    		stringbuffer.append("&amp;");
	    	else if(ac[j] == '<')
	            stringbuffer.append("&lt;");
	        else if(ac[j] == '>')
	            stringbuffer.append("&gt;");
	        else if(ac[j] == '"')
	            stringbuffer.append("&quot;");
	        else if(ac[j] == '\'')
	            stringbuffer.append("&#039;");
	        else
	            stringbuffer.append(ac[j]);
	    }

	    return stringbuffer.toString();
    }
	/**
     * <p>기준 문자열에 포함된 모든 대상 문자(char)를 제거한다.</p>
     *
     * <pre>
     * StringUtil.remove(null, *)       = null
     * StringUtil.remove("", *)         = ""
     * StringUtil.remove("queued", 'u') = "qeed"
     * StringUtil.remove("queued", 'z') = "queued"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @param remove  입력받는 문자열에서 제거할 대상 문자열
     * @return 제거대상 문자열이 제거된 입력문자열. 입력문자열이 null인 경우 출력문자열은 null
	 * @throws Exception 
     */
    /*public static String remove(String str, char remove) throws Exception {
        if (isEmpty(str) || str.indexOf(remove) == -1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != remove) {
                chars[pos++] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }*/
    /**
     * <p>문자열 내부의 마이너스 character(-)를 모두 제거한다.</p>
     *
     * <pre>
     * StringUtil.removeMinusChar(null)       = null
     * StringUtil.removeMinusChar("")         = ""
     * StringUtil.removeMinusChar("a-sdfg-qweqe") = "asdfgqweqe"
     * </pre>
     *
     * @param str  입력받는 기준 문자열
     * @return " - "가 제거된 입력문자열
     *  입력문자열이 null인 경우 출력문자열은 null
     * @throws Exception 
     */
    public static String removeMinusChar(String str) throws Exception {
        return remove(str, '-');
    }
    /** 
     * 랜덤한 문자열을 원하는 길이만큼 반환합니다. 
     *  
     * @param length 문자열 길이 
     * @return 랜덤문자열 
     */ 
    public static String getRandomString(int length) 
    { 
      StringBuffer buffer = new StringBuffer(); 
      Random random = new Random(); 
      
      String chars[] =  
        "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,1,2,3,4,5,6,7,8,9,0".split(","); 
      
      for (int i=0 ; i<length ; i++) 
      { 
        buffer.append(chars[random.nextInt(chars.length)]); 
      } 
      return buffer.toString(); 
    }
    
    /** 
     * 랜덤한 숫자를 원하는 길이만큼 반환합니다. 
     *  
     * @param length 문자열 길이 
     * @return 랜덤문자열 
     */ 
    public static String getRandomNumber(int length) 
    { 
    	StringBuffer buffer = new StringBuffer(); 
    	Random random = new Random(); 
    	
    	String chars[] =  
    			"1,2,3,4,5,6,7,8,9,0".split(","); 
    	
    	for (int i=0 ; i<length ; i++) 
    	{ 
    		buffer.append(chars[random.nextInt(chars.length)]); 
    	} 
    	return buffer.toString(); 
    }
    /** 
     * 번화번호를 배열로 리턴한다.
     *  
     * @param sNum 원본 전화번호
     * @return 번호1,번호2,번호3으로 리턴 
     * @throws Exception 
     */ 
    public static String[] getTelSlice(String str) throws Exception 
    { 
    	String [] retTel= {"","",""};
    	
    	if(isNullOrEmpty(str)){
			return retTel;
		}
		else
		{
			//공백과 "-" 를 제거
			str=deleteWhitespace(removeMinusChar(str));
		}

		String p1="";
		String p2="";
		String p3="";
		int len = str.length();
		if(str.length()>7){//최소자리수는 8 이어야함
			if(str.substring(0,2).equals("02"))
			{
				
				if(str.length()==9) // 02-123-4567 -> 021234567 -> 9자리 일때
				{
					p1 = str.substring(0,2);
					p2 = str.substring(2, len-4);
					p3 = str.substring(len-4,len);
				}else{				// 02-1234-5678 -> 0212345678-> 10자리 일때
					p1 = str.substring(0,2);
					p2 = str.substring(2, len-4);
					p3 = str.substring(len-4,len);
				}
			}
			else
			{							//지역번호가 3자리 일때 +핸드폰번호도 포함
				if(str.length()==10) // 032-123-4567 -> 0321234567 -> 10자리 일때
				{
					p1 = str.substring(0,3);
					p2 = str.substring(3, len-4);
					p3 = str.substring(len-4,len);
				}else{					// 032-1234-5678 -> 03212345678-> 11자리 일때
					p1 = str.substring(0,3);
					p2 = str.substring(3, len-4);
					p3 = str.substring(len-4,len);
				}
	
			}
				retTel[0]=p1;
				retTel[1]=p2;
				retTel[2]=p3;
		 }else{
			 return retTel;
		 }
        return retTel;
    }
    
     /** 
     * 전화번호를 더해서 표준 형태로 리턴한다.
     *  
     * @param 번호1,번호2 원본 우편번호 separator구분자
     * @return sNum 으로 리턴 
     * @throws Exception 
     */ 
    public static String getTelSum(String str1,String str2,String str3,String separator) throws Exception 
    { 
    	String  retTel="";
    	//번호가 3개 다 존재하여야함.
    	if(isNullOrEmpty(str1)||isNullOrEmpty(str2)||isNullOrEmpty(str3)){
			return retTel;
		}
		else
		{
			//공백과 "-" 를 제거
			str1=deleteWhitespace(removeMinusChar(str1));
			str2=deleteWhitespace(removeMinusChar(str2));
			str3=deleteWhitespace(removeMinusChar(str3));
		}
    	
		if((str1.length()>1)&&(str2.length()>2)&&(str3.length()>3)){
		 
			 return str1+separator+str2+separator+str3;
		 }
        return retTel;
    }
    
    /** 
     * 우편번호를 배열로 리턴한다.
     *  
     * @param sNum 원본 우편번호
     * @return 번호1,번호2 으로 리턴 
     * @throws Exception 
     */ 
    public static String[] getPostSlice(String str) throws Exception 
    { 
    	String [] retTel= {"",""};
    	
    	if(isNullOrEmpty(str)){
			return retTel;
		}
		else
		{
			//공백과 "-" 를 제거
			str=deleteWhitespace(removeMinusChar(str));
		}

		String p1="";
		String p2="";
		int len = str.length();
		if(str.length()>5){//최소자리수는 6 이어야함
			
				p1 = str.substring(0,3);
				p2 = str.substring(3, len);
				retTel[0]=p1;
				retTel[1]=p2;
		 }else{
			 return retTel;
		 }
        return retTel;
    }
    
    /** 
     * 우편번호를 더해서 표준 형태로 리턴한다.
     *  
     * @param 번호1,번호2 원본 우편번호 separator구분자
     * @return sNum 으로 리턴 
     * @throws Exception 
     */ 
    public static String getPostSum(String str1,String str2,String separator) throws Exception 
    { 
    	String  retTel="";
    	//번호가 3개 다 존재하여야함.
    	if(str1.equals("")||str2.equals("")){
			return retTel;
		}
		else
		{
			//공백과 "-" 를 제거
			str1=deleteWhitespace(removeMinusChar(str1));
			str2=deleteWhitespace(removeMinusChar(str2));
		}
    	
		if((str1.length()>1)&&(str2.length()>2)){
		 
			 return str1+separator+str2;
		 }
        return retTel;
    }
    /**
     * 금액표시타입 으로 변환한다.  
     * (예) 12345678  --> 12,345,678          
     *  
     * @param    moneyString 금액 (String형).
     * @return   변경된 금액 문자열.
     * @throws Exception 
     */
    public static String makeMoneyType(String dblMoneyString) throws Exception
    {
    	if(isNullOrEmpty(dblMoneyString)){
    		return "0";
    	}
            String format = "#,##0";
            DecimalFormat df = new DecimalFormat(format);
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();

            dfs.setGroupingSeparator(',');// 구분자를 ,로
            df.setGroupingSize(3);//3자리 단위마다 구분자처리 한다.
            df.setDecimalFormatSymbols(dfs);

            return (df.format(Double.parseDouble(dblMoneyString))).toString();
    } 
    /**
     * 주민번호 암호화
     * @param resident
     * @return
     */
    public static String encCoder(String resident){
    	
    	BASE64Encoder en = new  BASE64Encoder();  	
    	resident = en.encodeBuffer(resident.getBytes());
    	return resident;
    }
    /**
     * 주민번호 복호화
     * @param resident
     * @return
     */
    public static String decCoder(String resident){
    	
    	byte[] dec = new byte[10];
    	try{
    	
    		dec = new BASE64Decoder().decodeBuffer(resident);
    		resident = new String(dec);
    	}catch(IOException e){
    		
    		e.printStackTrace();
    	}  	
    	return resident;
    }

    /**
     * 32글자의 랜덤한 문자열(숫자포함)을 만들어서 반환
     * @return
     */
    public static String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    public static String nullToZero(String value){
		;
	   if(value == null || value.equals("")){
	    value   =   "0";
	   }
	   return value;
	  }

    /**
     * 문자열 좌측의 공백을 제거하는 메소드
    * @param  str 대상 문자열
    * @return trimed string with white space removed from the front.
     */
    public static String ltrim(String str){
     int len = str.length();
     int idx = 0;

    while ((idx < len) && (str.charAt(idx) <= ' '))
     {
      idx++;
     }
     return str.substring(idx, len);
    }

   /**
     * 문자열 우측의 공백을 제거하는 메소드
    * @param  str 대상 문자열
    * @return trimed string with white space removed from the end.
     */
    public static String rtrim(String str){
     int len = str.length();

    while ((0 < len) && (str.charAt(len-1) <= ' '))
     {
      len--;
     }
     return str.substring(0, len);
    }


   /**
     * String을
    * @param str
     * @return
     */
    public static String changeMoney(String str) {
     DecimalFormat df = new DecimalFormat("###,###");

    return df.format(parseInt(str));
    }

   /**
     * 파라미터로 넘어오는 String을 , 를 제거해준다.
     *
     * @param s java.lang.String
     * @return java.lang.String
     */
    public static String removeComma(String str) {
     String rtnValue = str;
     if ( isNull(str) ) {
      return "";
     }

    rtnValue = replace(rtnValue, ",", "");
     return rtnValue;
    }

   /**
     * 숫자 0이 넘어오면 ""로 대치
    * @param  int 대상 숫자
    * @return java.lang.String
     */
    public static String isOneNull(int num){
     if (num == 0) return "";
     else return Integer.toString(num);
    }
    
    /**
     * str이 null 이거나 "", "    " 일경우 return true
     * @param str
     * @return
     */
    public static boolean isNull(String str) {

    return (str == null || (str.trim().length()) == 0 );
    }

   public static boolean isNull(Object obj) {
     String str = null;
     if( obj instanceof String ) {
      str = (String)obj;
     } else {
      return true;
     }

    return isNull(str);
    }

   
    /**
     * null이 아닐때.
     * @param str
     * @return
     */
    public static boolean isNotNull(String str) {
     /**
      * isNull이 true이면 false
      * false이면 true
      */
     if( isNull(str) ){
      return false;

    } else {
      return true;
     }
    }

   /***
     * 널체크
    * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj) {
     String str = null;
     if( obj instanceof String ) {
      str = (String)obj;
     } else {
      return false;
     }

    return isNotNull(str);
    }

   /**
     * 파라미터가 null 이거나 공백이 있을경우
    * "" 로 return
     * @param value
     * @return
     */
    public static String replaceNull(String value) {
     return replaceNull(value, "");
    }

   /**
     * Object를 받아서 String 형이 아니거나 NULL이면 ""를 return
     * String 형이면 형 변환해서 넘겨준다.
     * @param value
     * @return
     */
    public static String replaceNull(Object value) {
     Object rtnValue = value;
     if( rtnValue == null || !"java.lang.String".equals(rtnValue.getClass().getName())) {
      rtnValue = "";
     }

    return replaceNull((String)rtnValue, "");
    }

   /**
     * 파라미터로 넘어온 값이 null 이거나 공백이 포함된 문자라면
    * defaultValue를 return
     * 아니면 값을 trim해서 넘겨준다.
     * @param value
     * @param repStr
     * @return
     */
    public static String replaceNull(String value, String defaultValue) {
     if (isNull(value)) {
      return defaultValue;
     }

    return value.trim();
    }

   /**
     * Object를 받아서 String 형이 아니거나 NULL이면 defaultValue를 return
     * String 형이면 형 변환해서 넘겨준다.
     * @param value
     * @param repStr
     * @return
     */
    public static String replaceNull(Object value, String defaultValue) {
     String valueStr = replaceNull(value);
     if ( isNull(valueStr) ) {
      return defaultValue;
     }

    return valueStr.trim();
    }

   /**
     * Method ksc2asc.
     * 8859-1를 euc-kr로 인코딩하는 함수
    * @param str - String
     * @return String
     */
    public static String ksc2asc(String str) {
     String result = "";

    if (isNull(str)) {
      result = "";
     } else {
      try {
       result = new String( str.getBytes("euc-kr"), "8859_1" );
      } catch( Exception e ) {
       result = "";
      }
     }

    return result;
    }

   /**
     * Method asc2ksc.
     * euc-kr을 8859-1로 인코딩하는 함수
    * @param str - String
     * @return String
     */
    public static String asc2ksc(String str) {
     String result = "";

    if (isNull(str)) {
      result = "";
     } else {
      try {
       result = new String( str.getBytes("8859_1"), "euc-kr" );
      } catch( Exception e ) {
       result = "";
      }
     }

    return result;
    }

   /**************************************************************************************/
    /* parse method start */


   /**
     * String을 int형으로
    * @param value
     * @return
     */
    public static int parseInt(String value) {
     return parseInt(value, 0);
    }
    /**
     * Object를 int형으로
    * defaultValue는 0이다.
     * @param value
     * @return
     */
    public static int parseInt(Object value) {
     String valueStr = replaceNull(value);
     return parseInt(valueStr, 0);
    }
    /**
     * Object를 int형으로
    * Object가 null이면 defaultValue return
     * @param value
     * @param defaultValue
     * @return
     */
    public static int parseInt(Object value, int defaultValue) {
     String valueStr = replaceNull(value);
     return parseInt(valueStr, defaultValue);
    }
    /**
     * String을 int형으로
    * String이 숫자 형식이 아니면 defaultValue return
     * @param value
     * @param defaultValue
     * @return
     */
    public static int parseInt(String value, int defaultValue) {
     int returnValue = 0;

    if( isNull(value) ) {
      returnValue = defaultValue;
     } else if( !isNumeric(value) ) {
      returnValue = defaultValue;
     } else {
      returnValue = Integer.parseInt(value);
     }

    return returnValue;
    }

   /**
     * String을 long형으로
    * defaultValue는 0이다.
     * @param value
     * @return
     */
    public static long parseLong(String value) {
     return parseLong(value, 0);
    }

   /**
     * String을 long형으로
    * 잘못된 데이타 일시 return은 defaultValue
     * @param value
     * @return
     */
    public static long parseLong(String value, long defaultValue) {
     long returnValue = 0;

    if( isNull(value) ) {
      returnValue = defaultValue;
     } else if( !isNumeric(value) ) {
      returnValue = defaultValue;
     } else {
      returnValue = Long.parseLong(value);
     }

    return returnValue;
    }

   /**
     * Exception을 String으로 뽑아준다.
     * @param ex
     * @return
     */
    public static String stackTraceToString(Throwable e) {
     try {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      return "------\r\n" + sw.toString() + "------\r\n";
      }catch(Exception e2) {
       return StringUtil.stackTraceToString2(e);
      }
    }
    /**
     * Exception을 String으로 뽑아준다.
     * @param ex
     * @return
     */
    public static String stackTraceToString2(Throwable e) {
     ByteArrayOutputStream b = new ByteArrayOutputStream();
     PrintStream p = new PrintStream(b);
     e.printStackTrace(p);
     p.close();
     String stackTrace = b.toString();
     try {
      b.close();
     } catch (IOException ex) {
      ex.printStackTrace();
     }

  //  return convertHtmlBr(stackTrace);
     return stackTrace;
    }

   /**
     * Html 코드에서 &#60;br&#62; 태크 제거
    * @param comment
     * @return
     */
    public static String convertHtmlBr(String comment) {
     String rtnValue = "";
     if( isNull(comment) ) {
      return "";
     }

    rtnValue = replace(comment, "\r\n", "<br>");

    return rtnValue;
    }


   /**
     * String 배열을 List로 변환한다.
     * @param values
     * @return
     */
    public static List changeList(String [] values) {
     List list = new ArrayList();

    if( values == null ) {
      return list;
     }
     for(int i=0,n=values.length; i<n; i++) {
      list.add(values[i]);
     }

    return list;
    }


   public static String[] toTokenArray(String str, String sep){

    String[] temp = null;

    try{
      StringTokenizer st = new StringTokenizer(str, sep);
      temp = new String[st.countTokens()];
      int index = 0;
      while(st.hasMoreTokens()){
       temp[index++] = st.nextToken();
      }
     }catch(Exception e){
      e.printStackTrace();
     }

    return temp;
    }
       public static String strip(String str, String str1){

       if(str == null || "".equals(str.trim())) return "";

       String temp = str;
     int pos = -1;
     while((pos = temp.indexOf(str1, pos)) != -1) {
      String left = temp.substring(0, pos);
      String right = temp.substring(pos + 1, temp.length());
      temp = left + "" + right;
      pos += 1;
     }
     return temp;
       }

      /**
     * Method ksc2asc.
     * euc-kr을 euc-kr로 인코딩하는 함수
    * @param str - String
     * @return String
     */
    public static String ksc2utf8(String str) {
     String result = "";

    if (isNull(str)) {
      result = "";
     } else {
      try {
       result = new String( str.getBytes("euc-kr"), "utf-8" );
      } catch( Exception e ) {
       result = "";
      }
     }

    return result;
    }

   /**
     * string에 있는 ', ", \r\n 를 HTML 코드로 변환한다.
     * @param str
     * @return
     */
    public static String changeQuotation(String str) {
     String rtnValue = str;
     rtnValue = replaceNull(rtnValue);
     rtnValue = replace(replace(replace(rtnValue, "'", "&#39;"), "\"", "&#34;"), "\r\n", "<br>");

    return rtnValue;
    }
    public static String changeQuotation(Object obj) {
     if( isStringInteger(obj) ) {
      return changeQuotation(String.valueOf(obj));
     }

    return "";
    }

   /**
     * 해당 Object가 String or Integer 이면 true
     * 아니면 false
     * @param obj
     * @return
     */
    public static boolean isStringInteger(Object obj) {

    boolean flag = false;

    if( obj instanceof String || obj instanceof Integer ) {
      flag = true;
     }

    return flag;
    }

   /**
     * 백분율을 구한다.
     * %는 빼고 값만 리턴
    * @param value
     * @param total
     * @return
     */
    public static String percentValue(int value, int total) {
     double val = Double.parseDouble(String.valueOf(value)) / Double.parseDouble(String.valueOf(total)) * 100;

    DecimalFormat df = new DecimalFormat("##0.0");
     return df.format(val);
    }

   


   /**
     *  XSS(Cross Site Scripting) 취약점 해결을 위한 처리
    *
     * @param sourceString String 원본문자열
    * @return String 변환문자열
    */
    public static String replaceXSS(String sourceString){
     String rtnValue = null;
     if(sourceString!=null){
      rtnValue = sourceString;
      if(rtnValue.indexOf("<x-") == -1){
       rtnValue = rtnValue.replaceAll("< *(j|J)(a|A)(v|V)(a|A)(s|S)(c|C)(r|R)(i|I)(p|P)(t|T)", "<x-javascript");
       rtnValue = rtnValue.replaceAll("< *(v|V)(b|B)(s|S)(c|C)(r|R)(i|I)(p|P)(t|T)", "<x-vbscript");
       rtnValue = rtnValue.replaceAll("< *(s|S)(c|C)(r|R)(i|I)(p|P)(t|T)", "<x-script");
       rtnValue = rtnValue.replaceAll("< *(i|I)(f|F)(r|R)(a|A)(m|M)(e|E)", "<x-iframe");
       rtnValue = rtnValue.replaceAll("< *(f|F)(r|R)(a|A)(m|M)(e|E)", "<x-frame");
       rtnValue = rtnValue.replaceAll("(e|E)(x|X)(p|P)(r|R)(e|E)(s|S)(s|S)(i|I)(o|O)(n|N)", "x-expression");
       rtnValue = rtnValue.replaceAll("(a|A)(l|L)(e|E)(r|R)(t|T)", "x-alert");
       rtnValue = rtnValue.replaceAll(".(o|O)(p|P)(e|E)(n|N)", ".x-open");
       rtnValue = rtnValue.replaceAll("< *(m|M)(a|A)(r|R)(q|Q)(u|U)(e|E)(e|E)", "<x-marquee");
       rtnValue = rtnValue.replaceAll("&#", "&amp;#");
      }
     }

    return rtnValue;
       }


   /**
     * 특정문자를 HTML TAG형식으로 변경하는 메소드.
     *
     * <xmp>
     * & --> &amp;
     * < --> &lt;
     * > --> &gt;
     * " --> &quot;
     * ' --> &#039;
     *-----------------------------------------------------------------
     * <option type=radio  name=r value="xxxxxxxx"> yyyyyyy
     * <input  type=hidden name=h value="xxxxxxxx">
     * <input  type=text   name=t value="xxxxxxxx">
     * <textarea name=msg rows=20 cols=53>xxxxxxx</textarea>
     *-
     * 위와 같은 HTML 소스를 생성할 때, xxxxxxx 부분의 문자열 중에서
    * 아래에 있는 몇가지 특별한 문자들을 변환하여야 합니다.
     * 만약 JSP 라면 미리 변환하여 HTML 전체 TAG를 만들거나, 혹은 아래처럼 사용하세요.
     *-
    * <option type=radio  name=r value="<%= StringUtil.translate(s) %>"> yyyyyyy
     * <input  type=hidden name=n value="<%= StringUtil.translate(s) %>">
     * <input  type=text   name=n value="<%= StringUtil.translate(s) %>">
     * <textarea name=body rows=20 cols=53><%= StringUtil.translate(s) %></textarea>
     *-
     * 또 필요하다면 yyyyyyy 부분도  translate(s)를 할 필요가 있을 겁니다.
     * 필요할 때 마다 사용하세요.
     *-
    * </xmp>
     *
     * @return the translated string.
     * @param str java.lang.String
     */
    public static String translate(String str){
     if ( str == null ) return null;

    StringBuffer buf = new StringBuffer();
     char[] c = str.toCharArray();
     int len = c.length;

    for ( int i=0; i < len; i++){
      if      ( c[i] == '&' ) buf.append("&amp;");
      else if ( c[i] == '<' ) buf.append("&lt;");
      else if ( c[i] == '>' ) buf.append("&gt;");
      else if ( c[i] == '"' ) buf.append("&quot;"); // (char)34
      else if ( c[i] == '\'') buf.append("&#039;"); // (char)39
      else buf.append(c[i]);
     }
     return buf.toString();
    }

     /**
       * String 앞 또는 뒤를 특정문자로 지정한 길이만큼 채워주는 함수    <BR>
       * (예) pad("1234","0", 6, 1) --> "123400"   <BR>
       *
       * @param    src  Source string
       * @param    pad  pad string
       * @param    totLen     total length
       * @param    mode     앞/뒤 구분 (-1:front, 1:back)
       * @return   String
       */
    public static String pad(String src, String pad, int totLen, int mode){
     String paddedString = "";

    if(src == null) return "";
     int srcLen = src.length();

    if((totLen<1)||(srcLen>=totLen)) return src;

    for(int i=0; i< (totLen-srcLen); i++){
      paddedString += pad;
     }

    if(mode == -1)
      paddedString += src; // front padding
     else
          paddedString = src + paddedString; //back padding

    return paddedString;
    }

   /**
     * 주어진 길이(iLength)만큼 주어진 문자(cPadder)를 strSource의 왼쪽에 붙혀서 보내준다.
     * ex) lpad("abc", 5, '^') ==> "^^abc"
     *     lpad("abcdefghi", 5, '^') ==> "abcde"
     *     lpad(null, 5, '^') ==> "^^^^^"
     *
     * @param strSource
     * @param iLength
     * @param cPadder
     */
    public static String lpad(String strSource, int iLength, char cPadder){
     StringBuffer sbBuffer = null;

    if (!isEmpty(strSource)){
      int iByteSize = getByteSize(strSource);
      if (iByteSize > iLength){
       return strSource.substring(0, iLength);
      }else if (iByteSize == iLength){
       return strSource;
      }else{
       int iPadLength = iLength - iByteSize;
       sbBuffer = new StringBuffer();
       for (int j = 0; j < iPadLength; j++){
        sbBuffer.append(cPadder);
       }
       sbBuffer.append(strSource);
       return sbBuffer.toString();
      }
     }

    //int iPadLength = iLength;
     sbBuffer = new StringBuffer();
     for (int j = 0; j < iLength; j++){
      sbBuffer.append(cPadder);
     }
     return sbBuffer.toString();
    }

   /**
     * 주어진 길이(iLength)만큼 주어진 문자(cPadder)를 strSource의 오른쪽에 붙혀서 보내준다.
     * ex) lpad("abc", 5, '^') ==> "abc^^"
     *     lpad("abcdefghi", 5, '^') ==> "abcde"
     *     lpad(null, 5, '^') ==> "^^^^^"
     *
     * @param strSource
     * @param iLength
     * @param cPadder
     */
    public static String rpad(String strSource, int iLength, char cPadder){
     StringBuffer sbBuffer = null;
      if (!isEmpty(strSource)){
      int iByteSize = getByteSize(strSource);
      if (iByteSize > iLength){
       return strSource.substring(0, iLength);
      }else if (iByteSize == iLength){
       return strSource;
      }else{
       int iPadLength = iLength - iByteSize;
       sbBuffer = new StringBuffer(strSource);
       for (int j = 0; j < iPadLength; j++){
        sbBuffer.append(cPadder);
       }
       return sbBuffer.toString();
      }
     }
     sbBuffer = new StringBuffer();
     for (int j = 0; j < iLength; j++){
      sbBuffer.append(cPadder);
     }
     return sbBuffer.toString();
    }

   /**
     *  byte size를 가져온다.
     *
     * @param str String target
     * @return int bytelength
     */
    public static int getByteSize(String str){
     if (str == null || str.length() == 0)
      return 0;
     byte[] byteArray = null;
      try{
      byteArray = str.getBytes("UTF-8");
     }catch (UnsupportedEncodingException ex){}
     if (byteArray == null) return 0;
     return byteArray.length;
    }
     /**
    * 긴 문자열 자르기
   * @param srcString      대상문자열
   * @param nLength        길이
   * @param isNoTag        테그 제거 여부
   * @param isAddDot        "..."을추가 여부
   * @return
    */
   public static String strCut(String srcString, int nLength, boolean isNoTag, boolean isAddDot){  // 문자열 자르기
      String rtnVal = srcString;
       int oF = 0, oL = 0, rF = 0, rL = 0;
       int nLengthPrev = 0;

      // 태그 제거
      if(isNoTag) {
           Pattern p = Pattern.compile("<(/?)([^<>]*)?>", Pattern.CASE_INSENSITIVE);  // 태그제거 패턴
          rtnVal = p.matcher(rtnVal).replaceAll("");
       }
       rtnVal = rtnVal.replaceAll("&amp;", "&");
       rtnVal = rtnVal.replaceAll("(!/|\r|\n|&nbsp;)", "");  // 공백제거
      try {
        byte[] bytes = rtnVal.getBytes("UTF-8");     // 바이트로 보관
       // x부터 y길이만큼 잘라낸다. 한글안깨지게.
        int j = 0;
        if(nLengthPrev > 0) while(j < bytes.length) {
         if((bytes[j] & 0x80) != 0) {
          oF+=2; rF+=3; if(oF+2 > nLengthPrev) {break;} j+=3;
            } else {if(oF+1 > nLengthPrev) {break;} ++oF; ++rF; ++j;}
        }

       j = rF;

       while(j < bytes.length) {
         if((bytes[j] & 0x80) != 0) {
          if(oL+2 > nLength) {break;} oL+=2; rL+=3; j+=3;
            } else {if(oL+1 > nLength) {break;} ++oL; ++rL; ++j;}
        }

       rtnVal = new String(bytes, rF, rL, "UTF-8");  // charset 옵션

       if(isAddDot && rF+rL+3 <= bytes.length) {rtnVal+="...";}  // ...을 붙일지말지 옵션
      } catch(UnsupportedEncodingException e){
        e.printStackTrace();
        return srcString;
       }

   return rtnVal;
   }
   
   /**
    * total과 success 로 % 구하고 소수점 1자리까지 계산
   * @param int success      
    * @param int total       

   * @return String %
    */
   public static String calculatePercent(int success,int total){ 
    String result   =  "0";
    
    if(total == 0){
      
     
    }else{
     
       Double tempSuccess  = new Double(success+".0");
       Double tempTotal    = new Double(total+".0");
       Double tempPercent   = new Double(100+".0");
     
       double cal =  tempSuccess.doubleValue()*tempPercent.doubleValue()/tempTotal.doubleValue();
     
     result = new java.text.DecimalFormat("#.#").format(cal);
     
    }
    
    return result;
   }
   
   
   private static final String ALGO = "AES";
   //generate 128bit key
   private static final String keyStr = "Z8LSq0wWwB5v+6YJzurcP463H3F12iZh74fDj4S74oUH4EONkiKb2FmiWUbtFh97GG/c/lbDE47mvw6j94yXxKHOpoqu6zpLKMKPcOoSppcVWb2q34qENBJkudXUh4MWcreondLmLL2UyydtFKuU9Sa5VgY/CzGaVGJABK2ZR94=";

   private static Key generateKey() throws Exception {
       byte[] keyValue = keyStr.getBytes("UTF-8");
       MessageDigest sha = MessageDigest.getInstance("SHA-1");
       keyValue = sha.digest(keyValue);
       keyValue = Arrays.copyOf(keyValue, 16); // use only first 128 bit       
       Key key = new SecretKeySpec(keyValue, ALGO);
       return key;
   }

   public static String encrypt(String Data) throws Exception {
           Key key = generateKey();
           Cipher c = Cipher.getInstance(ALGO);
           c.init(Cipher.ENCRYPT_MODE, key);
           byte[] encVal = c.doFinal(Data.getBytes());
           String encryptedValue = DatatypeConverter.printBase64Binary(encVal);
           return encryptedValue;
   }

   public static String decrypt(String encryptedData) throws Exception {
       Key key = generateKey();
       Cipher c = Cipher.getInstance(ALGO);
       c.init(Cipher.DECRYPT_MODE, key);       
       byte[] decordedValue = DatatypeConverter.parseBase64Binary(encryptedData);
       byte[] decValue = c.doFinal(decordedValue);
       String decryptedValue = new String(decValue);
       return decryptedValue;
   }

   
   public static String addJWT(String mediaKey, String userId) throws JsonGenerationException, JsonMappingException, IOException {
	   String token = "";
	   String cuid = userId;
	   
	   final JWTSigner signer = new JWTSigner("entostudy");
	   final HashMap<String, Object> claims = new HashMap<String, Object>();
		
        HashMap<String,Object> mc = new HashMap<String,Object>();
		mc.put("mckey", mediaKey.trim());
		//현재시간 기준 30초이상
		long iat = System.currentTimeMillis() / 1000L;
		
		long exp = iat + 1000L;
		
		ArrayList list = new ArrayList();
		list.add(0,mc);
		
		claims.put("cuid", cuid);
		claims.put("expt", exp);
		claims.put("mc", list);
	   
		token = signer.sign(claims);
	   
	    return token;
   }
      
   /**
	 * rquest parameters
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String parameters(HttpServletRequest request) throws Exception{
		Enumeration param = request.getParameterNames();
		String strParam = ""; 
		while(param.hasMoreElements()) { 
		    String name = (String)param.nextElement(); 
		    String value = request.getParameter(name); 
		    strParam += name + "=" + value + "&"; 
		}
		
       return strParam;
	}
	
}
