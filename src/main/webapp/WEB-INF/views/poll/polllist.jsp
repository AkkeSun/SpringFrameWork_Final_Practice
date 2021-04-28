<%@page import="bit.com.a.util.PollUtil"%>
<%@page import="bit.com.a.dto.MemberDto"%>
<%@page import="bit.com.a.poll.PollDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
List<PollDto> plists = (List<PollDto>)request.getAttribute("plists");

MemberDto mem = (MemberDto)session.getAttribute("login");
%>

<!-- 관리자 -->
<%
if(mem.getAuth() == 1){
%>
	<table class="list_table" style="width: 95%">
	<col width="50"><col width="50"><col width="300">
	<col width="100"><col width="100"><col width="50">
	<col width="50"><col width="100">	
	
	<tr>
		<th>번호</th><th>아이디</th><th>투표제목</th>
		<th>시작일</th><th>종료일</th><th>보기수</th>
		<th>투표회수</th><th>등록일</th>	
	</tr>
	
	<%
		for(int i = 0;i < plists.size(); i++){
			PollDto poll = plists.get(i);
			%>	
			<tr bgcolor="#aabbcc">
				<td><%=i+1 %></td>
				<td><%=poll.getId() %></td>
				<td><%=poll.getQuestion() %></td>
				<td><%=poll.getSdate() %></td>
				<td><%=poll.getEdate() %></td>
				<td><%=poll.getItemcount() %></td>
				<td><%=poll.getPolltotal() %></td>
				<td><%=poll.getRegdate() %></td>	
			</tr>
			<%
		}
	%>
	</table>
<%
}else/*  if(mem.getAuth() == 3) */{
%>
<!-- 사용자 -->
	<table class="list_table" style="width: 95%">
	<col width="50"><col width="50"><col width="300"><col width="100">
	<col width="100"><col width="100"><col width="50">
	<col width="50"><col width="100">
	
	<tr>
		<th>번호</th><th>아이디</th><th>투표제목</th><th>결과</th>	<!-- 진행중/종료 -->
		<th>시작일</th><th>종료일</th><th>보기수</th>
		<th>투표회수</th><th>등록일</th>	
	</tr>
	
	<%
		for(int i = 0;i < plists.size(); i++){
			PollDto poll = plists.get(i);
			%>
			
			<tr bgcolor="#aabbcc">
				<td><%=i+1 %></td>
				<td><%=poll.getId() %></td>
			
				<%	// 투표 주제
				boolean isS = poll.isVote();
				
				// 투표했음		  기간만료
				if(isS == true || PollUtil.isEnd(poll.getEdate()) == true){
					%>
					<td>[마감]<%=poll.getQuestion() %></td>					
					<%
				}else{	// 투표를 안했으며, 기간이 아직 만료되지 않음
					%>	
					<td>
						<a href="polldetail.do?pollid=<%=poll.getPollid() %>">
							<%=poll.getQuestion() %>
						</a>
					</td>						
					<%			
				}				
				%>
				
				<td>
				<!-- 결과확인 (투표한 사람만 볼 수 있음) -->
				<%
				if(isS == true || PollUtil.isEnd(poll.getEdate()) == true){
					%>
					<a href="pollresult.do?pollid=<%=poll.getPollid() %>">결과</a>
					<%
				}else{
					%>
					<img alt="" src="image/pen.gif">
					<%
				}
				%>				
				</td>
				<td><%=poll.getSdate() %></td>
				<td><%=poll.getEdate() %></td>
				<td><%=poll.getItemcount() %></td>
				<td><%=poll.getPolltotal() %></td>
				<td><%=poll.getRegdate() %></td>			
			</tr>
			<%
		}
	%>
	</table>
<%
}
%>

<%
if(mem.getAuth() == 1){
%>
	<a href="pollmake.do">투표 만들기</a>
<%
}
%>




