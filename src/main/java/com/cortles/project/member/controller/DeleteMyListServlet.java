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
		//사용자 입력값 처리
		// id랑 moviecode가져오기
		String memberId = request.getParameter("memberId");
		String movieCode = request.getParameter("movieCode");
		
		// 업무로직
		int result = memberService.deleteMyList(memberId, movieCode);
		
		// 응답처리
		response.sendRedirect(request.getContextPath() + "/member/myList?memberId=" + memberId);
	}

}
