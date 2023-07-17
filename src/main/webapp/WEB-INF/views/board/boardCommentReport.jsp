<%@page import="com.cortles.project.board.model.vo.BoardComment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<%
	BoardComment _reportComment = (BoardComment)request.getAttribute("_reportComment");
%>
<section id="board-container">
<table id="report_table" style="width: 100%;">
	<tr>
		<td>제목</td>
		<td colspan="3"><textarea></textarea></td>
	</tr>
</table>


</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
