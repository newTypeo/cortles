<%@page import="com.cortles.project.board.model.vo.BoardComment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header2.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<%
	BoardComment reportComment = (BoardComment)request.getAttribute("reportComment");
%>
<section id="board-container" 
	style="width:570px; 
	margin: 100px auto;
	margin-bottom: 150px; 
	text-align:center;
	border-radius: 4px;
	background-color: #222;
	padding:20px;">
	<h1><span style="color: red;">Report</span></h1>
	<form
		action="<%= request.getContextPath() %>/board/boardCommentReport" 
		name="boardCommentReportFrm"
		method="post"
		style="margin-top: 60px; margin-right: 100px;"
	>
		<textarea name="reporterId" readonly="" style="display:none; background-color: white;  resize: none;width: 100%; height: auto;"><%= loginMember.getMemberId() %></textarea>
		<table id="report_table" style="width: 100%; color: black;">
		<tr>
			<td><span>Reporter</span></td>
			<td><%= loginMember.getMemberId() %></td>
			<td><span>Type</span></td>
			<td>
				<select name="reportType">
					<option value="욕설">Curse</option>
					<option value="성적수치심">Sexual harassment</option>
					<option value="폭력성">violence</option>
					<option value="기타">etc</option>
				</select>
			</td>
		</tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr>
		<tr>
			<td><span>ReportComment</span></td>
			<td colspan="3"><textarea name="reportCommentContent" readonly style="background-color: #fff; border:none; resize: none;width: 100%; height: auto;"><%= reportComment.getContent() %></textarea></td>
		</tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr><td> </td><td> </td><td> </td><td> </td></tr>
		<tr>
			<td><span>Content</span></td>
			<td colspan="3"><textarea id="reportContent" name = "reportContent" style="background-color: white; resize: none; width: 100%; height: 300px;" required></textarea></td>
			</tr>
		</table>
		<input type="hidden" name="reportedId" value="<%= reportComment.getWriterId() %>"/>
		<input type="hidden" name="commentNo" value="<%= reportComment.getCommentNo() %>"/>
		<input type="hidden" name="boardNo" value="<%= reportComment.getBoardNo() %>"/>
	</form>
<button class="btn-report" value="<%= reportComment.getCommentNo() %>">report</button>
<button type="button" class="btn_cancel">cancel</button>
</section>
<script>
/**
 * 신고하는 내용 작성 안했을 시 신고 못하게 막아놓았음
 * @author 창환
 */
document.querySelector(".btn-report").onclick = (e) => {
	const frm = document.boardCommentReportFrm;
	const content = frm.reportContent.value.length;
	if(content == 0 || content == null) {
		alert("내용을 작성해주세요");
		return;
	} else {
		alert("신고가 접수되었습니다!!!");
	}
		frm.submit();
	
}
</script>
    
<%@ include file="/WEB-INF/views/common/footer.jsp" %>















