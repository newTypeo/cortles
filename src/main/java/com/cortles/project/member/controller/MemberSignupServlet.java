package com.cortles.project.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.common.util.CortlesUtils;
import com.cortles.project.member.model.vo.Gender;
import com.cortles.project.member.model.vo.Member;


@WebServlet("/member/memberSignup")
public class MemberSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/signup/signup.jsp")
			.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = CortlesUtils.getEncryptedPassword(request.getParameter("password"), memberId);
		String memberName = request.getParameter("name");
		String _gender = request.getParameter("gender");
		String _birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String[] _genres = request.getParameterValues("genre");
		
		Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
		Date birthday = _birthday != null && !"".equals(_birthday) ? Date.valueOf(_birthday) : null;
		String favoriteGenre = _genres != null ? String.join(",", _genres) : null;
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		member.setGender(gender);
		member.setBirthday(birthday);
		member.setEmail(email);
		member.setPhone(phone);
		member.setFavoriteGenre(favoriteGenre);
		
		System.out.println(member);
		
	}

}
