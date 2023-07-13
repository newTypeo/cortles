<%@page import="com.cortles.project.board.model.vo.BoardComment"%>
<%@page import="com.cortles.project.board.model.vo.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="com.cortles.project.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Board board = (Board) request.getAttribute("board");
	List<Attachment> attachments = board.getAttachments();
	List<BoardComment> boardComments = (List<BoardComment>) request.getAttribute("boardComments");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<section id="board-container">
	<div id="board" style ="width: 605px;">
    <div id="board_header"><span id="board_title" style="font-size: 30px;"><%= board.getTitle() %></span><br>
      <span>작성자 | <%= board.getWriterId() %></span> <span>작성일 | <%= board.getRegDate() %></span>
      <span id="option">
        <span>조회 <%= board.getReadCount() %></span> <span>추천 <%= board.getLikeCount() %></span> <span>댓글수 1</span>
      </span>
    </div>

    <hr>

    <div id="board_content">
      <textarea readonly="" style="resize: none;width: 100%;height: 400px;"><%= board.getContent() %></textarea>
    </div>
    <button>추천</button>
  </div>
	
	<hr style="margin-top:30px;" />    
	
	<div class="comment-container">
        <div class="comment-editor">
            <form
				action="<%=request.getContextPath()%>/board/boardCommentCreate" 
				method="post" 
				name="boardCommentFrm">
                <input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>" />
                <input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />
				<textarea name="content" cols="60" rows="3"></textarea>
                <button type="submit" id="btn-comment-enroll1">등록</button>
            </form>
        </div>
		<!--table#tbl-comment-->
		<%	if(boardComments != null && !boardComments.isEmpty()) { %>
			<table id="tbl-comment">
				<%
					for(BoardComment bc : boardComments) {
						boolean canRemove = 
								loginMember != null && 
								(loginMember.getMemberId().equals(bc.getWriterId())
								  || MemberRole.A == loginMember.getMemberRole());
				%>
						<%-- 댓글인 경우 tr.level1 --%>
						<tr class="level1">
							<td>
								<sub class=comment-writer><%= bc.getWriterId() %></sub>
								<sub class=comment-date><%= bc.getRegDate() %></sub>
								<br />
								<%= bc.getContent() %>
							</td>
							<td>
								<% 	if (canRemove) { %>
								<%-- 로그인하고, 작성자본인 또는 관리자인 경우만 노출 --%>
								<button class="btn-delete" value="<%= bc.getCommentNo() %>">삭제</button>
								<%  } %>
							</td>
						</tr>
				<%
					}
				%>
			</table>
		<% 	} %>
	</div>    
    
	</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>