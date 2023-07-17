<%@page import="com.cortles.project.board.model.vo.BoardComment"%>
<%@page import="com.cortles.project.board.model.vo.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="com.cortles.project.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Board board = (Board) request.getAttribute("board");
	Attachment attachment = (Attachment) session.getAttribute("attachment");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<section id="board-container">
	<div id="board" style ="width: 605px;">
	    

	<form 
		action="<%= request.getContextPath() %>/board/boardUpdate"
		name="boardUpdateFrm"
		method="POST"
		enctype="multipart/form-data">
		
		<div id="board_header">
			<input type="text" id="board_title" name="title" style="font-size: 30px; text-align: center" value="<%= board.getTitle() %>" /><br><br><br>
	      <span style="margin: 0;">작성자 | <%= board.getWriterId() %></span> <span>작성일 | <%= board.getRegDate() %></span>
	      <%-- <span id="option">
	        <span>조회 <%= board.getReadCount() %></span> <span>추천 <%= board.getLikeCount() %></span> <span>댓글수 <%= boardCommentCnt %></span>
	      </span> --%>
	    </div>

    	<hr>
			<input id="no" type="hidden" name="no" value="<%= board.getBoardNo() %>"/>
			<input id="writer" type="hidden" name="writer" value="<%= loginMember.getMemberId() %>"/>
			<input style="display: none;" id="delFile" type="checkbox" name="delFile" value="<%= attachment.getNo() %>" />
		    <div id="board_content" style="text-align: left;">
		    	<span style="padding: 0; margin: 20px 10px 10px 40px; clear: both;">첨부파일:</span>
		    	<% if(attachment.getOriginalFilename() != null) { %>
		    	<div id="fileName_wrapper" style="display: inline-block;">
		    		<span id="fileName"><%= attachment.getOriginalFilename() %></span>
		    		<input type="button" onclick="remove_div()" value="삭제">
		    	</div>
		    	<% } else { %>
		    	<input id="file" type="file" name="upFile" style="margin: 10px; margin-left: 30px;" />
		    	<% } %>
		      <textarea id="textarea" name="content" style="min-height: 400;border: ridge; background-color: #141414; color: white; resize: none;width: 100%; height: auto;"><%= board.getContent() %></textarea>
	    	</div>
	    
	    <button type="submit">수정하기</button>
	    <button onclick="history.go(-1);">취소</button>
	</form>
    
    <br>
    <br><br>
  </div>
  	<script>
	/**
	 * 삭제 버튼 누르면 display: none 처리됨.
	 */
  	const remove_div = () => {
  		const remove = document.querySelector("#fileName_wrapper");
  		//remove.style.display = 'none';
  		remove.innerHTML += `
  			<input id="file" type="file" name="upFile" />
  		`;
  		remove.children[0].style.display= 'none';
  		remove.children[1].style.display= 'none';
  		
  		// 폼 안에 있는 display: none처리된 checkbox를 checked로 변경. (전송하기 위해서)
  		document.querySelector("#delFile").checked = 'checked';
  	};
  	
  	const update = () => {
  		const frm = document.boardUpdateFrm;
  		
  		frm.title.value = document.querySelector('#textarea').value;
  		frm.content.value = document.querySelector().value;
  		
  		frm.submit();
  	};
  	
  	
  	const updateBoard = () => {
  		location.href = "<%= request.getContextPath() %>/board/boardUpdate?no=<%= board.getBoardNo() %>";
  	};
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
		method="POST"
		enctype="multipart/form-data">
		<%-- <input type="hidden" name="no" /> --%>
		<input id="no" type="hidden" name="no" value="<%= board.getBoardNo() %>"/>
		<input id="title" type="hidden" name="title" value=""/>
		<input id="content" type="hidden" name="content" value=""/>
		<input id="writer" type="hidden" name="writer" value=""/>
	</form>
	
	<%-- textarea 안에 있는 글의 길이가 넘어가면 자동 높낮이 조정 --%>
	<script>
	$(document).ready(function() {
	      $('#board_content').on( 'keyup', 'textarea', function (e){
	        $(this).css('height', 'auto' );
	        $(this).height( this.scrollHeight );
	      });
	      $('#board_content').find( 'textarea' ).keyup();
    });
	</script>
	
	<script>

	
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

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
