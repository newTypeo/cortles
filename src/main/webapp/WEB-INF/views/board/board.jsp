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
    <div class="search-container" id="search-name">
   	 <form id="search" action="<%= request.getContextPath()%>/board/searchTitle" name="searchTitleFrm">
    	<input type="hidden" name="searchType" value="title"/>
   		 <input type="text" name="searchKeyword" class="search-input" placeholder="Search by title" style="margin-top: 70px;">
   			<span class="btn-wrapper">
    			<button class="btn" type="submit" style="margin: 5px;">SELECT</button>
			</span>
   	 </form>
	</div>
	<% if(loginMember != null) { %>
		<button type="button" id="btn-add" class="btn"
			onclick="location.href = '<%= request.getContextPath() %>/board/boardCreate';"
			style="height: 30px; margin-top: 30px; display: flex; justify-content: center; align-items: center;">Writing
		</button> 
	<% } %>
<table id="tbl-board">
	<thead>
		<tr>
			<th>No</th>
			<th>Title</th>
			<th>Like</th>
			<th>Writer</th>
			<th>RegDate</th>
		</tr>
	</thead>
	<tbody>
			<%
			if(boards != null && !boards.isEmpty()){ 
						for(BoardEntity board : boards){
			%>
						<tr  id="title">
							<td><%= board.getBoardNo() %></td>
							
							<td>
								<a href="<%= request.getContextPath() %>/board/boardDetail?no=<%= board.getBoardNo() %>"><%= board.getTitle() %></a>
								<a href="<%= request.getContextPath() %>/board/boardDetail?no=<%= board.getBoardNo() %>">[<%= board.getReadCount() %>]</a>
								
							</td>
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
