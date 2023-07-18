package com.cortles.project.board.model.service;

import static com.cortles.project.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.cortles.project.board.model.dao.BoardDao;
import com.cortles.project.board.model.vo.Attachment;
import com.cortles.project.board.model.vo.Board;
import com.cortles.project.board.model.vo.BoardComment;
import com.cortles.project.board.model.vo.BoardEntity;
import com.cortles.project.board.model.vo.ReportComment;

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
			// System.out.println("boardNo = " + boardNo);
			
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

	public int updateLike(int boardNo, int likeCount) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.updateLike(conn, boardNo, likeCount);
			commit(conn);
		}catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
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

	public int totalBoardCommentCnt(int boardNo) {
		int boardCommentCnt = 0;
		Connection conn = getConnection();
		boardCommentCnt = boardDao.totalBoardCommentCnt(conn,boardNo);
		commit(conn);
		return boardCommentCnt;
	}

	public BoardComment boardCommentfindById(int no) {
		BoardComment updateBoardComment = null;
		Connection conn = getConnection();
		updateBoardComment = boardDao.boardCommentfindById(conn, no);
		commit(conn);
		return updateBoardComment;
	}

	public List<Board> searchboard(String keyword) {
		Connection conn = getConnection();
		List<Board> boards = new ArrayList<>();
		boards = boardDao.searchboard(conn,keyword);
		close(conn);
		
		return boards;
	}

	public int updateBoardComment(int no, String content) {
		int result = 0;
		Connection conn = getConnection();
		try{
			result = boardDao.updateBoardComment(conn, no, content);
			commit(conn);
		}catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
		return result;
	}

	public int insertReportBoardComment(ReportComment reportComment) {
		int result = 0;
		Connection conn = getConnection();
		
		try {
			
			result = boardDao.insertReportBoardComment(conn, reportComment);
			
			commit(conn);
		}catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
		return result;
	}
	
	/*
	 * 신고댓글 조회 - 주혜 
	 */
	public List<ReportComment> reportCommentFindAll() {
		Connection conn = getConnection();
		List<ReportComment> reportComments = boardDao.reportCommentFindAll(conn);
		
		close(conn);
		return reportComments;
	}
	
	
	/**
	 * 게시글 수정 - 창환 
	 */
	public int updateBoard(Board board) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = boardDao.updateBoard(conn, board);
			
			List<Attachment> attachments = board.getAttachments();
			if(attachments != null && !attachments.isEmpty()) {
				for(Attachment attach : attachments) {
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

	public int deleteAttachment(int attachNo) {
		int result = 0;
		Connection conn = getConnection();
		
		try {
			result = boardDao.deleteAttachment(conn, attachNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public Attachment findAttachmentById(int attachNo) {
		Connection conn = getConnection();
		Attachment attach = boardDao.findAttachmentById(conn, attachNo);
		close(conn);
		return attach;
	}

	public int countReport(int commentNo) {
		Connection conn = getConnection();
		int reportCnt = boardDao.countReport(conn, commentNo);
		close(conn);
		
		return reportCnt;
	}

	public int updateCountReport(int commentNo,int reportCnt) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = boardDao.updateCountReport(conn,commentNo, reportCnt);
			commit(conn);
		}catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

}


