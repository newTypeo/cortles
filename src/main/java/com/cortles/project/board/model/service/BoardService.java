package com.cortles.project.board.model.service;

import static com.cortles.project.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.cortles.project.board.model.dao.BoardDao;
import com.cortles.project.board.model.vo.Attachment;
import com.cortles.project.board.model.vo.Board;
import com.cortles.project.board.model.vo.BoardComment;
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
//		List<Attachment> attachments = boardDao.findAttachmentByBoardNo(conn, boardNo);
//		board.setAttachments(attachments);
		close(conn);
		
		return board;
	}

	public int updateReadCount(int boardNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.updateReadCount(conn, boardNo);
			commit(conn);
		}catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int insertBoard(Board board) {
		int result = 0;
		Connection conn= getConnection();
		try {
			
			// board 테이블 추가
			result = boardDao.insertBoard(conn, board);
			
			// 발급된 board.no를 조회	->  (attachment 테이블 추가)
			int boardNo = boardDao.getLastBoardNo(conn);
			board.setBoardNo(boardNo); // servlet에서 redirect시 사용
			System.out.println("boardNo = " + boardNo);
			
			// attachment 테이블 추가
			List<Attachment> attachments = board.getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
					// insert into web.attachment(seq_attachment_no.nextval, board_no, original_filename, renamed_filename)
					attach.setBoardNo(boardNo); // fk컬럼값 세팅
					result = boardDao.insertAttachment(conn, attach);
				}
			}
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int insertBoardComment(BoardComment boardComment) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.insertBoardComment(conn, boardComment);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<BoardComment> findBoardCommentByBoardNo(int boardNo) {
        Connection conn = getConnection();
        List<BoardComment> boardComments = boardDao.findBoardCommentByBoardNo(conn, boardNo);
        close(conn);
        return boardComments;
    }

	/*
	 * 게시글 삭제-주혜
	 */
	public int boardDelete(int no) {
		int result = 0;
		Connection conn = getConnection();
		try {
			Attachment attachment = boardDao.findAttachmentByBoardNo(conn, no);
			if(attachment != null) {
				result = boardDao.deleteAttachmentByBoardNo(conn, no);
			}
			result = boardDao.boardDelete(conn, no);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		return result;
	}
	
	
	public int deleteBoardComment(int commentNo) {
		int result = 0 ;
		Connection conn = getConnection();
		try{
			result = boardDao.deleteBoardComment(conn, commentNo);
			commit(conn);
		}catch (Exception e) {
			rollback(conn);
			throw e;
			
		}finally {
			close(conn);
		}
		
		return result;
	}

	public Attachment findAttachmentByBoardNo(int boardNo) {
		Connection conn = getConnection();
		Attachment attachment = boardDao.findAttachmentByBoardNo(conn, boardNo);
		close(conn);
		return attachment;
	}

}


















