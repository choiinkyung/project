package egovframework.MNG.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * HTML 소스 코드 컨트롤 관련 유틸리티 클레스
 * </p>
 *
 * @author
 * @date 2009. 10. 7. $Id: $
 */
public class HtmlUtil {
    private static String HTML_BR = "<br />";
    //private static String XHTML_BR = "<br />";
    private static char LINE_FEED = '\n';

    //private static String DOC_TYPE = "xhtml";

    /**
     * 환경설정의 html/xhtml 사용여부에 따라서 줄바꿈 특수 문자를 <code>&lt;br&gt;</code>
     * 또는 <code>&lt;br /&gt;</code>로 치환한다.
     *
     * @param str 치환대상 문자열
     * @return 치환된 문자열
     * @throws Exception 
     */
    public static String nlToBr(String str) throws Exception {

        return nlTo(str, HTML_BR);
    }

    /**
     * 줄바꿈 특수문자를 공백으로 치환.
     *
     * @param str 치환대상 문자열
     * @return 치환된 문자열
     * @throws Exception 
     */
    public static String nlToEmpty(String str) throws Exception {

        return nlTo(str, "");
    }

    /**
     * 줄바꿈 특수문자를 주어진 문자열로 치환.
     *
     * @param str 치환대상 문자열
     * @param str2 치환 문자열
     * @return 치환된 문자열
     * @throws Exception 
     */
    public static String nlTo(String str, String str2) throws Exception {

        // config 객체에서 html과 xhtml 여부를 구해  omit tag를 사용한다. <br />
        if(StringUtil.isNullOrEmpty(str)) return "";

        StringBuffer stringbuffer = new StringBuffer();
        char ac[] = str.toCharArray();
        int i = ac.length;
        for(int j = 0; j < i; j++)
            if(ac[j] == LINE_FEED)
                stringbuffer.append(str2);
            else
                stringbuffer.append(ac[j]);

        return stringbuffer.toString();
    }

    /**
     * HTML 문법의 코드를 치환한다.
     * 치환 대상 : & , <, >, ", '
     * 예 : & > &amp;
     * @param str 대상 문자열
     * @return 치환된 문자열
     * @throws Exception 
     */
    public static String htmlEscape(String str) throws Exception {
        if(StringUtil.isNullOrEmpty(str)) return "";

        StringBuffer stringbuffer = new StringBuffer();
        char ac[] = str.toCharArray();
        int i = ac.length;
        for(int j = 0; j < i; j++)
            if(ac[j] == '&')
                stringbuffer.append("&amp;");
            else
            if(ac[j] == '<')
                stringbuffer.append("&lt;");
            else
            if(ac[j] == '>')
                stringbuffer.append("&gt;");
            else
            if(ac[j] == '"')
                stringbuffer.append("&quot;");
            else
            if(ac[j] == '\'')
                stringbuffer.append("&#039;");
            else
                stringbuffer.append(ac[j]);

        return stringbuffer.toString();
    }
    
       
    public static String htmlEscape2(String str) throws Exception {
        if(StringUtil.isNullOrEmpty(str)) return "";

        StringBuffer stringbuffer = new StringBuffer();
        char ac[] = str.toCharArray();
        int i = ac.length;
        for(int j = 0; j < i; j++)
            if(ac[j] == '&')
                stringbuffer.append("&amp;");
            else
            if(ac[j] == '<')
                stringbuffer.append("&lt;");
            else
            if(ac[j] == '>')
                stringbuffer.append("&gt;");
            else
            if(ac[j] == '"')
                stringbuffer.append("&quot;");
            else
            if(ac[j] == '\'')
                stringbuffer.append("&#039;");
            else    
        	if(ac[j] == '[')
            stringbuffer.append("&#91;");
        	else    
    		if(ac[j] == ']')
    			stringbuffer.append("&#93;");
			else    
			if(ac[j] == '(')
				stringbuffer.append("&#40;");
			else    
			if(ac[j] == ')')
				stringbuffer.append("&#41;");
			else    
			if(ac[j] == '{')
				stringbuffer.append("&#123;");
			else    
			if(ac[j] == '}')
				stringbuffer.append("&#125;");
            else
                stringbuffer.append(ac[j]);

        return stringbuffer.toString();
    }

    public static String htmlEscape3(String str) throws Exception {
        if(StringUtil.isNullOrEmpty(str)) return "";

        StringBuffer stringbuffer = new StringBuffer();
        char ac[] = str.toCharArray();
        int i = ac.length;
        for(int j = 0; j < i; j++)
            if(ac[j] == '&')
                stringbuffer.append("");
            else
            if(ac[j] == '<')
                stringbuffer.append("");
            else
            if(ac[j] == '>')
                stringbuffer.append("");
            else
            if(ac[j] == '"')
                stringbuffer.append("");
            else
            if(ac[j] == '\'')
                stringbuffer.append("");
            else    
        	if(ac[j] == '[')
            stringbuffer.append("");
        	else    
    		if(ac[j] == ']')
    			stringbuffer.append("");
			else    
			if(ac[j] == '(')
				stringbuffer.append("");
			else    
			if(ac[j] == ')')
				stringbuffer.append("");
			else    
			if(ac[j] == '{')
				stringbuffer.append("");
			else    
			if(ac[j] == '}')
				stringbuffer.append("");
            else
                stringbuffer.append(ac[j]);

        return stringbuffer.toString();
    }
    
    
    
    /**
     * 태그제거
     *
     * @param str 제거대상 문자열
     * @return 제거된 문자열
     */
    public static String removeTag(String str){
        int lt = str.indexOf("<");
        if ( lt != -1 ) {
            int gt = str.indexOf(">", lt);
            if ( (gt != -1) ) {
                str = str.substring( 0, lt ) + str.substring( gt + 1 );
                str = removeTag(str);
            }
        }
        return str;
    }
    
    public static String removeTag2(String str){
        int lt = str.indexOf("BODY {");
        if ( lt != -1 ) {
            int gt = str.indexOf("}", lt);
            if ( (gt != -1) ) {
                str = str.substring( 0, lt ) + str.substring( gt + 1 );
                str = removeTag2(str);
            }
        }
        return str;
    }
    public static String removeTag3(String str){
        int lt = str.indexOf("P {");
        if ( lt != -1 ) {
            int gt = str.indexOf("}", lt);
            if ( (gt != -1) ) {
                str = str.substring( 0, lt ) + str.substring( gt + 1 );
                str = removeTag3(str);
            }
        }
        return str;
    }
    public static String removeTag4(String str){
        int lt = str.indexOf("LI {");
        if ( lt != -1 ) {
            int gt = str.indexOf("}", lt);
            if ( (gt != -1) ) {
                str = str.substring( 0, lt ) + str.substring( gt + 1 );
                str = removeTag4(str);
            }
        }
        return str;
    }
    public static String removeTag5(String str){
        int lt = str.indexOf("<img");
        if ( lt != -1 ) {
            int gt = str.indexOf("/>", lt);
            if ( (gt != -1) ) {
                str = str.substring( 0, lt ) + str.substring( gt + 1 );
                str = removeTag5(str);
            }
        }
        return str;
    }

    /**
	 * 작은따옴표, 큰따옴표를 지정된 문자열로 변환한다.
	 * @param str
	 * @return
	 */
	public static String QuoteHTMLEncoding(String str ) {
		if(str == null) return "";

		str = HTMLEncoding(str);
		str = str.replaceAll("'","&#039") ;	// 큰 따옴표
		str = str.replaceAll("\"","&quot;") ;	// 큰 따옴표

		return str;

	}


	public static String QuoteOnlyEncoding(String str ) {
		if(str == null) return "";

		str = str.replaceAll("'","&#039") ;	// 큰 따옴표
		str = str.replaceAll("\"","&quot;") ;	// 큰 따옴표

		// 추가
		str = str.replaceAll("&&#35;40;","(");
		str = str.replaceAll("&&#35;41;",")");
		return str;

	}


	/**
	 * html 태그를 지정된 문자열로 치환한다.
	 * @param str
	 * @return
	 */
	public static String HTMLEncoding(String str) {
		if(str == null) return "";
		String rtn = str;
		rtn = rtn.replaceAll("&", "&amp;");	// &
		rtn = rtn.replaceAll("\\<", "&lt;");
		rtn = rtn.replaceAll(">", "&gt;");

		return rtn;
	}

	public static String HTMLDecoding(String str) {
		if(str == null) return "";
		String rtn = str;
		rtn = rtn.replaceAll("&apos;", "\'");
		rtn = rtn.replaceAll("&#039", "'") ;	// 큰 따옴표
		rtn = rtn.replaceAll("&quot;", "\"") ;	// 큰 따옴표
		rtn = rtn.replaceAll("&&#35;40;","(");
		rtn = rtn.replaceAll("&&#35;41;",")");
		rtn = rtn.replaceAll("&amp;&#35;40;","(");
		rtn = rtn.replaceAll("&amp;&#35;41;",")");
		
		rtn = rtn.replaceAll("&amp;", "&");	// &
		rtn = rtn.replaceAll("&gt;", ">");
		rtn = rtn.replaceAll("&lt;", "\\<");
		return rtn;
	}

	/**
	 * 지정된 문자열의 공백을 지정된 문자열로 치환한다.
	 * @param str
	 * @return
	 */
	public static String TextToHtml(String str) {

		if(str == null) return "";
		//str = 	getRemoveScript(str);
		str = 	HTMLEncoding(str);
		str =  	str.replaceAll(" ", "&#160;");	// &
		str =   str.replaceAll("\\t","&#160;&#160;&#160;&#160;");
		str =  	str.replaceAll("\\n", "\\<br \\/>");	// &

		return str;
	}

	/**
	 * 지정된 문자열에 자바스크립트태그와 특수문자를 지정된 문자열로 치환한다.
	 * badTag에 추가할것.....
	 * @param strContent
	 * @return
	 */
	public static String getRemoveScript(String strContent){

		if(strContent == null) return "";

		  //Pattern patternTag = Pattern.compile("\\<(\\/?)(\\w+)*([^<>]*)>");
		  String badTag = "script|object|applet|form|iframe|frame|base|frameset|layer|ilayer";

		  Pattern patternScript=Pattern.compile("(?i)\\<[ ]*("+badTag+")(\\>)?(.*?)("+badTag+")?[ ]*>",Pattern.DOTALL | Pattern.MULTILINE);
		  Pattern patternJava=Pattern.compile("(?i)javascript|vbscript",Pattern.DOTALL | Pattern  .MULTILINE);
		  //Pattern patternScript=Pattern.compile("(?i)\\<[ ]?script",Pattern.DOTALL | Pattern.MULTILINE);

		  //Pattern patternMouseOver=Pattern.compile("(?i)on(.*?)=[\"']?([^>\"']+)[\"']*",Pattern.DOTALL | Pattern.MULTILINE);
		  Pattern patternMouseOver=Pattern.compile("(?i)\\<[^(on[a-z]+)|>]*on[a-z]+[ ]*=[ ]*[\"']{1}([^>\"']+)[\"']+",Pattern.DOTALL | Pattern.MULTILINE);
		  //Pattern patternMouseOut=Pattern.compile("(?i) onmouseout=[\"']?([^>\"']+)[\"']*",Pattern.DOTALL | Pattern.MULTILINE);
		  //Pattern patternMouseClick=Pattern.compile("(?i) onclick=[\"']?([^>\"']+)[\"']*",Pattern.DOTALL | Pattern.MULTILINE);


		  Matcher matcher=patternScript.matcher(strContent);
		  //strContent = matcher.replaceAll("");
		  StringBuffer sb = new StringBuffer();
		  while(matcher.find()) {
			  String str = matcher.group();
			  matcher.appendReplacement(sb, removeTag(str));
		  }
		  matcher.appendTail(sb);
		  strContent = sb.toString();


		  matcher=patternJava.matcher(strContent);
		  strContent = matcher.replaceAll("");

		  matcher=patternMouseOver.matcher(strContent);
		  sb = new StringBuffer();
		  while(matcher.find()) {
			  String str = matcher.group();
			  //strContent = Util.ReplaceAll(strContent, str, "");
			  matcher.appendReplacement(sb, str.substring(0,str.indexOf("on")));
		  }
		  matcher.appendTail(sb);
		  strContent = sb.toString();
		  //strContent = QuoteHTMLEncoding(strContent);
		 // strContent = HTMLEncoding(strContent);

		  return strContent;
		 // return QuoteOnlyEncoding(strContent);
	}

	//전화번호
	public static String phoneSubstring(String str ) {
		if(str == null){
			return "";
		}
		else
		{
			int result = str.indexOf("-");
			if(result >0 ) return str;
		}

		String p1="";
		String p2="";
		String p3="";
		int len = str.length();

		if(str.substring(0,2).equals("02"))
		{
			p1 = str.substring(0,2);
			p2 = str.substring(2, len-5);
			p3 = str.substring(len-5,len);

			if(p3.length()!=4)
			{

				p1 = str.substring(0,2);
				p2 = str.substring(2, len-4);
				p3 = str.substring(len-4,len);
			}
		}
		else
		{
			p1 = str.substring(0,3);
			p2 = str.substring(3, len-4);
			p3 = str.substring(len-4,len);

		}
		str = p1 +"-"+p2+"-"+p3;

		return str;
	}
	  /**
     *  첨부파일  매치
     */
    public static int matche(String filenameString){
    	
    	int result = 0;
    	String  filename = filenameString;
    	
    	 if (filename.matches(".*avi.*") ||filename.matches(".*mov.*")||filename.matches(".*wmv.*")
    			 	|filename.matches(".*wmv.*")||filename.matches(".*asf.*") ){
    		 result = 1;
    		
    	 }
    	 else{
    		 result = 0;

    	 }
 
    	return  result;
    }
    /**
     * 날싸 형식 변환
     * (예) Tue, 24 Apr 2012 18:51:46 +0900  --> 012-04-04       
     *  
     * @param    moneyString 금액 (String형).
     * @return   변경된 금액 문자열.
     */
    public static String changeDate(String dateString)
    {
    	  SimpleDateFormat formatter_one = new SimpleDateFormat ( "EEE, dd MMM yyyy hh:mm:ss",Locale.ENGLISH );
		  SimpleDateFormat formatter_two = new SimpleDateFormat ( "yyyy-MM-dd" );

		  String inString = dateString;

		  ParsePosition pos = new ParsePosition ( 0 );
		  Date frmTime = formatter_one.parse ( inString, pos );
		  String outString = formatter_two.format ( frmTime );

        return outString;
    } 
}
