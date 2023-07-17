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
	

//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int no = Integer.parseInt(request.getParameter("no"));
//		
//		BoardComment _reportComment = boardService.boardCommentfindById(no);
//		System.out.println("_reportComment = " + _reportComment);
//		
//		request.setAttribute("_reportComment", _reportComment);
//		
//		request.getRequestDispatcher("/WEB-INF/views/board/boardCommentReport.jsp")
//			.forward(request, response);
//		
//		
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
