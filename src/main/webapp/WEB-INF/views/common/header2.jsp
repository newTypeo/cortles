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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="<%= request.getContextPath() %>/js/jquery-3.7.0.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/vanilla-tilt/1.8.0/vanilla-tilt.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/header2.css"/>
<%
	
	Member loginMember = (Member) session.getAttribute("loginMember");
	
	Cookie[] cookies = request.getCookies();
	String saveId = null;
	if(cookies != null) {
		for(Cookie cookie : cookies) {
			String name = cookie.getName();
			String value = cookie.getValue();
			if ("saveId".equals(name))
				saveId = value;
		}
	}
%>
<% if(loginMember != null) { %>
	<script src="<%= request.getContextPath() %>/js/ws.js"></script>
<% } %>
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
			<a href="<%= request.getContextPath()%>/index.jsp"><img src="https://fontmeme.com/permalink/230720/d5f0db1f4809bc0deb07cc374662a4d6.png" alt="netflix-type" border="0"></a>
		</div>
		<ul>
			<li><a href="<%= request.getContextPath() %>">Home</a></li>
			<li><a href="<%= request.getContextPath() %>/board/boardList">Community</a></li>
			<li><a href="#" onclick="mylist()">MyList</a></li> <!-- 로그인 했을시에만 보이게 -->
			<!-- if(loginMember != null && loginMember.getMemberRole() == MemberRole.A){ %>  -->
			<li><a href="<%= request.getContextPath() %>/admin/findAllMembers">Members</a></li> <!-- 관리자  -->
			<li><a href="<%= request.getContextPath() %>/report">Report</a></li> <!-- 관리자  -->
			<!--  } %>  -->
		</ul>
		
			<div class="search-bar" style="opacity:0;">
				<input type="text" placeholder="Search..." id="input-search">
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
	                	<%= loginMember.getMemberName() %>님
	                	<span id="notification"></span>
	                </td>
	            </tr>
	            <tr>
	                <td>
	                	<!--  
	                	<button type="button" class="btn1"
	                    	onclick="location.href = '<%= request.getContextPath() %>/member/memberUpdate';">내정보보기
	                    </button>	
	                    <button type="button" class="btn1"
	                    	onclick="location.href = '<%= request.getContextPath() %>/member/logout';">로그아웃
	                    </button>
	                    -->	
	                    <div class="login-after">
	                   	 	<a href="<%= request.getContextPath() %>/member/memberUpdate" id="a">Info</a>
	                    	<a href="<%= request.getContextPath() %>/member/logout" id="b">logout</a>
	                    </div>
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
		if(input_text == "") {
			document.querySelector("#searchMovies-article").innerHTML = "";
			document.querySelector("#searchMovies-section").style.display = "block";
			return;
		}
		$.ajax({
			url: "<%=request.getContextPath()%>/movie/json/searchMovies",
			data : {input_text},
			method : "get",
			dataType : "json",
			success(movies) {
				// console.log("movies = " , movies);
				if(movies.length != 0) {
					document.querySelector("#searchMovies-section").style.display = "none";
					document.querySelector("#searchMovies-article").innerHTML = "";
				} 
				[...movies].forEach((movie) => {
					const {posterUrl, genre, movieCode} = movie;
					// console.log("posterUrl, genre, movieCode " , posterUrl, genre, movieCode);
					document.querySelector("#searchMovies-article").innerHTML += `<img name=\${movieCode} src=\${posterUrl}>`;
					
				}) // forEach
			}, // success
			complete() {
				[...document.querySelectorAll("img")].forEach((imgTag) => {
					imgTag.addEventListener('click', (e) =>{
						openModal(e.target.name);
						
					}) // eventListener
				}); // 모든 포스터에 clickEvent 추가용 forEach
			} // complete
		}) // ajax
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
