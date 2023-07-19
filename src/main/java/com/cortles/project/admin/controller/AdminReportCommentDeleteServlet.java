package com.cortles.project.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.admin.model.service.AdminService;
// 신고 댓글 삭제 - 주혜 
@WebServlet("/admin/reportCommentDelete")
public class AdminReportCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AdminService adminService = new AdminService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 신고 댓글 삭제 
		int commentNo = Integer.parseInt(request.getParameter("no"));
		String delOnReportComment = request.getParameter("delOnReportComment");
		
		//2. 비지니스로직 호출
		int result = adminService.deleteReportComment(commentNo);
	
		response.sendRedirect(request.getContextPath()+"/report");
	}

}
