<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>movie cortles</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
</head>
<body>
	<header>
		<div class="logo">
			<img src="<%=request.getContextPath()%>/images/cortles.PNG">
		</div>
		<ul>
			<li><a href="<%= request.getContextPath() %>">Home</a></li>
			<li><a href="#">Community</a></li>
			<li><a href="<%= request.getContextPath() %>/member/myList">My List</a></li> <!-- 로그인 했을시에만 보이게 -->
			<li><a href="#">Members</a></li> <!-- 관리자  -->
			<li><a href="#">Report</a></li> <!-- 관리자  -->
		</ul>
		
		<div class="search-bar">
			<input type="text" placeholder="Search...">
		</div>
		<div class="login">
			<a href="#">
				<span style="color:#fff;">Login</span>
			</a>
			<a href="<%= request.getContextPath() %>/member/memberSignup">
				<span style="color:#fff;">Sign up</span>
			</a>
		</div>
		
	</header>
	
	
