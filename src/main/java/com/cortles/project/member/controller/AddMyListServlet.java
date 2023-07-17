package com.cortles.project.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cortles.project.member.model.service.MemberService;

/**
 * Servlet implementation class AddMyListServlet
 */
@WebServlet("/member/AddMyListServlet") // 영화 폼 제출 만들고 매핑하기
public class AddMyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberService memberService = new MemberService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String movieCode = request.getParameter("movieCode");
		
		int result = memberService.addMyList(memberId, movieCode);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", "mylist추가 완료 ! ! !");
		
		response.sendRedirect(request.getContextPath());



	}
}
