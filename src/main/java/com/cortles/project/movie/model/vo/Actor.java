package com.cortles.project.movie.model.vo;

public class Actor {
	private String actorId;
	private String actorName;
	private String moiveCode;
	
	public Actor() {}

	public Actor(String actorId, String actorName, String moiveCode) {
		super();
		this.actorId = actorId;
		this.actorName = actorName;
		this.moiveCode = moiveCode;
	}

	public String getActorId() {return actorId;}
	public void setActorId(String actorId) {this.actorId = actorId;}

	public String getActorName() {return actorName;}
	public void setActorName(String actorName) {this.actorName = actorName;}

	public String getMoiveCode() {return moiveCode;}
	public void setMoiveCode(String moiveCode) {this.moiveCode = moiveCode;}

	@Override
	public String toString() {
		return "Actor [actorId=" + actorId + ", actorName=" + actorName + ", moiveCode=" + moiveCode + "]";
	}
	
	
}
