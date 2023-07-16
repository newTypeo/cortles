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

@WebServlet("/movie/json/findOneMovies")
public class MovieFindOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MovieService movieService = new MovieService(); 
       
	/**
	 * 모달용 영화 하나 가져오기 - 종환
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movie_code = request.getParameter("movie_code");
		System.out.println("모달용 movie_code (servlet) = " + movie_code);
		
		Movie movie = movieService.findOneMovies(movie_code);
		System.out.println(movie);
		response.setContentType("application/json; charset=utf-8");
				
		new Gson().toJson(movie, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
