package com.cortles.project.movie.model.vo;

import java.sql.Date;

public class Movie {
	private String movieCode;
	private String title;
	private int movieGrade;
	private String story;
	private Date openDate;
	private Date runtime;
	private String posterId;
	private String director;
	private String actorId;
	
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Movie(String movieCode, String title, int movieGrade, String story, Date openDate, Date runtime,
			String posterId, String director, String actorId) {
		super();
		this.movieCode = movieCode;
		this.title = title;
		this.movieGrade = movieGrade;
		this.story = story;
		this.openDate = openDate;
		this.runtime = runtime;
		this.posterId = posterId;
		this.director = director;
		this.actorId = actorId;
	}
	
	public String getMovieCode() {
		return movieCode;
	}
	
	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getMovieGrade() {
		return movieGrade;
	}
	
	public void setMovieGrade(int movieGrade) {
		this.movieGrade = movieGrade;
	}
	
	public String getStory() {
		return story;
	}
	
	public void setStory(String story) {
		this.story = story;
	}
	
	public Date getOpenDate() {
		return openDate;
	}
	
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	public Date getRuntime() {
		return runtime;
	}
	
	public void setRuntime(Date runtime) {
		this.runtime = runtime;
	}
	
	public String getPosterId() {
		return posterId;
	}
	
	public void setPosterId(String posterId) {
		this.posterId = posterId;
	}
	
	public String getDirector() {
		return director;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getActorId() {
		return actorId;
	}
	
	public void setActorId(String actorId) {
		this.actorId = actorId;
	}
	
	@Override
	public String toString() {
		return "Movie [movieCode=" + movieCode + ", title=" + title + ", movieGrade=" + movieGrade + ", story=" + story
				+ ", openDate=" + openDate + ", runtime=" + runtime + ", posterId=" + posterId + ", director="
				+ director + ", actorId=" + actorId + "]";
	}
	
}
