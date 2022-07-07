package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.MemberDao; //데이터베이스 연결처리와 각종 메서드들의 모임
import beans.Member; //데이터들을 이전처리할 때 필요한 필드를 모임

@WebServlet("/memberUpdate.do")//에노테이션: 컴퓨터가 읽는 주석
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//http://localhost:8080/Board01/memberUpdate.do?userid=one a태그 또는 method="get"
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid=request.getParameter("userid");
		MemberDao mDAO=MemberDao.getInstance();
		Member m=mDAO.getMember(userid);
		request.setAttribute("mem", m);
		RequestDispatcher dis=request.getRequestDispatcher("memberUpdate.jsp");
		dis.forward(request, response);
	}

	//http://localhost:8080/Board01/memberUpdate.do method="post"
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid=request.getParameter("userid");
		String pwd=request.getParameter("pwd");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String admin=request.getParameter("admin");
		
		Member m =new Member();
		m.setUserid(userid);
		m.setPwd(pwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAdmin(admin);
		
		MemberDao mDAO=MemberDao.getInstance();
		mDAO.updateMember(m);
		response.sendRedirect("login.do");
	}

}
