<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- core Tag -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
    
    
<!-- 테이블 -->
<table class="list_table" style="width: 85%" id="_list_table">
<colgroup>
	<col style="width:70px">
	<col style="width:auto">
	<col style="width:100px">
</colgroup>	

<tr>
	<th>번호</th><th>제목</th><th>작성자</th>
</tr>

<%-- 
<!-- 비어있다면  -->
<c:if test="${empty bbslist}">
<tr>
	<td colspan="3">작성된 글이 없습니다</td>
</tr>
</c:if>

<!-- 비어있지 않다면 -->
<c:if test="${bbslist != null}">
<!-- items:생성할 객채 var:객채명 varStatus:카운트  -->
<c:forEach items="${bbslist }" var="bbs" varStatus="vs">
	<tr>
		<td>${vs.count } </td>
		<td style="text-align:left">
			<a href="bbsdeatil.do?seq=${bbs.seq}">
				${bbs.id } 
			</a>
		</td>
	</tr>
</c:forEach>
</c:if>
 --%>
 
</table>
<br><br>


<!-- 페이징 -->
<div class="container">
	<nav aria-label="Page navigation">         <!-- bootstrap 중앙정렬 -->
		<ul class="pagination" id="pagination" style="justify-content:center;"></ul>
	</nav>
</div>
<br><br>


<script>
getBbsListData(0); //첫번째 페이지 가져오기
getBbsListCount();

//검색
$("#btnSearch").click(function(){
	getBbsListData(0);
	getBbsListCount();
});


//bbslist를 취득
function getBbsListData( pNumber ) {	
	$.ajax({
		url:"./bbslistData.do",
		type:"get",
		data:{ page:pNumber, choice:$("#_choice").val(), search:$("#_searchWord").val() },
		success:function( data ){
		
			//페이징 클릭 시 이전에 불러온 테이블은 지우고 다시 시작하기 
			$(".list_col").remove();
			
			                      //index, 변수
			$.each(data, function (i, item) {
				let app = "";
				    //문장이 어디서 마치는지 주의하며 보자
				    app = "<tr class='list_col'>"
						+ 		"<td>" + (i + 1) + "</td>"
						+		"<td style='text-align:left'>"
						+      		arrow(item.depth);
						if(item.del==0){
					app += 			"<a href='bbsdetail.do?seq=" + item.seq + "'>&nbsp;" + item.title + "</a>";
						}
						else{
					app += 			"<font color='#ff0000'>* 이 글은 작성자에 의해서 삭제되었습니다 *</font>";
						}
					app += 		"</td>"
						+ 		"<td>" + item.id + "</td>"
						+ "</tr>";
				
				//th 다음부터 하나씩 뿌려주기
				$("#_list_table").append(app);	
			});		
		},
		error:function(){
			alert('error');
		}
	});
}


//글의 총 수 처리 
function getBbsListCount() {
	$.ajax({
		url:"./bbslistCount.do",
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
	
	//페이지 설정	
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
			getBbsListData(page-1);
		}
	});
}



//댓글 화살표
function arrow(depth){
	let rs = "<img src='./image/arrow.png' width='15px' heiht='15px'/>";
	let nbsp = "&nbsp;&nbsp;&nbsp;&nbsp;";	// 여백
	let ts = "";
	for(i = 0;i < depth; i++)
		rs += nbsp;
		
	return depth==0 ? "":ts + rs;	
}

</script>