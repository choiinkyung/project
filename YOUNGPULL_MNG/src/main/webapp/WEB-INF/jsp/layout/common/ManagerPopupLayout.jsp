
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="decorator" 	uri="http://www.opensymphony.com/sitemesh/decorator" %>

<c:if test="${empty sessionScope.m_session}">
	<jsp:forward page="/mLogin/loginForm.do" />
</c:if>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title><decorator:title default="Spring-SiteMesh" /></title>
<link rel="stylesheet" href="/resource/management/css/common.css">
<link rel="stylesheet" href="/resource/management/css/layout.css">
<link rel="stylesheet" href="/resource/management/css/sub.css">
<link rel="stylesheet" href="/resource/management/css/table.css">
<link rel="stylesheet" href="/resource/common/css/validator.css">
<script type="text/javascript" src="/resource/common/js/validator.js"></script>
<script type="text/javascript" src="/resource/common/js/jquery-1.11.3.min.js" ></script>
<script type="text/javascript" src="/resource/common/js/common.js"></script> 
	<decorator:head /> 
</head>
<body>
	<decorator:body />
</body>
</html>