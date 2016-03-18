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
import com.reminder.utils.EventUtil;

@WebServlet(name = "SearchEventServlet", urlPatterns = "/search")
public class SearchEventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html";
	private static final String PARAMETER_NAME = "value";
	private static final String MESSAGE_ERROR = "Nothing found";
	private static final String MESSAGE_SUCCESS = "Found Event matching criteria";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		String events = "";
		String message;

		String searchValue = request.getParameter(PARAMETER_NAME);

		if (searchValue != null) {

			List<Event> foundEvents = EventService.find(searchValue);
			if (foundEvents.isEmpty()) {
				message = MESSAGE_ERROR;
			} else {
				message = MESSAGE_SUCCESS;
			}

			for (Event event : foundEvents) {
				events = events + EventUtil.toTable(event);
				request.setAttribute("events", events);

			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		} else {

		}

	}

}
