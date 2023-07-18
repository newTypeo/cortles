package com.cortles.project.movie.controller;

import java.io.IOException;
import java.util.List;

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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieCode = request.getParameter("movieCode");
		
		List<MovieComment> movieComments = movieService.findMovieCommentsByMovieCode(movieCode);
		
		// 응답
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(movieComments, response.getWriter());
	}


}
