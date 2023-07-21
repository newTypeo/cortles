<%@page import="com.cortles.project.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header2.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/login.css" />
	<section>
	<form
		id="loginFrm"
		name="loginFrm"
		action="<%=request.getContextPath()%>/member/memberLogin"
		method="POST">
	<div class="square">
        <i style="--clr:#00ff0a;"></i>
        <i style="--clr:#ff0057;"></i>
        <i style="--clr:#fffd44;"></i>
        <div class="login">
            <h2>Login</h2>
            <div class="inputBx">
                <input type="text" id="memberId" name="memberId" placeholder="Username">
            </div>
            <div class="inputBx">
                <input type="password" id="password" name="password" placeholder="Password">
            </div>
            <div class="inputBx">
                <input type="submit" value="Sign in">
            </div>
        </div>
    </div>
    </form>
  </section>
  
  <%@ include file="/WEB-INF/views/common/footer.jsp" %>