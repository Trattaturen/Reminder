package com.lebedev;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean found = false;
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		Event searchedEvent = new Event(request.getParameter("title"), request.getParameter("day"),
				request.getParameter("time"));

		for (Event e : EventRepo.getRepo()) {
			if (e.equals(searchedEvent)) {
				out.write("<h1>Found matching event</h1>" + e.toString());
				found = true;

			}
		}
		if (!found) {
			out.write("<h1>No event with folowing params found</h1>" + searchedEvent.toString());
		}

		request.getRequestDispatcher("mainpagebutton.html").include(request, response);
	}

}
