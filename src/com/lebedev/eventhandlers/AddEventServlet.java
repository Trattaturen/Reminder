package com.lebedev.eventhandlers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lebedev.logic.Event;
import com.lebedev.logic.EventRepo;

@WebServlet("/add")
public class AddEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "title";
	private static final String DAY = "day";
	private static final String TIME = "time";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		response.setContentType("text/html");

		String title = request.getParameter(TITLE);
		String day = request.getParameter(DAY);
		String time = request.getParameter(TIME);

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
