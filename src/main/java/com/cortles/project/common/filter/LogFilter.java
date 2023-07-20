package com.cortles.project.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter 작성하기
 * - Filter인터페이스 구현
 * - doFilter메소드 오버라이드
 * 
 * - 서블릿 호출전 작업
 * - 응답 메세지 발송전(jsp작업이후) 작업
 * 
 * 처리되는 필터의 순서를 변경하려면 web.xml에 등록.
 * - 필터 처리순서
 * - 1. web.xml에 등록된 순서
 * - 2. @WebFilter로 등록된 클래스
 */
@WebFilter("/*")
public class LogFilter implements Filter {

	/**
	 * ServletRequest는 HttpServletRequest의 부모타입. HttpServletRequest 다운캐스팅 가능
	 * ServletResponse는 HttpServletResponse의 부모타입. HttpServletResponse 다운캐스팅 가능
	 * FilterChain 필터묶음 객체. FilterChain#doFilter를 호출하면, 다음 필터 또는 서블릿으로 연결
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 서블릿 가기전 작업 공간
		chain.doFilter(request, response); 
		// 응답메세지 발송전 작업 공간
	}

}
