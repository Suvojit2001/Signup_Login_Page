package com.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.net.ssl.SSLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String uname = req.getParameter("name");
		String uemail = req.getParameter("email");
		String upass = req.getParameter("pass");
		String pass2 = req.getParameter("re_pass");
		String umobile = req.getParameter("contact");
		RequestDispatcher rd = null;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "system");
			PreparedStatement ps = con
					.prepareStatement("insert into users(uname,upass,uemail,umobile) values(?,?,?,?);");
			ps.setString(1, uname);
			ps.setString(2, upass);
			ps.setString(3, uemail);
			ps.setString(4, umobile);
			int count = ps.executeUpdate();
			rd = req.getRequestDispatcher("login.jsp");
			rd.forward(req, res);
		} catch (SSLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
