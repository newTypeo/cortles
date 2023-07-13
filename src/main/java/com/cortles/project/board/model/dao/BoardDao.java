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
import com.cortles.project.board.model.vo.Attachment;
import com.cortles.project.board.model.vo.Board;
import com.cortles.project.board.model.vo.BoardComment;
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
		Board board = new Board();
		String sql = prop.getProperty("findById");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, boardNo);
			try(ResultSet rset = pstmt.executeQuery()){
				if(rset.next()) {
					board.setBoardNo(boardNo);
					board.setWriterId(rset.getString("writer_id"));
					board.setTitle(rset.getString("title"));
					board.setContent(rset.getString("content"));
					board.setLikeCount(rset.getInt("like_count"));
					board.setReadCount(rset.getInt("read_count"));
					board.setRegDate(rset.getDate("reg_date"));
					System.out.println("boardDao = " + board);
				}
			}
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		return board;
	}
	


//	public List<Attachment> findAttachmentByBoardNo(Connection conn, int boardNo) {
//		List<Attachment> attachments = new ArrayList<>();
//		String sql = prop.getProperty("findAttachmentByBoardNo");
//		
//		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
//			pstmt.setInt(1, boardNo);
//			try(ResultSet rset = pstmt.executeQuery()){
//				while(rset.next()) {
//					Attachment attach = new Attachment();
//					attach.setNo(rset.getInt("no"));
//					attach.setBoardNo(rset.getInt("board_no"));
//					attach.setOriginalFilename(rset.getString("original_filename"));
//					attach.setRenamedFilename(rset.getString("renamed_filename"));
//					attach.setRegDate(rset.getDate("reg_date"));
//					
//					attachments.add(attach);
//				}
//			}
//		} catch (SQLException e) {
//			throw new BoardException(e);
//		}
//		
//		
//		return attachments;
//	}


	public int updateReadCount(Connection conn, int boardNo) {
		int result = 0;
		String sql = prop.getProperty("updateReadCount");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return result;
	}


	public List<BoardComment> findBoardCommentByBoardNo(Connection conn, int boardNo) {
		List<BoardComment> boardComments = new ArrayList<>();
		String sql = prop.getProperty("findBoardCommentByBoardNo");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, boardNo);
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					//int boardNo = rset.getInt("board_no");
					String writerId = rset.getString("writer_id");
					String content = rset.getString("content");
					int commentNo = rset.getInt("comment_no");
					Date regDate = rset.getDate("reg_date");
					BoardComment boardComment = new BoardComment(commentNo, boardNo, writerId, content, regDate);
					boardComments.add(boardComment);
				}
			}
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		return boardComments;
	}



}













