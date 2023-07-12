package com.cortles.project.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.Properties;

import com.cortles.project.member.model.exception.MemberException;
import com.cortles.project.member.model.vo.Gender;
import com.cortles.project.member.model.vo.Member;
import com.cortles.project.member.model.vo.MemberRole;

public class MemberDao {

private Properties prop = new Properties();
	
	public MemberDao() {
		String filename = 
			MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
//			System.out.println("prop@dao = " + prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int signup(Connection conn, Member member) {
		int result = 0;
		String sql = prop.getProperty("signup");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getFavoriteGenre());
			pstmt.setString(3, null);
			pstmt.setString(4, member.getMemberPw());
			pstmt.setString(5, member.getMemberName());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getGender().name());
			pstmt.setDate(9, member.getBirthday());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException(e);
		}
		
		return result;
	}

	/*
	 * 회원 조회 - 주혜 
	 */
	public List<Member> findAll() {
		List<Member> members = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		
		
		
		return members;
	}

	public Member findById(Connection conn, String memberId) {
		String sql = prop.getProperty("findById");
		Member member = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberId);
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					
					member = handleMemberResultSet(rset);	
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException();
		}		
		return member;
	}

	private Member handleMemberResultSet(ResultSet rset) throws SQLException {
		
		Member member;
		String _memberId = rset.getString("member_id");
		String favoriteGenreName = rset.getString("favorite_genre_name");
		String favoriteMovieCode = rset.getString("favorite_movie_code");
		String memberPw = rset.getString("member_pw");
		String memberName = rset.getString("member_name");
		String email = rset.getString("email");
		String phone = rset.getString("phone");
		String _gender = rset.getString("gender");
		Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
		String _meberRole = rset.getString("member_role");
		MemberRole memberRole = _memberId != null ? MemberRole.valueOf(_meberRole) : null;
		Date birthday = rset.getDate("birthday");
		Date enrollDate = rset.getDate("enroll_date");
		
		member = new Member(_memberId, favoriteGenreName, favoriteMovieCode, memberPw, memberName, email, phone, gender, memberRole, birthday, enrollDate);
		return member;
	}
	
	
	
	
	
}
