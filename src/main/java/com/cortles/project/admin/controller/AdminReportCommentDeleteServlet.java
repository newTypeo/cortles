package com.cortles.project.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.admin.model.service.AdminService;

@WebServlet("/admin/reportCommentDelete")
public class AdminReportCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AdminService adminService = new AdminService();
	
	/**
	 * 신고 댓글 삭제 - 주혜
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 신고 댓글 삭제 
		int commentNo = Integer.parseInt(request.getParameter("no"));
		int reportCommentNo = Integer.parseInt(request.getParameter("reportCommentNo"));
		
		String delOnReportComment = request.getParameter("delOnReportComment");
		
		//2. 비지니스로직 호출
		int result = adminService.deleteReportComment(commentNo);
		
		// 신고목록에서 제거 - 종환
		int result2 = adminService.deleteReportCommentOnReportList(reportCommentNo);
		response.sendRedirect(request.getContextPath()+"/report");
	}

}
