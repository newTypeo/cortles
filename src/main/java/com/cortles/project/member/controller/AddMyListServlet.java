package com.cortles.project.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cortles.project.member.model.service.MemberService;

@WebServlet("/member/AddMyListServlet") 
public class AddMyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberService memberService = new MemberService();
	
	/**
	 * 영화 폼 제출 만들고 매핑하기 - 경빈
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 입력값 받아오기
		String memberId = request.getParameter("memberId");
		String movieCode = request.getParameter("movieCode");
		
		// 업무로직처리 
		HttpSession session = request.getSession();
		
		// alert 띄우기
		session.setAttribute("msg", "The movie added on myList（＾∀＾●）ﾉｼ");
		
		// 응답처리
		response.sendRedirect(request.getContextPath());
	}
}
