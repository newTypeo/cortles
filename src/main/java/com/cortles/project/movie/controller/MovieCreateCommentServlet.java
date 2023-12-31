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
import com.google.gson.Gson;

@WebServlet("/movie/createMovieComment")
public class MovieCreateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final MovieService movieService = new MovieService();   
	 
	/**
	 * 영화 한줄평 등록 - 종환
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginMember");
		
		// 로그인 검사
		if(member == null) {
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson("로그인 후 이용해주세요", response.getWriter());
		}
		else { // 로그인 상태인 경우
			int starGrade = Integer.valueOf(request.getParameter("starGrade"));
			String movieCode = request.getParameter("movieCode");
			String commentInput= request.getParameter("movieContent");
			
			// 이미 한줄평을 작성했는지 검사용
			String memberId = member.getMemberId();
			int commentIsExist = movieService.findMovieCommentById(memberId, movieCode);
			
			int result = 0;
			String duplitedMsg = "";
			
			// 해당영화에 대해 한줄평 첫 작성일 시 한줄평 등록처리
			if(commentIsExist == 0) {
				result = movieService.createMovieComment(memberId, movieCode, commentInput, starGrade);
			} else { // 이미 한줄평을 등록했을 시
				duplitedMsg = "이미 한줄평을 등록하셨습니다.";
			}
			
			// 응답
			response.setContentType("application/json; charset=utf-8");
			new Gson().toJson(duplitedMsg, response.getWriter());
		}
	}
}
