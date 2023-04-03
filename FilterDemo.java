package com.registration;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FilterDemo implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpSession session=req.getSession();
		RequestDispatcher rd=null;
		if (session.getAttribute("name")!=null ) {
			chain.doFilter(request, response);
		}else {
			response.setContentType("text/html");
			rd=request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		
	}

}
