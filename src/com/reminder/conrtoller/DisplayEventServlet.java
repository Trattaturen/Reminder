package com.reminder.conrtoller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	private static final String MESSAGE_ERROR = "There are no Events in DB.";
	private static final String MESSAGE_SUCCESS = "List of all Events:";
	private static final String TYPE_ERROR = "error";
	private static final String TYPE_SUCCESS = "success";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Event> allEvents = new ArrayList<>();
		String message;
		String type;
		response.setContentType(CONTENT_TYPE);

		if (!EventService.getAll().isEmpty()) {
			for (Event event : EventService.getAll()) {
				allEvents.add(event);

			}
			message = MESSAGE_SUCCESS;
			type = TYPE_SUCCESS;
			request.setAttribute("events", allEvents);
		} else {
			message = MESSAGE_ERROR;
			type = TYPE_ERROR;

		}
		request.setAttribute("type", type);
		request.setAttribute("message", message);
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
