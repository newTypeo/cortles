package com.cortles.project.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.board.model.service.BoardService;
import com.cortles.project.board.model.vo.Board;


@WebServlet("/board/searchTitle")
public class BoardSearchTitleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 사용자 입력 값 처리
		String Keyword = request.getParameter("searchKeyword");
		
		// 업무로직
		List<Board> boards = boardService.searchboard(Keyword);
		
		request.setAttribute("boards", boards);
		
		// 응답처리
		request.getRequestDispatcher("/WEB-INF/views/board/board.jsp")
			.forward(request, response);
	}


}
