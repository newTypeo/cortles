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
// 멤버 전체 조회 - 주혜 
@WebServlet("/admin/findAllMembers")
public class AdminFindAllMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final MemberService memberService = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 일반 회원 리스트 
		List<Member> members = memberService.findAll();
		// 탈퇴한 회원 리스트 
		List<QuitMember> quitMembers = memberService.quitMemberFindAll();
		//System.out.println("members" + members);
		
		request.setAttribute("members", members);
		request.getSession().setAttribute("quitMembers", quitMembers);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/members.jsp")
			.forward(request, response);
	}

}
