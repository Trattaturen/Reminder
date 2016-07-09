package com.reminder.controller;

import com.reminder.model.Event;
import com.reminder.service.EventService;
import com.reminder.utils.EventUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@WebServlet(name = "AddEventServlet", urlPatterns = "/add")
public class AddEventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final Logger LOG = LogManager.getLogger(AddEventServlet.class);

	private static final String TITLE_PARAMETER_NAME = "title";
	private static final String DAY_PARAMETER_NAME = "day";
	private static final String TIME_PARAMETER_NAME = "time";
	private static final String DESCRIPTION_PARAMETER_NAME = "description";
	private static final String TYPE_PARAMETER_NAME = "type";
	private static final String REMIND_PARAMETER_NAME = "remind";
	private static final String USER_ID_ATTRIBUTE_NAME = "userId";
	private static final String CONTENT_TYPE = "text/html";
	private static final String MESSSAGE_SUCCESS = "Success! Event was added.";
	private static final String MESSAGE_PARAMETER_ERROR = "Error! Event can`t be added. Wrong parameters:";
	private static final String MESSAGE_DATABASE_ERROR = "Something is wrong with DB. Nothing was added";
	private static final String TYPE_ERROR = "error";
	private static final String TYPE_SUCCESS = "success";
	private static final String MESSAGE_ATTRIBUTE_NAME = "message";
	private static final String TYPE_ATTRIBUTE_NAME = "type";
	private static final String REDIRECT_TO = "add.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect(REDIRECT_TO);

		LOG.debug("GET request");
		LOG.debug("GET request redirected to {}", REDIRECT_TO);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOG.debug("POST request");

		HttpSession session = request.getSession(false);

		String message;
		String type;
		int userId = (int) session.getAttribute(USER_ID_ATTRIBUTE_NAME);

		response.setContentType(CONTENT_TYPE);

		try {
			Event newEvent = EventUtil.createEvent(userId, request.getParameter(TITLE_PARAMETER_NAME), request.getParameter(DAY_PARAMETER_NAME),
					request.getParameter(TIME_PARAMETER_NAME));
			EventUtil.updateEvent(newEvent, request.getParameter(DESCRIPTION_PARAMETER_NAME), request.getParameter(TYPE_PARAMETER_NAME), request.getParameter(REMIND_PARAMETER_NAME));
			if (EventService.add(newEvent)) {
				LOG.debug("Event added");
				message = MESSSAGE_SUCCESS;
				type = TYPE_SUCCESS;
			} else {
				LOG.warn("Could not add event");
				message = MESSAGE_DATABASE_ERROR;
				type = TYPE_ERROR;
			}
		} catch (IllegalArgumentException e) {
			LOG.error("Wrong Event parameters {}", e);
			message = MESSAGE_PARAMETER_ERROR + e.getMessage();
			type = TYPE_ERROR;

		}
		LOG.debug("Forwarding request to {}", REDIRECT_TO);
		request.setAttribute(MESSAGE_ATTRIBUTE_NAME, message);
		request.setAttribute(TYPE_ATTRIBUTE_NAME, type);
		request.getRequestDispatcher(REDIRECT_TO).forward(request, response);
	}

}
