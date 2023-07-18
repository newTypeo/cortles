package com.cortles.project.movie.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cortles.project.member.model.dao.MemberDao;
import com.cortles.project.movie.model.exception.MovieException;
import com.cortles.project.movie.model.vo.Movie;
import com.cortles.project.movie.model.vo.MovieComment;

import oracle.jdbc.proxy.annotation.Pre;

public class MovieDao {
	
private Properties prop = new Properties();
	
	public MovieDao() {
		String filename = 
			MemberDao.class.getResource("/sql/movie/movie-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Movie> findAllMovies(Connection conn) {
		List<Movie> movies = new ArrayList<>();
		String sql = prop.getProperty("findAllMovies");
		
		try(
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();
		){
			// System.out.println("movieDao test");
			while(rset.next())
				movies.add(handleMovieResultSet(rset));
			
		} catch (SQLException e) {
			throw new MovieException(e);
		}
		
		return movies;
	}

	private Movie handleMovieResultSet(ResultSet rset) throws SQLException {
		Movie movie = new Movie();
		movie.setMovieCode(rset.getString("movie_code"));
		movie.setTitle(rset.getString("title"));
		movie.setTitleEng(rset.getString("title_eng"));
		movie.setMovieGrade(rset.getInt("movie_grade"));
		movie.setGenre(rset.getString("genre"));
		movie.setStory(rset.getString("story"));
		movie.setOpenDate(rset.getDate("open_date"));
		movie.setRuntime(rset.getString("runtime"));
		movie.setPosterUrl(rset.getString("poster_url"));
		movie.setDirector(rset.getString("director"));
		movie.setActors(rset.getString("actors"));
		movie.setVod(rset.getString("vod"));
		return movie;
	}

	public Movie findOneMovies(Connection conn, String movie_code) {
		Movie movie = null;
		String sql = prop.getProperty("findOneMovies");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, movie_code);
			
			try(ResultSet rset = pstmt.executeQuery()){
				if(rset.next())
					movie = handleMovieResultSet(rset);
			}
		} catch (SQLException e) {
			throw new MovieException(e);
		}
		return movie;
	}

	public List<Movie> searchMovie(Connection conn, String inputText) {
		List<Movie> movies = new ArrayList<>();
		String sql = prop.getProperty("searchMovie");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, "%" + inputText + "%");
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					Movie movie = handleMovieResultSet(rset);				
					movies.add(movie);
				}
			}
		} catch (Exception e) {
			throw new MovieException(e);
		}
		
		 
		return movies;
	}

	/**
	 * 회원이 한줄평을 이미 등록했는지 확인용 - 종환
	 * @param movieCode 
	 */
	public int findMovieCommentById(Connection conn, String memberId, String movieCode) {
		int commentIsExist = 0;
		String sql = prop.getProperty("findMovieCommentById");
		// select count(*) from movie_comment where writer_id = ?
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberId);
			pstmt.setString(2, movieCode);
			
			try(ResultSet rset = pstmt.executeQuery()){
				if(rset.next())
					commentIsExist = rset.getInt(1);
			}
				
		} catch (SQLException e) {
			throw new MovieException(e);
		}
		return commentIsExist;
	}

	public int createMovieComment(Connection conn, String memberId, String movieCode, String commentInput, int starGrade) {
		int result = 0;
		String sql = prop.getProperty("createMovieComment");
		// insert into movie_comment values(seq_movie_comment_no.nextval, ?, ?, ?, default, ?)
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberId);
			pstmt.setString(2, movieCode);
			pstmt.setString(3, commentInput);
			pstmt.setInt(4, starGrade);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MovieException(e);
		}
		return result;
	}

	public List<MovieComment> findMovieCommentsByMovieCode(Connection conn, String movieCode) {
		List<MovieComment> movieComments = new ArrayList<>();
		String sql = prop.getProperty("findMovieCommentsByMovieCode");
		// select * from movie_comment where movie_code = ?
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, movieCode);
			
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next())
					movieComments.add(handleMovieCommentResultSet(rset));
			}
			
		} catch (SQLException e) {
			throw new MovieException(e);
		}
		return movieComments;
	}

	private MovieComment handleMovieCommentResultSet(ResultSet rset) throws SQLException {
		int commentNo = rset.getInt("comment_no");
		String writerId = rset.getString("writer_id");
		String movieCode = rset.getString("movie_code");
		String movieContent = rset.getString("movie_content");
		Date regDate = rset.getDate("reg_date");
		int starGrade = rset.getInt("star_grade");
		
		return new MovieComment(commentNo, writerId, movieCode, movieContent, regDate, starGrade);
	}

}
