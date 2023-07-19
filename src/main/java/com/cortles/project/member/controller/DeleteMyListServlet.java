package com.cortles.project.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cortles.project.member.model.service.MemberService;

@WebServlet("/member/deleteMyListServlet")
public class DeleteMyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberService memberService = new MemberService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String movieCode = request.getParameter("movieCode");
		
		int result = memberService.deleteMyList(memberId, movieCode);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", "찜취소 완료!");
		
		response.sendRedirect(request.getContextPath() + "/member/myList?memberId=" + memberId);
	}

}
