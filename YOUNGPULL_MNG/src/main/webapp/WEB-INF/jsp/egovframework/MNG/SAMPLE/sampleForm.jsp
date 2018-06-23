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
		
		$("#jsSave").click(function(){
			if (confirm("저장하시곘습니까?")){
				saveData("dataForm", "/mng/sample/sampleProc.json","jsList");
			}
		});
		$("#jsList").click(function(){
			$("#dataForm").attr("action", "/mng/sample/sampleList.do");
	        $("#dataForm").submit();  
		});
		$("#jsDelete").click(function(){
			$("#dataStatus").val("D");
			$("#dataForm").attr("action", "/mng/sample/sampleProc.do");
		});
	});
	
</script>
</head>

<body>
<!-- //검색파라미터/페이징 -->
<section id="content">
<form name="dataForm" method="post" id="dataForm" action="/mng/sample/json/sampleForm.do" enctype="multipart/form-data">
<input type="hidden" name="pageNo" value="${resultMap.pageNo }">
<input type="hidden" name="dataStatus" id="dataStatus" value="${resultMap.dataStatus}">
<c:if test="${resultMap.dataStatus eq 'U'}">
	<input type="hidden" name="seq_v" id="seq_v" value="<c:out value="${sample.seq_v}"/>">
</c:if>

	<h1>샘플 - 등록/수정</h1>
	<div class="location">Main &gt;콘텐츠관리&gt; <strong>E.fit 관리</strong></div>
	<div class="tableWrap">
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
					<th scope="row">작성자 *</th>
					<td> <input type="text" name="name_v" id="name_v" class="input_300" value="<c:out value="${sample.name_v}"/>" maxlength="20" title="제목" /> </td>
					<th scope="row">비밀번호 *</th>
					<td> <input type="text" name="pwd_v" id="pwd_v" class="input_300" value="<c:out value="${sample.pwd_v}"/>" maxlength="20" title="제목" /> </td>
				</tr>
				<tr>
					<th scope="row">첨부파일(생략)</th>
					<td colspan="3" class="photoWrap">
						<p id="imgInput">
							<input type="file" name="fileName" id="fileName" title="첨부파일" class="required" /><br /><br />				
						</p>
						<div class="text">
							<span class="txtRed">* gif, png, jpg / 10M 이하 파일만 등록 가능합니다.</span><br />
							* 370X278 이미지만 등록 가능 합니다.
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>
	<div class="btnGroupWrap">
		<div class="right">
			<button id="jsSave">저장</button>
			<button id="jsList">취소</button>
		</div>
	</div>
</section>

</body>