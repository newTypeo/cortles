package com.cortles.project.board.model.vo;

import java.sql.Date;

public class BoardComent {
	private int commentNo;
	private int boardNo;
	private String writerId;
	private String content;
	private Date regDate;
	
	public BoardComent() {}

	public BoardComent(int commentNo, int boardNo, String writerId, String content, Date regDate) {
		super();
		this.commentNo = commentNo;
		this.boardNo = boardNo;
		this.writerId = writerId;
		this.content = content;
		this.regDate = regDate;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "BoardComent [commentNo=" + commentNo + ", boardNo=" + boardNo + ", writerId=" + writerId + ", content="
				+ content + ", regDate=" + regDate + "]";
	}
}
