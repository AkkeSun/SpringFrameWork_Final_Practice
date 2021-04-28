<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8"/>      
    

    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 커스텀 css -->
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<!-- cookie -->
<script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript" ></script>
<!-- Jquary -->
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>    
    
<style type="text/css">
#login_wrap {
	margin:10px;
}
#login_wrap th {
	font-weight:bold;
}
#main_wrap { 
	width:800px; 
	margin-left:auto; 
	margin-right:auto; padding:0px; 
}			
#content_wrap { 
	width: 100%; 
	height: 500px; 
	background-image:url("image/backa.jpg"); 
	background-repeat:no-repeat; 
	background-position:top center;  
}
			
.login_title_warp {
	width:500px; 
	color:#FFFFFF; 
	text-align:center; 
	background-color:#3e5fba; 
	border:solid 1px #EFEFEF; 
	font-weight:bold; 
	height:60px;
}

/* table셋팅 */
.content_table { width:98%; border-bottom:1px solid #EFEFEF; border-right:1px solid #EFEFEF; border-collapse:collapse; margin-left:auto; margin-right:auto;  clear:both; }
.content_table td, .content_table th { text-align:center; border-top:1px solid #EFEFEF; border-left:1px solid #EFEFEF; padding:0.3em; }
.content_table th { background-color:#4D6BB3; color:#FFFFFF; line-height: 1.7em; font-weight:normal;}
.content_table td { padding-left:5px; text-align:left; line-height: 1.7em; }
.content_table td.contents { width:100%; height:400px; overflow:auto; }
.content_table th, .content_table td { vertical-align:middle; }

.content_table select { height:19px; border:#CCCCCC solid 1px; vertical-align:middle; line-height: 1.8em; padding-left:0px; }
.content_table select option { margin-right:10px; }

</style>

</head>
<body>

<div id="main_wrap">
	<div id="middle_wrap">
		<div id="content_wrap">
			
			<div style="width: 502px; height: 166px; margin-left: auto; margin-right: auto;
						position: relative; top: 100px;">
								
			<div class="login_title_warp">
				<div style="margin-top: 15px">
					<h2>My Home Page</h2>
				</div>			
			</div>
			
			<div id="login_wrap">
				<form action="" method="post" id="_frmForm" name="frmForm">
					
					<table class="content_table" style="width:75%">
					<colgroup>
						<col style="width:30%">
						<col style="width:70%">
					</colgroup>

					<tr>
						<th>아이디 체크</th>
						<td>
							<input type="text" name="sid" id="_sid" size="30">
							<a href="#noen" id="_btnGetId" title="id Check">
								<img alt="" src="./image/idcheck.png">
							</a>
							<div id="_rgetid"></div>
						</td>
					</tr>
					
					<tr>
						<th>아이디</th>
						<th>
							<input type="text" name="id" id="_userid" size="30" readonly>
						</th>					
					</tr>
							
					<tr>
						<th>패스워드</th>
						<th>
							<input type="text" name="pwd" id="_pwd" size="30" >
						</th>					
					</tr>		

					<tr>
						<th>이름</th>
						<th>
							<input type="text" name="name" id="_name" size="30" >
						</th>					
					</tr>							
						
					<tr>
						<th>이메일</th> <!-- 이메일도 사실 체크해야함 -->
						<th>
							<input type="text" name="email" id="_email" size="30" >
						</th>					
					</tr>								
							
					<tr>
						<td colspan="2" style="height:50px; text-align: center;">
							<a href="#none" id="_btnRegi" title="회원가입">
								<img src="./image/regi.jpg">
							</a> 
						</td>
					</tr>									
					</table>					
				</form>						
			</div>
			</div>		
		</div>	
	</div>
</div>

<script>
$("#_btnRegi").click(function(){
	
	//입력값 체크 (빈칸, 글자수 등)
	if( $("#_userid").val().trim() == ""){
		alert("id를 입력해 주십시오")
	  //$("#title").prop('required',true); ->부트스트랩
		$("#_sid").focus(); //포커스 이동
	}
	else if( $("#_pwd").val().trim() == ""){
		alert("패스워드를 입력해 주십시오")
		$("#_pwd").focus(); //포커스 이동
	}
	else {
		//체크 완료시 이동
		$("#_frmForm").attr("action", "regiAf.do").submit();
	}
});


//아이디 체크 (Ajax)
$("#_btnGetId").click(function(){
	$.ajax({
		url:"idCheck.do",
		type:"post",
		data: {id:$("#_sid").val()},
		success:function( data ){
			if(data == "YES"){
				$("#_userid").val($("#_sid").val());
			} else {
				alert("사용할 수 없는 아이디 입니다.");
				$("#_sid").focus(); //포커스 이동
			} 
		},
		error:function(){
			alert("error");
		}
	});		
});

</script>
</body>
</html>