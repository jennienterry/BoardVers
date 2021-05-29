package com.jimin.board7.cmt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jimin.board7.MyUtils;

@WebServlet("/board/cmtDelUpd")
public class BoardCmtDelUpdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	//댓글 삭제
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int icmt = MyUtils.getParamInt("icmt", request);
		int iuser = MyUtils.getLoginUserPk(request);
	
		BoardCmtEntity param = new BoardCmtEntity();
		param.setIcmt(icmt);
		param.setIuser(iuser);
		
		int result = BoardCmtDAO.delBoardCmt(param);
		
		//응답
		response.getWriter()
		.append("{")
		.append("\"result\":")
		.append(String.valueOf(result))
		.append("}")
		.flush();
	}

	//댓글 수정
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int icmt = MyUtils.getParamInt("icmt", request);
		String cmt = request.getParameter("cmt");
		int iuser = MyUtils.getLoginUserPk(request);
		
		BoardCmtEntity param = new BoardCmtEntity();
		param.setIcmt(icmt);
		param.setCmt(cmt);
		param.setIuser(iuser);
		
		int mod = BoardCmtDAO.modBoardCmt(param);
		String json = "{\"mod\":" + mod + "}";
		
//		PrintWriter ps = response.getWriter(); //printwriter객체
//		ps.append(json);
		response.getWriter().append(json);
		
	}

}