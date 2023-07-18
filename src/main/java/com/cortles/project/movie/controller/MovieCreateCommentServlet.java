package com.cortles.project.movie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.cortles.project.member.model.vo.Member;
import com.cortles.project.movie.model.service.MovieService;
import com.cortles.project.movie.model.vo.MovieComment;

@WebServlet("/movie/createMovieComment")
public class MovieCreateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final MovieService movieService = new MovieService();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * 영화 한줄평 등록 - 종환
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginMember");
		if(member == null) {
			session.setAttribute("msg", "로그인 후 이용해주세요.");
			response.sendRedirect(request.getContextPath());
			return;
		}
		
		int starGrade = Integer.valueOf(request.getParameter("rating"));
		String movieCode = request.getParameter("modal-movie-code");
		String commentInput= request.getParameter("commentInput");
		
		// 이미 한줄평을 작성했는지 먼저 확인
		String memberId = member.getMemberId();
		int commentIsExist = movieService.findMovieCommentById(memberId, movieCode);
		System.out.println("commentIsExist = " + commentIsExist);
		
		int result = 0;
		
		// 첫 작성일 시 한줄평 등록
		if(commentIsExist == 0) {
			result = movieService.createMovieComment(memberId, movieCode, commentInput, starGrade);
			
		}
		else { // 이미 한줄평을 등록했을 시
			session.setAttribute("msg", "이미 한줄평을 등록하셨습니다.");
		}
		
		// 응답
		String referer = request.getHeader("Referer");
		response.sendRedirect(referer);
	}

}
