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

	public List<Movie> searchMovie(String inputText) {
		Connection conn = getConnection();
		List<Movie> movies = movieDao.searchMovie(conn, inputText);
		close(conn);
		
		return movies;
	}

	public int findMovieCommentById(String memberId, String movieCode) {
		Connection conn = getConnection();
		int commentIsExist = movieDao.findMovieCommentById(conn, memberId, movieCode);
		close(conn);
		return commentIsExist;
	}

	public int createMovieComment(String memberId, String movieCode, String commentInput, int starGrade) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = movieDao.createMovieComment(conn, memberId, movieCode, commentInput, starGrade);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}
	
	
	
	
}
