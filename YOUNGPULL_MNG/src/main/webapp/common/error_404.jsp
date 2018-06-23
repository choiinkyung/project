<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isErrorPage="true" %>
<%
response.setStatus(HttpServletResponse.SC_OK);

Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");



%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/resource/common/css/error.css" />
<title><%=statusCode %> 에러페이지</title>
</head>

<body>
<div class="error_page_wrap">
		<div class="error_page_img">
			<img src="/resource/common/img/error_404_img.png" />
		</div>
		<div class="error_page_text">
			<div class="error_page_text_div01">
				<h2>주소가 잘못 입력 되었거나<br />
				원하시는 페이지를 찾을 수 없습니다.</h2>
				<p>요청하신 페이지를 실행하는 데 문제가 발생했습니다.</p>
				<p>링크 정보를 다시 확인해 주시기 바랍니다.</p>
			</div>
			<p class="error_page_border"></p>
			<div class="error_page_text_div02">
				<h2>Error-Page Not Found</h2>
				<p>The page you were looking for has been moved or no longer exists.</p>
			</div>
		<%
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
		</div>
		
	</div>	
</body>
</html>