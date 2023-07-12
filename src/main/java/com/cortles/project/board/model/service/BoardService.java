package com.cortles.project.board.model.service;

import static com.cortles.project.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.cortles.project.board.model.dao.BoardDao;
import com.cortles.project.board.model.vo.Board;

public class BoardService {
	private final BoardDao boardDao = new BoardDao();

	public List<Board> findAll(int start, int end) {
		
		return null;
	}

	public int getTotalContent() {
		Connection conn = getConnection();
		int totalContent = boardDao.getTotalContent(conn);
		close(conn);
		
		return totalContent;
	}

}
