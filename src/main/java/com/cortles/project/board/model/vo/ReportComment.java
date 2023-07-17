package com.cortles.project.board.model.vo;

import java.sql.Date;

public class ReportComment {
	private int commentNo;
	private int reportCount;
	private String reporterId;
	private String reportedId;
	private String reportContent;
	private Date reportDate;
	
	public ReportComment() {
		
	}

	public ReportComment(int commentNo, int reportCount, String reporterId, String reportedId, String reportContent,
			Date reportDate) {
		super();
		this.commentNo = commentNo;
		this.reportCount = reportCount;
		this.reporterId = reporterId;
		this.reportedId = reportedId;
		this.reportContent = reportContent;
		this.reportDate = reportDate;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
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

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	@Override
	public String toString() {
		return "ReportComment [commentNo=" + commentNo + ", reportCount=" + reportCount + ", reporterId=" + reporterId
				+ ", reportedId=" + reportedId + ", reportContent=" + reportContent + ", reportDate=" + reportDate
				+ "]";
	}
	
	
	
}
