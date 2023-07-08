package com.cortles.project.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * jdbc관련 static메소드를 제공
 * 예외처리를 미리 해서 클라이언트가 사용하기 쉽게 작성
 * 
 * - 드라이버클래스등록 (프로그램당 최초1회) - static 초기화블럭 사용
 * 
 * - Connection생성
 * - commit/rollback
 * - close
 *
 */
public class JdbcTemplate {
	
	private static String driverClass;
	private static String url;
	private static String user;
	private static String password;
	
	{
		// 인스턴스 초기화 블럭 - 객체생성시 마다 실행
		// 복잡한 연산에 의해 세팅해야할 필드전용
		// 객체 필드 초기화 순서 : 타입별기본값 - 명시적으로 작성한 값 - 초기화블럭 설정값 - 생성자에서 설정값
	}
	
	static {
		// static 초기화 블럭 - 해당클래스를 최초사용할때 딱 한번 실행
		// static변수 초기화용으로 사용
		// static 필드 초기화 순서 : 타입별기본값 - 명시적으로 작성한 값 - 초기화블럭 설정값
		
		// resources/datasource.properties 설정값 읽어오기
		Properties prop = new Properties();
		try {
			// Web App인 경우, build/classes 하위에서 파일을 참조한다. 
			String filename = JdbcTemplate.class.getResource("/datasource.properties").getPath();
			// System.out.println(filename); // /C:/Workspaces/web_server_workspace/hello-mvc/build/classes/datasource.properties
			prop.load(new FileReader(filename));
			driverClass = prop.getProperty("driverClass");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// 드라이버 클래스 등록 (프로그램단 최초 1번)
		try {
			Class.forName(driverClass);
//			System.out.println("[드라이버클래스가 등록되었습니다.]");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null && !pstmt.isClosed())
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed())
				rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	
	
	
	
	
	
	
}
