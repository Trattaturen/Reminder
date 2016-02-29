package com.lebedev.authorization;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NAME = "name";
	private static final String PASS = "password";
	private static final String INCORRECT = "Password is incorrect!";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String name = request.getParameter(NAME);
		String password = request.getParameter(PASS);

		request.getRequestDispatcher("links.html").include(request, response);

		if (password.equals("123456")) {

			out.write("Welcome, " + name);
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
		} else {

			out.print(INCORRECT);
			request.getRequestDispatcher("login.html").include(request, response);
		}

	}

}
