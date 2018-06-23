<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"      uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사이트관리 등록</title>
<script type="text/javascript">
   
</script>
</head>

<body>
<section id="content">
<form name="sFrom" method="post" id="sForm" action="/siteProc.do">
	<input type="hidden" name="type" id="type" value="insert">
	<h1>사이트 관리</h1>
	<div class="location">사이트관리&gt; <strong>사이트 등록 =관리</strong></div>
	<div class="tableWrap">
	<h3><span>도메인 정보</span></h3>
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
					<th scope="row">도메인 주소</th>
					<td>
						<input type="text">
						<a href="#none">주소 중복검사</a>
					</td>
					<th scope="row">도메인 명</th>
					<td>
						<input type="text" name="si_daddr_v" class="input_150 required validate-digits validate-white-space" value="" maxlength="20" title="조회수" />
					</td>
				</tr>
				<tr>
					<th scope="row">도메인 관리사이트</th>
					<td colspan="3">
						<input type="text" name="si_dname_v" class="input_300 required" value="" maxlength="20" title="제목" />
					</td>
				</tr>
				<tr>
					<th scope="row">계정정보</th>
					<td>
						<input type="text" name="si_did_v" placeholder="계정 아이디를 입력해 주세요.">
						<input type="text" name="si_dpwd_v"placeholder="계정 비밀번호를 입력해 주세요.">
					</td>
					<th scope="row">만료기간</th>
					<td>
						<input type="text" name="si_denddt_v" class="input_150 required validate-digits validate-white-space" value="" maxlength="20" title="조회수" />
					
					</td>
				</tr>
			</tbody>
		</table>
		
		
		<br />
		<br />
		<br />
		<h3><span>호스팅 정보</span></h3>
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
					<th scope="row">호스팅 관리사이트</th>
					<td colspan="3">
						<input type="text" name="si_haddr_v" class="input_300 required" value="" maxlength="20" title="제목" />
					</td>
				</tr>
				<th scope="row">계정정보</th>
					<td>
						<input type="text" name="si_hid_v" placeholder="계정 아이디를 입력해 주세요.">
						<input type="text" name="si_hpwd_v" placeholder="계정 비밀번호를 입력해 주세요.">
					</td>
					<th scope="row">만료기간</th>
					<td>
						<input type="text" name="si_henddt_v" class="input_150 required validate-digits validate-white-space" value="" maxlength="20" title="조회수" />
					
					</td>
				</tr>
				
				<tr>
					<th scope="row">상세정보</th>
					<td colspan="3">
					<textarea rows="10" cols="120" name="si_text_t"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="btnGroupWrap">
		<div class="right">
			<button onclick="jsInsert();return false;">등록</button>
			<button onclick="jsList();return false;">취소</button>
		</div>
	</div>
</form>
	</section>
</body>