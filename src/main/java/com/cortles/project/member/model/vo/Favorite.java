package com.cortles.project.member.model.vo;

public class Favorite {
	
	private String movieCode;
	private String memberId;
	
	public Favorite() {
		// TODO Auto-generated constructor stub
	}

	public Favorite(String movieCode, String memberId) {
		super();
		this.movieCode = movieCode;
		this.memberId = memberId;
	}

	public String getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Favorite [movieCode=" + movieCode + ", memberId=" + memberId + "]";
	}
	
}
