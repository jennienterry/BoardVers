package com.jimin.board7.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jimin.board7.MyUtils;

@WebServlet("/user/logout")
public class UserLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		hs.invalidate();
		
		String referer = request.getHeader("referer"); //접속경로
		System.out.println("referer : " + referer);
		
		response.sendRedirect(referer); // 로그아웃한 경로의 창 유지
	}

}
