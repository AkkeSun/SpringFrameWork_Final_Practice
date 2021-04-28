<%@page import="bit.com.a.dto.CalendarPlugDto"%>
<%@page import="bit.com.a.dto.CalendarDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- coretag -->    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>     
<!-- fCanendar -->
<link href="<%=request.getContextPath() %>/fcalLib/main.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/fcalLib/main.js"></script> <!-- ./fcalLib/main/js -->


<style>
.body {
  margin: 40px 10px;
  padding: 0;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 12px;
}
#calendar {
  max-width: 900px;
  margin: 0 auto;
} 
</style>


<!-- 실재 캘린더가 만들어지는 div -->
<div class='body'><div id="calendar"></div></div>

<!-- 디테일 모달 -->
<div class="modal fade" id="detail">
<div class="modal-dialog modal-md">
      <div class="modal-content">
       <div class="modal-body">
       		<form action="updateCal.do" id="_updateAfgo">	
       		<input type='hidden' name='seq' id="_seq_">
	          <input class='form-control'  type="text" size="78" name="title" id="_title_" placeholder='제목을 입력하세요' readonly> <br>
			  <textarea class="form-control" readonly rows="2"  id="_content_" name="content" style="resize:none" placeholder='내용을 입력하세요'></textarea> <br>        
	          <div align='center'>
	          	<div style="text-align:left">
	             중요도&nbsp;:&nbsp;
	          	 <select name='slevel' id="_slevel_">  	          	 		
					<option value=""checked>중요도</option>	
					<option value=1>1</option>	
			 		<option value=2>2</option>		
					<option value=3>3</option>
				  </select>			  
				  <br><br>
		          시작일&nbsp;:&nbsp; <input type="datetime-local" size="90" readonly name="startdate" id='_startdate_'>
		          <br><br>
		          종료일&nbsp;:&nbsp; <input type="datetime-local" size="90" readonly name="enddate" id='_enddate_'> 
		         </div> 
		          <br><br>
		          <button type="button" id='updateBtn' class="btn btn-light">수정</button>
		          <button type="button" id='del' class="btn btn-light">삭제</button>
		          <button type="button" id='can' class="btn btn-light" data-dismiss="modal" >닫기</button>
		          <input type="hidden" id='_updateAf' class="btn btn-light" value="수정완료">
		          <input type="hidden" id='_updateCan' class="btn btn-light" value="수정취소" data-dismiss="modal">
	          </div>
	          </form>
        </div>
        </div>
</div>
</div>




<script>
//Full Calendar 함수
document.addEventListener("DOMContentLoaded", function() {

	let calendarEl = document.getElementById('calendar');
	let calendar = new FullCalendar.Calendar(calendarEl, {
		
	headerToolbar: {
		left: "prev,next,today",  //왼쪽 이동바 설정
		center: "title", 
		right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth' // 오른쪽 메뉴바 설정
	},
	
	initialDate :new Date(),  // 처음 실행 시 기준되는 날짜 'yyyy-MM-dd' 형식으로 넣어도 됨 
	locale : 'ko',            // 언어 설정
	navLinks : true,          // 오른쪽 링크 클릭하면 이동
	businessHours : true, 
	
	// 값 뿌리기(json형식)
	events:[
		<c:forEach var="item" items="${callist}">
			{
				id:'${item.id}', 
				title:'${item.title}',
				start:'${item.startdate}',
				end:'${item.enddate}',
				constraint:'${item.content}',
				//내가 임의로 만들어준 변수
				extendedProps: {
					seq: "${item.seq}",
					slevel: "${item.slevel}"
				},
			},
		</c:forEach>
		]
	});

	
	//rendering
	calendar.render();   
	
	//event (날짜 가져오기) -> 누르면 일정 추가할 수 있도록 
	calendar.on("dateClick", function(info) {
		//alert(JSON.stringify(info));
		//alert(info.dateStr); //날짜 가져오기
		dateFormat2(info.dateStr);
		$("#addCalModal").modal("show");
	});
	
	
    //event (디테일) -> 제목 누르면 
	calendar.on("eventClick", function(info){
		//alert(JSON.stringify(info));
		//alert(info.event.extendedProps.seq);
		
		let start = dateFormat(info.event.start);
		// end가 있는지 없는지 확인
		let end = info.event.end;
		<c:choose>
			<c:when test="end != null">
				end = dateFormat(info.event.end);
			</c:when>	
			<c:otherwise>
				end = start
			</c:otherwise>
		</c:choose>		
		
		// 모달에 값 넣고 출력
		$("#_title_").val(info.event.title);
		$("#_content_").val(info.event.constraint);
		$("#_startdate_").val(start);
		$("#_enddate_").val(end);
		$("#_slevel_").val(info.event.extendedProps.slevel);
		$("#_seq_").val(info.event.extendedProps.seq);
		$("#detail").modal();
	});
});
	 


//dateformat 함수 
function dateFormat(date) {
    let yyyy = date.getFullYear().toString();
    let mm = (date.getMonth()+1).toString();
    let dd  = date.getDate().toString();
    let hh = date.getHours().toString();
    let min = date.getMinutes().toString();
    
    //한 글자씩 나눠라
    let mmChars = mm.split('');
    let ddChars = dd.split('');
    let hhChars = hh.split('');
    let minChars = min.split('');

    let x = yyyy + '-' + (mmChars[1]?mm:"0"+mmChars[0]) + '-' + (ddChars[1]?dd:"0"+ddChars[0]) 
                 + 'T' + (hhChars[1]?hh:"0"+hhChars[0]) + ':' + (minChars[1]?min:"0"+minChars[0]);         	      
    return x;
 };
 
 
 
//Date Format2 함수 (yyyy-mm-dd 형식을 넣었을경우)
 function dateFormat2(time) {
	 let date = new Date();
     let hh = date.getHours().toString();
     let min = date.getMinutes().toString();
     
     //한 글자씩 나눠라
     let hhChars = hh.split('');
     let minChars = min.split('');

     let x = time + 'T' + (hhChars[1]?hh:"0"+hhChars[0]) + ':' + (minChars[1]?min:"0"+minChars[0]);         	      
     $('#_startdate').val(x);
     $('#_enddate').val(x);
  };



  
$(document).ready(function(){
		
		<!-- 수정버튼 누르면 -->
		$("#updateBtn").click(function(){
			$("#_title_").removeAttr('readonly');
			$("#_content_").removeAttr('readonly');
			$("#_startdate_").removeAttr('readonly');
			$("#_enddate_").removeAttr('readonly');
			$("#updateBtn").hide();
			$("#del").hide();
			$("#can").hide();
			$("#_updateAf").attr("type", "button");
			$("#_updateCan").attr("type", "button");
		});
 		
		
		<!-- 수정취소 버튼 누르면 -->
		$("#_updateCan").on("hidden.bs.modal", function () {
			$("#detail").modal('hide');
			$("#_title_").attr('readonly', 'readonly');
			$("#_content_").attr('readonly', 'readonly');
			$("#_startdate_").attr('readonly', 'readonly');
			$("#_enddate_").attr('readonly', 'readonly');
			$("#updateBtn").show();
			$("#can").show();
			$("#_updateAf").attr("type", "hidden");
			$("#_updateCan").attr("type", "hidden");
		});
		
		
		<!-- 모달 꺼질 때 초기화 -->
		$("#detail").on("hidden.bs.modal", function () {
			$("#detail").modal('hide');
			$("#_title_").attr('readonly', 'readonly');
			$("#_content_").attr('readonly', 'readonly');
			$("#_startdate_").attr('readonly', 'readonly');
			$("#_enddate_").attr('readonly', 'readonly');
			$("#updateBtn").show();
			$("#can").show();
			$("#_updateAf").attr("type", "hidden");
			$("#_updateCan").attr("type", "hidden");
		});
		

		<!-- 삭제버튼 누르면 -->
		$("#del").click(function(){
			location.href='delCal.do?seq='+$("#_seq_").val();
		});
		
		
		<!-- 수정완료 버튼 누르면 -->
		$("#_updateAf").click(function(){
			let s = startNendCheck($("#_startdate_").val().toString())
			let e = startNendCheck($("#_enddate_").val().toString())

			if($("#_title_").val().trim() == ""){
				alert("제목을 입력해주세요");
				$("#_title_").focus();
				return;
			}
			else if($("#_content_").val().trim() == ""){
				alert("내용을 입력하세요");
				$("#_content_").focus();
				return;
			}
			else if($("#_slevel_").val().trim() == ""){
				alert("중요도를 선택하세요");
				$("#_slevel_").focus();
				return;
			}
			else if(s>e){
				alert("시작일은 종료일보다 늦을 수 없습니다");
				$("#_startdate_").focus();
				return;
			}
			else {
				$("#_updateAfgo").submit();
			} 
		});
	});
</script>