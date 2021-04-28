<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="menu_table">
	<ul style="width: 100%">
		<li class="title">일정관리</li>
		<li class="subtitle">일정관리</li>
		<li class="menu_item">
			<a href="calendarpluglist.do" title="전체목록">일정목록</a>
		</li>
		<li class="menu_item">
			<a href="" title="일정추가" id='addCal' data-toggle="modal" data-target="#addCalModal">일정추가</a>
		</li>
	</ul>
</div>    

        
<!-- 일정추가 모달 -->        
<div class="modal fade" id="addCalModal">
<div class="modal-dialog modal-md">
      <div class="modal-content">
       <div class="modal-body">
       		<form action="writeCalAf.do" id="_calWriteAfgo">	
	          <input class='form-control'  type="text" size="78" name="title" id="_title" placeholder='제목을 입력하세요'><br>
			  <textarea class="form-control" rows="2"  id="_content" name="content" style="resize:none" placeholder='내용을 입력하세요'></textarea> <br>        
	          <div align='center'>
	          	<div style="text-align:left">
	             중요도&nbsp;:&nbsp;
	          	 <select name='slevel' id="_slevel">  	          	 		
					<option value=""checked>중요도</option>	
					<option value=1>1</option>	
			 		<option value=2>2</option>		
					<option value=3>3</option>
				  </select>
				  <br><br>
		          시작일&nbsp;:&nbsp; <input type="datetime-local" size="90" name="startdate" id='_startdate'>
		          <br><br>
		          종료일&nbsp;:&nbsp; <input type="datetime-local" size="90" name="enddate" id='_enddate'> 
		         </div> 
		          <br><br>
	          	  <button type="button" class="btn btn-light" id="_calWriteAfbtn">입력완료</button>
		          <button type="button" class="btn btn-light" data-dismiss="modal" >취소</button>
	          </div>
	          </form>
        </div>
        </div>
</div>
</div>



<script>
//오늘 날짜로 초기화해주기 (2021-12-23T03:17)
$("#addCal").click(function (){
    var date = new Date();
    var yyyy = date.getFullYear().toString();
    var mm = (date.getMonth()+1).toString();
    var dd  = date.getDate().toString();
    var hh = date.getHours().toString();
    var min = date.getMinutes().toString();
    
    //한 글자씩 나눠라
    var mmChars = mm.split('');
    var ddChars = dd.split('');
    var hhChars = hh.split('');
    var minChars = min.split('');

    var x = yyyy + '-' + (mmChars[1]?mm:"0"+mmChars[0]) + '-' + (ddChars[1]?dd:"0"+ddChars[0]) 
                 + 'T' + (hhChars[1]?hh:"0"+hhChars[0]) + ':' + (minChars[1]?min:"0"+minChars[0]);
 	      
    $('#_startdate').val(x);
    $('#_enddate').val(x);
    $('#myModal1').modal('show');
 });
 
 
//일정추가 유효성 검사 
$("#_calWriteAfbtn").click(function (){
	
	// 시작일이 종료일보다 늦지 않게 하기 위해서 integer형식으로 포맷
	let s = startNendCheck($("#_startdate").val().toString())
	let e = startNendCheck($("#_enddate").val().toString())
	
	if($("#_title").val().trim() == ""){
		alert("제목을 입력해주세요");
		$("#_title").focus();
		return;
	}
	else if($("#_content").val().trim() == ""){
		alert("내용을 입력하세요");
		$("#_content").focus();
		return;
	}
	else if($("#_slevel").val().trim() == ""){
		alert("중요도를 선택하세요");
		$("#_slevel").focus();
		return;
	}
	else if(s>e){
		alert("시작일은 종료일보다 늦을 수 없습니다");
		$("#_startdate").focus();
		return;
	}
	else {
		$("#_calWriteAfgo").submit();
	} 
});	


//시작일이 종료일보다 늦지 않은지 체크하기 위한 포맷 (20210527)
function startNendCheck(date){
	let result = date.substring(0,4) + date.substring(5,7) + date.substring(8,10)
	            + date.substring(11,13) + date.substring(14);
	return result;		
}

</script> 
        