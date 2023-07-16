package com.cortles.project.movie.model.service;
import static com.cortles.project.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.cortles.project.movie.model.dao.MovieDao;
import com.cortles.project.movie.model.vo.Movie;

public class MovieService {
	private final MovieDao movieDao = new MovieDao();

	/**
	 * 메이페이지에 뿌릴 영화 다 가져오기 - 종환
	 */
	public List<Movie> findAllMovies() {
		List<Movie> movies = null;
		Connection conn = getConnection();
		movies = movieDao.findAllMovies(conn);
		close(conn);
		return movies;
	}

	public Movie findOneMovies(String movie_code) {
		Connection conn = getConnection();
		Movie movie = movieDao.findOneMovies(conn, movie_code);
		close(conn);
		return movie;
	}
	
	
	
	
}
