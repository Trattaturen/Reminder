package com.reminder.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.reminder.model.Event;
import com.reminder.service.EventService;

@WebServlet(name = "DisplayEventServlet", urlPatterns = "/display")
public class DisplayEventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final Logger LOG = LogManager.getLogger(DisplayEventServlet.class);

	private static final String CONTENT_TYPE = "text/html";
	private static final String MESSAGE_ERROR = "There are no Events in DB.";
	private static final String MESSAGE_SUCCESS = "List of all Events:";
	private static final String TYPE_ERROR = "error";
	private static final String TYPE_SUCCESS = "success";
	private static final String MESSAGE = "message";
	private static final String TYPE = "type";
	private static final String REDIRECT_TO = "dashboard.jsp";
	private static final String EVENTS = "events";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOG.debug("GET request");

		List<Event> allEvents = new ArrayList<>();
		String message;
		String type;
		response.setContentType(CONTENT_TYPE);

		if (!EventService.getAll().isEmpty()) {
			LOG.debug("Getting all Events from DB");
			for (Event event : EventService.getAll()) {
				allEvents.add(event);

			}
			message = MESSAGE_SUCCESS;
			type = TYPE_SUCCESS;
			request.setAttribute(EVENTS, allEvents);
		} else {
			LOG.debug("No events in DB");
			message = MESSAGE_ERROR;
			type = TYPE_ERROR;

		}
		request.setAttribute(TYPE, type);
		request.setAttribute(MESSAGE, message);
		LOG.debug("Forwarding request to {}", REDIRECT_TO);
		request.getRequestDispatcher(REDIRECT_TO).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		LOG.debug("POST request forwarded to doGET method");
	}
}
