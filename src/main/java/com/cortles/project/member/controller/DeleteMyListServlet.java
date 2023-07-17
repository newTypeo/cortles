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
 * Servlet implementation class DeleteMyListServlet
 */
@WebServlet("/member/deleteMyListServlet")
public class DeleteMyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberService memberService = new MemberService();


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String movieCode = request.getParameter("movieCode");
		System.out.println("memberId ==>> ! ! ! " + memberId);
		System.out.println("movieCode ==>> ! ! ! " + movieCode);
//		String memberId = "sejong";
//		String movieCode = "F1000";
		int result = memberService.deleteMyList(memberId, movieCode);
		
		if(result > 1 ) {
			System.out.println("삭제완료");
		}
		HttpSession session = request.getSession();
		session.setAttribute("msg", "mylist삭제 완료 ! ! !");
		
		response.sendRedirect(request.getContextPath());
	}

}
