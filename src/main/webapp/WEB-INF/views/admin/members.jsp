<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
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
                <th>아이디</th>
                <th>이름</th>
                <th>생년월일</th>
                <th>이메일</th>
                <th>휴대폰</th>
                <th>성별</th>
                <th>선호장르</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>홍길동</td>
                <td>30</td>
                <td>1990-09-09</td>
                <td>hong@example.com</td>
                <td>010-0000-0000</td>
                <td>남</td>
                <td>스릴러,코미디</td>
            </tr>
           
        </tbody>
    </table>
</body>
</html>