<%@page import="com.cortles.project.board.model.vo.BoardComment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<% 
	BoardComment updateBoardComment = (BoardComment)request.getAttribute("updateBoardComment");
%>
<style>
#update_table{
	border: 1px solid white;
}
</style>
<section>
<table id="update_table">
	<tr>
		<td> 작성자:<%= updateBoardComment.getWriterId() %> | 날짜:<%= updateBoardComment.getRegDate() %> </td>
	</tr>
	<tr>
		<td> <textarea style="color: black; border: none; resize: none;width: 100%; height: auto;"><%= updateBoardComment.getContent() %></textarea> </td>
	</tr>
</table>
<button>확인</button>
<button>취소</button>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>