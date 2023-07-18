package com.cortles.project.admin.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.cortles.project.admin.model.exception.AdminException;
import com.cortles.project.board.model.dao.BoardDao;
import com.cortles.project.movie.model.vo.Movie;

public class AdminDao {
	private Properties prop = new Properties();
	public AdminDao() {
		String filename = 
				BoardDao.class.getResource("/sql/admin/admin-query.properties").getPath();
			try {
				prop.load(new FileReader(filename));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public int addAllMovies(Connection conn, List<Movie> movies) {
		int result = 0;
		String sql = prop.getProperty("addAllMovies");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			for(Movie movie : movies) {
				// 영화코드, 제목, 영어제목, 장르, 줄거리, 개봉일, 상영시간, 포스터, 감독, 배우들, 티저
				pstmt.setString(1, movie.getMovieCode());
				pstmt.setString(2, movie.getTitle());
				pstmt.setString(3, movie.getTitleEng());
				pstmt.setInt(4, 0);
				pstmt.setString(5, movie.getGenre());
				pstmt.setString(6, movie.getStory());
				pstmt.setDate(7, movie.getOpenDate());
				pstmt.setString(8, movie.getRuntime());
				pstmt.setString(9, movie.getPosterUrl());
				pstmt.setString(10, movie.getDirector());
				pstmt.setString(11, movie.getActors());
				pstmt.setString(12, movie.getVod());
				
				result += pstmt.executeUpdate();
				pstmt.clearParameters();
			}
			
		} catch (SQLException e) {
			throw new AdminException(e);
		}
		return result;
	}

	/*
	 * 신고 댓글 삭제 - 주혜 
	 */
	public int deleteReportComment(Connection conn,int commentNo) {
		int result = 0;
		String sql = prop.getProperty("deleteReportComment");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new AdminException(e);
		}
		return result;
	}

}
