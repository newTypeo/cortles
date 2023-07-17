<%@page import="com.cortles.project.board.model.vo.ReportComment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>신고 목록</title>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	List<ReportComment> reportComments = (List<ReportComment>) request.getAttribute("reportComments");
%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/report.css" />
	<h1>report list</h1>
		<table>
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>글번호</th>
					<th>댓글번호</th>
					<th>댓글내용</th>
					<th>신고사유</th>
					<th>신고회수</th>
					<th>신고자</th>
					<th>신고일</th>
					<th>탈퇴처리여부</th>
				</tr>
			</thead>
			<tbody>
				<% if(reportComments == null || reportComments.isEmpty()) { %>
			<tr>
				<td colspan="10">조회 결과가 없습니다.</td>
			</tr>
<%	
			} 
			else { 
				for(ReportComment reportComment : reportComments) {
					%>
				<tr>
					<td><%= reportComment.getReportedId() %></td>
					<td><%= reportComment.getReportedName() %></td>
					<td><%= reportComment.getBoardNo() %></td>
					<td><%= reportComment.getCommentNo() %></td>
					<td><%= reportComment.getReportContent() %></td>
					<td><%= reportComment.getReportType() %></td>
					<td><%= reportComment.getReportCount() %></td>
					<td><%= reportComment.getReporterId() %></td>
					<td><%= reportComment.getReportDate() %></td>
					<td><%= reportComment.getReportCount() == 3	? "O" : "X"	%></td>
				</tr>
			</tbody>
			<%
					
			
			}
		}
%>
		</table>
<script>

</script>
</html>