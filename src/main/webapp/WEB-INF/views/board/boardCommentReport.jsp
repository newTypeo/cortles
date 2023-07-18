<%@page import="com.cortles.project.board.model.vo.BoardComment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<%
	BoardComment reportComment = (BoardComment)request.getAttribute("reportComment");
%>
<section id="board-container">
	<form
		action="<%= request.getContextPath() %>/board/boardCommentReport" 
		name="boardCommentReportFrm"
		method="post"
	>
		<table id="report_table" style="width: 100%; color: black;">
		<tr>
			<td>신고자</td>
			<td><textarea name="reporterId" readonly="" style="background-color: white;  resize: none;width: 100%; height: auto;"><%= loginMember.getMemberId() %></textarea></td>
			<td>신고유형</td>
			<td>
				<select name="reportType">
					<option value="욕설">욕설</option>
					<option value="성적수치심">성적수치심</option>
					<option value="폭력성">폭력성</option>
					<option value="기타">기타</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>신고댓글</td>
			<td colspan="3"><textarea name="reportCommentContent" readonly="" style="background-color: white;  resize: none;width: 100%; height: auto;"><%= reportComment.getContent() %></textarea></td>
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="3"><textarea name = "reportContent"  style="background-color: white;   resize: none;width: 100%; height: 300px;"></textarea></td>
			</tr>
		</table>
		<input type="hidden" name="reportedId" value="<%= reportComment.getWriterId() %>"/>
		<input type="hidden" name="commentNo" value="<%= reportComment.getCommentNo() %>"/>
		<input type="hidden" name="boardNo" value="<%= reportComment.getBoardNo() %>"/>
	</form>
<button class="btn-report" value="<%= reportComment.getCommentNo() %>">신고</button>
<button type="button" class="btn_cancel">취소</button>
</section>
<script>
document.querySelector(".btn-report").onclick = (e) => {
	const frm = document.boardCommentReportFrm;
	alert("신고가 접수되었습니다!!!");
	frm.submit();
}
</script>
    
<%@ include file="/WEB-INF/views/common/footer.jsp" %>















