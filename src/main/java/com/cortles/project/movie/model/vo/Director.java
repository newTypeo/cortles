package com.cortles.project.movie.model.vo;

public class Director {
	private String directorId;
	private String movieCode;
	private String directorName;
	
	public Director() {}

	public Director(String directorId, String movieCode, String directorName) {
		super();
		this.directorId = directorId;
		this.movieCode = movieCode;
		this.directorName = directorName;
	}

	public String getDirectorId() {
		return directorId;
	}

	public void setDirectorId(String directorId) {
		this.directorId = directorId;
	}

	public String getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	@Override
	public String toString() {
		return "Director [directorId=" + directorId + ", movieCode=" + movieCode + ", directorName=" + directorName
				+ "]";
	}
	
}
