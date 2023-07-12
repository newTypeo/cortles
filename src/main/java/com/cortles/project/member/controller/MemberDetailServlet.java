package com.cortles.project.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.cortles.project.member.model.service.MemberService;
import com.cortles.project.member.model.vo.Member;

@WebServlet("/member/memberDetail")
public class MemberDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
//		System.out.println("loginMember = " + loginMember);
		
		request.getRequestDispatcher("/WEB-INF/views/member/memberDetail.jsp")
		.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
