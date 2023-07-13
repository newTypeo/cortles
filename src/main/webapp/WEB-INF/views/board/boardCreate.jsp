<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/boardCreate.css" />
<div id="board-wrapper">
  <section id="board-container">
    <h2>게시판 작성</h2>
    <form name="boardCreateFrm" action="<%=request.getContextPath() %>/board/boardCreate" method="post" enctype="multipart/form-data">
        <table id="tbl-board-view">
            <tr>
                <th>제목</th>
                <td><input type="text" name="title" required></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><input type="text" name="writer" value="" readonly></td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td>
                    <input type="file" name="upFile1">
                </td>
            </tr>
            <tr>
                <th>내용</th>
                <td><textarea rows="5" cols="40" name="content"></textarea></td>
            </tr>
            <tr>
                <th colspan="2">
                    <input type="submit" value="등록하기">
                </th>
            </tr>
        </table>
    </form>
  </section>
</div>
<script>
/**
* boardCreateFrm 유효성 검사
*/
document.boardCreateFrm.onsubmit = (e) => {
	const frm = e.target;
	const title = e.target.title;
	const content = e.target.content;
	
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	if(!/^.+$/.test(title.value)) {
		alert("제목을 작성해주세요.");
		return;
	}
	
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	if(!/^(.|\n)+$/.test(content.value)) {
		alert("내용을 작성해주세요.");
		return;
	}

}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
