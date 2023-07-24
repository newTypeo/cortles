package com.cortles.project.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class CortlesUtils {

	/**
	 * - 비밀번호 암호화
	 */
	public static String getEncryptedPassword(String rawPassword, String salt) {
		String encryptedPassword = null;
		// 1. 암호화
		byte[] output = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] input = rawPassword.getBytes("utf-8");
			byte[] saltBytes = salt.getBytes("utf-8");
			md.update(saltBytes); // salt 추가
			output = md.digest(input); // 평문비밀번호 전달
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// 2. 인코딩
		Encoder encoder = Base64.getEncoder();
		encryptedPassword = encoder.encodeToString(output);
		
		return encryptedPassword;
	}
	
	/**
	 *  - totalContent 전체게시물수 
	 *  - totalPage 전체페이지수
	 *  - pagebarSize 5개
	 *  - pageNo 페이지바의 숫자(반복처리)
	 *  - pageStart 페이지바 시작번호
	 *  - pageEnd 페이지바 끝번호
	 *  - url 요청url
	 */
	public static String getPagebar(int cpage, int limit, int totalContent, String url) {
		StringBuilder pagebar = new StringBuilder(); // 문자열 더하기 연산에 최적화
		// 총게시물수가 60개이고, LIMIT이 10이라면 6페이지
		// 총게시물수가 61개이고, LIMIT이 10이라면 7페이지
		int totalPage = (int) Math.ceil((double) totalContent / limit);
		url += "?cpage=";
		
		int pagebarSize = 5;
		// 페이지바사이즈 5일때, 현재페이지가 1이면, 1 2 3 4 5
		// 페이지바사이즈 5일때, 현재페이지가 2이면, 1 2 3 4 5
		// 페이지바사이즈 5일때, 현재페이지가 3이면, 1 2 3 4 5
		// 페이지바사이즈 5일때, 현재페이지가 4이면, 1 2 3 4 5
		// 페이지바사이즈 5일때, 현재페이지가 5이면, 1 2 3 4 5
		// 페이지바사이즈 5일때, 현재페이지가 6이면, 6 7 8 9 10
		// 페이지바사이즈 5일때, 현재페이지가 7이면, 6 7 8 9 10
		// 페이지바사이즈 5일때, 현재페이지가 8이면, 6 7 8 9 10
		// 페이지바사이즈 5일때, 현재페이지가 9이면, 6 7 8 9 10
		// 페이지바사이즈 5일때, 현재페이지가 10이면, 6 7 8 9 10
		int pageStart = (cpage - 1) / pagebarSize * pagebarSize + 1;
		int pageEnd = pageStart + pagebarSize - 1;
		int pageNo = pageStart;
		
		// 1. 이전
		if (pageNo == 1) {
			// 이전 버튼 비활성화
		} 
		else {
			pagebar.append("<a href='%s%d'>이전</a>".formatted(url, pageNo - 1));
			pagebar.append("\n");
		}
		
		// 2. 숫자
		while (pageNo <= pageEnd && pageNo <= totalPage) {
			if (pageNo == cpage) {
				// 현재페이지인 경우
				pagebar.append("<span class='cPage'>%d</span>".formatted(pageNo));
				pagebar.append("\n");
			}
			else {
				pagebar.append("<a href='%s%d'>%d</a>".formatted(url, pageNo, pageNo));
				pagebar.append("\n");
			}
			pageNo++;
		}
		
		// 3. 다음
		if (pageNo > totalPage) {
			// 마지막페이지가 이미 노출된 경우
		}
		else {
			pagebar.append("<a href='%s%d'>다음</a>".formatted(url, pageNo));
		}
		
		return pagebar.toString();
	}
	public static String escapeHtml(String unsecureText) {
		return unsecureText.replace("<", "&lt;").replace(">", "&gt;");
	}

}
