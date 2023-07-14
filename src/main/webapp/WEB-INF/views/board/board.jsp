<%@page import="com.cortles.project.board.model.vo.BoardEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<BoardEntity> boards = (List<BoardEntity>) request.getAttribute("boards");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<title>Insert title here</title>

<section id="board-container">
	<h2>게시판 </h2>
	<br>
    <div class="search-container" id="search-name">
   	 <form action="<%= request.getContextPath()%>/board/searchTitle" name="searchTitleFrm">
    	<input type="hidden" name="searchType" value="name"/>
   		 <input type="text" name="searchKeyword" class="search-input" placeholder="제목으로 검색">
   			<span class="btn-wrapper">
    			<button class="btn" type="submit">Button</button>
			</span>
   	 </form>
	</div>
	<br>
	<% if(loginMember != null) { %>
	<input 
			type="button" id="btn-add" value="글쓰기" 
			onclick="location.href = '<%= request.getContextPath() %>/board/boardCreate';" />
	<% } %>
	<br>
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
