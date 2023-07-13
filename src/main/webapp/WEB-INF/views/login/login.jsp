<%@page import="com.cortles.project.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login.css" />
	<section>
	 <div class="container">
    <form
    	id="loginFrm"
    	name="loginFrm"
    	action="<%=request.getContextPath()%>/member/memberLogin"
    	method="post">
      <h2>로그인</h2>
      <div class="input-group">
        <label for="username">아이디</label>
        <input type="text" id="memberId" required name="memberId">
      </div>
      <div class="input-group">
        <label for="password">비밀번호</label>
        <input type="password" id="password" required  name="password">
      </div>
      <div class="input-group">
        <button type="submit">로그인</button>
      </div>
    </form>
  </div>
  </section>
  
  <%@ include file="/WEB-INF/views/common/footer.jsp" %>