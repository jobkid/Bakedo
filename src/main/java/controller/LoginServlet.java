package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;//RequestDispatcher 사용시 필요한 import
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDao;
import beans.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis=request.getRequestDispatcher("login.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url="login.jsp";
		request.setCharacterEncoding("utf-8");
		String userid=request.getParameter("userid");
		String pwd=request.getParameter("pwd");
		MemberDao mDAO=MemberDao.getInstance();//싱글톤을 유지하기 위해 new의 사용을 제한
		int result=mDAO.userCheck(userid, pwd);//1. 로그인 성공 0. 비밀번호 틀림 -1. 아이디 없음.
		if(result==1) {
			Member m=mDAO.getMember(userid);
			HttpSession session=request.getSession();
			session.setAttribute("loginUser",m);
			request.setAttribute("message", "로그인에 성공했습니다.");
			url="main.jsp";
			//해당 멤버의 정보를 가져와서 세션에 저장
			//url값 보정, 로그인 성공 시 메인페이지로 이동
		}else if(result==0) {
			//비밀번호가 틀렸다는 아낸 메시지를 전달
			request.setAttribute("massage","비밀번호가 맍지 않습니다.");
		}else if(result==-1) {
			//아이디가 존재하지 않는다는 안내 메시지를 전달
			request.setAttribute("massage", "존재하지 않는 회원입니다.");
		}
		RequestDispatcher dis=request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
