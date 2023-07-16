package com.cortles.project.member.model.vo;

import java.sql.Date;

public class QuitMember{
	private String memberId;
	private String memberPassword;
	private String name;
	private String email;
	private String phone;
	private Gender gender;
	private MemberRole memberRole;
	private Date birthday;
	private Date enrollDate;
	private Date quitDate;
	
	public QuitMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuitMember(String memberId, String memberPassword, String name, String email, String phone, Gender gender,
			MemberRole memberRole, Date birthday, Date enrollDate, Date quitDate) {
		super();
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.memberRole = memberRole;
		this.birthday = birthday;
		this.enrollDate = enrollDate;
		this.quitDate = quitDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}

	@Override
	public String toString() {
		return "QuitMember [memberId=" + memberId + ", memberPassword=" + memberPassword + ", name=" + name + ", email="
				+ email + ", phone=" + phone + ", gender=" + gender + ", memberRole=" + memberRole + ", birthday="
				+ birthday + ", enrollDate=" + enrollDate + ", quitDate=" + quitDate + "]";
	}
	
	
	
}
