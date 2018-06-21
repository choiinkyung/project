package egovframework.MNG.util;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
/**
 * <p>
 * 웹 관련 유틸리티 클래스
 * </p>
 *
 * @author
 * @date 2009. 10. 7. $Id: $
 */
public final class WebUtils {
	

    /**
     * 현재 요청의 세션 ID를 가져온다.
     *
     * @param request
     *            요청 객체
     * @return String 세션ID
     * @throws Exception 
     */
    public static String getSessionId(HttpServletRequest request) throws Exception {
        AssertUtil.isNotNull(request, "Request 객체는 null이 아니어야 합니다.");
        HttpSession hs = request.getSession(false);

        return (hs != null ? hs.getId() : null);
    }

    /**
     * 현재 세션에 저장된 name으로 저장된 Object를 반환한다.
     *
     * @param request
     *            요청 객체
     * @param name
     *            세션의 속성명
     * @return Object 세션에 저장된 값을 Object 형으로 반환
     * @throws Exception 
     */
    public static Object getSessionAttribute(HttpServletRequest request,
            String name) throws Exception {

        AssertUtil.isNotNull(request, "Request 객체는 null이 아니어야 합니다.");
        HttpSession hs = request.getSession(false);

        Object sessObj = null;

        if (hs != null) {
            sessObj = hs.getAttribute(name);
        }
        return sessObj;
    }

    /**
     * 현재 세션에 name 명으로 value에 값이 있으면 Object를 저장하고, 세션이 있고 value가 null 일 경우 세션의
     * 속성을 삭제한다.
     *
     * @param request
     *            요청 객체
     * @param name
     *            속성명
     * @param value
     *            속성값
     * @throws Exception 
     */
    public static void setSessionAttribute(HttpServletRequest request,
            String name, Object value) throws Exception {
        setSessionAttribute(request, name, value, 0);
    }

    /**
     * 현재 세션에 name 명으로 value에 값이 있으면 Object를 저장하고, 세션이 있고 value가 null 일 경우 세션의
     * 속성을 삭제한다.
     *
     * @param request
     *            요청 객체
     * @param name
     *            속성명
     * @param value
     *            속성값
     * @param timeOut
     *            세션유지시간. 0 인경우 Was 기본 설정을 이용.
     * @throws Exception 
     */
    public static void setSessionAttribute(HttpServletRequest request,
            String name, Object value, int timeOut) throws Exception {
        AssertUtil.isNotNull(request, "Request 객체는 null이 아니어야 합니다.");
        HttpSession hs = request.getSession();
        if (value != null) {
            if (timeOut != 0)
                hs.setMaxInactiveInterval(timeOut);
            hs.setAttribute(name, value);
        }
        else {
            if (hs != null) {
                hs.removeAttribute(name);
            }
        }
    }

    /**
     * 주어진 session key name의 session에서 remove시킨다.
     *
     * @param request
     * @param name
     * @throws Exception 
     */
    public static void removeSessionAttribute(HttpServletRequest request,
            String name) throws Exception {

        AssertUtil.isNotNull(request, "Request 객체는 null이 아니어야 합니다.");
        HttpSession hs = request.getSession();

        hs.removeAttribute(name);
    }

    /**
     * 현재 HttpServletRequest 객체에 name에 해당하는 Cookie를 찾아 반환한다. name에 해당하는 Cookie가
     * 존재하지 않을 경우 null 이 반환된다.
     *
     * @param request
     *            현재 요청
     * @param name
     *            Cookie 이름
     * @return Cookie 또는 null
     * @throws Exception 
     */
    public static Cookie getCookie(HttpServletRequest request, String name) throws Exception {
        AssertUtil.isNotNull(request, "Request 객체는 null이 아니어야 합니다.");
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (name.equals(cookies[i].getName())) {
                    return cookies[i];
                }
            }
        }
        return null;
    }

    /**
     * 현재 요청에 대한 <code>HttpServletRequest.getQueryString()</code> 반환한다. 예 :
     * key=key&value=value&subject=제목
     *
     * @param request
     *            요청 객체
     * @return query string
     * @throws Exception 
     */
    public static String getCreateQueryString(HttpServletRequest request) throws Exception {
        AssertUtil.isNotNull(request, "[오류] 대상 HttpServletRequest는 null 입니다.");

        StringBuffer query = new StringBuffer();
        String paramName = "";

        Enumeration names = request.getParameterNames();
        while (names.hasMoreElements()) {
            paramName = (String) names.nextElement();
            query.append(paramName);
            query.append("=");
            query.append(request.getParameter(paramName));
            query.append("&");
        }
        if (query.length() <= 0) {
            return "";
        }
        return query.toString();
    }

    /**
     * 현재 요청에 대한 <code>HttpServletRequest.getQueryString()</code> 반환한다. 단 특정 변수의
     * 값을 변경하여 query string을 만들도록 한다. 예 : key=key&value=value&subject=제목
     *
     * @param request
     *            요청 객체
     * @param targetVar
     *            값을 변경할 대상 변수
     * @param replaceValue
     *            변경될 변수의 값
     * @return 값이 변경된 query string
     * @throws Exception 
     * @see WebUtils#getCreateQueryString(HttpServletRequest)
     */
    public static String getReplaceQueryString(HttpServletRequest request,
            String[] targetVar, String[] replaceValue) throws Exception {
        AssertUtil.isNotNull(request, "[오류] 대상 HttpServletRequest는 null 입니다.");
        AssertUtil.isEqualArrLength(targetVar, replaceValue);

        StringBuffer query = new StringBuffer();
        String paramName = "";

        Enumeration names = request.getParameterNames();
        int tCnt = targetVar.length;
        while (names.hasMoreElements()) {
            for (int i = 0; i < tCnt; i++) {
                paramName = (String) names.nextElement();
                query.append(paramName);
                query.append("=");
                if (paramName.equals(targetVar[i])) {
                    query.append(replaceValue);
                }
                else {
                    query.append(request.getParameter(paramName));
                }
                query.append("&");
            }
        }
        if (query.length() <= 0) {
            return "";
        }
        return query.toString();
    }

    /**
     * 사용자 브라우져 버젼을 가져온다.
     *
     * @param req
     * @return
     */
    public static String userAgent(HttpServletRequest req) {

        String userAgent = req.getHeader("User-Agent") != null ? req
                .getHeader("User-Agent") : "etc";
        int trimUAStart = userAgent.indexOf("MSIE");
        if (trimUAStart != -1)
            userAgent = userAgent.substring(trimUAStart);
        int trimUAEnd = userAgent.indexOf(";");
        if (trimUAEnd != -1)
            userAgent = userAgent.substring(0, trimUAEnd);
        if (userAgent.length() > 15)
            userAgent = userAgent.substring(0, 14);
        return userAgent;
    }

    /**
     * 사용자의 IP를 가져온다.
     *
     * @param request
     * @return
     */
   /* public static String getRemoteIp(HttpServletRequest request) {

        return request.getRemoteAddr();
    }

    // 도메인 네임이 여러개 일경우 도메인을 통일 시켜 주는 용도
    private static final String ALIAS_GROUP = "siteinfo.alias-group.alias";

    public static String getDomainName(HttpServletRequest request) {

		Map aliasInfoMap = new HashMap();

		DaonConfig dc = DaonConfigManager.getConfig();
		List tmpSiteList = dc.getList(ALIAS_GROUP);
		if(null != tmpSiteList) {
			int siteListCnt = tmpSiteList.size();
			for(int i=0 ; i < siteListCnt ; i++) {
				aliasInfoMap.put(dc.getString(ALIAS_GROUP+"("+i+")"), dc.getString(ALIAS_GROUP+"("+i+")[@domain]"));
			}
		}

		String domain_nm_v = request.getServerName();
    	String alias = (String)aliasInfoMap.get(domain_nm_v);
    	if(null != alias)
    		domain_nm_v = alias;

    	return domain_nm_v;
	}

    //사용자 세션 권한 체크
    public static boolean checkUserAuth(HttpServletRequest request,String SESSION_NAME){
//    	System.out.println("SESSION_NAME=="+SESSION_NAME);
    	if(SESSION_NAME.equals("MANAGER_SESSION")){
    		ManagerBean sessionBean = (ManagerBean)request.getSession().getAttribute(SESSION_NAME);
    		if(sessionBean == null) {
        		WebUtils.setSessionAttribute(request, "ref_url_v", request.getRequestURI());
        		return false;
        	}else{
        		return true;
        	}
    	}else{
    		MemberBean sessionBean = (MemberBean)request.getSession().getAttribute(SESSION_NAME);
    		if(sessionBean == null) {
        		WebUtils.setSessionAttribute(request, "ref_url_v", request.getRequestURI());
        		return false;
        	}else{
        		return true;
        	}
    	}
    }*/
    
    /**
     * 다운로드 대상 excel template 파일명을 리틴한다.
     *
     * @param request
     * @param className
     * @return String
     */
    public static String getExcelTemplateName(HttpServletRequest request, String className) {

        String uri = request.getRequestURI();
        String excelFileName = "";
        uri = StringUtils.replace(uri.substring(uri.lastIndexOf("/") + 1), ".do", "");
        
        excelFileName = className + "." + uri;
        
//        String[] pacs = StringUtils.split(className, ".");
        
//        System.out.println("--> 클래스명은 : " + pacs[pacs.length - 1]);
//        System.out.println("--> 호출된 uri는 : " + uri);
//        System.out.println("--> pacs: " + pacs[pacs.length - 1] + "." + uri);
        
//        return pacs[pacs.length - 1] + "." + uri;
        return excelFileName;
        
        
    }
   
}
