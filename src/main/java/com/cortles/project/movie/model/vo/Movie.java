package com.cortles.project.movie.model.vo;

import java.sql.Date;

public class Movie {
	// 영화코드   제목     영어제목      장르    줄거리       개봉일       상영시간     포스터       감독	      배우 티저
	private String movieCode; // 영화코드 (api)
	private String title;	// 제목 (api)
	private String titleEng;	// 영어제목 (api)
	private int movieGrade; // 영화평점
	private String genre;	// 장르
	private String story;	// 줄거리
	private Date openDate;	// 개봉일 (api)
	private String runtime;	// 상영시간 (api)
	private String posterUrl;// 포스터 아이디
	private String director;// 감독 (api)
	private String actors; // 배우 (api)
	private String vod; // vod (api) 
	
	public Movie() {}

	public Movie(String movieCode, String title, String titleEng, int movieGrade, String genre, String story,
			Date openDate, String runtime, String posterUrl, String director, String actors, String vod) {
		super();
		this.movieCode = movieCode;
		this.title = title;
		this.titleEng = titleEng;
		this.movieGrade = movieGrade;
		this.genre = genre;
		this.story = story;
		this.openDate = openDate;
		this.runtime = runtime;
		this.posterUrl = posterUrl;
		this.director = director;
		this.actors = actors;
		this.vod = vod;
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

	public String getTitleEng() {
		return titleEng;
	}

	public void setTitleEng(String titleEng) {
		this.titleEng = titleEng;
	}

	public int getMovieGrade() {
		return movieGrade;
	}

	public void setMovieGrade(int movieGrade) {
		this.movieGrade = movieGrade;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getVod() {
		return vod;
	}

	public void setVod(String vod) {
		this.vod = vod;
	}

	@Override
	public String toString() {
		return "Movie [movieCode=" + movieCode + ", title=" + title + ", titleEng=" + titleEng + ", movieGrade="
				+ movieGrade + ", genre=" + genre + ", story=" + story + ", openDate=" + openDate + ", runtime="
				+ runtime + ", posterUrl=" + posterUrl + ", director=" + director + ", actors=" + actors + ", vod="
				+ vod + "]";
	}
	
	
}
