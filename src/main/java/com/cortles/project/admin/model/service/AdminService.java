package com.cortles.project.admin.model.service;
import static com.cortles.project.common.JdbcTemplate.close;
import static com.cortles.project.common.JdbcTemplate.commit;
import static com.cortles.project.common.JdbcTemplate.getConnection;
import static com.cortles.project.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.cortles.project.admin.model.dao.AdminDao;
import com.cortles.project.movie.model.vo.Movie;

public class AdminService {
	private final AdminDao adminDao = new AdminDao();

	public int addAllMovies(List<Movie> movies) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = adminDao.addAllMovies(conn, movies);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	/*
	 * 신고 댓글 삭제 - 주혜 
	 */
	public int deleteReportComment(int commentNo) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = adminDao.deleteReportComment(conn,commentNo);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}finally {
			close(conn);
			
		}
		return result;
	}

	public int deleteReportCommentOnReportList(int reportCommentNo) {
		Connection conn = getConnection();
		int result2 = 0;
		try {
			result2 = adminDao.deleteReportCommentOnReportList(conn, reportCommentNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return result2;
	}
	
	
	
	
}
