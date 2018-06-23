<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="decorator" 	uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title><decorator:title default="Spring-SiteMesh" /></title>
<link href="/resource/user/css/reset.css" rel="stylesheet" />
<link href="/resource/user/css/common.css" rel="stylesheet" />
<link href="/resource/user/css/contents.css" rel="stylesheet" />
<script type="text/javascript" src="/resource/common/js/jquery-1.11.3.min.js" ></script>
<script type="text/javascript" src="/resource/common/js/common.js"></script> 
	<decorator:head /> 
</head>
<body>
	<decorator:body />
</body>
</html>