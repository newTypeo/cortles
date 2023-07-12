package com.cortles.project.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.util.Properties;

import com.cortles.project.member.model.exception.MemberException;
import com.cortles.project.member.model.vo.Member;

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

	/**
	 * 찜 목록 추가 - 경빈
	 * @param movieCode 
	 */
	public int addMyList(Connection conn, String memberId, String movieCode) {
		int result = 0;
		String sql = prop.getProperty("addMyList");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			try (){
				
			} catch (SQLException e) {
				
			}
		} catch (Exception e) {
			
		}
		return result;
	}
	
	
	
	
	
}
