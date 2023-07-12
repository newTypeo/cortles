<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String memberId = request.getParameter("memberId");
	boolean available = (boolean) request.getAttribute("available");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디중복검사</title>
<style>
div#checkId-container{text-align:center; padding-top:50px;}
span#available {color:green; font-weight:bold;}
span#duplicated {color:red; font-weight:bold;}
</style>
</head>
<body>
	<div id="checkId-container">
	<%	if (available) { %>
		<p>
			<span id="available"><%= memberId %></span>는 사용가능합니다.
		</p>
		<button onclick="popupClose();">닫기</button>
	<% 	} else { %>
		<p>
			<span id="duplicated"><%= memberId %></span>는 이미 사용중입니다.
		</p>
		<form 
			name="checkIdDuplicateFrm">
			<input type="text" name="memberId" placeholder="아이디를 입력하세요."/>
			<input type="submit" value="중복검사" />
		</form>
		
	<%  } %>
	</div>
	<script>
	const popupClose = () => {
		// 부모창 window를 가리키는 변수
		opener.document.memberEnrollFrm.memberId.value = "<%= memberId %>";
		opener.document.memberEnrollFrm.idValid.value = "1";
		self.close(); // 현재 팝업창 닫기
	};
	</script>
</body>
</html>
