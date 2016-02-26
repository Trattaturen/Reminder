package com.lebedev;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/remove")
public class RemoveEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		Event remEvent = new Event(request.getParameter("title"), request.getParameter("day"),
				request.getParameter("time"));
		if (EventRepo.removeEvent(remEvent)) {
			out.write("<h1>Removed object with following params: </h1>" + remEvent.toString());
		} else {
			out.write("<h>Sorry, no such object in pool</h1>");
		}

		request.getRequestDispatcher("mainpagebutton.html").include(request, response);

	}

}
