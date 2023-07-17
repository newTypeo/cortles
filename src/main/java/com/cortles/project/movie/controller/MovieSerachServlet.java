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


@WebServlet("/movie/json/serchMovies")
public class MovieSerachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MovieService movieService = new MovieService();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputText = request.getParameter("input_text");

		List<Movie> movies = movieService.searchMovie(inputText);
		
		System.out.println("movies = " + movies);
		
		
	}

}
