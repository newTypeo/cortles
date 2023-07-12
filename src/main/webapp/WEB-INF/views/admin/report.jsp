<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 목록</title>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
</head>
<style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        
        th {
            background-color: #f2f2f2;
        }
    </style>
<body>
	<h1>report list</h1>
		<table>
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>댓글내용</th>
					<th>신고사유</th>
					<th>신고자</th>
					<th>신고날짜</th>
					<th>처리</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td></td>
				</tr>
			</tbody>
		</table>
</body>
</html>