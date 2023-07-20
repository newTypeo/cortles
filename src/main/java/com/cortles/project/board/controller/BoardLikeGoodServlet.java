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
			// 인코딩처리
			response.setContentType("text/html; charset=UTF-8");
			// 사용자 처리값
		    String boardNoStr = request.getParameter("boardNo");
		    int boardNo = 0; 
		   // 제대로 받아오면 int로 형변환해주기
		    if (boardNoStr != null) {
		        boardNo = Integer.parseInt(boardNoStr);
		    }
		    // 세션 생성 또는 이미 생성된 세션 가져오기
		    
		    HttpSession session = request.getSession();
		    // 사용자가 추천한 게시물 번호를 저장하는 Set 선언
		    Set<Integer> likedBoards = (Set<Integer>) session.getAttribute("likedBoards");
		    // 세션에 처음으로 추천한 게시물이면 Set 객체 생성하고 세션에 저장
		    if (likedBoards == null) {
		        likedBoards = new HashSet<>();
		        session.setAttribute("likedBoards", likedBoards);
		    }
	    	// 이미 해당 게시물에 추천을 누른 경우, 경고창을 띄우고 이전 페이지로 이동		 
	    	if (likedBoards.contains(boardNo)) {
		    	PrintWriter script = response.getWriter();

				script.println("<script>");

				script.println("alert('이미 추천을 누른 글입니다.');");

				script.println("history.back();");

				script.println("</script>");

				script.close();
				

	    	} else { // 추천을 아직 누르지 않은 경우
		        likedBoards.add(boardNo); // 해당 게시물 번호를 Set에 추가하여 추천 누른 상태로 처리

		        // 현재 추천 수를 받아와서 업데이트할 준비
		        int likeCount = Integer.parseInt(request.getParameter("likeCount"));
		        System.out.println("boardNo = " + boardNo);
		        System.out.println("likeCount = " + likeCount);

		        // 게시물 추천 수 업데이트를 위해 서비스 클래스의 메서드 호출
		        int result = boardService.updateLike(boardNo, likeCount);

		        // 업데이트가 완료되면 해당 게시물 상세 페이지로 리다이렉트
		        response.sendRedirect(request.getContextPath() + "/board/boardDetail?no=" + boardNo);
		    }
		}

	}


