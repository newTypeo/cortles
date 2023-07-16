package com.cortles.project.member.model.service;
import static com.cortles.project.common.JdbcTemplate.close;
import static com.cortles.project.common.JdbcTemplate.commit;
import static com.cortles.project.common.JdbcTemplate.getConnection;
import static com.cortles.project.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.cortles.project.member.model.dao.MemberDao;
import com.cortles.project.member.model.vo.Member;
import com.cortles.project.member.model.vo.MemberRole;
import com.cortles.project.member.model.vo.QuitMember;

public class MemberService {
	private final MemberDao memberDao = new MemberDao();
	
	
	/**
	 * 아이디로 회원 조회
	 * 로그인 - 현우
	 */
	public Member findById(String memberId) {
		Connection conn = getConnection();
		Member member = memberDao.findById(conn, memberId);
		close(conn);
		
		return member;
	}

	/**
	 * 회원가입 -종환
	 */
	public int signup(Member member) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = memberDao.signup(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	/*
	 * 회원 조회 - 주혜
	 */
	public List<Member> findAll() {
		Connection conn = getConnection();
		List<Member> members = memberDao.findAll(conn);
		close(conn);
		return members;
	}

	/**
	 * 찜 목록추가 - 경빈
	 * @param movieCode 
	 */
	public int addMyList(String memberId, String movieCode) {
		Connection conn = getConnection();
		int result = 0;
		result = memberDao.addMyList(conn, memberId, movieCode);
		close(conn);
		return result;
	}

	public int deleteMyList(String memberId, String movieCode) {
		Connection conn = getConnection();
		int result = 0;
		result = memberDao.deleteMyList(conn, memberId, movieCode);
		close(conn);
		return result;
	}

	/*
	 * 권한 수정 - 주혜 
	 */
	public int memberRoleUpdate(String memberId, MemberRole memberRole) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.memberRoleUpdate(conn,memberId,memberRole);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		return result;
	}

	/**
	 * - 회원정보 수정 - 종환
	 */
	public int updateMember(Member member) {
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.updateMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	/*
	 * 회원 검색 - 주혜 
	 */
	public List<Member> searchMember(String keyword) {
		List<Member> members = new ArrayList<>();
		Connection conn = getConnection();
		members = memberDao.searchMember(conn,keyword);
		close(conn);
		return members;
	}

	/**
	 * 회원 탈퇴 - 종환
	 */
	public int deleteMemberById(String delMemberId) {
		int result = 0;
		Connection conn = getConnection();
		
		try {
			result = memberDao.deleteMemberById(conn, delMemberId);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}

	/*
	 * 탈퇴한 회원 조회 - 주혜 
	 */
	public List<QuitMember> quitMemberFindAll() {
		Connection conn = getConnection();
		List<QuitMember> quitMembers = memberDao.quitMemberFindAll(conn);
		close(conn);
		return quitMembers;
	}

	

}
