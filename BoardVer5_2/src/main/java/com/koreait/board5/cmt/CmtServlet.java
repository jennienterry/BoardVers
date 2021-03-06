package com.koreait.board5.cmt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board5.MyUtils;

@WebServlet("/board/cmt")
public class CmtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int iboard = MyUtils.getParamInt("iboard", request);
		int icmt = MyUtils.getParamInt("icmt", request);
		int iuser = MyUtils.getLoginUserPk(request);
		
		CmtVO param = new CmtVO();
		param.setIcmt(icmt);
		param.setIuser(iuser);
		
		CmtDAO.delCmt(param);
		response.sendRedirect("detail?iboard=" + iboard);
	
	}
	
	//댓글 insert,update 같이 사용
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int icmt = MyUtils.getParamInt("icmt", request);
		int iboard = MyUtils.getParamInt("iboard", request);
		String cmt = request.getParameter("cmt");
		int iuser = MyUtils.getLoginUserPk(request);
		
		CmtVO param = new CmtVO();
		param.setCmt(cmt);	
		param.setIuser(iuser); //이사람이 그 사람이 맞는지 확인하기 위함
		
		if(icmt != 0) { // 수정
			param.setIcmt(icmt); //icmt값으로 iboard를 알 수 있음
			CmtDAO.updCmt(param);
		}else {
			param.setIboard(iboard); //등록 일 때는 iboard값이 필요
			CmtDAO.insCmt(param);
		}
		
		response.sendRedirect("detail?iboard=" + iboard);
		//detail은 무조건 iboard값 가져와야 한다.
		}

}
