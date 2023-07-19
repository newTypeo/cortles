<%@page import="com.cortles.project.board.model.vo.BoardComment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header2.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<% 
	BoardComment updateBoardComment = (BoardComment)request.getAttribute("updateBoardComment");
%>

<section id="board-container">
<table id="update_table" style="width: 100%;">
	<tr>
		<td> 작성자:<%= updateBoardComment.getWriterId() %> | 날짜:<%= updateBoardComment.getRegDate() %> </td>
	</tr>
	<tr>
		<td> <textarea id = "content" style="color: black; border: none; resize: none;width: 100%; height: auto;"><%= updateBoardComment.getContent() %></textarea> </td>
	</tr>
</table>
<button class="btn-update" value="<%= updateBoardComment.getCommentNo() %>">수정</button>
<button type="button" class="btn-cancle">취소</button>
<form 
	action="<%= request.getContextPath() %>/board/boardCommentUpdate" 
	name="boardCommentUpdateFrm"
	method="post">
	<input type="hidden" name="no" />
	<input type="hidden" name="content" />
	<input type="hidden" name="boardNo" value=<%= updateBoardComment.getBoardNo() %> />

</section>
<script>
document.querySelector(".btn-cancle").onclick = (e) => {
	window.history.back();
}


document.querySelector(".btn-update").onclick = (e) => {
	const frm = document.boardCommentUpdateFrm;
	const content = document.querySelector("#content");
	const {value} = e.target;
	frm.no.value = value;
	frm.content.value = content.value;
	frm.submit();
}


</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>