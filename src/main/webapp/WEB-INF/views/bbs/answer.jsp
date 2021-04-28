<%@page import="bit.com.a.dto.MemberDto"%>
<%@page import="bit.com.a.dto.BbsDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div align="center">
<table class="list_table" style="width: 85%">
<col width="200"><col width="500">
<tr>
	<td>작성자</td>
	<td>${bbs.id }</td>
</tr>
<tr>
	<td>제목</td>
	<td>${bbs.title }</td>
</tr>
<tr>
	<td>작성일</td>
	<td>${bbs.wdate }</td>
</tr>
<tr>
	<td>조회수</td>
	<td>${bbs.readcount }</td>
</tr>
<tr>
	<td>내용</td>
	<td><textarea rows="10" cols="50" readonly="readonly">${bbs.content }</textarea></td>
</tr>
</table>
<br><br><br>



<form class="list_table" style="width: 85%">
<input type="hidden" name="seq" value="${bbs.seq }"> <!-- 부모글의 seq -->
<table class="list_table" style="width: 85%">
<col width="200"><col width="500">
<tr>
	<td>아이디</td>
	<td><input type="text" name="id" size="50" readonly="readonly" value="${login.id }"></td>
</tr>
<tr>
	<td>제목</td>
	<td><input type="text" name="title" size="50"></td>
</tr>
<tr>
	<td>내용</td>
	<td><textarea rows="10" cols="50" name="content"></textarea></td>
</tr>
<tr>
	<td colspan="2"><input type="submit" value="답글작성완료"></td>
</tr>

</table>
</form>
</div>