package com.cortles.project.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cortles.project.member.model.service.MemberService;
import com.cortles.project.member.model.vo.Gender;
import com.cortles.project.member.model.vo.Member;

@WebServlet("/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/memberUpdate.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
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
		member.setMemberName(memberName);
		member.setGender(gender);
		member.setBirthday(birthday);
		member.setEmail(email);
		member.setPhone(phone);
		member.setFavoriteGenre(favoriteGenre);
		
		int result = memberService.updateMember(member);
		
		// session의 속성 loginMember도 바로 갱신
		HttpSession session = request.getSession();
		session.setAttribute("loginMember", memberService.findById(memberId));

		// 4. 사용자피드백 및 리다이렉트 처리
		session.setAttribute("msg", "성공적으로 회원정보를 수정했습니다.");

		response.sendRedirect(request.getContextPath());
	}
}
