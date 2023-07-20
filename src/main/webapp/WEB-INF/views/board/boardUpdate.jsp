<%@page import="com.cortles.project.board.model.vo.BoardComment"%>
<%@page import="com.cortles.project.board.model.vo.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="com.cortles.project.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header2.jsp" %>
<%
	Board board = (Board) request.getAttribute("board");
	Attachment attachment = (Attachment) session.getAttribute("attachment");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<%-- 
	게시글 수정을 위한 jsp 
	@author 창환
--%>
<section id="board-detail-container">
	<div id="board" style ="width: 605px;">
	    
	<%-- 게시글을 수정한 내용을 boardUpdate 서블릿으로 전달하기 위한 form --%>
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
			
			<%-- 작성자는 변경 하지 못하게 input태그로 사용하지 않았기 때문에 hidden으로 작성자 아이디 입력 --%>
			<input id="writer" type="hidden" name="writer" value="<%= loginMember.getMemberId() %>"/>
			
			<%-- 첨부파일을 수정했는지 확인하기 위한 display: none 체크박스 --%>
			<input style="display: none;" id="delFile" type="checkbox" name="delFile" value="<%= attachment.getNo() %>" />
			
		    <div id="board_content" style="text-align: left;">
		    	<span style="padding: 0; margin: 20px 10px 10px 40px; clear: both;">첨부파일:</span>
		    	
		    	<%-- 기존 작성했던 첨부파일이 있는 경우 --%>
		    	<% if(attachment.getOriginalFilename() != null) { %>
		    	<div id="fileName_wrapper" style="display: inline-block;">
		    		<span id="fileName"><%= attachment.getOriginalFilename() %></span>
		    		
		    		<%-- 삭제버튼 누를 시 remove_div() 실행 --%>
		    		<input type="button" onclick="remove_div()" value="삭제">
		    	</div>
		    	
		    	<%-- 기존 작성했던 첨부파일이 없는 경우 --%>
		    	<% } else { %>
		    	<input id="file" type="file" name="upFile" style="margin: 10px; margin-left: 30px;" />
		    	<% } %>
		      <textarea id="textarea" name="content" style="#141414; background-color: #fff; color: #000; border: none; border-radius: 4px; resize: none;width: 100%; height: auto; min-height: 400px;"><%= board.getContent() %></textarea>
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
  		
  		remove.innerHTML += `
  			<input id="file" type="file" name="upFile" />
  		`;
  		remove.children[0].style.display= 'none';
  		remove.children[1].style.display= 'none';
  		
  		// 폼 안에 있는 display: none처리된 checkbox를 checked로 변경. (전송하기 위해서)
  		document.querySelector("#delFile").checked = 'checked';
  	};
  	
  	
	<%-- textarea 안에 있는 글의 길이가 넘어가면 자동 높낮이 조정 --%>
	$(document).ready(function() {
	      $('#board_content').on( 'keyup', 'textarea', function (e){
	        $(this).css('height', 'auto' );
	        $(this).height( this.scrollHeight );
	      });
	      $('#board_content').find( 'textarea' ).keyup();
    });

	
	</script>
	
	    
    
	</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
