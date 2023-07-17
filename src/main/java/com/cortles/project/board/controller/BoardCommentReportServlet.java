package com.cortles.project.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.board.model.service.BoardService;
import com.cortles.project.board.model.vo.BoardComment;
import com.cortles.project.board.model.vo.ReportComment;

/**
 * Servlet implementation class BoardCommentReportServlet
 */
@WebServlet("/board/boardCommentReport")
public class BoardCommentReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		 int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		 String reportTitle = request.getParameter("reportTitle");
		 String reporterId = request.getParameter("reporterId");
		 String reportType = request.getParameter("reportType");
		 String reportContent = request.getParameter("reportContent");
		 String reportedId = request.getParameter("reportedId");
		 
		 ReportComment reportComment = new ReportComment(commentNo, boardNo, 0, reporterId, reportedId, reportContent, reportType, null);
		 
		 int result = boardService.insertReportBoardComment(reportComment);
		 
		 
		 
		 
		 response.sendRedirect(request.getContextPath());
	}

}
