package com.cortles.project.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.board.model.service.BoardService;

/**
 * Servlet implementation class BoardLikeGoodServlet
 */
@WebServlet("/board/boardLikeGood")
public class BoardLikeGoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자 입력값 처리
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int likeCount = Integer.parseInt(request.getParameter("likeCount"));
		System.out.println("boardNo = " +  boardNo);
		System.out.println("likeCount = " + likeCount);
		
		
		// 2. 업무로직
		int result = boardService.updateLike(boardNo, likeCount);
		
		// 3. 응답처리
		
		response.sendRedirect(request.getContextPath() + "/board/boardDetail?no=" + boardNo);
		
	}

}
