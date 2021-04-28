<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div align="center">
<form action="bbsWriteAf.do" method="post">
<table class="list_table" style="width: 85%">
<col width="200"><col width="400">

<tr>
	<th>아이디</th>
	<td><input type="text" name="id" size="50px" value=${login.id } readonly="readonly"></td>
</tr>

<tr>
	<th>제목</th>
	<td><input type="text" name="title" size="50px"></td>
</tr>

<tr>
	<th>내용</th>
	<td><textarea rows="20" cols="50px" name="content"></textarea></td>
</tr>
<tr>
	<td colspan="2">&nbsp;&nbsp;<input type="submit" value="글쓰기"></td>
</tr>

</table>
</form>
</div>