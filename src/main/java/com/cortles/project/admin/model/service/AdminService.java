package com.cortles.project.admin.model.service;
import static com.cortles.project.common.JdbcTemplate.*;

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
	
	
	
	
}
