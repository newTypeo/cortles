package com.cortles.project.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.member.model.service.MemberService;
import com.cortles.project.member.model.vo.MemberRole;
// 권한 수정 - 주혜 
@WebServlet("/admin/memberRoleUpdate")
public class AdminMemberRoleUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberService memberService = new MemberService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String _memberRole = request.getParameter("memberRole");
		MemberRole memberRole = MemberRole.valueOf(_memberRole);
		
		int result = memberService.memberRoleUpdate(memberId,memberRole);
		
		response.sendRedirect(request.getContextPath()+"/admin/searchMembers");
	}

}
