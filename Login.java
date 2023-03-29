package com.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		HttpSession session=request.getSession();
		String uname = request.getParameter("username");
		String upass = request.getParameter("password");
		RequestDispatcher rd=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?useSSl=false", "root", "system");

			PreparedStatement ps = con.prepareStatement("select * from users where uname=? and upass=? ;");
			ps.setString(1, uname);
			ps.setString(2, upass);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				session.setAttribute("name", rs.getString("uname"));
//				session.setMaxInactiveInterval(360);
				rd=request.getRequestDispatcher("index.jsp");
			}else {
				rd=request.getRequestDispatcher("login.jsp");
			}
			rd.forward(request, response);
		} catch (Exception e) {		
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
