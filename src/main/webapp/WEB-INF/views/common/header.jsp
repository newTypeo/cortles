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
<<<<<<< HEAD
			<li><a href="#">My List</a></li>
			<li><a href="<%= request.getContextPath() %>/members">Members</a></li> <!-- 관리자  -->
			<li><a href="<%= request.getContextPath() %>/report">Report</a></li> <!-- 관리자  -->
=======
			<li><a href="<%= request.getContextPath() %>/member/myList">My List</a></li> <!-- 로그인 했을시에만 보이게 -->
			<li><a href="#">Members</a></li> <!-- 관리자  -->
			<li><a href="#">Report</a></li> <!-- 관리자  -->
>>>>>>> branch 'master' of https://github.com/newTypeo/Cortles.git
		</ul>
		
		<div class="search-bar">
			<input type="text" placeholder="Search...">
		</div>
		<div class="login">
			<a href="<%= request.getContextPath()%>/member/memberLogin">
				<span style="color:#fff;">Login</span>
			</a>
			
			<a href="<%= request.getContextPath() %>/member/memberSignup">
				<span style="color:#fff;">Sign up</span>
			</a>
		</div>
		
	</header>
	
	
