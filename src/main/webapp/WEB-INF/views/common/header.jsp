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
			<li><a href="#">Home</a></li>
			<li><a href="<%= request.getContextPath() %>/board/boardList">Community</a></li>
			<li><a href="#">My List</a></li>
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
			<a href="#">
				<span style="color:#fff;">Sign up</span>
			</a>
		</div>
		
	</header>
	
	
