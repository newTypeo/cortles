package com.cortles.project.movie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cortles.project.movie.model.service.MovieService;
import com.cortles.project.movie.model.vo.Movie;

@WebServlet("/movie/findAllMovies")
public class MovieFindAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final MovieService movieService = new MovieService(); 
    
	/**
	 * 영화 객체 모두 가져오기
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Movie> movies = movieService.findAllMovies();
		
		request.getSession().setAttribute("movies", movies);
		request.getRequestDispatcher(request.getContextPath())
			.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
