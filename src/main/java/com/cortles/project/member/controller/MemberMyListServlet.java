package com.cortles.project.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.member.model.service.MemberService;
import com.cortles.project.member.model.vo.Member;

@WebServlet("/member/myList")
public class MemberMyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberService memberService = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
//		System.out.println("memberId@mylist = " + memberId);
		
		Member member = memberService.findById(memberId);
		
		request.getRequestDispatcher("/WEB-INF/views/member/myList.jsp")
			.forward(request, response);
	}
	

}
