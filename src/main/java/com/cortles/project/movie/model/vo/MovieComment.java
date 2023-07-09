package com.cortles.project.movie.model.vo;

import java.sql.Date;

public class MovieComment {
	private int commentNo;
	private String writerId;
	private String movieCode;
	private String movieContent;
	private Date regDate;
	private int starGrade;
	
	public MovieComment() {}

	public MovieComment(int commentNo, String writerId, String movieCode, String movieContent, Date regDate,
			int starGrade) {
		super();
		this.commentNo = commentNo;
		this.writerId = writerId;
		this.movieCode = movieCode;
		this.movieContent = movieContent;
		this.regDate = regDate;
		this.starGrade = starGrade;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}

	public String getMovieContent() {
		return movieContent;
	}

	public void setMovieContent(String movieContent) {
		this.movieContent = movieContent;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getStarGrade() {
		return starGrade;
	}

	public void setStarGrade(int starGrade) {
		this.starGrade = starGrade;
	}

	@Override
	public String toString() {
		return "MovieComment [commentNo=" + commentNo + ", writerId=" + writerId + ", movieCode=" + movieCode
				+ ", movieContent=" + movieContent + ", regDate=" + regDate + ", starGrade=" + starGrade + "]";
	}
}
