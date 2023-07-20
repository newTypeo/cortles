<%@page import="com.cortles.project.board.model.vo.ReportComment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>신고 목록</title>
<%@ include file="/WEB-INF/views/common/header2.jsp"%>
<%
	List<ReportComment> reportComments = (List<ReportComment>) request.getAttribute("reportComments");
%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/report.css" />
		<table id="report">
		<!-- 신고할 회원의 정보 -->
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>TextNo</th>
					<th>CommentNo</th>
					<th>CommentContent</th>
					<th>Reason</th>
					<th>ReportedCnt</th>
					<th>Reporter</th>
					<th>Date</th>
					<th>Quit</th>
					<th>Delete</th>
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
					<td> <!-- 댓글 삭제 라디오 -->
						<input class="yes" type="radio" name="<%= reportComment.getReportedId() %>" id="<%= reportComment.getCommentNo() + "" + commentNo %>" value="<%= reportComment.getCommentNo() %>"/>
						<label id="<%= reportComment.getReportCommentNo() %>" for="<%= reportComment.getReportedId() %>">Y</label>
						<input type="radio" name="<%= reportComment.getCommentNo() + commentNo %>" id="<%= reportComment.getCommentNo() + "" +  commentNo++ %>" value="N" checked/>
						<label for="<%= reportComment.getCommentNo() + "" +  commentNo++ %>">N</label> <!-- 같은 댓글이 신고 되었을 경우 ++로 다른 값을 줌  -->
					</td>
				</tr>
			<%
					
			
			}
		}
%>
			</tbody>
		</table>
<%-- 댓글 삭제 폼  --%>
<form id="delReportCommentFrm"
	name="delReportCommentFrm"
	action="<%= request.getContextPath() %>/admin/reportCommentDelete"
	method="POST">
	<input type="hidden" name="no" id="no" value="">
	<input type="hidden" name="reportCommentNo" id="reportCommentNo" value="">
	<input type="hidden" name="delOnReportComment" id="delOnReportComment" value="delOnReportComment">
</form>
<script>
//댓글 강제 삭제 - 주혜 
[...document.querySelectorAll(".yes")].forEach((yesRadio)=>{
	//console.log("yesRadio", yesRadio);
	yesRadio.onclick = (e) => {
		//console.log("e", e);
		const radioName = e.target.id;
		const memberName = e.target.name;
		const commentNo = e.target.value;
		//console.log("commentNo", commentNo);
		//console.log("e.target.nextSibling.nextElementSibling.nextElementSibling",e.target.nextSibling.nextElementSibling.nextElementSibling);
		if (confirm(`\${memberName}님의 댓글을 삭제하시겠습니까?`)){
			document.querySelector("#delReportCommentFrm #no").value = commentNo;
			// console.log("e.target.nextSibling.nextSibling.id", e.target.nextSibling.nextSibling.id);
			document.querySelector("#reportCommentNo").value = e.target.nextSibling.nextSibling.id;
			document.delReportCommentFrm.submit();
			e.target.querySelector(".yes").selected = true;
		} else { // 취소 
			e.target.nextSibling.nextElementSibling.checked = true;
			return false;
		}
	}
});
</script>
</html>
