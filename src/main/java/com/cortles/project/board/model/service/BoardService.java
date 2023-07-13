package com.cortles.project.board.model.service;

import static com.cortles.project.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.cortles.project.board.model.dao.BoardDao;
import com.cortles.project.board.model.vo.Attachment;
import com.cortles.project.board.model.vo.Board;
import com.cortles.project.board.model.vo.BoardEntity;

public class BoardService {
	private final BoardDao boardDao = new BoardDao();

	public List<BoardEntity> findAll(int start, int end) {
		Connection conn = getConnection();
		List<BoardEntity> boards = boardDao.findAll(conn, start, end);
		close(conn);
		return boards;
	}

	public int getTotalContent() {
		Connection conn = getConnection();
		int totalContent = boardDao.getTotalContent(conn);
		close(conn);
		
		return totalContent;
	}

	public Board findById(int boardNo) {
		Connection conn = getConnection();
		Board board = boardDao.findById(conn, boardNo);
		List<Attachment> attachments = boardDao.findAttachmentByBoardNo(conn, boardNo);
		board.setAttachments(attachments);
		close(conn);
		return board;
	}

}
