package com.cortles.project.board.model.vo;

import java.sql.Date;

public class ReportComment {
	private int reportCommentNo;
	private int commentNo;
	private int boardNo;
	private String reporterId; // 신고자
	private String reportedId; // 범죄자 
	private String reportedName; // 범죄자 이름 
	private String reportType;
	private String reportContent;
	private int reportCount;
	private Date reportDate;
	
	public ReportComment() {}

	public ReportComment(int reportCommentNo, int commentNo, int boardNo, String reporterId, String reportedId,
			String reportedName, String reportType, String reportContent, int reportCount, Date reportDate) {
		super();
		this.reportCommentNo = reportCommentNo;
		this.commentNo = commentNo;
		this.boardNo = boardNo;
		this.reporterId = reporterId;
		this.reportedId = reportedId;
		this.reportedName = reportedName;
		this.reportType = reportType;
		this.reportContent = reportContent;
		this.reportCount = reportCount;
		this.reportDate = reportDate;
	}

	public int getReportCommentNo() {
		return reportCommentNo;
	}

	public void setReportCommentNo(int reportCommentNo) {
		this.reportCommentNo = reportCommentNo;
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

	public String getReportedName() {
		return reportedName;
	}

	public void setReportedName(String reportedName) {
		this.reportedName = reportedName;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	@Override
	public String toString() {
		return "ReportComment [reportCommentNo=" + reportCommentNo + ", commentNo=" + commentNo + ", boardNo=" + boardNo
				+ ", reporterId=" + reporterId + ", reportedId=" + reportedId + ", reportedName=" + reportedName
				+ ", reportType=" + reportType + ", reportContent=" + reportContent + ", reportCount=" + reportCount
				+ ", reportDate=" + reportDate + "]";
	}
	
	
}
