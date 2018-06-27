<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"      uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	
	//초기화
	jsCodeList(1);
	
	//검색
	$("#jsSearch").click(function(){
		jsCodeList(1);
	});
  
	//초기화
	$("#jsClear").click(function(){
		$("#searchForm").submit(function(event) {
			$("#searchForm").reset();
		});
	});
	
	//신규
	$("#jsNew").click(function(){
		$("form").each(function(){
		    this.reset();
		 });
		$("input[name=code_id_v]").attr("readonly",false);
		$("input[name=dataStatus]").val("I");
		$("input[name=code_upId_v]").val(null);
		$("#code_upId_v").empty();
		$("#code_upId_v").append("자동생성됩니다.");
	});
	
	//등록폼이동
	$("#jsForm").click(function(){
		$("#dataStatus").val("I");
		$("#searchForm").attr("action", "/mng/sample/sampleForm.do");
        $("#searchForm").submit();  
	});
	
	//엑셀다운로드
	$("#jsExcelDown").click(function(){
		$("#searchForm").submit(function(event) {
			$("#searchForm").attr("action", "/mng/sample/excel/excelDown.do");
			event.preventDefault();
	   });
	});
	
	$("#jsSave").click(function(){
		if (confirm("저장하시곘습니까?")){
			/* $("array_code_id").val($("input[name=code_id_v]").map(function(){ 
								if (this.value != "") {
									return this.value; 
								}
							  }).get().join(",")); */
			saveData("dataForm", "/code/codeProc.json","jsSearch");
			jsCodeList(1);				  
		}
	});
	
});

	
function jsCodeList(gubun) {
	$.ajax({
		 url : "/code/codeList.json"
		,type:"post"
		,data : $("#searchForm").serialize()
		,dataType : "json"
		,success:function(data){
			if (data.result == "SUCCESS"){
				if (gubun != 1) {
					alert(data.returnMsg);
				}
				var strHtml = "";
				$("#dataList").empty();
				var prevCode = "";
				for (var i=0; i<data.returnList.length; i++) {
					if (prevCode == data.returnList[i].code_upId_v) continue;
					strHtml +="<tr>"; 
					strHtml +="<td  onclick=jsView('"+data.returnList[i].code_upId_v+"')>"+data.returnList[i].code_upId_v+"</td>"; 
					strHtml +="<td>"+data.returnList[i].code_upName_v+"</td>"; 
					strHtml +="</tr>";
					prevCode = data.returnList[i].code_upId_v;
				}
				$("#dataList").html(strHtml);
			}
		}
		,error : function(data) {
			console.log("err");
			alert(data.returnMsg);
		}
	})
	
}


function jsView(code_upId_v) {
	$("#code_upId_v").attr("readonly",true);
	$("#codeUpId").val(code_upId_v);
	$("input[name=code_upId_v]").val(code_upId_v);
	$.ajax({
		 url : "/code/codeList.json"
		,type:"post"
		,data : $("#searchForm").serialize()
		,dataType : "json"
		,success:function(data){
			if (data.result == "SUCCESS"){
				for (var i=0; i<data.returnList.length; i++) {
					$("#code_upId_v").empty();
					$("#code_upId_v").append(data.returnList[0].code_upId_v);
					$("#code_upName_v").val(data.returnList[0].code_upName_v);
					$("input[name=dataStatus]:eq("+i+")").val("U");
					$("input[name=code_id_v]:eq("+i+")").val(data.returnList[i].code_id_v);
					$("input[name=code_id_v]:eq("+i+")").attr("readonly",true);
					$("input[name=code_name_v]:eq("+i+")").val(data.returnList[i].code_name_v);
					$("input[name=code_order_n]:eq("+i+")").val(data.returnList[i].code_order_n);
					$("input[name=code_ref1_v]:eq("+i+")").val(data.returnList[i].code_ref1_v);
					$("input[name=code_ref2_v]:eq("+i+")").val(data.returnList[i].code_ref2_v);
					$("input[name=code_ref3_v]:eq("+i+")").val(data.returnList[i].code_ref3_v);
					$("input[name=code_ref4_v]:eq("+i+")").val(data.returnList[i].code_ref4_v);
					$("input[name=code_ref5_v]:eq("+i+")").val(data.returnList[i].code_ref5_v);
				}
			}
		}
		,error : function(data) {
			console.log("err");
			alert(data.returnMsg);
		}
	})
}


</script>
</head>

<body>
<section id="content">
	<h1>공통코드관리 - 목록</h1>
	<div class="location">Main &gt;운영관리&gt; <strong>공통코드</strong></div>
	<form name="searchForm" id="searchForm" method="post" action="/mng/common/code/codeList.do">
	<input type="hidden" id="codeUpId" name="codeUpId" />
		<div class="searchBox" style="padding:3%;">
			<dl class="kind">
				<dt>검색조건</dt>
				<dd>
					상위코드 : <input type="text" name="codeId" id="codeId" class="inpText input_150"  />
					상위코드명 : <input type="text" name="codeName" id="codeName" class="inpText input_150"  />
				</dd>
			</dl>
			<div class="btnGroup">
				<button id="jsSearch" class="btnRed btn">검색</button>
				<button id="jsClear"  class="btn">초기화</button>
			</div>
		</div>
	</form>
	<div class="tableWrap">
		<div class="btnGroupWrap">
			<div class="right">
				<button id="jsNew">신규</button>
				<button id="jsSave">저장</button>
			</div>
		</div>
		<table>
			<colgroup>
				<col width="30%" />
				<col width="5%" />
				<col width="65%" />
			</colgroup>
			<tbody>
			<form id="dataForm" name="dataForm" method="post">
			<input type="hidden" name="code_upId_v" />
				<tr>
					<td>
						<table class="tableList2" style="height:300px; overflow: auto;">
							<caption></caption>
							<colgroup>
								<col style="width:40%;" />
								<col style="width:60%;" />
							</colgroup>
							<thead>
								<tr>
									<th scope="col">상위코드번호</th>
									<th scope="col">상위코드명</th>
								</tr>
							</thead>
							<tbody id="dataList">
								
							</tbody>
						</table>
					</td>
					<td></td>
					<td>
							<table class="tableList2">
								<caption></caption>
								<colgroup>
									<col style="width:160px;" />
									<col />
									<col style="width:160px;" />
									<col />
								</colgroup>
								<tbody>
									<tr>
										<th scope="row">상위 코드아이디 *</th>
										<td colspan="2" id="code_upId_v">
											자동생성됩니다.
											<!-- <input type="text" name="code_upId_v" id="code_upId_v" class="input_300" maxlength="20" title="코드아이디" /> -->
										</td>
										<th scope="row">상위 코드명 *</th>
										<td colspan="2">
											<input type="text" name="code_upName_v" id="code_upName_v" class="input_300" maxlength="20" title="코드명" />
										</td>
									</tr>
								</tbody>
							</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<table class="tableList">
							<caption></caption>
							<colgroup>
								<col style="10%;" />
								<col style="10%;" />
								<col style="10%;" />
								<col style="10%;" />
								<col style="10%;" />
								<col style="10%;" />
								<col style="10%;" />
								<col style="10%;" />
							</colgroup>
							<thead>
								<tr>
									<th scope="col">코드아이디</th>
									<th scope="col">코드명</th>
									<th scope="col">정렬순서</th>
									<th scope="col">참조1</th>
									<th scope="col">참조2</th>
									<th scope="col">참조3</th>
									<th scope="col">참조4</th>
									<th scope="col">참조5</th>
								</tr>
							</thead>
							<tbody class="addSub">
								<c:forEach var="i" begin="1" end="10">
									<tr>
										<input type="hidden" name="dataStatus" id="dataStatus" value="I"/>
										<td><input type="text" name="code_id_v" value="" style="width:100%;"/></td>
										<td><input type="text" name="code_name_v" value=""style="width:100%;"/></td>
										<td><input type="text" name="code_order_n" value=""style="width:100%;"/></td>
										<td><input type="text" name="code_ref1_v" value=""style="width:100%;"/></td>
										<td><input type="text" name="code_ref2_v" value=""style="width:100%;"/></td>
										<td><input type="text" name="code_ref3_v" value=""style="width:100%;"/></td>
										<td><input type="text" name="code_ref4_v" value=""style="width:100%;"/></td>
										<td><input type="text" name="code_ref5_v" value=""style="width:100%;"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
			</form>
			</tbody>
		</table>
		
	</div>
	
	
</section>
</body>