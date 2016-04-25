package com.reminder.conrtoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reminder.model.Event;
import com.reminder.service.EventService;

@WebServlet(name = "SearchEventServlet", urlPatterns = "/search")
public class SearchEventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html";
	private static final String PARAMETER_NAME = "value";
	private static final String MESSAGE_NOT_FOUND = "Event was not found";
	private static final String MESSAGE_PARAMETER_ERROR = "Wrong search parameters";
	private static final String MESSAGE_SUCCESS = "List of found Events";
	private static final String TYPE_ERROR = "error";
	private static final String TYPE_SUCCESS = "success";
	private static final String MESSAGE = "message";
	private static final String TYPE = "type";
	private static final String REDIRECT_TO = "dashboard.jsp";
	private static final String EVENTS = "events";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		String message;
		String type;

		String searchValue = request.getParameter(PARAMETER_NAME);

		if (searchValue != null && searchValue != "") {

			List<Event> foundEvents = EventService.find(searchValue);
			if (foundEvents.isEmpty()) {
				message = MESSAGE_NOT_FOUND;
				type = TYPE_ERROR;

			} else {
				message = MESSAGE_SUCCESS;
				type = TYPE_SUCCESS;
				request.setAttribute(EVENTS, foundEvents);
			}

		} else {
			message = MESSAGE_PARAMETER_ERROR;
			type = TYPE_ERROR;
		}
		request.setAttribute(TYPE, type);
		request.setAttribute(MESSAGE, message);
		request.getRequestDispatcher(REDIRECT_TO).forward(request, response);
	}

}
