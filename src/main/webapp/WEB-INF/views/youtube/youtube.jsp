<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String str = (String)request.getAttribute("yulist");
if(str == null || str.equals("")){
	str = "";
}
%>

<script>
$(function(){
	let str = '<%=str %>';
	json = JSON.parse(str); // string -> json
	// item.videoRenderer.videoId : 비디오 고유번호
	// item.videoRenderer.title.runs[0].text : 비디오 제목
	
	// json data 타고타고 
	let obj = json.contents.twoColumnSearchResultsRenderer.primaryContents.sectionListRenderer.contents[0].itemSectionRenderer.contents[1].shelfRenderer.content.verticalListRenderer.items;
	$.each(obj, function (i, item) {
		let s = "<tr class='_hover_tr'>"
					+ "<td>" + (i + 1) + "</td>" 
					+ "<td style='text-align:left'><div class='c_vname ' vname='" + item.videoRenderer.videoId + "'>" + item.videoRenderer.title.runs[0].text + "</div></td>"
					+ "<td>" + item.videoRenderer.videoId + "</td>"
					+ "<td><img src='image/save.png' class='ck_seq' id='" + item.videoRenderer.videoId + "' loginIn='${login.id}' title='" + item.videoRenderer.title.runs[0].text + "'></td>"
				+ "</tr>"
		$("#tbody").append(s);
	});

	obj = json.contents.twoColumnSearchResultsRenderer.primaryContents.sectionListRenderer.contents[0].itemSectionRenderer.contents;
		   $.each(obj, function (i, item) {
		      if(item.videoRenderer != undefined){
		         let s = "<tr class='_hover_tr'>"
		            + "<td>" + (i + 1) + "</td>"
		            + "<td style='text-align: left;'><div class='c_vname' vname='" + item.videoRenderer.videoId + "'>" + item.videoRenderer.title.runs[0].text + "</div></td>"
		            + "<td>" + item.videoRenderer.videoId + "</td>"
		            + "<td><img src='image/save.png' class='ck_seq' id='" + item.videoRenderer.videoId + "'   loginIn='${login.id}' title='" + item.videoRenderer.title.runs[0].text + "'></td>"
		                + "</tr>";
		      
		         $("#tbody").append(s);
		      }
		   });	
});
</script>





<div class="box_order" style="margin-top:5px; margin-bottom: 10px">
<form id="frmForm" method="post">
<table style="margin-left:auto; margin-right:auto; margin:3px; margin-bottom:3px">
<tr>
	<td>검색:</td>
	<td style="padding-left: 5px">
		<input type="text" id="_s_keyword" name="s_keyword" value="${empty s_keyword?'':s_keyword }">
	</td>
	<td style="padding-left: 5px">
		<span class="button blue">
			<button type="button" id="_btnSearch">검색</button>
		</span>
	</td>
</tr>
</table>
</form>
</div>

<!-- 뿌려주기 -->
<!-- 동영상 재생 -->
<div id="_youtube_">
	<iframe id="_youtube" width="640" height="360" src="http://www.youtube.com/embed/"
		frameborder="0" allowfullscreen>	
	</iframe>
</div>

<!-- 검색 리스트 뿌려주기 -->
<table class="list_table" style="width:85%">
	<colgroup>
		<col style="width: 70px"><col style="width: auto">
		<col style="width: 100px"><col style="width: 30px">
	</colgroup>
	<thead>
		<tr>
			<th>제목</th><th>번호</th><th>유투브 고유번호</th><th>저장</th>
		</tr>
	</thead>
	<tbody id='tbody'>
	</tbody>
</table>



<script>
$(document).ready(function(){
	//검색버튼
	$("#_btnSearch").click(function(){
		$("#frmForm").attr("action", "youtube.do").submit();
	});
	
	
	// 일단 재생파트 가리기	
	$("#_youtube").hide();	

	
	//이름 누르면 재생파트 보이기
	$(".c_vname").click(function () {
		$("#_youtube").show();	
		$("#_youtube").attr("src", "http://www.youtube.com/embed/" + $(this).attr("vname"));
	});

	
	//표 갖다대면 색 바뀌기
	$("._hover_tr").mouseover(function() {
	    $(this).children().css("background-color", "#f0f5ff");
	 }).mouseout(function() {
	    $(this).children().css("background-color", "#ffffff");
	 });   
	
	
	// 저장
	$(document).on("click", ".ck_seq", function(){
		//사용자 지정 attr
		let id = $(this).attr("loginIn");
		let title = $(this).attr("title");
		let url = $(this).attr("id");
		
		$.ajax({
			url:"./youtubesave.do",
			type:"post",
			async:true, // 비동기 처리
			data:{ id:id, title:title, url:url},
			success:function( msg ){
				alert(msg);
			},
			error:function(){
				alert("fail");
			}
		})
	})
})
</script>