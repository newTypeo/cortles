package com.cortles.project.member.model.vo;

import java.sql.Date;

public class Member {
	private String memberId;
	private String favoriteGenreId;
	private String favoriteMovieCode;
	private String memberPw;
	private String memberName;
	private String email;
	private String phone;
	private Gender gender;
	private MemberRole memberRole;
	private Date birthday ;
	private Date enrollDate;
	
	public Member() {}
	
	public Member(String memberId, String favoriteGenreId, String favoriteMovieCode, String memberPw, String memberName,
			String email, String phone, Gender gender, MemberRole memberRole, Date birthday, Date enrollDate) {
		super();
		this.memberId = memberId;
		this.favoriteGenreId = favoriteGenreId;
		this.favoriteMovieCode = favoriteMovieCode;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.memberRole = memberRole;
		this.birthday = birthday;
		this.enrollDate = enrollDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getFavoriteGenreId() {
		return favoriteGenreId;
	}

	public void setFavoriteGenreId(String favoriteGenreId) {
		this.favoriteGenreId = favoriteGenreId;
	}

	public String getFavoriteMovieCode() {
		return favoriteMovieCode;
	}

	public void setFavoriteMovieCode(String favoriteMovieCode) {
		this.favoriteMovieCode = favoriteMovieCode;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public MemberRole getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", favoriteGenreId=" + favoriteGenreId + ", favoriteMovieCode="
				+ favoriteMovieCode + ", memberPw=" + memberPw + ", memberName=" + memberName + ", email=" + email
				+ ", phone=" + phone + ", gender=" + gender + ", memberRole=" + memberRole + ", birthday=" + birthday
				+ ", enrollDate=" + enrollDate + "]";
	}

	
}
