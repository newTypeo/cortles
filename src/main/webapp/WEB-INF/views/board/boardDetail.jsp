<%@page import="com.cortles.project.board.model.vo.BoardComment"%>
<%@page import="com.cortles.project.board.model.vo.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="com.cortles.project.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header2.jsp" %>
<%
	Board board = (Board) request.getAttribute("board");
	List<Attachment> attachments = board.getAttachments();
	List<BoardComment> boardComments = (List<BoardComment>) request.getAttribute("boardComments");
	
	Attachment attachment = (Attachment) session.getAttribute("attachment");
	if(attachment != null) session.removeAttribute("attachment");
	
	int boardCommentCnt = (int)request.getAttribute("boardCommentCnt");
	
%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<section id="board-detail-container">
    <div id="board_header"><span id="board_title" style="font-size: 30px;"><%= board.getTitle() %></span><br><br><br>
      <span style="margin: 0;">Writer / <%= board.getWriterId() %></span> <span>regDate | <%= board.getRegDate() %></span>
      <span id="option">
        <span>조회 <%= board.getReadCount() %></span> <span>추천 <%= board.getLikeCount() %></span> <span>댓글수 <%= boardCommentCnt %></span>
      </span>
    </div>

    <hr>

	<%-- 
		사용자가 파일 업로드 한 경우, 업로드한 사진을 띄워줌.
		@author 창환
	--%>
    <div id="board_content">
    	<% if(attachment.getRenamedFilename() != null) { %>
    		<img src="<%= request.getContextPath() %>/upload/board/<%= attachment.getRenamedFilename() %>" />
   		<% } %>
      <textarea readonly="" style="background-color: #141414; color: white; border: none; border-radius: 4px; resize: none;width: 100%; height: auto; min-height: 400px; "><%= board.getContent() %></textarea>
    </div>
    <br>
    <!-- 추천 폼 시작 경빈 -->
	 <form action="<%= request.getContextPath()%>/board/boardLikeGood"  name="boardLikeGoodFrm" onsubmit="return checkLoginForm();">
   <input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>" />
   <input type="hidden" name="likeCount" value="<%= board.getLikeCount() %>" />
   <button type="submit" id="btn" class="likegood" onclick="alertLikeResult();">❤&nbsp;<%= board.getLikeCount() %></button>
</form>
   <!-- 추천 폼 끝 -->
    <br><br>
   
   	<%
   		boolean showButton = loginMember != null
   			&& (loginMember.getMemberId().equals(board.getWriterId())
   					|| loginMember.getMemberRole() == MemberRole.A);
   		if(showButton){
   	%>
   	
   	<div id="subbtn-wrapper" style="display: flex; justify-content: center;">
   	
	   	<%-- modify 버튼 클릭시 updateBoard() 실행 --%>
	   	<input type="button" id="btn" class="subbtn" value="modify" onclick="updateBoard()" />
	   	
	   	<%-- delete 버튼 클릭시 updateBoard() 실행 --%>
	   	<input type="button" id="btn" class="subbtn" value="delete" onclick="boardDelete()"/>
	   	
   	</div>
  	<% } %>
  	<script>
  	/**
  	 * 게시글 수정을 위한 boardUpdate 서블릿으로 이동.
  	 * @author 창환
  	 */
  	const updateBoard = () => {
  		location.href = "<%= request.getContextPath() %>/board/boardUpdate?no=<%= board.getBoardNo() %>";
  	};
  	
	<%-- 글삭제-주혜 --%>
	const boardDelete = () =>{
		if(confirm("글을 삭제하시겠습니까?")){
			document.boardDeleteFrm.submit();
		}
	};
	</script>
	<%-- 게시글 수정 서블릿 전달용 hidden frm --%>
  	<form 
		action="<%= request.getContextPath() %>/board/boardUpdate" 
		name="boardUpdateFrm"
		method="POST">
		<input type="hidden" name="no" />
		<input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>"/>
	</form>
	
	<script>
	$(document).ready(function() {
	      $('#board_content').on( 'keyup', 'textarea', function (e){
	        $(this).css('height', 'auto' );
	        $(this).height( this.scrollHeight );
	      });
	      $('#board_content').find( 'textarea' ).keyup();
    });
	</script>
	<hr style="margin-top:30px;" />    
	
	<div class="comment-container">
        <div class="comment-editor">
            <form
				action="<%=request.getContextPath()%>/board/boardCommentCreate" 
				method="post" 
				name="boardCommentFrm">
                <input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>" />
                <input type="hidden" name="writerId" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />
                <input type="hidden" name="boardCnt" value="<%= board.getCommentCnt() %>" />
				<textarea name="content" cols="60" rows="3"></textarea>
                <button type="submit" id="btn-comment-enroll1">push</button>
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
								
								<button class="btn-update" value="<%= bc.getCommentNo() %>">update</button>
								<button class="btn-delete" value="<%= bc.getCommentNo() %>">delete&nbsp;</button>
								<%  } %>
								
							<% if (loginMember != null && !loginMember.getMemberId().equals(bc.getWriterId()) && MemberRole.A != loginMember.getMemberRole()) { %>
							    <button class="btn-report" value="<%= bc.getCommentNo() %>">report</button>
							<% } %>

							</td>
						</tr>
				<%
					}
				%>
			</table>
		<% 	} %>
	</div>
	
	<form 
		action="<%= request.getContextPath() %>/board/boardCommentReport" 
		name="boardCommentReportFrm"
		method="get">
		<input type="hidden" name="no" />
		<input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>"/>
		<input type="hidden" name="reporterId" value="<%= loginMember.getMemberId() %>"/>
	</form>
	<form 
		action="<%= request.getContextPath() %>/board/boardCommentDelete" 
		name="boardCommentDelFrm"
		method="POST">
		<input type="hidden" name="no" />
		<input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>"/>
	</form>
	<form 
		action="<%= request.getContextPath() %>/board/boardCommentUpdate"
		name="boardCommentUpdateFrm"
		method="get">
		<input type="hidden" name="no" />
		<input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>"/>
	</form>
	<script>
	var modal = document.getElementById("myModal");
	function openModal() {
        modal.style.display = "block";
        document.body.style.overflow = "hidden"; // 스크롤 비활성화
    }

    function closeModal() {
        modal.style.display = "none";
        document.body.style.overflow = "auto"; // 스크롤 활성화
    }
    
    
    document.querySelectorAll(".btn-report").forEach((button) => {
		button.onclick = (e) => {
			const frm = document.boardCommentReportFrm;
			const {value} = e.target;
			console.log(value);
			frm.no.value = value;
			frm.submit();
		}
	});
    
	
	
	document.querySelectorAll(".btn-update").forEach((button) => {
		button.onclick = (e) => {
			const frm = document.boardCommentUpdateFrm;
			const {value} = e.target;
			console.log(value);
			frm.no.value = value;
			frm.submit();
		}
	});
	

	document.querySelectorAll(".btn-delete").forEach((button) => {
		button.onclick = (e) => {
			if(confirm("해당 댓글을 삭제하시겠습니까?")){
				const frm = document.boardCommentDelFrm;
				const {value} = e.target;
				console.log(value);
				frm.no.value = value;
				frm.submit();
			}
		}
	});
	
	document.querySelectorAll(".btn-reply").forEach((button) => {
		button.onclick = (e) => {
			const {value} = e.target;
			const parentTr = e.target.parentElement.parentElement;
			console.log(parentTr);
			
			const tr = `
				<tr>
					<td colspan="2">
						<form
							action="<%=request.getContextPath()%>/board/boardCommentCreate" 
							method="post"
							name="boardCommentFrm">
			                <input type="hidden" name="boardNo" value="<%= board.getBoardNo() %>" />
			                <input type="hidden" name="writerId" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />
							<textarea name="content" cols="60" rows="1"></textarea>
			                <button type="submit" class="btn-comment-enroll2">등록</button>
			            </form>
					</td>
				</tr>
			`;
			// beforebegin 시작태그전 - 이전형제요소로 추가
			// afterbegin 시작태그후 - 첫자식요소로 추가
			// beforeend 종료태그전 - 마지막요소로 추가
			// afterend 종료태그후 - 다음형제요소로 추가
			parentTr.insertAdjacentHTML('afterend', tr);
			
			button.onclick = null; // 이벤트핸들러 제거 (1회용)
		};
	});
	
	// 이벤트버블링을 이용한 textarea focus핸들러
	// focus, blur 버블링되지 않음. 대신 focusin, focusout 사용.
	<%--document.addEventListener("focusin", (e) => {
		if(e.target.matches("form[name=boardCommentFrm] textarea")) {
			<% 	if (loginMember == null) { %>
				loginAlert();
			<% 	} %>
		}
	});--%>
	
	// 이벤트버블링을 이용한 폼유효성 검사 
	document.addEventListener("submit", (e) => {
		
		// 특정선택자와 매칭여부 matches
		if (e.target.matches("form[name=boardCommentFrm]")) {			
			<% 	if (loginMember == null) { %>
				loginAlert();
				e.preventDefault();
				return;
			<% 	} %>
			
			const frm = e.target;
			const content = frm.content;
			
			if(!/^(.|\n)+$/.test(content.value)) {
				alert("내용을 작성해주세요.");
				e.preventDefault();
				return;
			}
		}
		
	});
	
	const loginAlert = () => {
		alert("로그인후 댓글을 작성할 수 있습니다.");
	};

	</script>
	
	    
    
	</section>
<% if(showButton){ %>
<form action="<%= request.getContextPath()%>/board/boardDelete" name="boardDeleteFrm" method="POST">
	<input type="hidden" name="no" value="<%= board.getBoardNo() %>" />
	<input type="hidden" name="filename" value="<%= attachment.getRenamedFilename() %>" />
</form>

<% } %>
</body>
</html>