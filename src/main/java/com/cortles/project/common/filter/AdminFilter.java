package com.cortles.project.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/admin/*")
public class AdminFilter extends HttpFilter implements Filter {
       
    public AdminFilter() {super();}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
//		HttpServletRequest httpReq = (HttpServletRequest) request; 
//		HttpServletResponse httpRes = (HttpServletResponse) response; 
//		
//		HttpSession session= httpReq.getSession();
//		Member loginMember = (Member) session.getAttribute("loginMember");
//		
//		if (loginMember == null || loginMember.getMemberRole() != MemberRole.A) {
//			session.setAttribute("msg", "관리자만 사용할 수 있습니다.");
//			httpRes.sendRedirect(httpReq.getContextPath() + "/");
//			return;
//		}
//		
//		// pass the request along the filter chain
//		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
