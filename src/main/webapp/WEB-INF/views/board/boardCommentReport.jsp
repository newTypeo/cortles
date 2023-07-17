<%@page import="com.cortles.project.member.model.vo.Member"%>
<%@page import="com.cortles.project.board.model.vo.BoardComment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<%
	BoardComment _reportComment = (BoardComment)request.getAttribute("_reportComment");
%>
<section id="board-container">
<table id="report_table" style="width: 100%;">
	<tr>
		<td>제목</td>
		<td colspan="3"><textarea style="background-color: white;  resize: none;width: 100%; height: auto;"></textarea></td>
	</tr>
	<tr>
		<td>신고자</td>
		<td><textarea readonly="" style="background-color: white;  resize: none;width: 100%; height: auto;"><%= loginMember.getMemberId() %></textarea></td>
		<td>신고유형</td>
		<td>
			<select>
				<option value="">욕설</option>
				<option value="">성적수치심</option>
				<option value="">폭력성</option>
				<option value="">기타</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3"><textarea style="background-color: white;   resize: none;width: 100%; height: 300px;"></textarea></td>
	</tr>
</table>
<button>신고</button>
<button>취소</button>

</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>S
