<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"      uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사이트관리 리스트</title>
<script type="text/javascript">
//사이트 삭제
function siteD(addr){
	alert(addr+"삭제 처리");
}

//등록 이동
function goForm(){
	window.location.href="/mng/site/siteForm.do";
}

//페이징처리
function linkPage(pageNo){
//페이지를 다시 불러오는 것을 방지하기 위해 preventDefault()를 호출
$("#searchForm").on("submit", function(event) {
  event.preventDefault();
  $("#pageNo").val(pageNo);
});
}
</script>
</head>

<body>
<section id="content">
	<h1>사이트관리 </h1>
	<div class="location">사이트관리&gt; <strong>사이트 목록</strong></div>
	<form name="searchForm" id="searchForm" method="post" action="/mng/site/siteList.do">
	<input type="hidden" name="pageNo" value="${resultMap.pageNo }">
	<input type="hidden" name="dataStatus" id="dataStatus">
		
		<div class="searchBox" style="padding:3%;">
			<dl class="kind">
				<dt>등록일</dt>
				<dd>
					<input type="text" name="shSdate" class="inpText input_150" value="${resultMap.shSdate }" placeholder="시작일"/>
					~ <input type="text" name="shEdate" class="inpText input_150" value="${resultMap.shEdate }" placeholder="종료일" />
				</dd>
			</dl>
			<dl class="kind">
				<dt>도메인 명</dt>
				<dd>
					<input type="text" name="shText" class="inpText input_150" value="${resultMap.shText }" placeholder="도메인 명을 입력해 주세요."/>
				</dd>
			</dl>
			<div class="btnGroup">
				<button id="jsSearch" class="btnRed btn">검색</button>
				<button id="jsClear"  class="btn">초기화</button>
			</div>
		</div>
	</form>
	<div class="tableWrap">
		<div class="tableInfo">
			<span> ${fn:length(resultList)}건</span>
		</div>
		<table class="tableList">
			<caption></caption>
			<colgroup>
				<col style="width:5%;" />
				<col style="width:10%;" />
				<col style="width:20%;"/>
				<col style="width:15%;" />
				<col style="width:15%;" />
				<col style="width:10%;" />
				<col style="width:20%;"/>
				<col style="width:10%;" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">도메인명</th>
					<th scope="col">도메인 주소</th>
					<th scope="col">등록일자</th>
					<th scope="col">만료기간</th>
					<th scope="col">남은일자</th>
					<th scope="col">도메인 관리사이트</th>
					<th scope="col">삭제</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="list" items="${resultList }" varStatus="status">
				<tr>
					<td><c:out value="${resultMap.paginationInfo.totalRecordCount+1 - ((resultMap.pageNo-1) * resultMap.paginationInfo.recordCountPerPage + status.count)}"/></td>
					<td><c:out value="${list.SI_DNAME_V }" /></td>
					<td><a href="http://${list.SI_DADDR_V }" target="_blank"> <c:out value="${list.SI_DADDR_V }" /></a></td>
					<td><c:out value="${list.reg_dt_d }" /></td>
					<td><c:out value="${list.SI_DENDDT_V }" /></td>
					<td><c:out value="${list.USE_DAYS }" /></td>
					<td><a href="http://${list.SI_DSITE_V }" target="_blank"> <c:out value="${list.SI_DSITE_V }" /></a></td>
					<td><a href="#none;" onclick="siteD('${list.SI_DADDR_V}');">삭제</a></td>
					
				</tr>
				</c:forEach>
				
				<c:if test="${(empty resultList && param.searchCheck == 1) || (empty resultList)}">
					<tr>
						<td colspan="8">데이터가 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	
	<div class="paging" style="padding-top: 20px;">
		﻿<div class="paging">
 			<span class="wrap">
 				<ui:pagination paginationInfo = "${resultMap.paginationInfo}" type="image" jsFunction="linkPage"/>
        	</span>
		</div>
	</div>
	
	
	<div class="btnGroupWrap">
		<div class="right">
			<button id="jsForm" onclick="goForm();">등록</button>
		</div>
	</div>
</section>

</body>

<script type="text/javascript">
$(function(){
	//등록폼이동
	/* $("#jsForm").click(function(){
		alert("fo");
			$("#dataStatus").val("I");
			$("#searchForm").attr("action", "/mng/site/siteForm.do");
			$("#searchForm").submit();
	}); */
	
	

});



</script>