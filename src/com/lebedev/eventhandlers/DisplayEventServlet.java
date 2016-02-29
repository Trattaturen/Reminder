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

@WebServlet(name = "DisplayEventServlet", urlPatterns = "/display")
public class DisplayEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAIN_PAGE_BUTTON = "mainpagebutton.html";
	private static final String ALL_EVENTS = "<h1>Displaying all events: </h1>";
	private static final String CHECKBOX = "<td align='center'><input type='checkbox' ";
	private static final String TABLE_FORM_HEADER = "<table border='1' style='width:50%; border: 1px solid black; border-collapse: collapse;'><form action='remove'><th>Title<th>Day<th>Time<th>Delete</th>";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		ArrayList<Event> foundEvents = RepoHandler.find(request);

		if (foundEvents != null) {

			out.write("<h1>Found " + foundEvents.size() + " matching events:</h1>");
			out.write(TABLE_FORM_HEADER);
			for (Event e : foundEvents) {

				// showing EVENT itself and a checkbox to delete it
				out.write(e.toString() + CHECKBOX + "name='" + e.getId() + "'></td></tr>");

			}
		} else {

			out.write(ALL_EVENTS + TABLE_FORM_HEADER);
			for (Event e : EventRepo.getRepo()) {
				out.write(e.toString() + CHECKBOX + "name='" + e.getId() + "'></td></tr>");

			}
		}

		out.write("</table>");
		out.write("<input type='submit' value='Remove marked'></form>");

		request.getRequestDispatcher("display.html").include(request, response);

		request.getRequestDispatcher(MAIN_PAGE_BUTTON).include(request, response);

	}
}
