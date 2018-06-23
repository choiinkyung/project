<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"      uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//페이징
$(document).ready(function(){
	//검색
	$("#jsSearch").click(function(){
		$("#dataForm").on("submit", function(event) {
			event.preventDefault();
		});
	});
  
	//초기화
	$("#jsClear").click(function(){
		$("#dataForm").on("submit", function(event) {
			event.preventDefault();
			$("#dataForm").reset();
		});
	});
	
	//등록폼이동
	$("#jsInsertForm").click(function(){
		$("#dataForm").on("submit", function(event) {
			event.preventDefault();
			$("#dataForm").attr("action", "/MNG/sample/sampleForm.do");
	   });
	});
	
	//엑셀다운로드
	$("#jsExcelDown").click(function(){
		$("#dataForm").on("submit", function(event) {
			event.preventDefault();
			$("#dataForm").attr("action", "/MNG/sample/excel/excelDown.do");
	   });
	});
   
	
});
	
    
//페이징처리
function linkPage(pageNo){
 //페이지를 다시 불러오는 것을 방지하기 위해 preventDefault()를 호출
   $("#dataForm").on("submit", function(event) {
      event.preventDefault();
      $("#pageNo").val(pageNo);
   });
}

</script>
</head>

<body>
<section id="content">
	<h1>샘플 - 목록</h1>
	<div class="location">Main &gt;운영관리&gt; <strong>샘플</strong></div>
	<form name="searchForm" method="post" action="/MNG/sample/sampleList.do">
	<input type="hidden" name="pageNo" value="${resultMap.pageNo }">
	<input type="hidden" name="dataStatus" id="dataStatus">
		
		<div class="searchBox" style="padding:3%;">
			<dl class="kind">
				<dt>검색조건</dt>
				<dd>
					<select class="select" name="schKey">
						<option value="all">전체</option>
						<option value="schId" <c:if test="${resultMap.schKey eq 'schId' }">selected="selected"</c:if> >아이디</option>
						<option value="schName" <c:if test="${resultMap.schKey eq 'schName' }">selected="selected"</c:if> >이름</option>
					</select>
					<input type="text" name="schVal" class="inpText input_150" value="${resultMap.schVal }" />
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
				<col style="width:10%;" />
				<col style="width:10%;" />
				<col />
				<col style="width:10%;" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">이름</th>
					<th scope="col">제목</th>
					<th scope="col">등록일</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="list" items="${resultList }" varStatus="status">
				<tr>
					<td><c:out value="${resultMap.paginationInfo.totalRecordCount+1 - ((resultMap.pageNo-1) * resultMap.paginationInfo.recordCountPerPage + status.count)}"/></td>
					<td></td>
					<td>
						<a href="javascript:jsView('<c:out value="${list.bi_seq_n }" />');">
							<c:if test="${list.new_data eq 'NEW' }"><img src="/resource/management/images/new.gif" /></c:if>
							<c:out value="${list.bi_title_v }" />
						</a>
					</td>
					<td>
						<c:if test="${list.bi_openYn_c eq 'Y' }">공개</c:if>
						<c:if test="${list.bi_openYn_c eq 'N' or empty list.bi_openYn_c}">비공개</c:if>
					</td>
					<td><c:out value="${list.reg_dt_d}" /></td> 
					
				</tr>
				</c:forEach>
				
				<c:if test="${(empty resultList && param.searchCheck == 1) || (empty resultList)}">
					<tr>
						<td colspan="4">데이터가 없습니다.</td>
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
			<button onclick="jsInsertForm();return false;">등록</button>
		</div>
	</div>
</section>
</body>