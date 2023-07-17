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
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputText = request.getParameter("input_text");
		
//		System.out.println("inputText = " + inputText);
		List<Movie> movies = movieService.searchMovie(inputText);
//		System.out.println("movies = " + movies);
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(movies, response.getWriter());
		
	}

}
