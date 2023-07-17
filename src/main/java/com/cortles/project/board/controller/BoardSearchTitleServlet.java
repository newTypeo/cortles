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

/**
 * Servlet implementation class BoardSearchTitleServlet
 */
@WebServlet("/board/searchTitle")
public class BoardSearchTitleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Keyword = request.getParameter("searchKeyword");
		
		List<Board> boards = boardService.searchboard(Keyword);
		
		request.setAttribute("boards", boards);
		
		request.getRequestDispatcher("/WEB-INF/views/board/board.jsp")
			.forward(request, response);
	}


}
