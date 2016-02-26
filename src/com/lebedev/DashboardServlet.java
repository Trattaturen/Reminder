package com.lebedev;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/dash")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		request.getRequestDispatcher("links.html").include(request, response);

		HttpSession ses = request.getSession(false);

		if (ses == null || ses.getAttribute("name") == null) {

			out.write("You need to be logged in to acces Dashboard");
			request.getRequestDispatcher("login.html").include(request, response);
		} else {
			request.getRequestDispatcher("dashboard.html").include(request, response);
		}

	}

}
