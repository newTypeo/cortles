<%@page import="com.cortles.project.member.model.vo.MemberRole"%>
<%@page import="com.cortles.project.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String msg = (String) session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg"); // 1회용
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>movie cortles</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css"/>
<%
	// System.out.println("msg = " + msg);
	
	Member loginMember = (Member) session.getAttribute("loginMember");
	
	Cookie[] cookies = request.getCookies();
	String saveId = null;
	if(cookies != null) {
		for(Cookie cookie : cookies) {
			String name = cookie.getName();
			String value = cookie.getValue();
			// System.out.println("[Cookie] " + name + " = " + value);
			if ("saveId".equals(name))
				saveId = value;
		}
	}
%>
<style>
#login-member{
color: white;
}
</style>
<script>
window.onload = () => {
<% 	if(msg != null) { %>
	alert('<%= msg %>');
<% 	} %>	
};
</script>
</head>
<body>
	<header>
		<div class="logo">
			<img src="<%=request.getContextPath()%>/images/cortles.PNG">
		</div>
		<ul>
			<li><a href="<%= request.getContextPath() %>">Home</a></li>
			<li><a href="<%= request.getContextPath() %>/board/boardList">Community</a></li>
			<li><a href="#" onclick="mylist()">MyList</a></li> <!-- 로그인 했을시에만 보이게 -->
			<!-- if(loginMember != null && loginMember.getMemberRole() == MemberRole.A){ %>  -->
			<li><a href="<%= request.getContextPath() %>/members">Members</a></li> <!-- 관리자  -->
			<li><a href="<%= request.getContextPath() %>/report">Report</a></li> <!-- 관리자  -->
			<!--  } %>  -->
		</ul>
		
			<div class="search-bar">
				<input type="text" placeholder="Search...">
			</div>
		
		<% if(loginMember == null) { %>
			<div class="login1">
				<a href="<%= request.getContextPath()%>/member/memberLogin">
					<span style="color:#fff;">Login</span>
				</a>
				
				<a href="<%= request.getContextPath() %>/member/memberSignup">
					<span style="color:#fff;">Signup</span>
				</a>
			</div>
			
		<% } else { %>
			<!-- 로그인사용자정보 시작 -->
			<table id="login">
	            <tr>
	                <td id="login-member">
	                	<%= loginMember.getMemberName() %>님, 안녕하세요.
	                	<span id="notification"></span>
	                </td>
	            </tr>
	            <tr>
	                <td>
	                    <input 
	                    	type="button" 
	                    	value="내정보보기"
	                    	onclick="location.href = '<%= request.getContextPath() %>/member/memberUpdate';"
	                    	class='btn' style="width: 10px;">
	                    <input 
	                    	type="button" 
	                    	value="로그아웃" 
	                    	onclick="location.href='<%= request.getContextPath() %>/member/logout';" class='btn'>
	                </td>
	            </tr>
	        </table>
		<% } %>
		
		
	</header>
	
	<form name="myList" action="<%=request.getContextPath()%>/member/myList">
	     <% if(loginMember != null) { %>
	     	  <input id="memberId" type="hidden" name="memberId" value="<%= loginMember.getMemberId()%>"/>      	  
	     <% } %>
 	</form>
<script>
document.querySelector(".search-bar").oninput = (e) => {
		// console.log("e.target = ", e.target.value);
		const input_text = e.target.value;
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/json/searchMovies",
			data : {input_text},
			method : "get",
			dataType : "json",
			success(searchMovie) {
				console.log("멋지다 종환아~~!! 브라보~~!!");
			},
		
		})

};



window.onload = () => {
	<% if(msg != null) { %>
		alert('<%= msg %>');
	<% } %>
};

const mylist = () => {
	const frm = document.myList;
	frm.submit();
};
</script>
	
	
