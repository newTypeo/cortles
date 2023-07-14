package com.cortles.project.movie.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cortles.project.member.model.dao.MemberDao;
import com.cortles.project.movie.model.exception.MovieException;
import com.cortles.project.movie.model.vo.Movie;

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

}
