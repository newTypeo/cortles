package com.cortles.project.movie.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.movie.model.service.MovieService;
import com.cortles.project.movie.model.vo.MovieComment;
import com.google.gson.Gson;

@WebServlet("/movie/findMovieComments")
public class MovieFindMovieCommentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final MovieService movieService = new MovieService();
	 
	/**
	 * 영화별로 한줄평 모두 조회 (모달창) - 종환
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieCode = request.getParameter("movieCode");
		
		List<MovieComment> movieComments = movieService.findMovieCommentsByMovieCode(movieCode);
		
		double avgMovieGrade = movieService.findMovieAvgGrade(movieCode);

		// 영화 avg(별점) 소숫점 첫번 째에서 버림
		avgMovieGrade = Math.floor(avgMovieGrade * 10) / 10;
		
		// 응답
		response.setContentType("application/json; charset=utf-8");
		Map<String, Object> map = new HashMap<>();
		map.put("movieComments", movieComments); // 영화 한줄평 전체 
		map.put("avgMovieGrade", avgMovieGrade); // 영화 평점
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(map, response.getWriter());
	}


}
