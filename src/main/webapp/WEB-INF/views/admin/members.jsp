<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
  <h1>members</h1>
	 <table>
        <thead>
            <tr>
                <th>이름</th>
                <th>나이</th>
                <th>이메일</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>홍길동</td>
                <td>30</td>
                <td>hong@example.com</td>
            </tr>
            <tr>
                <td>김철수</td>
                <td>25</td>
                <td>kim@example.com</td>
            </tr>
            <tr>
                <td>이영희</td>
                <td>35</td>
                <td>lee@example.com</td>
            </tr>
        </tbody>
    </table>
</body>
</html>