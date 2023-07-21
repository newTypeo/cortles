package com.cortles.project.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.board.model.service.BoardService;
import com.cortles.project.board.model.vo.ReportComment;

@WebServlet("/report")
public class AdminReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();

	/**
	 * 신고 댓글 전체 조회 - 주혜 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ReportComment> reportComments = boardService.reportCommentFindAll();
		request.setAttribute("reportComments", reportComments);
		
		request.getRequestDispatcher("WEB-INF/views/admin/report.jsp")
			.forward(request, response);
	}

}
