package com.cortles.project.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.member.model.service.MemberService;
import com.cortles.project.member.model.vo.Member;
import com.cortles.project.member.model.vo.QuitMember;

@WebServlet("/admin/findAllMembers")
public class AdminFindAllMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final MemberService memberService = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Member> members = memberService.findAll();
		List<QuitMember> quitMembers = memberService.quitMemberFindAll();
		System.out.println("members" + members);
		request.setAttribute("members", members);
		
		request.getSession().setAttribute("quitMembers", quitMembers);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/members.jsp")
			.forward(request, response);
	}

}
