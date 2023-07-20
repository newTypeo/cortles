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
 * 게시글 댓글 등록 -장준-
 */
@WebServlet("/board/boardCommentCreate")
public class BoardCommentCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();
	private final com.cortles.project.notification.NotificationService notificationService = new com.cortles.project.notification.NotificationService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				// 1. 사용자입력값 처리
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				String writerId = request.getParameter("writerId");
				String content = request.getParameter("content");
				
				BoardComment boardComment = new BoardComment(0, boardNo, writerId, content, null);
				
				// 2. 업무로직
				// 댓글 등록
				int result = boardService.insertBoardComment(boardComment);
				
				// 댓글 등록 실시간 알림
				Board board = boardService.findById(boardNo);
				result = notificationService.notifyNewBoardComment(board);
				
				// 3. 응답처리 - redirect
				response.sendRedirect(request.getContextPath() + "/board/boardDetail?no=" + boardNo);
	}

}
