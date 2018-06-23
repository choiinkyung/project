<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"      uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="decorator" 	uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<meta charset="utf-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<title><decorator:title default="Spring-SiteMesh" /></title>
<link href="/resource/user/css/reset.css" rel="stylesheet" />
<link href="/resource/user/css/common.css" rel="stylesheet" />
<link href="/resource/user/css/contents.css" rel="stylesheet" />
<link href="/resource/common/css/validator.css" rel="stylesheet" >
<link href="/resource/user/css/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" />
<script type="text/javascript" src="/resource/common/js/new_validator.js"></script>
<script type="text/javascript" src="/resource/user/js/lib/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="/resource/user/js/lib/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="/resource/user/js/common.js"></script>
<script type="text/javascript" src="/resource/user/js/ui.js"></script>
<script type="text/javascript" src="/resource/common/js/common.js"></script>
<!--[if lt IE 9]>
    <script type="text/javascript">
        document.createElement("header"); 
        document.createElement("nav"); 
        document.createElement("section"); 
        document.createElement("article"); 
        document.createElement("footer");
    </script>
<![endif]-->

<script type="text/javascript">
</script>
<decorator:head /> 
</head>
<body <decorator:getProperty property="body.class" writeEntireProperty="true" />>
	<section id="container">
		<decorator:body /> 
	</section>
	
</body>
</html>
