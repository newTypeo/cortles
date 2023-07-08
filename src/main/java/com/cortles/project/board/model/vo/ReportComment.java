package com.cortles.project.board.model.vo;

import java.sql.Date;

public class ReportComment {
	private int commentNo;
	private int reportCount;
	private String reportId;
	private String reportContent;
	private Date reportDate;
	
	public ReportComment() {}

	public ReportComment(int commentNo, int reportCount, String reportId, String reportContent, Date reportDate) {
		super();
		this.commentNo = commentNo;
		this.reportCount = reportCount;
		this.reportId = reportId;
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

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
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
		return "ReportComment [commentNo=" + commentNo + ", reportCount=" + reportCount + ", reportId=" + reportId
				+ ", reportContent=" + reportContent + ", reportDate=" + reportDate + "]";
	}
	
}
