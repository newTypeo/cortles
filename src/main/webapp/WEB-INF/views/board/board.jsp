<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<style>
table {
	margin: 20px 0;
	border: 1px solid #000;
	border-collapse: collapse;
}
th, td {
	border: 1px solid #000;
	padding: 5px;
}
</style>
<title>Insert title here</title>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>조회</th>
			<th>추천</th>
			<th>글쓴이</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td colspan="6">조회된 게시글이 없습니다.</td>
		</tr>
	</tbody>

</table>
<div id='pagebar'>
		<%= request.getAttribute("pagebar") %>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
