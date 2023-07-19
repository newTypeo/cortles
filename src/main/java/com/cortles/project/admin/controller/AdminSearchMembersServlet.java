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

@WebServlet("/admin/searchMembers")
public class AdminSearchMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("searchKeyword");
		
		List<Member> members = memberService.searchMember(keyword);
		request.getSession().setAttribute("members", members);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/members.jsp")
		.forward(request, response);
	}

}
