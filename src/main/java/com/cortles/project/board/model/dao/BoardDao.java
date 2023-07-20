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
import com.cortles.project.board.model.vo.ReportComment;
import com.cortles.project.member.model.exception.MemberException;
import com.cortles.project.member.model.vo.Member;

public class BoardDao {
	private Properties prop = new Properties();
	public BoardDao() {
		String filename = 
				BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
				//BoardDao.class.getResource("/sql/member/member-query.properties").getPath();
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

	/**
	 * 게시판 목록 전체 조회 - 장준 
	 */
	public List<BoardEntity> findAll(Connection conn, int start, int end) {
		List<BoardEntity> boards = new ArrayList<>();
		
		// select * from (select row_number() over (order by b.board_no desc) rnum, b.*  from board b) where rnum between ? and ?
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
		// insert into attachment(attachment_no, board_no, original_filename, renamed_filename) 
		// values(seq_attachment_no.nextval, ?, ?, ?)
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

	// 게시글 댓글 등록 -장준-
	public int insertBoardComment(Connection conn, BoardComment boardComment) {
		int result = 0;
		// insert into board_comment values(seq_board_comment_no.nextval, ?, ?, ?, sysdate)
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


	/**
	 * 경빈
	 * 좋아요 기능
	 */
	public int updateLike(Connection conn, int boardNo, int likeCount) {
		int result = 0;
		String sql = prop.getProperty("updateLike");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, likeCount);
            pstmt.setInt(2, boardNo);
            
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
        	throw new BoardException(e);
        }
		return result;
	}
		
	/**
	 * 게시글 댓글 삭제 -장준- 
	 */
	public int deleteBoardComment(Connection conn, int commentNo) {
		int result = 0;
		// delete from board_comment where comment_no = ?
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


	public int deleteAttachmentByBoardNo(Connection conn, int no) {
		int result = 0;
		String sql = prop.getProperty("deleteAttachmentByBoardNo");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		return result;
	}


	public int totalBoardCommentCnt(Connection conn, int boardNo) {
		int boardCommentCnt = 0;
		String sql = prop.getProperty("totalBoardCommentCnt");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, boardNo);
			try(ResultSet rset = pstmt.executeQuery()){
				if(rset.next()) {
					
					boardCommentCnt = rset.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		return boardCommentCnt;
	}

	/**
	 * 댓글하나 가져오기 - 장준 
	 */
	public BoardComment boardCommentfindById(Connection conn, int no) {
		BoardComment updateBoardComment = null;
		// select * from board_comment where comment_no = ?
		String sql = prop.getProperty("boardCommentfindById");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, no);
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					int commentNo = rset.getInt("comment_no");
					int boardNo = rset.getInt("board_no");
					String writerId = rset.getString("writer_id");
					String content = rset.getString("content");
					Date regDate = rset.getDate("reg_date");
					updateBoardComment = new BoardComment(commentNo, boardNo, writerId, content, regDate);

					
				}
			}
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		return updateBoardComment;
	}
	/**
	 * 영화 검색 - 현우 
	 */
	public List<Board> searchboard(Connection conn, String keyword) {
		List<Board> boards = new ArrayList<>();
		String sql = prop.getProperty("searchBoard");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, "%" + keyword + "%");
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					int boardNo = rset.getInt("board_no");
	                String writerId = rset.getString("writer_id");
	                String title = rset.getString("title");
	                String content = rset.getString("content");
	                int likeCount = rset.getInt("like_count");
	                int readCount = rset.getInt("read_count");
	                Date regDate = rset.getDate("reg_date");
	                
	                Board board = new Board();
	                board.setBoardNo(boardNo);
	                board.setWriterId(writerId);
	                board.setTitle(title);
	                board.setContent(content);
	                board.setLikeCount(likeCount);
	                board.setReadCount(readCount);
	                board.setRegDate(regDate);
	                
	                boards.add(board);
				}
			}
			
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		return boards;
	}

	/**
	 * 댓글 수정 - 장준 
	 */
	public int updateBoardComment(Connection conn, int no, String content) {
		int result = 0;
		// update board_comment set content = ? where comment_no = ?
		String sql = prop.getProperty("updateBoardComment");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		return result;
	}

	/**
	 * 신고 댓글 조회 - 주혜 
	 */
	public List<ReportComment> reportCommentFindAll(Connection conn) {
		List<ReportComment> reportComments = new ArrayList<>();
		String sql = prop.getProperty("reportCommentFindAll");
		//String sql2 = prop.getProperty("searchMember");
		//searchMemberId = select * from member where member_id = ?
		try(PreparedStatement pstmt = conn.prepareStatement(sql);  
				
			ResultSet rset = pstmt.executeQuery();
				
		){
			while(rset.next()) {
				ReportComment reportComment = handleReportCommentResultSet(rset);
				reportComment.setReportedName(rset.getString("member_name"));
				reportComments.add(reportComment);
			}
		
			
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return reportComments;
	}

	/**
	 * 신고 댓글 조회 메소드 - 주혜 
	 */
	private ReportComment handleReportCommentResultSet(ResultSet rset) throws SQLException {
		int reportCommentNo = rset.getInt("report_comment_no");
		int commentNo = rset.getInt("comment_no");
		int boardNo = rset.getInt("board_no");
		int reportCount = rset.getInt("report_count");
		String reporterId = rset.getString("reporter_id"); // 신고자
		String reportedId = rset.getString("reported_id"); // 범죄자
		String reportContent = rset.getString("report_content");
		String reportType = rset.getString("report_type");
		Date reportDate = rset.getDate("report_date");
		
		return new ReportComment(reportCommentNo, commentNo, boardNo, reporterId, reportedId, null, reportType, reportContent, reportCount, reportDate);
	}

	/**
	 * 신고댓글 추가 - 장준
	 */
	public int insertReportBoardComment(Connection conn, ReportComment reportComment) {
		int result = 0;
		String sql = prop.getProperty("insertReportBoardComment");
		//insert into report_comment values(seq_report_comment_no.nextval, ?, ?, ?, ?, ?, ?, 0, default)
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, reportComment.getCommentNo());
			pstmt.setInt(2, reportComment.getBoardNo());
			pstmt.setString(3, reportComment.getReporterId());
			pstmt.setString(4, reportComment.getReportedId());
			pstmt.setString(5, reportComment.getReportType());
			pstmt.setString(6, reportComment.getReportContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		
		return result;
		
	}
	public int updateBoard(Connection conn, Board board) {
		int result = 0;
		String sql = prop.getProperty("updateBoard");
		// update board set title = ?, content = ?  where board_no = ?
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getBoardNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return result;
	}


	public int deleteAttachment(Connection conn, int attachNo) {
		int result = 0;
		String sql = prop.getProperty("deleteAttachment");
		// delete from attachment where attachment_no = ?
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, attachNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return result;
	}


	public Attachment findAttachmentById(Connection conn, int attachNo) {
		Attachment attach = new Attachment();
		String sql = prop.getProperty("findAttachmentById");
		// findAttachmentById = select * from attachment where no = ?
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, attachNo);
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					attach.setNo(rset.getInt("attachment_no"));
					attach.setBoardNo(rset.getInt("board_no"));
					attach.setOriginalFilename(rset.getString("original_filename"));
					attach.setRenamedFilename(rset.getString("renamed_filename"));
					attach.setRegDate(rset.getDate("reg_date"));
				}
			}
			
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return attach;
	}

	/**
	 * 신고수 조회 - 장준
	 */
	public int countReport(Connection conn, int commentNo) {
		int reportCnt = 0;
		// select count(*) from report_comment where comment_no = ?
		String sql = prop.getProperty("countReport");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, commentNo);
			try(ResultSet rset = pstmt.executeQuery()){
				if(rset.next()) {
					reportCnt = rset.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		return reportCnt;
	}

	/**
	 * 신고 수 DB에 넣기 - 장준 
	 */
	public int updateCountReport(Connection conn, int commentNo, int reportCnt) {
		int result = 0;
		// update report_comment set report_count = ? where comment_no = ?
		String sql = prop.getProperty("updateCountReport");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, reportCnt);
			pstmt.setInt(2, commentNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		
		
		return result;
	}

	/**
	 * 신고 회수 조회 - 주혜
	 */
	public int memberCountReport(Connection conn, String reportedId) {
		int memberCountReport = 0;
		//System.out.println("dao");
		String sql = prop.getProperty("memberCountReport");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, reportedId);
			
			try(ResultSet rset = pstmt.executeQuery()){
				if(rset.next()) {
					memberCountReport = rset.getInt(1);
					memberCountReport++;
				}
				
			}
		} catch (SQLException e) {
			throw new BoardException(e);
		}
		return memberCountReport;
	}









}













