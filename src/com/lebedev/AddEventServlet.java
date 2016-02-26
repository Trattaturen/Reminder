package com.lebedev;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/add")
public class AddEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession ses = request.getSession(false);

		response.setContentType("text/html");

		String title = request.getParameter("title");
		String day = request.getParameter("day");
		String time = request.getParameter("time");

		if (title == "" || day == "" || time == "") {

			out.write("Adding failed! (Wrong parameters in request) ");

		} else {
			EventRepo.addEvent(new Event(title, day, time));
			out.write("<h1>New event added succesfully!</h1>");
			out.write("<p> All Events in pool: <p><br>");
			for (Event e : EventRepo.getRepo()) {
				out.write(e.toString());
			}

		}

		request.getRequestDispatcher("mainpagebutton.html").include(request, response);

	}
}
