package com.reminder.conrtoller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reminder.model.Event;
import com.reminder.service.EventService;

@WebServlet(name = "DisplayEventServlet", urlPatterns = "/display")
public class DisplayEventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html";
	private static final String DATABASE_EMPTY = "Nothing to display. No events in DB";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		PrintWriter out = response.getWriter();

		if (!EventService.getAll().isEmpty()) {
			for (Event event : EventService.getAll()) {
				out.write(event.toString());
			}
		} else {
			out.write(DATABASE_EMPTY);
		}

	}
}
