package egovframework.MNG.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.MNG.util.sms.SMSComponent;
import net.coobird.thumbnailator.Thumbnails;

/**
 * @author sanguri
 *
 */
public class CommUtil {
	
	@Resource(name = "commUtilService")
	
	private final static String  DISALLOW_FILE_EXTENTION_PATTERN = "(php|php3|php4|asp|jsp|cgi|inc|pl|htm|html|js|java)";
	/**
	 * 로그인체크 : 세션에 id값이 있는지 검사
	 * @return 로그인 : true, 비로그인 : false
	 */
	public static boolean isLogin(){
		String admin_id = (String)RequestContextHolder.getRequestAttributes().getAttribute("admin_id", RequestAttributes.SCOPE_SESSION);		
		return (admin_id == null || "".equals(admin_id))?false:true;
	}
	/**
	 * 사용자 모드 로그인 여부 확인
	 * 사용자 세션 web_id, web_name, web_type, web_lev, web_email
	 * @return
	 */
	public static boolean isWebLogin(){
		String webId = (String)RequestContextHolder.getRequestAttributes().getAttribute("web_id", RequestAttributes.SCOPE_SESSION);		
		return (webId == null || "".equals(webId))?false:true;
	}
	
	/**
	 * 게시판 레벨과 사용자 레벨을 비교함 , 사용자의 레벨이 게시판 레벨보다 같거나 커야 함
	 * @param lev 게시판 해당 모드의 레벨
	 * @return
	 */
	public static boolean authorityCheck(int lev ){
		String web_lev = isNull(getSessionFromField("web_lev"),"0");
		int intLev = Integer.parseInt(web_lev);		
		return (intLev >= lev)?true: false;
	}

	/**
	 * 필드에 해당하는 세션 값을 가져온다.
	 * SessionVO 객체를 사용할때는 getSessionInfo()
	 * id, name, type, lev 중에 한가지씩만 가져오려면 매개변수에 값을 준다.
	 * @param strFld
	 * @return
	 */
	public static String getSessionFromField(String strFld){
		return isNull((String)RequestContextHolder.getRequestAttributes().getAttribute(strFld, RequestAttributes.SCOPE_SESSION));
	}
	
	/**
	 * 처리 완료 후 안내문구 및 이동페이지 설정
	 * @param title : 페이지 타이틀 제목 (보통 오류또는 안내)
	 * @param msg : alert() 경고 문으로 보여줄 안내문, "" 값이면 경고창 없음
	 * @param script : javascript 처리문장 (보통 location.href='~~~~')
	 * @return HashMap<String, String>
	 */
	public static HashMap<String, String> doComplete(String title, String msg, String script){
		HashMap<String, String> message = new HashMap<String, String>();
		message.put("title",title);
		message.put("msg",msg);
		message.put("script",script);
		return message;
	}
	/**
	 * 처리 완료 후 안내문구 및 이동페이지 설정, 
	 * @param model ModelMap
	 * @param title : 페이지 타이틀 제목 (보통 오류또는 안내)
	 * @param msg : alert() 경고 문으로 보여줄 안내문, "" 값이면 경고창 없음
	 * @param script : javascript 처리문장 (보통 location.href='~~~~')
	 * @return /common/message/message
	 */
	public static String doComplete(ModelMap model, String title, String msg, String script) {
		HashMap<String, String> message = new HashMap<String, String>();
		message.put("title",title);
		message.put("msg",msg);
		message.put("script",script);
		model.addAttribute("message", message);
		return "/common/message/message";
	}
	
	/**
	 * str변수가 null이거나 empty이면 "" 값을 가져온다.
	 * @param str
	 * @return
	 */
	public static String isNull(String str){
		String result = "";
		if(str != null){
			result = str;
		}
		return result;
	}
	/**
	 * str변수가 null이거나 empty 이면 defaultStr 을 가져온다.
	 * @param str
	 * @param defaultStr
	 * @return
	 */
	public static String isNull(String str, String defaultStr){
		String result = defaultStr;
		if(str != null && !"".equals(str)){
			result = str;
		}
		return result;
	}
	/**
	 * Object를 String 으로 변환
	 * @param obj 문자열로된 Object
	 * @param defaultStr
	 * @return
	 */
	public static String isNull(Object obj, String defaultStr){
		String result = defaultStr;
		if(obj != null && !"".equals(obj)){
			result = (String)obj;
		}
		return result;
	}
	/**
	 * 세션값을 가져온다.
	 * @return
	 */
	public static String getSession(){
		return (String)RequestContextHolder.getRequestAttributes().getAttribute("admin_id", RequestAttributes.SCOPE_SESSION);
	}
	
	/**
	 * form select box selected 설정 TagLib에서도 사용
	 * @param val1 원본 변수값
	 * @param val2 비교 변수값
	 * @return selected = "selected" OR "";
	 */
	public static String selected(String val1, String val2){
		String returnValue = "";
		if(val1 != null && val2 != null && val1.equals(val2)){
			returnValue = "selected=\"selected\"";
		}
		return returnValue;
	}
	/**
	 * form radio, checkbox checked 설정
	 * @param val1 원본 변수값
	 * @param val2 비교 변수값
	 * @return checked="checked" OR ""
	 */
	public static String checked(String val1, String val2){
		String returnValue = "";
		if(val1 != null && val2 != null && val1.equals(val2)){
			returnValue = "checked=\"checked\"";
		}
		return returnValue;
	}
	
	/**
     * Ajax json 등을 출력
     * @param json
     * @param response
     * @throws Exception
     */
    public static void printWriter(String json, HttpServletResponse response)throws Exception{
	    response.setCharacterEncoding("UTF-8");
	    response.setHeader("Cache-Control", "no-cache");
		response.getWriter().println(json);
		response.getWriter().flush();
	    response.getWriter().close();
    }
	
    /**
     * Object 를 Map으로 변경
     * 주로 VO 에서 Map 으로 변경해서 json사용시 필요함
     * @param obj
     * @return map
     */
    public static Map ConverObjectToMap(Object obj){
        try {
            //Field[] fields = obj.getClass().getFields(); //private field는 나오지 않음.
            java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
            Map resultMap = new HashMap();
            for(int i=0; i<=fields.length-1;i++){
                fields[i].setAccessible(true);
                resultMap.put(fields[i].getName(), fields[i].get(obj));
            }
            return resultMap;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
	 * 필드에 해당하는 세션 값을 가져온다
	 * @param strFld 세션의 Key
	 * @return 해당하는 값이 있으면 해당 값, 없으면 ""
	 */
	public static String getSession(String strFld){
		return isNull(RequestContextHolder.getRequestAttributes().getAttribute(strFld, RequestAttributes.SCOPE_SESSION), "");
	}  
	
	/**
	 * 인코딩을 변경한다.
	 * @param str
	 * @return
	 */
	public static String getUrlEndcoding(String str){
		if(str != null){
			try {
				str = java.net.URLEncoder.encode(str, "EUC-KR");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return str;
	}
	
	/**
	 * 주민등록번호로 나이를 알아낸다.
	 * @param ssn
	 * @return
	 */
	public static String getAgeFromSSN(String ssn){
		String returnValue = "";
		if(ssn != null && !"".equals(ssn) && ssn.length()>6){
			int tmpYear = Integer.parseInt(ssn.substring(0,2));
			String tmpType = ssn.substring(7,8);
			int year = 2000;
			if("1".equals(tmpType) || "2".equals(tmpType)){
				year = 1900;
			}
			year = year + tmpYear;
			int nowYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
			returnValue = String.valueOf(nowYear - year);
		}
		return returnValue;
	}
	/**
	 * 회원관리에서 성별 구분 메소드(주민등록번호로 성별을 알아낸다)
	 * @param ssn
	 * @return
	 */
	public static String getGenderFromSSN(String ssn){
		String returnValue = "";
		if(ssn != null && !"".equals(ssn) && ssn.length()>6){
			String tmpType = ssn.substring(7,8);
			if("1".equals(tmpType) || "3".equals(tmpType)){
				returnValue = "남성";
			}else{
				returnValue = "여성";
			}
		}
		return returnValue;
	}
	/**
	 * 오늘날짜를 패턴에 맞게 가져오기 
	 * @param pattern (yyyy-MM-dd : 2019-02-28 , yyyy-MM-dd hh:mm:ss.SSS : 2019-02-28 01:59:28.002
	 * @return
	 */
	public static String getDatePattern(String pattern){
		String rtnStr = null;
    	try {
    		java.text.SimpleDateFormat sdfCurrent = new java.text.SimpleDateFormat(pattern, Locale.KOREA);
    	    java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
    	    rtnStr = sdfCurrent.format(ts.getTime());
    	} catch (Exception e) {
    	    //e.printStackTrace();
    		
    	    //throw new RuntimeException(e);	// 보안점검 후속조치
    	    //LOG.debug("IGNORED: " + e.getMessage());
    	}
    	return rtnStr;
	}
	/**
	 * 현재 날짜를 가져온다(yyyy-MM-dd)형식
	 * @return
	 */
	public static java.sql.Date getDate(){
		return (new java.sql.Date(System.currentTimeMillis()));
	}
	/**
	 * yyyyMMddhhmmssSSS 문자열을 가져온다.
	 * @return
	 */
	public static String getTimeStamp() {
    	String rtnStr = null;
    	// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
    	String pattern = "yyyyMMddhhmmssSSS";
    	try {
    		java.text.SimpleDateFormat sdfCurrent = new java.text.SimpleDateFormat(pattern, Locale.KOREA);
    	    java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
    	    rtnStr = sdfCurrent.format(ts.getTime());
    	} catch (Exception e) {
    	    //e.printStackTrace();
    		
    	    //throw new RuntimeException(e);	// 보안점검 후속조치
    	    //LOG.debug("IGNORED: " + e.getMessage());
    	}
    	return rtnStr;
    }
	
	/**
	 * 날짜 더하기
	 * @return
	 */
	public static String getTimePlus(int addNumber, String addDate) {
		String rtnStr = "";
		 DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
	    try {
	        Date date = df.parse(addDate);
	         
	        // 날짜 더하기 
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, addNumber);
	        rtnStr = df.format(cal.getTime());
	         
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

		return rtnStr;
	}
	/**
	 * 날짜 차이
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	 public static long diffOfDate(String begin, String end) throws Exception
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    Date beginDate = formatter.parse(begin);
	    Date endDate = formatter.parse(end);
	    long diff = endDate.getTime() - beginDate.getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000);
	    return diffDays;
	  }


	
	/**
	 * 사이트의 공통 업로드 폴더 : 최종 포팅시에는 자동으로 구해지지만, 개발시에는 하드코딩해야함
	 * @param request
	 * @return
	 */
	public static String getFileRoot(HttpServletRequest request) {
    	String path = request.getSession().getServletContext().getRealPath("");
    	path = new java.io.File(new java.io.File(path).getParent()) + System.getProperty("file.separator") + "upload_data/"; //linux
    	return path;
    	//return "C:\\eGovFrameDev-2.0.1-FullVer\\workspace\\KCENTER_www\\data\\board\\"; //개발용
    	//return "C:\\home\\kcenter\\kc.iumap.co.kr\\data\\board\\"; //개발용
        //return request.getSession().getServletContext().getRealPath("/") ;
    }
	
	/**
	 * 첨부파일을 서버에 저장하기전에 파일크기 및 확장자를 먼저 검사해야한다.(기본 금지 확장자는 당연히 제한함)
	 * @param files
	 * @param size 제한용량(Byte 단위)
	 * @return
	 * @throws Exception
	 */
	public static String parseFileInfBeforeCheck(Map<String, MultipartFile> files, long size) throws Exception{
		return parseFileInfBeforeCheck(files, size, false);
	}
	/**
	 * 첨부파일을 서버에 저장하기전에 파일크기 및 확장자를 먼저 검사해야한다.
	 * @param files 
	 * @param size 제한용량(Byte 단위)
	 * @param isImage 이미지만 허용할 것인지 true : 이미지만, false : 모든 파일 허용
	 * @return 0: 정상 , 1: 사이즈 오류, 2: 확장자 오류(금지된 확장자이거나 isImage true일때 이미지가 아닌경우)
	 * @throws Exception
	 */
	public static String parseFileInfBeforeCheck(Map<String, MultipartFile> files, long size, boolean isImage) throws Exception{
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;		
		while (itr.hasNext()) {
		    Entry<String, MultipartFile> entry = itr.next();

		    file = entry.getValue();
		    String orginFileName = file.getOriginalFilename();

		    //--------------------------------------
		    // 원 파일명이 없는 경우 처리
		    // (첨부가 되지 않은 input file type)
		    //--------------------------------------
		    if ("".equals(orginFileName)) {
		    	continue;
		    }
		    ////------------------------------------
		    
		    long _size = file.getSize();
		    
		    int index = orginFileName.lastIndexOf(".");
		    //String fileName = orginFileName.substring(0, index);
		    String fileExt = orginFileName.substring(index + 1);
		    if(_size > size){
		    	return "1";
		    }
		    if(regEx(DISALLOW_FILE_EXTENTION_PATTERN, fileExt.toLowerCase())){ //금지확장자에 해당하면 
		    	return "2";
	    	}
		    if("".equals(fileExt)){
		    	return "2";
		    }
		    if(isImage){
		    	String mimeType = file.getContentType();//image/jpeg|image/jpg|image/x-png|image/gif|image/bmp|image/pjpeg
			    if(!regEx("(image.jpg|image.jpeg|image.gif|image.png|image.bmp|image.x-png|image.pjpeg)",file.getContentType().toLowerCase())){
			    	return "2";
			    }
		    }
		}
		return "0";
	}
	
	
	
		/**
		 * TB_FILEUPLOAD 외에 첨부 <br/>주의: file name이 달라야 함
		 * @param files
		 * @param fileOrder
		 * @param storePath
		 * @return
		 * @throws Exception
		 */
		public static HashMap parseFileInfEtc(Map<String, MultipartFile> files, int fileOrder,String storePath) throws Exception {
		
		File saveFolder = new File(storePath);

		if (!saveFolder.exists() || saveFolder.isFile()) {
		    saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		List<HashMap> result  = new ArrayList<HashMap>();
		HashMap hmFiles = new HashMap();

		String strHashMapKey = "";
		while (itr.hasNext()) {
		    Entry<String, MultipartFile> entry = itr.next();

		    file = entry.getValue();
		    String orginFileName = file.getOriginalFilename();

		    //--------------------------------------
		    // 원 파일명이 없는 경우 처리
		    // (첨부가 되지 않은 input file type)
		    //--------------------------------------
		    if ("".equals(orginFileName)) {
		    	fileOrder++;
		    	continue;
		    }
		    ////------------------------------------

		    int index = orginFileName.lastIndexOf(".");
		    //String fileName = orginFileName.substring(0, index);
		    String fileExt = orginFileName.substring(index + 1);
		    String newName = getTimeStamp()+getRandomString(8) + "_" +  String.valueOf(fileOrder);
		    long _size = file.getSize();
		    
		    if (!"".equals(orginFileName)) {
			filePath = storePath + File.separator + newName + "." + fileExt;
			file.transferTo(new File(filePath));
		    }
		    strHashMapKey = file.getName();
		    if(hmFiles.containsKey(strHashMapKey)) strHashMapKey += strHashMapKey + "0";
		    hmFiles.put(strHashMapKey, newName + "." + fileExt);
//		    tbFileuploadVO = new TbFileuploadVO();
//		    
//		    tbFileuploadVO.setAeOrgname(orginFileName);
//		    tbFileuploadVO.setAeSavename(newName);
//		    tbFileuploadVO.setAeExt(fileExt);
//		    tbFileuploadVO.setAeSize(_size);
//
//		    result.add(tbFileuploadVO);
		    
		    fileOrder++;
		}

		return hmFiles;
	}
	/**
	 * 정규식 검사 메소드
	 * @param ptn 패턴
	 * @param str 검사할 문자열
	 * @return
	 */
	public static boolean regEx(String ptn, String str){
	    java.util.regex.Pattern p = java.util.regex.Pattern.compile(ptn);
	    java.util.regex.Matcher m = p.matcher(str);
	    return m.find();
	}
	/**
	 * String.replaceAll 메소드를 사용하는데, jstl에서 사용하기 위해 추가함.
	 * @param strSource
	 * @param strPattern
	 * @param strResult
	 * @return
	 */
	public static String replaceAll(String strSource, String strPattern, String strResult){
		return strSource.replaceAll(strPattern, strResult);
	}
	
	/**
	 * 입력된 날짜(yyyy-MM-dd)형식이 유효한 날짜인지를 검사한다.
	 * @param dt yyyy-MM-dd 형식의 날짜형식 문자열
	 * @return 유효한 날짜 true, 아니면 false
	 */
	public static boolean isDate(String dt) {
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.setLenient(false); 
            format.parse(dt);               
        } catch (Exception e) { return false; }         
        return true;
    } 
	/**
	 * 게시판 제목등 문자열 길이를 설정한 갯수에 맞게 잘라온다
	 * @param str 문자열
	 * @param len 짜를 갯수(영문,숫자는 1 한글등 유니코드는 2로 계산해서 보통 출력되는 갯수 *2 값으로 설정)
	 * @param trail 짜른뒤에 붙일 문자열 "..."
	 * @return
	 */
	public static String cutString(String str, int len, String trail) {
		if (str==null) return "";
		String returnValue = str;
		int slen = 0, blen = 0;
		char c;
		for(int i=0; i < str.length();i++){
			c = str.charAt(i);
			if(c > 127) slen+=2;
			else slen++;
			if(slen > len){
				returnValue = str.substring(0, i) + trail;
				break;
			}
		}		
		return returnValue;
	}   
	/**
	 * oracle의 DE_CODE 함수로 deCode("직업", "0,학생,1,회사원,2,주부,3,프리랜서","백수"); 
	 * @param compareStr 비교할 변수
	 * @param targetPattern 대상이 되는 값과 리턴값 패턴, 콤마로 구분되면 홀수는 비교변수, 짝수는 그에 해당하는 리턴값.
	 * @param otherwise 같은 값이 없을때 가져올 값
	 * @return 패턴의 갯수가 쌍이아니면 NaN 리턴, compareStr 과 targetPattern 값이 같을때 지정한 리턴 값
	 */
	public static String deCode(String compareStr, String targetPattern, String otherwiseStr ){
		String retStr = "NaN";
		String[] arrArgs = targetPattern.split(",");
		String tmpStr = "";
		if(arrArgs.length % 2 == 0){
			for(int i = 0; i < arrArgs.length; i+=2){
				tmpStr = arrArgs[i];
				if(compareStr.equals(tmpStr)){
					retStr = arrArgs[i +1];
					return retStr;
				}
			}
			retStr = otherwiseStr;
		}
		return retStr;
	}
	
	public static void deleteFile(String filePathFullFileName ){
		File f = new File(filePathFullFileName );
		if(f.exists()){
			f.delete();
		}
	}
	
	public static String getRandomString(int length){
		String[] arrString = {"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","j","k","m","n","p","q","r","s","t","u","v","w","x","y","x","A","B","C","D","E","F","G","H","J","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","X"};
		java.util.Random rnd = new java.util.Random();
		String returnValue = "";
		for(int i =0; i< length; i++){
		returnValue+= arrString[rnd.nextInt(arrString.length-1)];
		}
		return returnValue;
		
	}
	
	/**
	 * 입력된 값과 현재 시간의 차이를   이전인지 현재인지 지났는지 검사
	 * 입력날짜기 기준(strDate - nowDate)
	 * @param strDate yyyy-MM-dd 형식으로 날짜만 
	 * @return
	 */
	public static int dateDiff(String strDate){
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
//		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		
		Date date1 = null;
		try {
			date1 = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();			
		}
		Date date2 = new Date();
		
		cal1.setTime(date1);
		cal2.setTime(date2);
		
		int diff = (int)((cal1.getTimeInMillis() - cal2.getTimeInMillis())/(long)(60*60*24*1000));
		if(diff > 0){
			return 1;
		}else if(diff < 0){
			return -1;
		}else{			
			return 0;
		}
	}
	/**
	 * 두 날짜의 차이를 설정한 type에 맞게 가져온다. 주의할것은 strDate1 - strDate2 이므로 양,음수를 잘 구분해야함
	 * @param strDate1 첫번째 날짜
	 * @param strDate2 두번째 날짜
	 * @param pattern 날짜 패턴 두 날짜가 패턴과 일치해야함 (yyyy-MM-dd HH:mm:ss) 형식
	 * @param type 리턴받는 두 날짜의 차이를 일(d), 시간(h), 분(m) 단위로 선택함
	 * @return
	 */
	public static int dateDiff(String strDate1, String strDate2, String pattern, String type){
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern, Locale.KOREA);
		
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = sdf.parse(strDate1);
			date2 = sdf.parse(strDate2);
		} catch (ParseException e) {
			e.printStackTrace();			
		}
		
		cal1.setTime(date1);
		cal2.setTime(date2);
		long divide = 0;
		if("d".equals(type)){
			divide = 60*60*24*1000;
		}else if("h".equals(type)){
			divide = 60*60*1000;
		}else{
			divide = 60 * 1000;
		}		
		return (int)((cal1.getTimeInMillis() - cal2.getTimeInMillis())/(divide));
		
	}
	public static String dateAdd(String strDate, int intIncrease){
		Calendar cal1 = Calendar.getInstance();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date date1 = null;
		try {
			date1 = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();			
		}
		cal1.setTime(date1);
		long addTimeStamp = ((long)(60*60*24*1000) * intIncrease);
		cal1.setTimeInMillis(cal1.getTimeInMillis() + addTimeStamp);
		
		return sdf.format(new java.sql.Timestamp(cal1.getTimeInMillis()));
	}
	/**
	 * 사업자등록번호 유효성 체크
	 * 1~8자리까지는 각자리마다 1, 3, 7, 1, 3, 7, 1, 3을 곱해서 더한다.
	 * 9자리는 5를 곱해서 10단위와 1단위를 더한다. int(x*5)/10 + (x*5) %10 
	 * 위 2개를 다 더한다.
	 * 위 합을 10으로 나눠서 나머지를 취한다.
	 * 10에서 위 값을 빼고 그값을 10으로 나눠서 나머지를 구한다. (10-(sum%10))%10
	 * 10번째 자리숫자와 위 자리숫자가 같아야한다.
	 * @param num
	 * @return
	 */
	public static boolean bizNumCheck(String num){
		int sum = 0;
		num = num.replaceAll("-", "");
		if(num != null){
			if(num.length() == 10){
					sum+= Integer.parseInt(num.substring(0,1)) * 1;
					sum+= Integer.parseInt(num.substring(1,2)) * 3;
					sum+= Integer.parseInt(num.substring(2,3)) * 7;
					sum+= Integer.parseInt(num.substring(3,4)) * 1;
					sum+= Integer.parseInt(num.substring(4,5)) * 3;
					sum+= Integer.parseInt(num.substring(5,6)) * 7;
					sum+= Integer.parseInt(num.substring(6,7)) * 1;
					sum+= Integer.parseInt(num.substring(7,8)) * 3;
					sum+= Math.floor(((Integer.parseInt(num.substring(8,9))) * 5) / 10);
					sum+= ((Integer.parseInt(num.substring(8,9))) * 5) % 10;
					
					int checkNum = (10 - (sum%10)) %10;
//					System.out.println(sum);
//					System.out.println(num.substring(9,10));
//					System.out.println(checkNum);
					return (num.substring(9,10).equals(String.valueOf(checkNum)));
			}
		}
		return false;
	}
	
	
	/**
	 * 메일 발송 셋팅
	 * @param request
	 * @param mailTemplate : 템플릿파일명
	 * @param content :메일 발송내용
	 * @param toName : 받는사람이름
	 * @param toEmail : 받는사람 이메일주소
	 * @param emailTitle : 이메일 제목
	 * @param fromName : 보내는사람이름 빈값 이면  에이풀스터디
	 * @return string
	 * @throws Exception
	 */
	public static String setMail (HttpServletRequest request, String mailTemplate, 
			String content, String toName, String toEmail, String emailTitle) throws Exception{
		
		String result = "";
		String mailContent = "";
		try{
			mailContent = CommUtil.readFileEventMail("",request, mailTemplate).toString();
			if(mailContent.indexOf("#servername#") > -1)			{mailContent = mailContent.replace("#servername#", request.getServerName());}
			if(mailContent.indexOf("#nowDate#") > -1)				{mailContent = mailContent.replace("#nowDate#", CalendarUtil.getFormatDate("yyyy-MM-dd"));}
			if(mailContent.indexOf("#content#") > -1)				{mailContent = mailContent.replace("#content#",content);}
			if(mailContent.indexOf("#name#") > -1)				{mailContent = mailContent.replace("#name#",toName);}
			sendMail("apulledu@gmail.com", "에이풀스터디", toEmail, toName, emailTitle, mailContent);
			result = "success";
		}catch(Exception e) {
			result = "fail";
			e.printStackTrace();
		}
		return result;
		
	}
	/**
	 * HTML 형식의 메일을 발송한다.
	 * @param toEmail 받는사람 이메일주소
	 * @param toName 받는사람 이름 , 없으면 ""
	 * @param subject 이메일 제목
	 * @param content 이메일 내용 HTML 형식
	 * @throws Exception
	 */
	public static  void sendMail(String fromEmail, String fromName, String toEmail, String toName, String subject, String content) throws Exception{
		
		HtmlEmail email = new HtmlEmail();
		email.setCharset("UTF-8");
		email.setSmtpPort(465);
		email.setSSLOnConnect(true); 
		email.setHostName("smtp.gmail.com"); //개발서버테스트용
		email.setAuthentication("apulledu@gmail.com", "@ap0325ap@");
		
		email.addTo(toEmail, toName);
		email.setFrom(fromEmail, fromName);
		email.setSubject(subject);  

		//이메일 형식이 아니면 그냥 리턴.
		if(!regEx("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",toEmail )) {
			System.out.println("이메일 발송오류 : " + toEmail);
			return;
		}
		// embed the image and get the content id
		// java.net.URL url = new java.net.URL("http://www.apache.org/images/asf_logo_wide.gif");
		//String cid = email.embed(url, "Apache logo");

		// set the html message
		email.setHtmlMsg("<html>"+content+"</html>");
		// set the alternative message
		email.setTextMsg(content + "회원님의 메일은 HTML 형식을 지원하지 않습니다");

		// send the email
		try{
			System.out.println("[[[[[[[[[[[[[[[이메일 보내기 시도]]]]]]]]]]]]]]]]");
			email.send();
		}catch(Exception e){
			//return;
			e.printStackTrace();
		}
	}
	
	  private void postMail(String recipients[], String subject, String message, String from) throws MessagingException {
	        boolean debug = false;
	        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	  
	        String SMTP_HOST_NAME = "gmail-smtp.l.google.com";
	         
	        // Properties 설정
	        Properties props = new Properties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.starttls.enable","true");
	        props.put("mail.smtp.host", SMTP_HOST_NAME);
	        props.put("mail.smtp.auth", "true");
	  
	        Authenticator auth = new SMTPAuthenticator();
	        Session session = Session.getDefaultInstance(props, auth);
	  
	        session.setDebug(debug);
	  
	        // create a message
	        Message msg = new MimeMessage(session);
	  
	        // set the from and to address
	        InternetAddress addressFrom = new InternetAddress(from);
	        msg.setFrom(addressFrom);
	  
	        InternetAddress[] addressTo = new InternetAddress[recipients.length];
	        for (int i = 0; i < recipients.length; i++) {
	            addressTo[i] = new InternetAddress(recipients[i]);
	        }
	        msg.setRecipients(Message.RecipientType.TO, addressTo);
	  
	        // Setting the Subject and Content Type
	        msg.setSubject(subject);
	        msg.setContent(message, "text/plain");
	        Transport.send(msg);
	    }

	
	 /**
     * 구글 사용자 메일 계정 아이디/패스 정보
     */
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            String username =  "apulledu@gmail.com"; // gmail 사용자;
            String password = "*ap0325*"; // 패스워드;
            return new PasswordAuthentication(username, password);
        }
    }

	
	/**
	 * 메일보내기를 위해 소스파일 읽어들이기. 파일내용에서 message라는 문구를 인자값(eventMessage)으로 교체
	 * @param eventMessage : 소스에서 교체할 문구
	 * @return StringBuffer
	 * @호출예: CommUtil.readFileHtml("경기TP 공용장비 사용신청내역이 수정되었습니다.", request)
	 */
	public static StringBuffer readFileEventMail (String eventMessage, HttpServletRequest request, String mailHtml) throws IOException {
		StringBuffer sb = new StringBuffer(); 
		BufferedReader in = new BufferedReader(new FileReader(getContextRoot()+"/WEB-INF/template/mail/"+mailHtml));
																					
		String htmlSource = null;
		
		HashMap<String, String> hmDomain = CommUtil.makeDomainStr(request);
    	String strPoolDom = hmDomain.get("strPoolDom");
    	
		while ( (htmlSource = in.readLine()) != null ) {	
			if(htmlSource.indexOf("message") > -1) { //인자값으로 받은 내용으로 치환할 부분	  
				htmlSource = htmlSource.replace("message", eventMessage); 	
	    	}			
			if(htmlSource.indexOf("poolDomain") > -1) { //접속사이트명에 따라 메일소스의 이미지 링크부분들을 수정한다.	  
				htmlSource = htmlSource.replace("poolDomain", strPoolDom); 	
	    	}		
			
			sb.append(htmlSource+"\n"); // 메일 파일을 라인단위로 읽어들인다.
			htmlSource = null;
		}
		System.out.println("readFileEventMail에서 리턴됨");
		return sb;
	}
	
	/**
	 * 처리 완료 후 안내문구 및 이동페이지 설정
	 * @param title : 페이지 타이틀 제목 (보통 오류또는 안내)
	 * @param msg : alert() 경고 문으로 보여줄 안내문, "" 값이면 경고창 없음
	 * @param script : javascript 처리문장 (보통 location.href='~~~~')
	 * @return HashMap<String, String>
	 */
	public static HashMap<String, String> makeDomainStr(HttpServletRequest request) {
		
    	String strRemHost = request.getServerName();
    	String strDomain = "gtp.or.kr";
    	String strIfrDom = "www.gtp.or.kr";
    	String strPoolDom = "pool.gtp.or.kr";
    	
    	HashMap<String, String> hmResult = new HashMap<String, String>();

    	if(strRemHost.indexOf("gtp.or.kr") > -1) {
    		
    		strDomain = "gtp.or.kr";
    		strIfrDom = "www.gtp.or.kr";
    		strPoolDom = "pool.gtp.or.kr";
    		
    	} else if(strRemHost.indexOf("iumap.co.kr") > -1) {
    		
    		strDomain = "iumap.co.kr";
    	    strIfrDom = "gtpwww.iumap.co.kr:88";
    	    strPoolDom = "gtppool.iumap.co.kr:83";
    		
    	} else if(strRemHost.indexOf("localhost") > -1) {
    	    
    		strDomain = "localhost";
    	    strIfrDom = "localhost";
    	} 
    	
    	hmResult.put("strDomain", strDomain);
    	hmResult.put("strIfrDom", strIfrDom);
    	hmResult.put("strPoolDom", strPoolDom);
    	
    	return hmResult;
	}
	
	/**
	 * 홈페이지 접속 로그 정보를 HashMap에 담아서 리턴한다.
	 * @param request 
	 * @param accessType 홈페이지는 h, 모바일은 m
	 * @return
	 */
	public static HashMap insertLog(HttpServletRequest request, String accessType){
		
    	String agent   = isNull(request.getHeader("user-agent"), "");
    	String referer = isNull(request.getHeader("referer"),"");
    	String os      = "";
    	String browser = "";
    	
    	//홈페이지에서 메인으로 이동시는 로그저장안하도록함
    	if( referer.toLowerCase().indexOf("localhost") > 0){
    		return null;
    	}
    	
    	if(!"".equals(agent)){
    		
    		String[] arrTmp = agent.split(";");
    		
    		if(arrTmp.length >= 3){
    			if(arrTmp[1].indexOf("MSIE") > 0){
    				os = arrTmp[2];
    				browser = arrTmp[1];
    			}else if(arrTmp[1].indexOf("U") > 0){
    				os = arrTmp[2];
    				browser = getBrowser(agent);
    			}else{
    				os = arrTmp[0];
    				browser = getBrowser(agent);
    			}
    		}else if(arrTmp.length >=2 ){
    			os = arrTmp[0];
    			browser = getBrowser(agent);
    		}
    	}
    	
    	HashMap hmParam = new HashMap();
    	
    	hmParam.put("L_IP", isNull(request.getRemoteAddr(),""));
    	hmParam.put("L_REGDT", getDatePattern("yyyy-MM-dd HH:mm:ss"));
    	hmParam.put("L_AGENT", agent);
    	hmParam.put("L_REFERER", referer);
    	hmParam.put("L_OS", os);
    	hmParam.put("L_BROWSER", browser);
    	hmParam.put("L_ACCESSTYPE", accessType);
    	
    	return hmParam;
	}
	
	public static String getBrowser(String agent){
    	String browser = "";
    	if(agent.toLowerCase().indexOf("chromeplus")> 0){
			browser = "ChromePlus";
		}else if(agent.toLowerCase().indexOf("chrome")> 0){
			browser = "Chrome";
		}else if(agent.toLowerCase().indexOf("safari")> 0){
			browser = "Safari";
		}if(agent.toLowerCase().indexOf("firefox")> 0){
			browser = "Firefox";
		}
    	return browser;
    }
	
	public static String quot(String str){
		String returnValue = "";
		if(!"".equals(str)){
			returnValue = str.replaceAll("\"", "&quot;");
		}
		return returnValue;
	}
	
	
	/**
	 * 첨부된 파일의 썸네일 이미지를 생성한다.
	 * @param path
	 * @param filename
	 * @param tail
	 * @param width
	 * @param height
	 * @return
	 */
	public static String thumbnail(Map paramMap, MultipartHttpServletRequest request, HttpServletResponse response){
		OutputStream out = null;
		String fileName = "";
		String returnVal = "";
		try {
			Iterator<String> itr =  request.getFileNames();
		    MultipartFile mpf = request.getFile(itr.next());
		    
			fileName = mpf.getOriginalFilename();
			byte[] bytes = mpf.getBytes();
//			String uploadPath = SettingKey.THUMBNAIL_FILE_PATH + "/" + new String(fileName.getBytes("UTF-8"));
			String uploadPath = "";
			System.out.println("uploadPath = " + uploadPath);
			Runtime.getRuntime().exec("chmod 777 " + uploadPath);
			out = new FileOutputStream(new File(uploadPath));
			out.write(bytes);

			
			Runtime.getRuntime().exec("chmod 777 " + uploadPath);
			File imagefile = new File(uploadPath);
			
//			String thumbReName = SettingKey.THUMBNAIL_FILE_PATH + "/" + new String(fileName.getBytes("UTF-8"));
			String thumbReName = "";
			java.awt.image.BufferedImage  originalImage = javax.imageio.ImageIO.read(imagefile);
				java.awt.image.BufferedImage thumbnail = 	Thumbnails.of(originalImage)        
						.size(Integer.parseInt(paramMap.get("img_width").toString()), Integer.parseInt(paramMap.get("img_height").toString()))
						.imageType(BufferedImage.TYPE_INT_ARGB)        
						.asBufferedImage(); 
				Runtime.getRuntime().exec("chmod 777 " + thumbReName);
				Thumbnails.of(thumbnail).size(Integer.parseInt(paramMap.get("img_width").toString()), Integer.parseInt(paramMap.get("img_height").toString())).toFile(thumbReName);
			
				out.close();
		
//				returnVal = SettingKey.THUMBNAIL_FILE_SIMPLE_PATH + "/" + new String(fileName.getBytes("UTF-8"));
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return returnVal;
	}
	
	/**
	 * 첨부된 파일의 썸네일 이미지를 생성한다.
	 * @param path
	 * @param filename
	 * @param tail
	 * @param width
	 * @param height
	 * @return
	 */
	/*public static String thumbnail(String path, String filename, String tail, int width, int height){
		
		if("".equals(isNull(path,"")) || "".equals(isNull(filename,""))){
			return "";
		}
		String toFilename = "";
		String filePath = "";
		try {
			
			File saveFolder = new File(path);
			
			if (!saveFolder.exists() || saveFolder.isFile()) {
				saveFolder.mkdirs();
			}
			String fileExt = filename.substring(filename.lastIndexOf(".") + 1);
			toFilename = filename.substring(0, filename.lastIndexOf(".")) + tail + "." + fileExt;
			
			String org_fileName = filename;
			int fileNameIdx = org_fileName.lastIndexOf("\\");
			org_fileName = org_fileName.substring(fileNameIdx+1);
			
			System.out.println(">>>>>>>>>>>>>>>>> org_fileName = " + org_fileName);
			
			System.out.println(">>>>>>>>>>>>> fileExt = " + fileExt);
			System.out.println(">>>>>>>>>>>>> toFilename = " + toFilename);
			
			
			path = path.replaceAll("/", "\\\\");
			filePath = path + "\\" + org_fileName;
//			filePath = path + "/" + org_fileName;
			filePath = filePath.replaceAll("\\\\", "/");
			Runtime.getRuntime().exec("chmod 777 " + filePath);
			//filename = filename.replaceAll("\\\\", "/");
			
			File imagefile = new File(filename);
			
			System.out.println(">>>>>>>>>>>>>>>>>> filename.isFile :"+imagefile.getAbsolutePath());
			System.out.println(">>>>>>>>>>>>>>>>>> filename.isFile :"+imagefile.isFile());
			System.out.println(">>>>>>>>>>>>>>>>>> filename.length :"+imagefile.length());
			java.awt.image.BufferedImage  originalImage = javax.imageio.ImageIO.read(imagefile);
			java.awt.image.BufferedImage thumbnail = 	Thumbnails.of(originalImage)        
					.size(width, height)
					.imageType(BufferedImage.TYPE_INT_ARGB)        
					.asBufferedImage(); 
			Thumbnails.of(thumbnail).size(width, height).toFile(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
	*/
	
	/**
	 * 썸네일 파일명 (기존 파일 이름 뒤에 지정된 문자를 추가하여 파일명을 새로 만든다)
	 * @param filename
	 * @param tail
	 * @return
	 */
	public static String getThumbFileName(String filename, String tail){
		
		String fileExt = filename.substring(filename.lastIndexOf(".") + 1);
		
		if(!"".equals(fileExt)){
			return filename.substring(0, filename.lastIndexOf(".")) + tail + "." + fileExt;
		}else{
			return "";
		}
	}
	
	/**
	 * 날짜를 받아서 '년 월 일'로 변화해서 리턴한다.
	 * @param request 
	 * @param 
	 * @return
	 */
	public static String getFormatDateKorean(String dateString){
    	
		String formatDateKorean = "";
		
		String arrDate[] = dateString.split("-");
		
		if(arrDate.length > 2){
			formatDateKorean = arrDate[0]+"년 "+arrDate[1]+"월 "+arrDate[2]+"일";
		}
		
		return formatDateKorean;
    }
	
	/**
	 * 소수점 자리 숫자를 받아서 반올림해서 정수를 리턴한다.
	 * @param request 
	 * @param 
	 * @return
	 */
	public static int getFormatRound(double deci){
    	
		int roundNo = 0;
		
		DecimalFormat f1 = new DecimalFormat("0"); //소숫점 이하 1자리 (자동 반올림)
	        
	    String roundString = f1.format(deci);
	    
	    roundNo = Integer.parseInt(roundString);
	    
		return roundNo;
    }
	
	
	/**
	 * 첨부파일을 서버에 저장하기전에 파일크기 및 확장자를 먼저 검사해야한다.
	 * @param files 
	 * @param size 제한용량(Byte 단위)
	 * @param allowExt 첨부할 수 있는 파일형식입력 정규식형식(xls|xlsx|doc|docx)
	 * @return 0: 정상 , 1: 사이즈 오류, 2: 확장자 오류(금지된 확장자이거나 isImage true일때 이미지가 아닌경우) 3:확장자가 없음 4:이미지파일이 아님(isImage true일경우만)
	 * @throws Exception
	 */
	public static String fileUploadBeforeCheck(Map<String, MultipartFile> files, long size, String allowExt, boolean imageOnly) throws Exception{
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;		
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			
			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();
			
			//--------------------------------------
			// 원 파일명이 없는 경우 처리
			// (첨부가 되지 않은 input file type)
			//--------------------------------------
			if ("".equals(orginFileName)) {
				continue;
			}
			////------------------------------------
			
			long _size = file.getSize();
			
			int index = orginFileName.lastIndexOf(".");
			
			if(index < 0){ return "확장자가 없는 파일은 첨부 할 수 없습니다." ; } //확장자 없는 파일 첨부 금지.
			
			//String fileName = orginFileName.substring(0, index);
			String fileExt = orginFileName.substring(index + 1);
			if(_size > size){
				return String.format("첨부파일 용량이 초과 했습니다.\\n  용량제한 : %s, 첨부파일 용량 : %s  ", fileSize(size), fileSize(_size));
			}
			if(regEx(DISALLOW_FILE_EXTENTION_PATTERN, fileExt.toLowerCase())  || fileExt.length() > 19 ){ //금지확장자에 해당하면 
				return "업로드 할 수 없는 확장자 입니다.";
			}
			if("".equals(fileExt)){
				return "확장자가 없는 파일은 첨부 할 수 없습니다.";
			}
			if(!regEx(allowExt,fileExt.toLowerCase())){
				return String.format("첨부 가능한 파일 형식이 아닙니다.\\n가능한 파일 형식 : %s", allowExt.replace("|", ","));
			}
		    if(imageOnly && // 이미지만 받도록 설정된 경우 
		    		!regEx("(image.jpg|image.jpeg|image.gif|image.png|image.bmp|image.x-png|image.pjpeg)",file.getContentType().toLowerCase())){
		    	return "이미지 파일만 첨부가 가능합니다.";
		    }
		}
		return "";
	}
	
	/**
	 * 지정된 이름의 파일 확장자를 검사함
	 * @param files
	 * @param fieldName <input type='file' name=fieldName
	 * @return
	 * @throws Exception
	 */
	public static String fileUploadBeforeCheck(Map<String, MultipartFile> files, long size,  String fieldName, String allowExt, boolean imageOnly, String required) throws Exception{
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;		
		boolean isRequired = false;
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			
			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();
			
			//--------------------------------------
			// 원 파일명이 없는 경우 처리
			// (첨부가 되지 않은 input file type)
			//--------------------------------------
			if ("".equals(orginFileName)) {
				continue;
			}
			////------------------------------------
			
			long _size = file.getSize();
			int index = orginFileName.lastIndexOf(".");
			if(fieldName.equals(file.getName())){
				isRequired = true;
				if(index < 0){ return "확장자가 없는 파일은 첨부 할 수 없습니다." ; } //확장자 없는 파일 첨부 금지.
				
				//String fileName = orginFileName.substring(0, index);
				String fileExt = orginFileName.substring(index + 1);
				if(regEx(DISALLOW_FILE_EXTENTION_PATTERN, fileExt.toLowerCase())  || fileExt.length() > 19 ){ //금지확장자에 해당하면 
					return "업로드 할 수 없는 확장자 입니다.";
				}
				if(_size > size){
					return String.format("첨부파일 용량이 초과 했습니다.\\n  용량제한 : %s, 첨부파일 용량 : %s  ", fileSize(size), fileSize(_size));
				}
				if("".equals(fileExt)){
					return "확장자가 없는 파일은 첨부 할 수 없습니다.";
				}
				if(!regEx(allowExt,fileExt.toLowerCase())){
					return String.format("첨부 가능한 파일 형식이 아닙니다.\\n가능한 파일 형식 : %s", allowExt.replace("|", ","));
				}
				if(imageOnly && // 이미지만 받도록 설정된 경우 
						!regEx("(image.jpg|image.jpeg|image.gif|image.png|image.bmp|image.x-png|image.pjpeg)",file.getContentType().toLowerCase())){
					return "이미지 파일만 첨부가 가능합니다.";
				}
			}
		}
		if("required".equals(required ) && isRequired == false){
			return "파일을 첨부 해 주세요.";
		}
		return "";
	}
	
	public static String fileSize(long fileSize ){
		String strResult = "";
		if(fileSize> 1024000000){
			fileSize = fileSize / 1024000000;
			strResult = fileSize + " GB";
		}else if(fileSize > 1024000){
			fileSize = fileSize / 1024000;
			strResult = fileSize + " MB";
		}else if(fileSize > 1024){
			fileSize = fileSize / 1024;
			strResult = fileSize + " KB";
		}else{
			strResult = fileSize + " B";
		}
		return strResult;		
	}
	
	/**
	 * 숫자앞에 0 붙이기
	 * @param no
	 * @return
	 */
	public static String getZeroPlus(int no){
		return (no < 10 )? "0" + String.valueOf(no) : String.valueOf(no);
	}
	
	/**()
	 * fieldName 에 해당하는 업로드 파일정보를 구한다
	 * @param files
	 * @param fieldName &lt;input type="file" name="fieldName"~
	 * @param fileOrder 다중파일업로드일경우 order
	 * @param storePath 업로드 폴더 공통 (CommUtil.getRootFile(request) + "하위폴더")
	 * @return  
	 			hm.put("F_SAVENAME", newName + "." + fileExt);
			    hm.put("F_EXT", fileExt);
			    hm.put("F_FILESIZE", _size);
			    hm.put("F_ORGNAME", originalFileName);
	 * @throws Exception
	 */
	public static HashMap fileUpload(Map<String, MultipartFile> files, String fieldName, String storePath) throws Exception {
		return fileUpload(files,fieldName,storePath, "");
	}
	
	//특정폴더에 업로드 할경우 4번째 매개변수에 폴더 지정
	public static HashMap fileUpload(Map<String, MultipartFile> files, String fieldName, String storePath, String path) throws Exception {
		if("".equals(path)){
			storePath = getFileRoot() + System.getProperty("file.separator")  + storePath;
		}else{
			storePath = path + System.getProperty("file.separator")  + storePath;
		}
		File saveFolder = new File(storePath);

		if (!saveFolder.exists() || saveFolder.isFile()) {
		    saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		HashMap hm = null;

		while (itr.hasNext()) {
		    Entry<String, MultipartFile> entry = itr.next();

		    file = entry.getValue();
		    String originalFileName = file.getOriginalFilename();

		    //--------------------------------------
		    // 원 파일명이 없는 경우 처리
		    // (첨부가 되지 않은 input file type)
		    //--------------------------------------
		    if ("".equals(originalFileName)) {
		    	continue;
		    }
		    ////------------------------------------
		    if(fieldName.equals(file.getName())){
		    	hm = new HashMap();
		    	int index = originalFileName.lastIndexOf(".");
			    //String fileName = orginFileName.substring(0, index);
			    String fileExt = originalFileName.substring(index + 1);
			    String newName = getUniqueFileName(storePath, fileExt); //날짜 20121011+랜덤8자리
			    long _size = file.getSize();
	
			    filePath = storePath + File.separator + newName + "." + fileExt ;
				file.transferTo(new File(filePath));
				
			    hm.put("F_SAVENAME", newName + "." + fileExt);
			    hm.put("F_EXT", fileExt);
			    hm.put("F_FILESIZE", _size);
			    hm.put("F_ORGNAME", originalFileName);
		    }
		}

		return hm;
	}
	
	/**
	 * 사이트의 공통 업로드 폴더 : 최종 포팅시에는 자동으로 구해지지만, 개발시에는 하드코딩해야함
	 * @param request
	 * @return
	 */
	public static String getFileRoot() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	   	String path = request.getSession().getServletContext().getRealPath("");
	   	path = new java.io.File(new java.io.File(path).getParent()) + System.getProperty("file.separator") + "upload_data"; 
	   	return path;
   }	
	
	/**
	 * 해당 경로에 중복되지 않는 파일명을 구한다.
	 * @param path 전체 풀 경로
	 * @param fileExt 첨부파일의 호
	 * @return
	 */
	public static String getUniqueFileName(String path, String fileExt){
		String filename = "";
		while(true){
			filename = "f" + getDatePattern("yyyyMMdd")+getRandomString(4);
			if(!new File(path + System.getProperty("file.separator") + filename + "." + fileExt).exists()){
				break;
			}
		}
		return filename;
	}
	
	public static String getContextRoot(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	   	return request.getSession().getServletContext().getRealPath("");
	}
	
	public static String getUserIP() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRemoteHost();
	}
	
	/**
	 * 파일을 삭제한다.
	 * @param fullpath 파일경로 및 파일명
	 * @throws Exception
	 */
	public static void fileDel(String fullpath) throws Exception{
		java.io.File delFile = new java.io.File(fullpath);
		if(delFile.exists()){
			delFile.delete();
		}
	}
	
	@SuppressWarnings("deprecation")
	public static String getExcelCellValueString(HSSFCell cell) {

        String cellValue = "";
        
        if(cell != null) {
            
            int type = cell.getCellType(); 
            
            switch(type){ 
                
                case HSSFCell.CELL_TYPE_BLANK:
                    break;
                    
                case HSSFCell.CELL_TYPE_NUMERIC:
                    cellValue = Double.toString(cell.getNumericCellValue());  
                    break;
                    
                case HSSFCell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                    
                case HSSFCell.CELL_TYPE_ERROR:
                    cellValue = Byte.toString(cell.getErrorCellValue());  
                    break;
                    
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    cellValue = Boolean.toString(cell.getBooleanCellValue());  
                    break;
                    
                case HSSFCell.CELL_TYPE_FORMULA: 
                    cellValue = cell.getCellFormula();  
                    break;
            }
        }
        
        return cellValue;
    }
	
	/**
	 * 파일 다운로드용 파일명 복호화
	 * @param str
	 * @return
	 */
	/*public static String getFileDec(String str){
		String tmpStr = seedDecBase64(str);
		if(tmpStr != null && tmpStr.length()> 8) tmpStr = tmpStr.substring(8);
		return tmpStr;
	}*/
	
	/**
	  * 스트립트 실행을 위한 페이지를 생성
	 * @param msg
	 * @param script
	 * @return
	 */
	public static String getAlertHtml(String msg, String script){
		
    	StringBuilder sb = new StringBuilder();
    	sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"ko\" xml:lang=\"ko\">");
    	sb.append("<head>");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
    	sb.append("<title>오류</title>");
    	sb.append("<script type=\"text/javascript\">");
    	sb.append("alert(\""+msg+"\");");
    	sb.append(script);
    	sb.append("</script>");
    	sb.append("</head>");
    	sb.append("<body>");
    	sb.append("</body>");
    	sb.append("</html>");
    	return sb.toString();
    }
	
	/**
	 * 입력받은 폴더와 파일명을 암호화 한다.
	 * @param folder
	 * @param filename
	 * @return
	 */
	/*public static String getDownloadLink(String fileIndex, String folder, String saveFilename, String originalFilename){
		
		String returnValue = "";
		if(!"".equals(saveFilename) && !"".equals(folder)){
			returnValue = getFileEnc(fileIndex + "::" + folder + "::" + saveFilename + "::" + originalFilename);
			returnValue = java.net.URLEncoder.encode(returnValue);
		}
		return returnValue;
	}*/
	
	/**
	 * 파일 다운로드용 파일명 암호화, 암호화된 파일명은 urlencoding으로 변경 후 링크에 사용해야 한다.
	 * @param str
	 * @return
	 */
	/*public static String getFileEnc(String str){		
		return seedEncBase64(getRandomString(8) + str);
	}*/
	
	
	/**
	 * 문자열이 숫자인지 판단
	 * @param s
	 * @return
	 */
	 public static boolean isStringDouble(String s) {
	    try {
	        Double.parseDouble(s);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	  }
	 
	 
	 /**
	     * 다운로드 대상 excel template 파일명을 리틴한다.
	     *
	     * @param request
	     * @param clazzName
	     * @return String
	     */
	    public static String getExcelTemplateName(HttpServletRequest request, String clazzName) {

    	  String uri = request.getRequestURI();
          uri = StringUtils.replace(uri.substring(uri.lastIndexOf("/") + 1), ".do", "");
         // String[] pacs = StringUtils.split(clazzName, ".");

          System.out.println("--> 클래스명은 : " + clazzName);
          System.out.println("--> 호출된 uri는 : " + uri);
          System.out.println("--> pacs: " + clazzName + "." + uri);

          return clazzName + "." + uri;
	    }
	    
	    
	    
	    public static boolean isDate(int m, int d, int y) // This method is used to check for a VALID date
	    {
	        m -= 1;
	        Calendar c = Calendar.getInstance();
	        c.setLenient(false);

	        try
	        {
	                c.set(y,m,d);
	                java.util.Date dt = c.getTime();
	        }
	          catch (IllegalArgumentException e)
	        {
	                return false;

	        }
	                return true;
	    }
	    
	    /**
	     * 문자 발송
	     * @param phoneNumber : 핸드폰번호
	     * @param subject : 제목
	     * @param content : 내용
	     * @return
	     */
		public static String sendSms(String phoneNumber, String subject, String content) {
	    	String returnMsg = "";
	    	//수신자번호
	    	String[] phone_number = {phoneNumber != null ? phoneNumber : ""};
	    	//발신자번호
	    	String send_tel_v = "023230378";
	    	//SMS 연결
	    	SMSComponent smsc = new SMSComponent();
	    	try {
	    		smsc.connect();
	    		returnMsg = smsc.SendMsg(phone_number, send_tel_v, subject, "", content, 1);
			} catch (Exception e) {
				// TODO: handle exception
				returnMsg = "N";
			}
	    	
	    	return returnMsg;
	    }

}
