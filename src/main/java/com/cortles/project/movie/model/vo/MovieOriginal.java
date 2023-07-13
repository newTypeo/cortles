package com.cortles.project.movie.model.vo;

import java.sql.Date;

public class MovieOriginal {
	private String movieCode; // 영화코드 (api)
	private String title;	// 제목 (api)
	private int movieGrade; // 영화평점
	private String story;	// 줄거리
	private Date openDate;	// 개봉일 (api)
	private Date runtime;	// 상영시간 (api)
	private String posterId;// 포스터 아이디
	private String director;// 감독 (api)
	private String actorId; // 배우 (api)
	
	public MovieOriginal() {}
	
	public MovieOriginal(String movieCode, String title, int movieGrade, String story, Date openDate, Date runtime,
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
	
	public String getMovieCode() {return movieCode;}
	public void setMovieCode(String movieCode) {this.movieCode = movieCode;}
	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	
	public int getMovieGrade() {return movieGrade;}
	public void setMovieGrade(int movieGrade) {this.movieGrade = movieGrade;}
	
	public String getStory() {return story;}
	public void setStory(String story) {this.story = story;}
	
	public Date getOpenDate() {return openDate;}
	public void setOpenDate(Date openDate) {this.openDate = openDate;}
	
	public Date getRuntime() {return runtime;}
	public void setRuntime(Date runtime) {this.runtime = runtime;}
	
	public String getPosterId() {return posterId;}
	public void setPosterId(String posterId) {this.posterId = posterId;}
	
	public String getDirector() {return director;}
	public void setDirector(String director) {this.director = director;}
	
	public String getActorId() {return actorId;}
	public void setActorId(String actorId) {this.actorId = actorId;}
	
	@Override
	public String toString() {
		return "Movie [movieCode=" + movieCode + ", title=" + title + ", movieGrade=" + movieGrade + ", story=" + story
				+ ", openDate=" + openDate + ", runtime=" + runtime + ", posterId=" + posterId + ", director="
				+ director + ", actorId=" + actorId + "]";
	}
	
}
