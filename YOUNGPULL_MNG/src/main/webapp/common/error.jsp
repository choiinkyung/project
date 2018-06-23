<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isErrorPage="true" %>
<%
response.setStatus(HttpServletResponse.SC_OK);

Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");

if (servletName == null) {
	servletName = "Unknown";
}
String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

if (requestUri == null) {
	requestUri = "Unknown";
}

if (statusCode != 500) {
	out.write("statusCode = " + statusCode + " <br />");
	out.write("requestUri = " + requestUri);
}
if (throwable != null) {
	out.write("servletName = " + servletName + "<br />");
	out.write("exceptionName  = " + throwable.getClass().getName() + "<br />");
	out.write("requestUri = " + requestUri + " <br />");
	out.write("exception message = " + throwable.getMessage() + "<br />");
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/egovframework/sample.css'/>" />
<title><%=statusCode %></title>
</head>

<body>



   <%--  <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="100%" height="100%" align="center" valign="middle" style="padding-top: 150px;"><table border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="<spring:message code='image.errorBg' />">
                            <span style="font-family: Tahoma; font-weight: bold; color: #000000; line-height: 150%; width: 440px; height: 70px;">
                            	<c:catch var ="catchException">
								   <c:out value="${requestScode['javax.servlet.error.mesasge']}" />
								</c:catch>
								
								<c:if test = "${catchException != null}">
								   <p>The exception is : ${catchException} <br />
								   There is an exception: ${catchException.message}</p>
								</c:if>
                            	
                            </span>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table> --%>
</body>
</html>