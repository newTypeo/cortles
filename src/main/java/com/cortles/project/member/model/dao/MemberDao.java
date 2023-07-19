package com.cortles.project.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cortles.project.member.model.exception.MemberException;
import com.cortles.project.member.model.vo.Favorite;
import com.cortles.project.member.model.vo.Gender;
import com.cortles.project.member.model.vo.Member;
import com.cortles.project.member.model.vo.MemberRole;
import com.cortles.project.member.model.vo.QuitMember;

import oracle.jdbc.proxy.annotation.Pre;

public class MemberDao {

private Properties prop = new Properties();
	
	public MemberDao() {
		String filename = 
			MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원가입 - 현우
	 */
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
	public List<Member> findAll(Connection conn) {
		List<Member> members = new ArrayList<>();
		String sql = prop.getProperty("findAll");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rset = pstmt.executeQuery();
			
			while(rset.next())
				members.add(handleMemberListResultSet(rset));
			
		} catch (SQLException e) {
			throw new MemberException(e);
		}
		return members;
	}

	/**
	 * 찜 목록 추가 - 경빈
	 */
	public int addMyList(Connection conn, String memberId, String movieCode) {
		int result = 0;
		// addMyList = update member set favorite_movie_code = favorite_movie_code || ',' || ? where member_id = ?
		String sql = prop.getProperty("addMyList");
		 try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        // Check if the movie code exists in the movie table
		        String checkSql = "SELECT COUNT(*) FROM movie WHERE movie_code = ?";
		        PreparedStatement checkStatement = conn.prepareStatement(checkSql);
		        checkStatement.setString(1, movieCode);
		        if (!checkStatement.executeQuery().next()) {
		            // Movie code does not exist in the movie table, insert it first
		            String insertSql = "INSERT INTO movie (movie_code) VALUES (?)";
		            PreparedStatement insertStatement = conn.prepareStatement(insertSql);
		            insertStatement.setString(1, movieCode);
		            insertStatement.executeUpdate();
		            System.out.println("Movie added successfully.");
		        }

		        // Insert favorite
		        String favoriteSql = "INSERT INTO favorite (movie_code, member_id) VALUES (?, ?)";
		        PreparedStatement favoriteStatement = conn.prepareStatement(favoriteSql);
		        favoriteStatement.setString(1, movieCode);
		        favoriteStatement.setString(2, memberId);
		        favoriteStatement.executeUpdate();

		        System.out.println("Favorite added successfully.");
	    } catch (SQLException e) {
	        System.err.println("Error adding favorite: " + e.getMessage());
	    }
	    return result;
	}
	
	
	/**
	 * 아이디로 회원 조회 - 현우
	 */
	public Member findById(Connection conn, String memberId) {
		String sql = prop.getProperty("findById");
		Member member = null;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberId);
			try(ResultSet rset = pstmt.executeQuery()) {
				while(rset.next())
					member = handleMemberResultSet(rset);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MemberException();
		}		
		return member;
	}

	
	/**
	 * 찜삭제 - 경빈
	 */
	public int deleteMyList(Connection conn, String memberId, String movieCode) {
		int result = 0;
		String sql = prop.getProperty("deleteMyList");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberId);
			pstmt.setString(2, movieCode);
			
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw new MemberException(e);
		}
		return result;
	}

	
	/*
	 * 권한 수정 - 주혜 
	 */
	public int memberRoleUpdate(Connection conn, String memberId, MemberRole memberRole) {
		int result = 0;
		//System.out.println("result"+result);
		String sql = prop.getProperty("memberRoleUpdate");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberRole.name());
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException(e);
		}
		
		return result;
	}

	
	/**
	 * 회원정보 수정 - 종환
	 */
	public int updateMember(Connection conn, Member member) {
		int result = 0;
		String sql = prop.getProperty("updateMember");
		// update member set member_name = ?, gender = ?, birthday = ?, email = ?, phone = ?, favorite_genre_name = ? where member_id = ?
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getGender().name());
			pstmt.setDate(3, member.getBirthday());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getFavoriteGenre());
			pstmt.setString(7, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new MemberException(e);
		}
		return result;
	}

	
	/*
	 * 회원 검색 - 주혜 
	 */
	public List<Member> searchMember(Connection conn,String keyword) {
		List<Member> members = new ArrayList<>();
		String sql = prop.getProperty("searchMember");
		//System.out.println("keyword:" + keyword);
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, "%" + keyword + "%");
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					Member member = handleMemberListResultSet(rset);
					members.add(member);
				}
			}
		} catch (SQLException e) {
			throw new MemberException(e);
		}
		return members;
	}

	
	/**
	 * 회원아이디로 회원 탈퇴 -  ?
	 */
	public int deleteMemberById(Connection conn, String delMemberId) {
		int result = 0;
		String sql = prop.getProperty("deleteMemberById");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, delMemberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException(e);
		}
		return result;
	}
	
	
	/*
	 * 탈퇴 회원 조회 - 주혜 
	 */
	public List<QuitMember> quitMemberFindAll(Connection conn) {
		List<QuitMember> quitMembers = new ArrayList<>();
		String sql = prop.getProperty("quitMemberFindAll");
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rset = pstmt.executeQuery();
				){
			while(rset.next()) {
				QuitMember quitMember = handleQuitMemberListResultSet(rset);
				quitMembers.add(quitMember);
			}
		} catch (SQLException e) {
			throw new MemberException(e);
		}
		return quitMembers;
	}

	
	/**
	 * 회원아이디로 찜목록 조회 - 종환
	 */
	public List<Favorite> MovieCodefindById(Connection conn, String memberId) {
		List<Favorite> favorites = new ArrayList<>();
		
		String sql = prop.getProperty("MovieCodefindById");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberId);
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					Favorite favorite = new Favorite();
					favorite.setMemberId(rset.getString("member_id"));
					favorite.setMovieCode(rset.getString("movie_code"));
					
					favorites.add(favorite);
				}
			}
		} catch (Exception e) {
			throw new MemberException(e);
		}
		return favorites;
	}

	
	/*
	 * 자동 탈퇴 - 주혜 
	 */
	public int reportMemberDelete(Connection conn, String reportedId) {
		int reportMemberDelete = 0;
		String sql = prop.getProperty("reportMemberDelete");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			
		} catch (SQLException e) {
			throw new MemberException(e);
		}
		return reportMemberDelete;
	}
	
	/**
	 * 탈퇴회원조회용 - ?
	 */
	private QuitMember handleQuitMemberListResultSet (ResultSet rset) throws SQLException {
		String memberId = rset.getString("member_id");
		String name = rset.getString("member_name");
		String email = rset.getString("email");
		String phone = rset.getString("phone");
		String _gender = rset.getString("gender");
		Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
		String _memberRole = rset.getString("member_role");
		MemberRole memberRole = _memberRole != null ? MemberRole.valueOf(_memberRole) : null;
		Date birthday = rset.getDate("birthday");
		Date enrollDate = rset.getDate("enroll_date");
		Date quitDate = rset.getDate("quit_date");
		QuitMember quitMember = new QuitMember();
		quitMember.setMemberId(memberId);
		quitMember.setName(name);
		quitMember.setEmail(email);
		quitMember.setPhone(phone);
		quitMember.setGender(gender);
		quitMember.setMemberRole(memberRole);
		quitMember.setBirthday(birthday);
		quitMember.setEnrollDate(enrollDate);
		quitMember.setQuitDate(quitDate);
		return quitMember;
	}
	
	
	/**
	 * 이거 뭐임? 
	 */
	private Member handleMemberResultSet(ResultSet rset) throws SQLException {
		Member member = null;
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
	
	/*
	 * 회원 전체 조회
	 */
	private Member handleMemberListResultSet (ResultSet rset) throws SQLException {
		String memberId = rset.getString("member_id");
		String memberName = rset.getString("member_name");
		Date birthday = rset.getDate("birthday");
		String email = rset.getString("email");
		String phone = rset.getString("phone");
		String _gender = rset.getString("gender");
		Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
		String favoriteGenre = rset.getString("favorite_genre_name");
		Date enrollDate = rset.getDate("enroll_date");
		String _memberRole = rset.getString("member_role");
		MemberRole memberRole = _memberRole != null ? MemberRole.valueOf(_memberRole) : null;
		
		return new Member(memberId,favoriteGenre,null,null,memberName,email,phone,gender,memberRole,birthday,enrollDate);
	}
	
	
}
