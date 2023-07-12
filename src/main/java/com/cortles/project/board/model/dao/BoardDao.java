package com.cortles.project.board.model.dao;

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

import com.cortles.project.board.model.exception.BoardException;
import com.cortles.project.board.model.vo.Board;
import com.cortles.project.board.model.vo.BoardEntity;

public class BoardDao {
	private Properties prop = new Properties();
	public BoardDao() {
		String filename = 
				BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
			try {
				prop.load(new FileReader(filename));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	

	public int getTotalContent(Connection conn) {
		int totalContent = 0;
		String sql = prop.getProperty("getTotalContent"); // select count(*) from board
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next())
					totalContent = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return totalContent;
	}


	public List<BoardEntity> findAll(Connection conn, int start, int end) {
		List<BoardEntity> boards = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					int boardNo = rset.getInt("board_no");
					String writerId = rset.getString("writer_id");
					String title = rset.getString("title");
					String content = rset.getString("content");
					int likeCount = rset.getInt("like_count");
					int readCount = rset.getInt("read_count");
					Date regDate = rset.getDate("reg_date");
					BoardEntity board = new BoardEntity(boardNo, writerId, title, content, likeCount, readCount, regDate);
					
					boards.add(board);
				}
			}
			
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return boards;
	}


	public Board findById(Connection conn, int boardNo) {
		Board board = null;
		String sql = prop.getProperty("findById");
		// select * from board where board_no = ?
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, boardNo);
			
			try(ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					board.setBoardNo(boardNo);
					board.setWriterId(rset.getString("writer_id"));
					board.setTitle(rset.getString("title"));
					board.setContent(rset.getString("content"));
					board.setLikeCount(rset.getInt("like_count"));
					board.setReadCount(rset.getInt("read_count"));
					board.setRegDate(rset.getDate("reg_date"));
				}
			}
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		return board;
	}



}













