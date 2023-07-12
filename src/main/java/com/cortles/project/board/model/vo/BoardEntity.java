package com.cortles.project.board.model.vo;

import java.sql.Date;

public class BoardEntity {
	private String boardNo;
	private String writerId;
	private String title;
	private String content;
	private int likeCount;
	private int readCount;
	private Date regDate;
	
	public BoardEntity() {}

	public BoardEntity(String boardNo, String writerId, String title, String content, int likeCount, int readCount,
			Date regDate) {
		super();
		this.boardNo = boardNo;
		this.writerId = writerId;
		this.title = title;
		this.content = content;
		this.likeCount = likeCount;
		this.readCount = readCount;
		this.regDate = regDate;
	}

	public String getBoardNo() {return boardNo;}
	public void setBoardNo(String boardNo) {this.boardNo = boardNo;}

	public String getWriterId() {return writerId;}
	public void setWriterId(String writerId) {this.writerId = writerId;}

	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}

	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}

	public int getLikeCount() {return likeCount;}
	public void setLikeCount(int likeCount) {this.likeCount = likeCount;}

	public int getReadCount() {return readCount;}
	public void setReadCount(int readCount) {this.readCount = readCount;}

	public Date getRegDate() {return regDate;}
	public void setRegDate(Date regDate) {this.regDate = regDate;}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", writerId=" + writerId + ", title=" + title + ", content=" + content
				+ ", likeCount=" + likeCount + ", readCount=" + readCount + ", regDate=" + regDate + "]";
	}

}
