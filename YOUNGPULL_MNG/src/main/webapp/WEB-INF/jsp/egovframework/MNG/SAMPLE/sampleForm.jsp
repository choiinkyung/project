<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"      uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
</script>
</head>

<body>
<!-- //검색파라미터/페이징 -->
<section id="content">
<form name="dForm" method="post" id="dForm" action="/management/bbs/bbsRegist.do" enctype="multipart/form-data">
<input type="hidden" name="man_id_v" value="<c:out value="${sessionScope.m_session.man_id_v}" />">
<!-- 검색파라미터/페이징 -->
<input type="hidden" name="pageNo" value="${resultMap.pageNo }">
<input type="hidden" name="bs_seq_n" value="${resultMap.bs_seq_n}">
<input type="hidden" name="bi_thumbNail_v" id="bi_thumbNail_v" />
	<h1>E.fit 관리</h1>
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
					<th scope="row">제목 *</th>
					<td colspan="3">
						<input type="text" name="bi_title_v" class="input_300 required" value="" maxlength="20" title="제목" />
					</td>
				</tr>
				
				<tr>
					<th scope="row">분류 *</th>
					<td>
						<select name="bi_gubun_v" id="bi_gubun_v" title="분류" class="select">
							<option value="">분류선택</option>
							<option value="A">Healthy</option>
							<option value="B">Learning</option>
							<option value="C">Story</option>
						</select>
					</td>
					<th scope="row">조회수 *</th>
					<td>
						<input type="text" name="bi_hitCount_n" class="input_150 required validate-digits validate-white-space" value="" maxlength="20" title="조회수" />
					</td>
				</tr>
				
				<tr>
					<th scope="row">썸네일 이미지</th>
					<td colspan="3" class="photoWrap">
						<p id="imgInput">
							<input type="file" name="fileName" id="fileName" title="썸네일 이미지" class="required" onchange="imgUpdate();"/><br /><br />				
						</p>
						<p class="photo" style="display:none;" id="imgView">
						</p>
						<div class="buttonGroup" style="display:none;" id="imgRegist">
							<a href="#upload" class="btn" onclick="imgDelete('delete'); return false;">삭제</a>
							<a href="#upload" class="btn" onclick="imgDelete('update'); return false;">변경</a>
						</div>
						<div class="text">
							<span class="txtRed">* gif, png, jpg / 10M 이하 파일만 등록 가능합니다.</span><br />
							* 370X278 이미지만 등록 가능 합니다.
						</div>
					</td>
				</tr>
	
				<tr>
					<th scope="row">내용</th>
					<td colspan="3">
						<textarea name="bi_content_t" id="ckeditor" rows="10" cols="100" style="width:766px; height:412px;"></textarea>
						<script>
						CKEDITOR.replace( 'ckeditor', {
							filebrowserUploadUrl: '/cmmn/file/ckeditorImageUpload.do'
						});
						</script>
					</td>
				</tr>
				<tr>
					<th scope="row">공개여부 *</th>
					<td colspan="3">
						<input type="radio" name="bi_openYn_c" id="bi_openYn_c1" value="Y" checked><label for="bi_openYn_c1">공개</label>
						<input type="radio" name="bi_openYn_c" id="bi_openYn_c2" value="N"><label for="bi_openYn_c2">비공개</label>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>
	<div class="btnGroupWrap">
		<div class="right">
			<button onclick="jsInsert();return false;">등록</button>
			<button onclick="jsList();return false;">취소</button>
		</div>
	</div>
</section>

</body>