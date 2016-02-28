package com.lebedev.eventhandlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lebedev.logic.Event;
import com.lebedev.logic.EventRepo;
import com.lebedev.logic.RepoHandler;

@WebServlet("/display")
public class DisplayEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAIN_PAGE_BUTTON = "mainpagebutton.html";
	private static final String ALL_EVENTS = "<h1>Displaying all events: </h1>";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		ArrayList<Event> foundEvents = RepoHandler.find(request);

		if (foundEvents != null) {

			out.write("<h1>Found " + foundEvents.size() + " matching events:</h1>");
			for (Event e : foundEvents) {
				out.write(e.toString());

			}
		} else {

			out.write(ALL_EVENTS);
			for (Event e : EventRepo.getRepo()) {
				out.write(e.toString());

			}
		}

		request.getRequestDispatcher("display.html").include(request, response);

		request.getRequestDispatcher(MAIN_PAGE_BUTTON).include(request, response);

	}
}
