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
	 * 댓글 수정 페이지 요청
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리
		int no = Integer.parseInt(request.getParameter("no"));

		// 2. 업무로직 댓글하나 가져오기
		BoardComment updateBoardComment = boardService.boardCommentfindById(no);
		
		request.setAttribute("updateBoardComment", updateBoardComment);
		// 3. 응답처리
		request.getRequestDispatcher("/WEB-INF/views/board/boardCommentUpdate.jsp")
			.forward(request, response);
	}

	/**
	 * 댓글 수정 - 장준 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String content = request.getParameter("content");
		
		// 수정할 댓글 가져오기
		BoardComment _updateBoardComment = boardService.boardCommentfindById(no);
		// 댓글 수정
		int result = boardService.updateBoardComment(no, content);
		_updateBoardComment.setContent(content);
		System.out.println("_updateBoardComment = " + _updateBoardComment);
		
		request.setAttribute("_updateBoardComment", _updateBoardComment);
		
		
		response.sendRedirect(request.getContextPath() + "/board/boardDetail?no=" + boardNo);
	}

}
