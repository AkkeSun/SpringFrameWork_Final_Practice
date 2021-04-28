<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- coretag -->    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>   


<!-- 검색 -->
<div class="box_border" style="margin-top: 5px; margin-bottom: 10px">
<form action="" id="_frmFormSearch" method="get">

<table style="margin-left: auto; margin-right: auto; margin-top: 3px; margin-bottom: 3px">
<tr>
	<td>검색</td>
	<td style="padding-left: 5px">
		<select id="_choice" name="choice">
			<option value="" selected="selected">선택</option>
			<option value="title">제목</option>
			<option value="content">내용</option>
			<option value="writer">작성자</option>
		</select>
	</td>
	<td style="padding-left: 5px">
		<input type="text" id="_searchWord" name="searchWord">
	</td>
	<td style="padding-left: 5px">
		<span class="button blue">
			<button type="button" id="btnSearch">검색</button>
		</span>
	</td>
</tr>
</table>
</form>
</div>


<table class="list_table" id="_list_table" style='width:85%'>
<colgroup>
	<col width="50"><col width="100"><col width="300"><col width="50">
	<col width="50"><col width="50"><col width="100"><col width="50">
</colgroup>

<thead>
<tr>
	<th>번호</th><th>작성자</th><th>제목</th><th>다운로드</th>
	<th>조회수</th><th>다운수</th><th>작성일</th><th>삭제</th>
</tr>
</thead>
<tbody>

<%--
<c:forEach var="pds" items="${pdslist }" varStatus="i">

<tr>
	<th>${i.count }</th>
	<td>${pds.id }</td>
	<td style="text-align: left;">
		<a href="pdsdetail.do?seq=${pds.seq}">
			${pds.title }
		</a>
	</td>
	<td>
		<input type="button" name="btnDown" value="다운로드"
			onclick="filedown('${pds.filename}', '${pds.newfilename}', '${pds.seq }')"> <!-- 따옴표 잘 넣기 -->		
	</td>
	<td>${pds.readcount }</td>
	<td>${pds.downcount }</td>
	<td>
		<font size="1">${pds.regdate }</font>
	</td>
	<td>
 		<img alt="" id="_img" src="image/del.png" data-file-seq="${pds.seq }" class="btn_fileDelete">
 	</td>
</tr>
</c:forEach>
--%>

</tbody> 
</table>
<br><br>

<!-- 페이징 -->
<div class="container">
	<nav aria-label="Page navigation">          <!-- bootstrap 중앙정렬 -->
		<ul class="pagination" id="pagination" style="justify-content:center;"></ul>
	</nav>
</div>
<br><br>



<script>

//일단 이동 후 Ajax를 통해 처음 뿌려지는 것
getPdsListCount();
getPdsListData(0);

//검색
$("#btnSearch").click(function(){
	getPdsListCount();
	getPdsListData(0);
});


//글의 총 수 처리 
function getPdsListCount() {
	$.ajax({
		url:"./psdlistCount.do",
		type:"get",
		data:{ page:0, choice:$("#_choice").val(), search:$("#_searchWord").val() },
		success:function( count ){
			loadPage(count); //페이징 처리
		},
		error:function(){
			alert('error');
		}		
	});
}

//페이징 처리 
function loadPage( totalCount ){
	let pageSize = 10;		// 페이지의 크기 1 ~ 10 
	let nowPage = 1;		// 현재 페이지	

	//총 페이지의 수
	let _totalPages = totalCount / pageSize;
	if(totalCount % pageSize > 0){
		_totalPages++;
	}
	
	// 페이지 초기화 : 검색 시 이전 값을 지워주기 위함
	$("#pagination").twbsPagination('destroy');
	
	$("#pagination").twbsPagination({
		//startPage: 1,
		totalPages: _totalPages,
		visiblePages: pageSize,
		first:'<span sria-hidden="true">«</span>',
		prev:"이전",
		next:"다음",
		last:'<span sria-hidden="true">»</span>',
		initiateStartPageClick:false, // 처음 로드 시 onPageClick 자동 실행되지 않도록
		
		//클릭시 페이지를 바꿔라
		onPageClick:function(event, page){
			nowPage = page;
			getPdsListData(page-1);
		}
	});
}

//pdslist를 취득
function getPdsListData( pNumber ) {
	
	$.ajax({
		url:"./pdslistData.do",
		type:"get",
		data:{ page:pNumber, choice:$("#_choice").val(), search:$("#_searchWord").val() },
		success:function( data ){
			//페이징 클릭 시 이전에 불러온 테이블은 지우고 다시 시작하기 
			$(".list_col").remove();
			
			                     //index, 변수
			$.each(data, function (i, item) {
				let app = "";
		
				    //문장이 어디서 마치는지 주의하며 보자
				    app =     "<tr class='list_col'>"
						+ 		"<th>" + (i + 1) + "</th>";
					app +=      "<td>" + item.id+ "</td>";	
					app	+=		"<td style='text-align:left'>"
						+       	"<a href='pdsdetail.do?seq="+ item.seq + "'>" + item.title +"</a>";
					app +=      "</td>"
					    +       "<td>"                                                                   //ajax는 변수가 들어오는게 아니라 값이 들어오므로 문자열이면 ""붙여줘야함
					    +  			"<input type='button' name='btnDown' value='다운로드' onclick='filedown(\"" +item.filename+ "\",\"" +item.newfilename+ "\"," +item.seq+ ")'>";
					app	+=      "</td>"											
						+       "<td>"+item.readcount+"</td>";
					app +=      "<td>"+item.downcount+"</td>";
  					app +=      "<td>"
  					    +       	"<font size='1'>"+item.regdate+"</font>";
					app +=      "</td>"	
					    +       "<td>"
					    +            "<img src='image/del.png' onclick='delBtn(\""+item.seq+"\")'>";
					app +=	    "</td>"
					    +     "</tr>";
				    
				//th 다음부터 하나씩 뿌려주기
				$("#_list_table").append(app);	
			});		
		},
		error:function(){
			alert('error');
		}
	});
}


//글쓰기
$("#_pdsAdd").click(function () {
	location.href = "pdswrite.do";
});

//삭제하기
function delBtn(seq){
    location.href = "pdsdelete.do?seq=" + seq;
}
</script>



<!-- /////////////// 다운로드 //////////////// -->
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