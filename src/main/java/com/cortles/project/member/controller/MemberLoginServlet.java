package com.cortles.project.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cortles.project.common.util.CortlesUtils;
import com.cortles.project.member.model.service.MemberService;
import com.cortles.project.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/memberLogin")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MemberService memberService = new MemberService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("넘어왔는지 확인");
		request.getRequestDispatcher("/WEB-INF/views/login/login.jsp").
			forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 사용자 입력 값 처리
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String saveId = request.getParameter("saveId");
		password = CortlesUtils.getEncryptedPassword(password, memberId);
		// 업무로직
		Member member = memberService.findById(memberId);
		System.out.println("member@loginservice = " + member);
		// 응답처리
		HttpSession session = request.getSession(); // request.getSession(true)와 동일.
//		System.out.println(session.getId());
		System.out.println("db비번 = " + member.getMemberPw());
		System.out.println("사용자 입력비번 = " + password);
		if(member != null && password.equals(member.getMemberPw())) {
			// 로그인 성공
			session.setAttribute("loginMember", member);
			// session.setAttribute("msg", "로그인에 성공했습니다.");
			System.out.println("비밀번호 매칭 성공");
			// 아이디저장 쿠키처리
			// - Path : 쿠키를 사용할 url. 서버전송시 부모경로만 지정. 
			//    - / 설정시 모든 요청에 사용. 
			//    - /mvc 설정시 /mvc로 시작하는 모든 요청에 사용
			// - Session Cookie : setMaxAge설정하지 않은 경우. 접속한 동안만 클라이언트에 보관
			// - Persistent Cookie : setMaxAge설정한 경우. 지정한 시각까지만 클라이언트에 보관
			
			
			Cookie cookie = new Cookie("saveId", memberId);
			cookie.setPath(request.getContextPath()); // 쿠키를 사용할 url
			if(saveId != null) {
				cookie.setMaxAge(60 * 60 * 24 * 7); // 쿠키 유효기간 7일
			}
			else {
				// 기존의 쿠키 삭제
				cookie.setMaxAge(0); // 클라이언트 있던 쿠기의 만료기간을 0으로 변경함과 동시에 삭제 
			}
			response.addCookie(cookie); // 응답 헤더 Set-Cookie : saveId=honggd
			
			response.sendRedirect(request.getContextPath() + "/"); // redirect를 통한 url변경
		}
		else {
			// 로그인 실패
			session.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			String referer = request.getHeader("Referer");
			System.out.println("referer = " + referer);
			response.sendRedirect(referer);
		}
		
		// 3. 응답처리
	}

}
