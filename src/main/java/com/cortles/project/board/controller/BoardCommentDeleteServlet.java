package com.cortles.project.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.board.model.service.BoardService;

/**
 * Servlet implementation class BoardCommentDeleteServlet
 */
@WebServlet("/board/boardCommentDelete")
public class BoardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터값 가져오기
				int boardNo = Integer.parseInt(request.getParameter("boardNo") 
						!= null ? request.getParameter("boardNo") : "0");
				int commentNo = Integer.parseInt(request.getParameter("no"));
				//System.out.println("boardNo="+boardNo+", no=" + commentNo);
				
				// 관리자 신고 댓글 삭제 
				String delOnReportComment = request.getParameter("delOnReportComment");
				System.out.println("delOnReportComment"+delOnReportComment);
				
				//2. 비지니스로직 호출
				int result = boardService.deleteBoardComment(commentNo);
				//3. 리다이렉트
				
				if(boardNo != 0 && result == 1) { // 회원 댓글 삭제 시 
					request.getSession().setAttribute("msg", "댓글 삭제 성공!");
					response.sendRedirect(request.getContextPath() + "/board/boardDetail?no=" + boardNo);
				}
				else { // 관리자 신고 댓글 삭제 시
					response.sendRedirect(request.getContextPath()+"/report");  
				}
	}

}
