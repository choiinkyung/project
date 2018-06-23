<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : egovSampleList.jsp
  * @Description : Sample List 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2009.02.01            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.02.01
  *
  * Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>
<head>
<title><c:out value="${resultMap.title }" />결과 페이지</title>
<script type="text/javascript">
<c:if test="${empty resultMap.returnUrl}">
alert("${resultMap.message }");
history.back();
</c:if>
<c:if test="${!empty resultMap.returnUrl}">
	jsResult();
	function jsResult() {
		alert("${resultMap.message }");
		location.href="${resultMap.returnUrl}";
	}
</c:if>	
</script>
</head>
<body>
${resultMap.returnUrl }
<%-- 	<div class="success">
		<form name="dForm" method="post" onsubmit="jsResult();return false;">
			<div class="result">
				<c:out value="${resultMap.message }"  escapeXml="false" /> <br /><br />
				<a href="javascript:jsResult();" class="btn" style="font-size:25px;">확인</a>			
			</div>	
				
		</form>
	</div> --%>
</body>
