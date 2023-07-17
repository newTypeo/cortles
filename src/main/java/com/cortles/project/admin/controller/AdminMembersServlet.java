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

/**
 * Servlet implementation class AdminMembersServlet
 */
@WebServlet("/members")
public class AdminMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Member> members = memberService.findAll();
		//System.out.println("members = " + members);
		List<QuitMember> quitMembers = memberService.quitMemberFindAll();
		
		request.getSession().setAttribute("quitMembers", quitMembers);
		
		request.setAttribute("members", members);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/members.jsp")
			.forward(request, response);
	}

}
