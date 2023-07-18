<%@page import="com.cortles.project.board.model.vo.BoardEntity"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
    List<BoardEntity> boards = (List<BoardEntity>) request.getAttribute("boards");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<title>Insert title here</title>

<section id="board-container">
    <h2>게시판 </h2>
    <br>
    <div class="search-container" id="search-name">
        <form action="<%= request.getContextPath()%>/board/searchTitle" name="searchTitleFrm">
            <input type="hidden" name="searchType" value="title"/>
            <input type="text" name="searchKeyword" class="search-input" placeholder="제목으로 검색">
            <span class="btn-wrapper">
                <button class="btn" type="submit" id="boardSelect">SELECT</button>
            </span>
        </form>
    </div>
    <br>
    <% if(loginMember != null) { %>
    <input 
            type="button" id="boardCreateId" value="글쓰기" 
            onclick="location.href = '<%= request.getContextPath() %>/board/boardCreate';" />
    <% } %>
    <br>
    <table id="tbl-board">
        <thead>
            <tr>
                <th id="boarderz">번호</th>
                <th id="boarderz">제목</th>
                <th id="boarderz">조회</th>
                <th id="boarderz">추천</th>
                <th id="boarderz">글쓴이</th>
                <th id="boarderz">작성일</th>
            </tr>
        </thead>
        <tbody>
            <%
            if(boards != null && !boards.isEmpty()){ 
                for(BoardEntity board : boards){
            %>
                <tr>
                    <td id="boarderz"><%= board.getBoardNo() %></td>
                    <td id="boarderz">
                        <a href="<%= request.getContextPath() %>/board/boardDetail?no=<%= board.getBoardNo() %>"><%= board.getTitle() %></a>
                        <%--<% if(board.getCommentCnt() > 0) { %>
                        <%-- [<%= board.getCommentCnt() %>] --%>
                        <%-- ✉ --%>    
                        <%--<% } %> --%>
                    </td>
                    <td id="boarderz"><%= board.getReadCount() %></td>
                    <td id="boarderz"><%= board.getLikeCount() %></td>
                    <td id="boarderz"><%= board.getWriterId() %></td>
                    <td id="boarderz"><%= board.getRegDate() %></td>
                </tr>
            <%
                }
            } 
            else { 
            %>
            <% } %>
        </tbody>
    </table>
</section>
<div id='pagebar'>
    <%= request.getAttribute("pagebar") %>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
