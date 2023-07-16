package com.cortles.project.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.board.model.service.BoardService;
import com.cortles.project.board.model.vo.Board;
import com.cortles.project.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardCommentUpdateServlet
 */
@WebServlet("/board/boardCommentUpdate")
public class BoardCommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("no = " + no);
		// 2. 업무로직
		BoardComment updateBoardComment = boardService.boardCommentfindById(no);
		System.out.println("updateBoardComment = " + updateBoardComment);
		
		request.setAttribute("updateBoardComment", updateBoardComment);
		// 3. 응답처리
		request.getRequestDispatcher("/WEB-INF/views/board/boardCommentUpdate.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

}
