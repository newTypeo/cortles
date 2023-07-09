package com.cortles.project.movie.model.vo;

public class Genre {
	private String genreId;
	private String genreName;
	
	public Genre() {}

	public Genre(String genreId, String genreName) {
		super();
		this.genreId = genreId;
		this.genreName = genreName;
	}

	public String getGenreId() {
		return genreId;
	}

	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	@Override
	public String toString() {
		return "Genre [genreId=" + genreId + ", genreName=" + genreName + "]";
	}
	
}
