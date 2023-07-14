package com.cortles.project.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cortles.project.member.model.service.MemberService;

@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String delMemberId = request.getParameter("delMemberId");
//		System.out.println("delMemberId =" + delMemberId);
		
		int result = memberService.deleteMemberById(delMemberId);
//		System.out.println("result@deleteMemberservlet=" + result);
		
		// 세션 회원 삭제해야함
		if(result == 1) {
			HttpSession session = request.getSession();
			session.removeAttribute("loginMember");
			session.setAttribute("msg", "회원탈퇴 성공! 다음에 또 만나요 ><");
		}
		response.sendRedirect(request.getContextPath());
	}

}
