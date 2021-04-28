
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>


<div align="center">
<form name="frmForm" id="_frmForm" action="pdsupdate.do" method="post"
	enctype="multipart/form-data">
<!-- hidden -->	
<input type='hidden' name='seq' value=${pds.seq }>	
<input type='hidden' name='filename' value=${pds.filename }>	
<input type='hidden' name='newfilename' value=${pds.newfilename }>	

<table class='list_table' style='width:85%'>
<col width="100"><col width="250">
<tr>
	<th>게시자</th>
	<td><input class='form-control' name='id' id="_id" type="text" value='${pds.id }' readonly></td>	
</tr>

<tr>
	<th>제목</th>
	<td><input class='form-control'  name='title' id='_title' type="text" value='${pds.title }' readonly></td>
</tr>

<tr>
	<th>조회수</th>
	<td><input class='form-control' type="text" value='${pds.readcount }' readonly></td>
</tr>

<tr>
	<th>다운수</th>
	<td><input class='form-control' type="text" value='${pds.downcount}' readonly></td>
</tr>

<tr>
	<th>이미지</th>
	<td>
	<img src="./upload/${pds.newfilename}" width=300px> <!-- 부를땐 newfilename -->
	</td>
</tr>

<tr id='_newFileload'>
	<th>새로운 파일</th>
	<td style="text-align: left;">
		<input type="file" name="fileload" style="width: 400px">
	</td>
</tr>

<tr>
	<th>등록일</th>
	<td><input class='form-control'  type="text" value='${pds.regdate}' readonly></td>
</tr>

<tr>
	<th>내용</th>
<td>
<textarea class="form-control" readonly rows="2"  id="_content" name="content" style="resize:none">
${pds.content}
</textarea> 

</td>
</tr>

<tr>
	<td colspan="2">
		<!-- 수정 -->	 
		<input type="button" class='btn btn-light' id='_update' value="수정">	
		<!-- 삭제 -->
		<input type="button" class='btn btn-light' id="_del" value="삭제"
			    onclick="location.href='pdsdelete.do?seq=${pds.seq }'"/>
		<!-- 목록 -->
		<input type="button" class='btn btn-light' id='_goList' onclick="javascript:history.back(-1)" value="목록">
		<!-- 수정완료 -->
		<input type="submit" id='_updateBtn' class='btn btn-light' value="수정완료">	
		<!-- 수정취소 -->
		<input type="button"  id='_updateCan' class='btn btn-light' onclick="javascript:history.back(-1)" value="수정취소">

	</td>
</tr>
</table>
</form>
</div>

<!-- ///////// 다운로드 /////////// -->
<!-- filedown은 무조건 POST로 가야하는데 javascript로 보내면 무조건 GET으로 간다. 해결법!!! -->
<form name="file_Down" action="fileDownload.do" method="post">
	<input type="hidden" name="filename">
	<input type="hidden" name="newfilename">
	<input type="hidden" name="seq">
</form>

<script>
function filedown(filename, newfilename, seq) {
	//name값에 접근
	let doc = document.file_Down;
	//value값 지정
	doc.filename.value = filename;
	doc.newfilename.value = newfilename;
	doc.seq.value = seq;
	//내보내기
	doc.submit();
}
</script>


<!-- /////update//////// -->
<script>
$(document).ready(function () {
//update 부분 숨겨놓기	
$('#_newFileload').hide(); 
$('#_updateBtn').hide(); 
$('#_updateCan').hide();

//수정버튼 눌렀을 때 
$("#_update").click(function(){
		$("#_content").attr('readonly', false)
		$("#_title").attr('readonly', false)
		$("#_golist").hide();
		$("#_update").hide();
		$("#_del").hide();
		$("#_goList").hide();
		$("#_updateBtn").show();
		$("#_updateCan").show();
		$('#_newFileload').show(); 

	});
});

</script>




