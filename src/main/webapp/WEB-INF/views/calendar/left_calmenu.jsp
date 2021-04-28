<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="menu_table">
	<ul style="width: 100%">
		<li class="title">일정관리</li>
		<li class="subtitle">일정관리</li>
		<li class="menu_item">
			<a href="calList.do" title="전체목록">일정목록</a>
		</li>
		<li class="menu_item">
			<a href="" title="일정추가" id='addCal' data-toggle="modal" data-target="#myModal1">일정추가</a>
		</li>
	</ul>
</div>    




        
        
<!-- 일정추가 모달 -->        
<div class="modal fade" id="myModal1">
<div class="modal-dialog modal-md">
      <div class="modal-content">
       <div class="modal-body">
       	  <form action="writeCalAf.do">
	          <input class='form-control' type="text" size="78" name="title" placeholder='제목을 입력하세요'> <br>
			  <textarea class="form-control" rows="2"  name="content" style="resize:none" placeholder='내용을 입력하세요'></textarea> <br>        
	          <div align='center'>
	          	 <select name='slevel'>  	          	 		
					<option value=""checked>중요도</option>	
					<option value=1>1</option>	
			 		<option value=2>2</option>		
					<option value=3>3</option>
				  </select>
		          <input type="datetime-local" size="78" name="startdate" id='_startDate'>
		          <input type="datetime-local" size="78" name="enddate" id='_endDate'> 
		          <br><br>
		          <button type="submit" class="btn btn-light">입력완료</button>
		          <button type="button" class="btn btn-light" data-dismiss="modal" >취소</button>
	          </div>
          </form>	
        </div>
        </div>
</div>
</div>


<script>
//2021-12-23T03:17
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
             	      
    $('#_startDate').val(x);
    $('#_endDate').val(x);
    $('#myModal1').modal('show');
    
 });
 
</script> 
        