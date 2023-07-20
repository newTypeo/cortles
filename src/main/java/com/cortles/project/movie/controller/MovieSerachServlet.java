package com.cortles.project.movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cortles.project.movie.model.service.MovieService;
import com.cortles.project.movie.model.vo.Movie;
import com.google.gson.Gson;


@WebServlet("/movie/json/searchMovies")
public class MovieSerachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MovieService movieService = new MovieService();
	
	
	/**
	 * 비동기식 영화 검색 - 종환, 현우
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 타자를 입력할 때마다 oninput 이벤트로 영화 조회
		String inputText = request.getParameter("input_text"); // 입력값
		
		List<Movie> movies = movieService.searchMovie(inputText); // like %입력값%
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(movies, response.getWriter());
	}

}
