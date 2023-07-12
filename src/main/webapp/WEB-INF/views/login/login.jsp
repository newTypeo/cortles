<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login.css" />

</head>
<body>
	 <div class="container">
    <form>
      <h2>로그인</h2>
      <div class="input-group">
        <label for="username">사용자명</label>
        <input type="text" id="username" required>
      </div>
      <div class="input-group">
        <label for="password">비밀번호</label>
        <input type="password" id="password" required>
      </div>
      <div class="input-group">
        <button type="submit">로그인</button>
      </div>
    </form>
  </div>

</body>
</html>