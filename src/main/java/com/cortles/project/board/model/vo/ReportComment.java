package com.cortles.project.board.model.vo;

import java.sql.Date;

public class ReportComment {
	private int commentNo;
	private int boardCommentNo;
	private int reportCount;
	private String reporterId;
	private String reportedId;
	private String reportContent;
	private String reportType;
	private Date reportDate;
	
	public ReportComment() {
		
	}

	public ReportComment(int commentNo, int boardCommentNo, int reportCount, String reporterId, String reportedId,
			String reportContent, String reportType, Date reportDate) {
		super();
		this.commentNo = commentNo;
		this.boardCommentNo = boardCommentNo;
		this.reportCount = reportCount;
		this.reporterId = reporterId;
		this.reportedId = reportedId;
		this.reportContent = reportContent;
		this.reportType = reportType;
		this.reportDate = reportDate;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getBoardCommentNo() {
		return boardCommentNo;
	}

	public void setBoardCommentNo(int boardCommentNo) {
		this.boardCommentNo = boardCommentNo;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public String getReporterId() {
		return reporterId;
	}

	public void setReporterId(String reporterId) {
		this.reporterId = reporterId;
	}

	public String getReportedId() {
		return reportedId;
	}

	public void setReportedId(String reportedId) {
		this.reportedId = reportedId;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	@Override
	public String toString() {
		return "ReportComment [commentNo=" + commentNo + ", boardCommentNo=" + boardCommentNo + ", reportCount="
				+ reportCount + ", reporterId=" + reporterId + ", reportedId=" + reportedId + ", reportContent="
				+ reportContent + ", reportType=" + reportType + ", reportDate=" + reportDate + "]";
	}
	
	
	
	
	
}
