<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"      uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="decorator" 	uri="http://www.opensymphony.com/sitemesh/decorator" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title><decorator:title default="Spring-SiteMesh" /></title>
<link href="/resource/css/reset.css" rel="stylesheet" />
<link href="/resource/css/contents.css" rel="stylesheet" />
<script type="text/javascript" src="/resource/js/common.js"></script>
<script type="text/javascript" src="/resource/js/ui.js"></script>
<script type="text/javascript" src="/resource/common/js/common.js"></script>
<decorator:head /> 
<script type="text/javascript">
	function jsLogout() {
		if(confirm("로그아웃 하시겠습니까?")) {
			location.href="/mLogin/logout.do";
		}
	}
	
</script>
</head>
<body>
<%-- <c:set var="currentUrl" value="${pageContext.request.requestURI}?${pageContext.request.queryString}"/> --%>
<div id="wrap">
	<header id="header">
		<h1><a href="#"><img src="/resource/images/logo.gif" alt="ento study" /></a></h1>
		<div class="topLink">
			<strong>OOO님이 로그인하셨습니다.</strong>
			<a href="#loginOut" onclick="jsLogout();">로그아웃</a>
			<a href="/index.jsp" target="_blank"> 사이트가기</a>
		</div>
	</header>
	
	<section id="container">
		<div class="bar"></div>
		<nav id="gnb">
			<h1>Navigation</h1>
			<a href="#" class="btnMenu">메뉴</a>
			<ul>
				<li class="menu1 on"><a href="/mng/me/member/memberList.do">회원관리</a>
					<div class="subBox">
						<a href="/mng/me/member/memberList.do" class="on">등록회원관리</a>
						<a href="#" class="">탈퇴회원관리</a>
					</div>
				</li>
				<li class="menu8"><a href="#">운영자관리</a>
					<div class="subBox">
						<a href="/mng/site/siteList.do" class="#">사이트관리</a>
						<a href="/mng/admin/adminList.do" class="#">운영자관리</a>
						<a href="/mng/common/code/codeList.do" class="#">공통코드관리</a>
						<a href="/mng/sample/sampleList.do" class="#">샘플목록</a>
					</div>
				</li>
			</ul>
		</nav>
		<decorator:body /> 
		</section>
</div>
</body>
</html>