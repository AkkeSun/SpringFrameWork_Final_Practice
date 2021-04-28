<%@page import="bit.com.a.dto.BbsDto"%>
<%@page import="bit.com.a.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- coretag --> 
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>


<div align="center">
<table class="list_table" style="width: 85%">
<colgroup>
	<col style="width: 100px">
	<col style="width: 400px">
</colgroup>

<tr>
	<th>작성자</th>
	<td>${bbs.id }</td>
</tr>

<tr>
	<th>제목</th>
	<td>${bbs.title }</td>
</tr>

<tr>
	<th>작성일</th>
	<td>${bbs.wdate }</td>
</tr>

<tr>
	<th>조회수</th>
	<td>${bbs.readcount }</td>
</tr>

<tr>
	<th>정보</th>
	<td>"ref-step=depth" ${bbs.ref }-${bbs.step }-${bbs.depth }</td>
</tr>

<tr>
	<th>내용</th>
	<td align="center">
	<textarea rows="15" cols="90" readonly="readonly">${bbs.content }</textarea>
	</td>
</tr>
</table>

<br>
<button type="button" onclick="location.href='bbslist.do'">글목록</button>
<c:if test="${login.id !=null && login.id != '' }">
	<button type="button" onclick="answerbbs(${bbs.seq})">답글</button>
	<c:if test="${bbs.id == login.id}">
		<button type="button" onclick="updateBbs(${bbs.seq})">수정</button>
		<button type="button" onclick="deleteBbs(${bbs.seq})">삭제</button>
	</c:if>
</c:if>
</div>

<script type="text/javascript">
function updateBbs(seq) {
	location.href = "bbsupdate.do?seq=" + seq;
}

function deleteBbs(seq) {
	location.href = "bbsDelete.do?seq=" + seq;
}

function answerbbs(seq) {
	location.href = "answer.do?seq=" + seq;	
}

</script>