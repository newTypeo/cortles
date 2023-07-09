package com.cortles.project.movie.model.vo;

import java.sql.Blob;

public class Poster {
	private String posterId;
	private String movieCode;
	private Blob posterImg;
	
	public Poster() {}

	public Poster(String posterId, String movieCode, Blob posterImg) {
		super();
		this.posterId = posterId;
		this.movieCode = movieCode;
		this.posterImg = posterImg;
	}

	public String getPosterId() {
		return posterId;
	}

	public void setPosterId(String posterId) {
		this.posterId = posterId;
	}

	public String getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}

	public Blob getPosterImg() {
		return posterImg;
	}

	public void setPosterImg(Blob posterImg) {
		this.posterImg = posterImg;
	}

	@Override
	public String toString() {
		return "Poster [posterId=" + posterId + ", movieCode=" + movieCode + ", posterImg=" + posterImg + "]";
	}
	
}
