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
import com.cortles.project.member.model.vo.Member;

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


	public int insertBoard(Connection conn, Board board) {
		int result = 0;
		String sql = prop.getProperty("insertBoard");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriterId());
			pstmt.setString(3, board.getContent());
			
			result = pstmt.executeUpdate(); 
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		return result;
	}


	public int getLastBoardNo(Connection conn) {
		int boardNo = 0;
		String sql = prop.getProperty("getLastBoardNo");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			try(ResultSet rset = pstmt.executeQuery()){
				if(rset.next()) {
					boardNo = rset.getInt(1); // 첫번째 컬럼값
				}
			}
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return boardNo;
	}


	public int insertAttachment(Connection conn, Attachment attach) {
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, attach.getBoardNo());
			pstmt.setString(2, attach.getOriginalFilename());
			pstmt.setString(3, attach.getRenamedFilename());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		return result;
	}


	public int insertBoardComment(Connection conn, BoardComment boardComment) {
		int result = 0;
		String sql = prop.getProperty("insertBoardComment");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, boardComment.getBoardNo());
			pstmt.setString(2, boardComment.getWriterId());
			pstmt.setString(3, boardComment.getContent());
			
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

	/*
	 * 게시글 삭제 - 주혜 
	 */
	public int boardDelete(Connection conn, int no) {
		int result = 0;
		String sql = prop.getProperty("boardDelete");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1,no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return result;
	}


	public int deleteBoardComment(Connection conn, int commentNo) {
		int result = 0;
		String sql = prop.getProperty("deleteBoardComment");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return result;
	}


	public Attachment findAttachmentByBoardNo(Connection conn, int boardNo) {
		Attachment attachment = new Attachment();
		String sql = prop.getProperty("findAttachmentByBoardNo");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, boardNo);
			try (ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					attachment.setNo(rset.getInt("attachment_no"));
					attachment.setBoardNo(boardNo);
					attachment.setOriginalFilename(rset.getString("original_filename"));
					attachment.setRenamedFilename(rset.getString("renamed_filename"));
					attachment.setRegDate(rset.getDate("reg_date"));
					
				}
			}
			
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		
		return attachment;
	}



}













