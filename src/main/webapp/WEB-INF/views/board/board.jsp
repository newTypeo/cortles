<%@page import="com.cortles.project.board.model.vo.BoardEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
List<BoardEntity> boards = (List<BoardEntity>) request.getAttribute("boards");
%>
<style>
table {
	margin: 20px 0;
	border: 1px solid #000;
	border-collapse: collapse;
}
th, td {
	border: 1px solid #000;
	padding: 5px;
}
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<title>Insert title here</title>
<style>
button {
margin-left: 540px;
margin-bottom: 10px;
}
</style>
</head>
<body>
<section id="board-container">
	<h2>게시판 </h2><br>
	<button>글쓰기</button>
<table id="tbl-board">
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>조회</th>
			<th>추천</th>
			<th>글쓴이</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody>
			<%
			if(boards != null && !boards.isEmpty()){ 
						for(BoardEntity board : boards){
			%>
						<tr>
							<td><%= board.getBoardNo() %></td>
							<td>
								<a href="<%= request.getContextPath() %>/board/boardDetail?no=<%= board.getBoardNo() %>"><%= board.getTitle() %></a>
								<%--<%	if(board.getCommentCnt() > 0) { --%>
								<%-- [<%= board.getCommentCnt() %>] --%>
								<%-- ✉	--%>	
								<%--<% 	} --%>
							</td>
							<td><%= board.getReadCount() %></td>
							<td><%= board.getLikeCount() %></td>
							<td><%= board.getWriterId() %></td>
							<td><%= board.getRegDate() %></td>
						</tr>
			<%
					}
				} 
				else { 
			%>
				 
			<% } %>
		</tbody>

</table>
</section>
<div id='pagebar'>
		<%= request.getAttribute("pagebar") %>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
