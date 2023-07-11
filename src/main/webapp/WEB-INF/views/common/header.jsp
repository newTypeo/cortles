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
			<li><a href="#">TV Shows</a></li>
			<li><a href="#">Movies</a></li>
			<li><a href="#">New & Popular</a></li>
			<li><a href="#">My List</a></li>
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
	
	<section>
		<span>홍길동 님의 취향저격 베스트 콘텐츠</span>
		<article id="art1">
			<img src="<%= request.getContextPath() %>/images/flower1.PNG"/>
			<img src="<%= request.getContextPath() %>/images/flower2.PNG"/>
			<img src="<%= request.getContextPath() %>/images/flower3.PNG"/>
			<img src="<%= request.getContextPath() %>/images/flower4.PNG"/>
			<img src="<%= request.getContextPath() %>/images/flower5.PNG"/>
			<img src="<%= request.getContextPath() %>/images/river1.PNG"/>
		</article>
		<hr/>
		<span>액션 본능이 폭발한다</span>
		<article id="art2">
			<img src="<%= request.getContextPath() %>/images/flower1.PNG"/>
			<img src="<%= request.getContextPath() %>/images/flower2.PNG"/>
			<img src="<%= request.getContextPath() %>/images/flower3.PNG"/>
			<img src="<%= request.getContextPath() %>/images/flower4.PNG"/>
			<img src="<%= request.getContextPath() %>/images/flower5.PNG"/>
			<img src="<%= request.getContextPath() %>/images/river1.PNG"/>
		</article>
		<hr/>
	</section>
