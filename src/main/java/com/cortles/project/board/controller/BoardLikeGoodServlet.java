package com.cortles.project.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	


		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			response.setContentType("text/html; charset=UTF-8");

		    String boardNoStr = request.getParameter("boardNo");
		    int boardNo = 0; 

		    if (boardNoStr != null) {
		        boardNo = Integer.parseInt(boardNoStr);
		    }

		    HttpSession session = request.getSession();

		    Set<Integer> likedBoards = (Set<Integer>) session.getAttribute("likedBoards");
		    if (likedBoards == null) {
		        likedBoards = new HashSet<>();
		        session.setAttribute("likedBoards", likedBoards);
		    }

		    if (likedBoards.contains(boardNo)) {
		    	PrintWriter script = response.getWriter();

				script.println("<script>");

				script.println("alert('이미 추천을 누른 글입니다.');");

				script.println("history.back();");

				script.println("</script>");

				script.close();
				
		    } else {
		        likedBoards.add(boardNo); 

		        int likeCount = Integer.parseInt(request.getParameter("likeCount"));
		        System.out.println("boardNo = " + boardNo);
		        System.out.println("likeCount = " + likeCount);

		        int result = boardService.updateLike(boardNo, likeCount);

		        response.sendRedirect(request.getContextPath() + "/board/boardDetail?no=" + boardNo);
		    }
		}

	}


