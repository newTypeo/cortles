package com.cortles.project.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cortles.project.member.model.service.MemberService;
// 회원 탈퇴 
// 관리자의 회원 탈퇴 - 주혜 
@WebServlet("/member/memberDelete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final MemberService memberService = new MemberService();  
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String delMemberId = request.getParameter("memberId");
		String delOnMembers = request.getParameter("delOnMembers");
		//System.out.println("delMemberId =" + delMemberId);
		
		int result = memberService.deleteMemberById(delMemberId);
		//System.out.println("주혜result@deleteMemberservlet=" + result);
		
		// 세션 회원 삭제해야함
		if(delOnMembers == null || "".equals(delOnMembers)) { // 회원 조회에서 탈퇴 시 
			if(result == 1) {
				HttpSession session = request.getSession();
				session.removeAttribute("loginMember");
			}
			response.sendRedirect(request.getContextPath());
			
		}else { // 관리자가 회원 목록에서 탈퇴 시 
			response.sendRedirect(request.getContextPath()+"/admin/searchMembers");
		}
	}

}
