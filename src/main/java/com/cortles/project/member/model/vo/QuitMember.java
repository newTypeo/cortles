package com.cortles.project.member.model.vo;

import java.sql.Date;

public class QuitMember extends Member{
	private Date quitDate;
	
	public QuitMember() {}

	public QuitMember(String memberId, String favoriteGenreId, String favoriteMovieCode, String memberPw,
			String memberName, String email, String phone, Gender gender, MemberRole memberRole, Date birthday,
			Date enrollDate, Date quitDate) {
		super(memberId, favoriteGenreId, favoriteMovieCode, memberPw, memberName, email, phone, gender, memberRole,
				birthday, enrollDate);
		this.quitDate = quitDate;
	}

	public Date getQuitDate() {return quitDate;}
	public void setQuitDate(Date quitDate) {this.quitDate = quitDate;}

	@Override
	public String toString() {
		return "QuitMember [quitDate=" + quitDate + ", toString()=" + super.toString() + "]";
	}
	
}
