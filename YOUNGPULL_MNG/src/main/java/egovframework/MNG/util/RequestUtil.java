package egovframework.MNG.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;

public class RequestUtil {
	
	/**
     * 객체 바인딩 없음<br />
     * <code>HttpServletRequest</code> 요청 객체를 인자로 받아 multipart 인 경우와 일반인 경우를
     * 구분하여 multipart인 경우 파일이 있다면 저장하고, 파라미터는 <code>HashMap</code>으로 반환한다.
     *
     * @param request
     * @return <code>HttpServletRequest</code>의 parameter와 파일정보를
     *         <code>HashMap</code>로 반환
     */
	public static Map process(HttpServletRequest request) {

        return parseRequestMap(request);
    }
	
	/**
     * <code>HttpServletRequest</code> 요청 객체를 인자로 받아 multipart 인 경우와 일반인 경우를
     * 구분하여 multipart인 경우 파일이 있다면 저장하고, 파라미터는 <code>HashMap</code>으로 반환한다.<br />
     * <br />
     * 추가적으로 인자로 넘어온 bindObj의 맴버 변수에 parameter 값을 설정한다.<br />
     * <br />
     *
     * @param request
     *            요청객체
     * @param bindObj
     *            파라미터를 바인딩할 <code>Object</code>
     * @return <code>HttpServletRequest</code>의 parameter와 파일정보를
     *         <code>HashMap</code>로 반환
	 * @throws Exception 
     * @throws FileUploadException
     */
    public static Map process(HttpServletRequest request, Object bindObj) throws Exception {

        Map hm = parseRequestMap(request);
        BindUtil.bind(bindObj, hm);

        return hm;
    }
	/**
     * <code>HttpServletRequest</code>의 parameter를 <code>HashMap</code> 키와 값으로
     * 반환<br />
     * HttpServletRequest.getParameterValues(paramName)의 결과 기준으로 복수의 배열이 생성되는
     * 경우에는 List로 생성된다.
     * <p />
     *
     * 예 : List param = (List)Map.get(paramName);<br />
     * checkbox나 multi 속성의 select의 경우라도 1개의 값 만 넘어온경우는 String 타입으로 반환된다.
     *
     * @param request
     *            <code>HttpServletRequest</code> 요청객체
     * @return Map <code>HashMap</code>
     */
	public static Map parseRequestMap(HttpServletRequest request) {
        String tempStr;
        String[] tempStrArr;
        Enumeration e = request.getParameterNames();
        HashMap hm = new HashMap();

        while (e.hasMoreElements()) {
            tempStr = (String) e.nextElement();
            tempStrArr = request.getParameterValues(tempStr);

            if (null != tempStrArr) {
                if (tempStrArr.length > 1) {
                    int arrCnt = tempStrArr.length;
                    ArrayList al = new ArrayList();
                    for (int i = 0 ; i < arrCnt ; i++) {
                        al.add(HtmlUtil.getRemoveScript(tempStrArr[i]));
                    }

                    hm.put(tempStr, al);
                }
                else {
                    hm.put(tempStr, HtmlUtil.getRemoveScript(tempStrArr[0]));
                }
            }
        }
        return hm;
    }
	
}
