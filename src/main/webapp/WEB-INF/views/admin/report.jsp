<%@page import="com.cortles.project.board.model.vo.ReportComment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>신고 목록</title>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	List<ReportComment> reportComments = (List<ReportComment>) request.getAttribute("reportComments");
%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/report.css" />
<h1>report list</h1>
<table>
    <thead>
        <tr>
            <th id="borderz">아이디</th>
            <th id="boarderz">이름</th>
            <th id="boarderz">글번호</th>
            <th id="boarderz">댓글번호</th>
            <th id="boarderz">댓글내용</th>
            <th id="boarderz">신고사유</th>
            <th id="boarderz">신고회수</th>
            <th id="boarderz">신고자</th>
            <th id="boarderz">신고일</th>
            <th id="boarderz">탈퇴처리여부</th>
            <th id="boarderz">삭제</th>
        </tr>
    </thead>
    <tbody>
        <% if(reportComments == null || reportComments.isEmpty()) { %>
        <tr>
            <td id="boarderz" colspan="10">조회 결과가 없습니다.</td>
        </tr>
        <%	
            } else { 
                for(ReportComment reportComment : reportComments) {
        %>
        <tr>
            <td id="boarderz"><%= reportComment.getReportedId() %></td>
            <td id="boarderz"><%= reportComment.getReportedName() %></td>
            <td id="boarderz"><%= reportComment.getBoardNo() %></td>
            <td id="boarderz"><%= reportComment.getCommentNo() %></td>
            <td id="boarderz"><%= reportComment.getReportContent() %></td>
            <td id="boarderz"><%= reportComment.getReportType() %></td>
            <td id="boarderz"><%= reportComment.getReportCount() %></td>
            <td id="boarderz"><%= reportComment.getReporterId() %></td>
            <td id="boarderz"><%= reportComment.getReportDate() %></td>
            <td id="boarderz"><%= reportComment.getReportCount() == 3 ? "O" : "X" %></td>
            <td id="boarderz">
                <input class="yes" type="radio" name="yes" id="<%= reportComment.getReportedName() %>" value="<%= reportComment.getCommentNo() %>"/>
                <label for="yes">Y</label>
                <input type="radio" name="no" id="no" value="N" checked/>
                <label for="no">N</label>
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
    action="<%= request.getContextPath() %>/board/boardCommentDelete"
    method="POST">
    <input type="hidden" name="no" id="no" value="">
    <input type="hidden" name="delOnReportComment" id="delOnReportComment" value="delOnReportComment">
</form>
<script>
    //댓글 강제 삭제 
    document.querySelector(".yes").onclick = (e) => {
        console.log(e);
        const memberName = e.target.id;
        const commentNo = e.target.value;
        console.log("commentNo", commentNo);
        
        if (confirm(`\${memberName}님의 댓글을 삭제하시겠습니까?`)){
            document.querySelector("#delReportCommentFrm #no").value = commentNo;
            document.delReportCommentFrm.submit();
        } else {
            document.querySelector("#no").checked = true;
        }
    };
</script>
</html>
