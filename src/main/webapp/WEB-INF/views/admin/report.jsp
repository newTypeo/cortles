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
					<th>삭제</th>
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
				int commentNo = 0;
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
					<td>
						<input class="yes" type="radio" name="<%= reportComment.getReportedId() %>" id="<%= reportComment.getCommentNo() + "" + commentNo %>" value="<%= reportComment.getCommentNo() %>"/>
						<label for="<%= reportComment.getReportedId() %>">Y</label>
						<input type="radio" name="<%= reportComment.getCommentNo() + commentNo %>" id="<%= reportComment.getCommentNo() + "" +  commentNo++ %>" value="N" checked/>
						<label for="<%= reportComment.getCommentNo() + "" +  commentNo++ %>">N</label>
					</td>
				</tr>
			<%
					
			
			}
		}
%>
			</tbody>
		</table>
<form id="delReportCommentFrm"
	name="delReportCommentFrm"
	action="<%= request.getContextPath() %>/admin/reportCommentDelete"
	method="POST">
	<input type="hidden" name="no" id="no" value="">
	<input type="hidden" name="delOnReportComment" id="delOnReportComment" value="delOnReportComment">
</form>
<script>
//댓글 강제 삭제 
[...document.querySelectorAll(".yes")].forEach((yesRadio)=>{
	console.log("yesRadio", yesRadio);
	yesRadio.onclick = (e) => {
		console.log("e", e);
		const radioName = e.target.id;
		const memberName = e.target.name;
		const commentNo = e.target.value;
		console.log("commentNo", commentNo);
		console.log("e.target.nextSibling.nextElementSibling.nextElementSibling",e.target.nextSibling.nextElementSibling.nextElementSibling);
		if (confirm(`\${memberName}님의 댓글을 삭제하시겠습니까?`)){
			document.querySelector("#delReportCommentFrm #no").value = commentNo;
			document.delReportCommentFrm.submit();
		} else {
			e.target.nextSibling.nextElementSibling.checked = true;
			return false;
		}
	}
});
</script>
</html>