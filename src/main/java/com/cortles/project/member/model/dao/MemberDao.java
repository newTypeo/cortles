package com.cortles.project.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import com.cortles.project.member.model.vo.Member;

public class MemberDao {

private Properties prop = new Properties();
	
	public MemberDao() {
		// src/main/resources/sql/member/member-query.properties 작성
		// build/classes/sql/member/member-query.properties 톰캣용 읽기파일
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
		System.out.println(sql);
		
		
		return result;
	}
	
	
	
	
	
}
