package com.cortles.project.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.board.model.service.BoardService;
import com.cortles.project.board.model.vo.Attachment;
import com.cortles.project.board.model.vo.Board;
import com.cortles.project.board.model.vo.BoardComment;
import com.cortles.project.common.util.CortlesUtils;

/**
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/board/boardDetail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자입력값 처리 ?no=12
		int boardNo = Integer.parseInt(request.getParameter("no"));
		
		// 2. 업무로직
		// 게시글 읽음 여부 검사
		Cookie[] cookies = request.getCookies();
		boolean hasRead = false; // 기본값 안읽음
		String boardCookieVal = "";
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();
				if("boardCookie".equals(name)) {
					boardCookieVal = value; // 기존값 대입
					if(value.contains("[" + boardNo + "]")) {
						hasRead = true;
					}
				}
			}
		}
		
		if(!hasRead) {
			int result = boardService.updateReadCount(boardNo);
			
			// 쿠키생성
			Cookie cookie = new Cookie("boardCookie", boardCookieVal + "[" + boardNo + "]");
			cookie.setPath(request.getContextPath() + "/board/boardDetail");
			cookie.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie(cookie); // Set-Cookie : boardCookie=[10][20]
		}
		Board board = boardService.findById(boardNo); // Board, List<Attachment>
//		List<BoardComment> boardComments = boardService.findBoardCommentByBoardNo(boardNo);
//		System.out.println("board = " + board);
//		System.out.println("boardComments = " + boardComments);
		List<BoardComment> boardComments = boardService.findBoardCommentByBoardNo(boardNo);
		
		Attachment attachment = boardService.findAttachmentByBoardNo(boardNo);
		int boardCommentCnt = boardService.totalBoardCommentCnt(boardNo);
		
		// secure coding처리
		String unsecureTitle = board.getTitle();
		String secureTitle = CortlesUtils.escapeHtml(unsecureTitle);
		board.setTitle(secureTitle);
		
		// 3. 응답처리 jsp
		request.setAttribute("board", board);
		request.setAttribute("boardComments", boardComments);
		request.setAttribute("attachment", attachment);
		request.setAttribute("boardCommentCnt", boardCommentCnt);
		
		request.getRequestDispatcher("/WEB-INF/views/board/boardDetail.jsp")
			.forward(request, response);
	}

}
