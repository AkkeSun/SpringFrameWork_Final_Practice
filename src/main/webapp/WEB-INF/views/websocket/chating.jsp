<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<table class="list_table" style="width: 85%">
<colgroup>
	<col width="200px"><col width="500px">
</colgroup>

<tr>
	<th>채팅명</th>
	<td style="text-align: left;">
		<input type="text" id="name">
		<input type="button" id="enterBtn" value="입장" onclick="connect()">
		<input type="button" id="exitBtn" value="나가기" onclick="disconnect()">
	</td>
</tr>

<tr>
	<th>아이디</th>
	<td style="text-align: left;">
		<input type="text" id="id" value="${login.id}">
	</td>
</tr>

<tr>
	<td colspan="2">
		<textarea rows="10" cols="70" id="MessageArea"></textarea>
	</td>
</tr>

<tr>
	<td colspan="2">
		<input type="text" id="message" size="50">
		<input type="button" id="sendBtn" value="전송" onclick="send()">
	</td>
</tr>

</table>

<script>
var wsocket;

// 접속
function connect() {
	
	if(wsocket != undefined && wsocket.readyState != WebSocket.CLOSED){
		alert("이미 입장하셨습니다");
		return;
	}
	
	// Web Socket 생성 
	// server먼저 키고 이걸로 들어가기. 여기에 param 주면 채팅방 만들기 응용가능. DB와 상관없음
	wsocket = new WebSocket("ws://192.168.0.18:8090/smaple10/echo.do");		

	/*
	테스트용 
	if( $("#name").val() == "aaa" ){
		wsocket = new WebSocket("ws://localhost:8090/sample10/echo.do");
	}
	else{
		wsocket = new WebSocket("ws://192.168.0.210:8090/sample10/echo.do");		
	}
	*/
	
	
	wsocket.onopen = onOpen;
	wsocket.onmessage = onMessage;
	wsocket.close = onClose;
}

// 접속을 중단
function disconnect() {
	wsocket.close();
	location.href = "chating.do";
}

// 연결이 되었을 때
function onOpen(evt) {
	appendMessage("연결되었습니다");
}

// 메시지 수신이 되었을 때
function onMessage(evt) {
	let data = evt.data;
	if(data.substring(0, 4) == "msg:"){		// msg:안녕하세요
		appendMessage( data.substring(4) );	// 안녕하세요
	}
}

// 끊겼을 때
function onClose(evt) {
	appendMessage("연결이 끊겼습니다");
}

// 메시지 송신
function send() {
	let id = $("#name").val();
	let msg = $("#message").val();
	
	wsocket.send("msg:" + id + ":" + msg);	// msg:aaa:안녕하세요
	$("#message").val("");
}

// 추가 문자열을 기입
function appendMessage( msg ) {
	// 메시지를 추가 하고 개행
	$("#MessageArea").append(msg + "\n");
	
	// 스크롤을 위로 이동 시킨다
	const top = $("#MessageArea").prop("scrollHeight");	
	$("#MessageArea").scrollTop(top);
}

</script>