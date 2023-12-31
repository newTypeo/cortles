package com.cortles.project.board.controller;

import java.io.IOException;

//github.com/newTypeo/Cortles.git

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.board.model.service.BoardService;
import com.cortles.project.board.model.vo.BoardComment;
import com.cortles.project.board.model.vo.ReportComment;
import com.cortles.project.member.model.service.MemberService;

/**
 * 댓글신고 -장준-
 */
@WebServlet("/board/boardCommentReport")
public class BoardCommentReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BoardService boardService = new BoardService();
	private final MemberService memberService = new MemberService();


	/**
	 * 댓글신고 페이지 요청 -장준-
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardComment reportComment = boardService.boardCommentfindById(no);
		
		request.setAttribute("reportComment", reportComment);
		
		request.getRequestDispatcher("/WEB-INF/views/board/boardCommentReport.jsp").forward(request, response);
	}
	

	/**
	 * 댓글신고 -장준-
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String reporterId = request.getParameter("reporterId");
		String reportedId = request.getParameter("reportedId");
		String reportType = request.getParameter("reportType");
		String reportContent = request.getParameter("reportContent");
		 
		 
		ReportComment reportComment = new ReportComment(0,commentNo, boardNo, reporterId, reportedId, null, reportType, reportContent, 0, null);
		 
		 /*
		  * 신고 횟수 조회 - 주혜 
		  */
		 int memberCountReport = boardService.memberCountReport(reportedId);
		 
		 /**
		  * 신고 댓글 db에 추가 - 장준
		  * 신고 수 - 장준
		  */
		 int result = boardService.insertReportBoardComment(reportComment);
		 int reportCnt = boardService.countReport(commentNo);
		 result = boardService.updateCountReport(commentNo, reportCnt);

	
		 /*
		  * 자동 탈퇴 - 주혜 
		  */
		 if(memberCountReport >= 3) {
			 System.out.println("reportedId="+reportedId);
			 int reportMemberDelete = memberService.deleteMemberById(reportedId);
			 System.out.println("reportMemberDelete = "+reportMemberDelete);
		 };
		 
		 
		 
		 //response.sendRedirect(request.getContextPath());

		 
		response.sendRedirect(request.getContextPath()+"/board/boardDetail?no=" + boardNo);

	}

}
