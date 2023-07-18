package com.cortles.project.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardComment reportComment = boardService.boardCommentfindById(no);
		
		request.setAttribute("reportComment", reportComment);
		
		request.getRequestDispatcher("/WEB-INF/views/board/boardCommentReport.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String reporterId = request.getParameter("reporterId");
		String reportedId = request.getParameter("reportedId");
		String reportType = request.getParameter("reportType");
		String reportContent = request.getParameter("reportContent");
		 
		 
		ReportComment reportComment = new ReportComment(commentNo, boardNo, reporterId, reportedId, null, reportType, reportContent, 0, null);
		 
		int result = boardService.insertReportBoardComment(reportComment);
		int reportCnt = boardService.countReport(commentNo);
		
		result = boardService.updateCountReport(commentNo, reportCnt);
		 
		 
		response.sendRedirect(request.getContextPath()+"/board/boardDetail?no=" + boardNo);
	}

}
