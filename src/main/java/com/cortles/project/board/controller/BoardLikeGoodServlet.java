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
		
		String boardNoString = request.getParameter("boardNo");
	    int boardNo = 0; // 기본값 설정

	    if (boardNoString != null) {
	        boardNo = Integer.parseInt(boardNoString);
	    }

	    // Session 생성
	    HttpSession session = request.getSession();

	    Set<Integer> likedBoards = (Set<Integer>) session.getAttribute("likedBoards");
	    if (likedBoards == null) {
	        likedBoards = new HashSet<>();
	        session.setAttribute("likedBoards", likedBoards);
	    }

	    // 추천 했을 시
	    if (likedBoards.contains(boardNo)) {
	    	response.setContentType("text/html;charset=UTF-8");
	    	PrintWriter out = response.getWriter();
	    	out.println("<script>alert('이미 추천하셨습니다.'); location.href='" + request.getContextPath() + "/board/boardDetail?no=" + boardNo + "';</script>");

	    } else {
	        // 추천 안 했을 시
	        likedBoards.add(boardNo); // 추천한 게시글 번호를 세션에 추가

	        int likeCount = Integer.parseInt(request.getParameter("likeCount"));
	        System.out.println("boardNo = " + boardNo);
	        System.out.println("likeCount = " + likeCount);

	        // 2. 업무로직
	        int result = boardService.updateLike(boardNo, likeCount);
	    }

	    // 3. 응답처리
	    response.sendRedirect(request.getContextPath() + "/board/boardDetail?no=" + boardNo);
	}
}
