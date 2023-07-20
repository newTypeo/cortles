package com.cortles.project.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * jdbc관련 static메소드를 제공
 * 예외처리를 미리 해서 클라이언트가 사용하기 쉽게 작성
 * 
 * - 드라이버클래스등록 (프로그램당 최초1회) - static 초기화블럭 사용
 * 
 * - Connection생성
 * - commit/rollback
 * - close
 */
public class JdbcTemplate {
	
	/**
	 * JNDI Java Name and Directory Interface
	 * - 서로 다른 서비스(자원)을 참조하기 위한 규격
	 * - jndi루트디렉토리 하위에 자원을 등록/참조
	 * - jndi루트디렉토리/comp/env/자원
	 * 
	 * - /comp/env/jdbc/hellooracle 하위 DataSource객체를 등록/참조
	 */
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/hellooracle");
			conn = ds.getConnection(); // Connection Pool로부터 Connection 가져오기
			conn.setAutoCommit(false);
		} catch (SQLException | NamingException e) {
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
