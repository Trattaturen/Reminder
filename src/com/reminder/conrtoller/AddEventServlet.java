package com.reminder.conrtoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reminder.model.Event;
import com.reminder.service.EventService;
import com.reminder.utils.EventUtil;

@WebServlet(name = "AddEventServlet", urlPatterns = "/add")
public class AddEventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String TITLE = "title";
	private static final String DAY = "day";
	private static final String TIME = "time";
	private static final String CONTENT_TYPE = "text/html";
	private static final String SUCCESS = "Success! Event was added.";
	private static final String PARAMETER_ERROR = "ErrorS! Event can`t be added. Wrong parameters:";
	private static final String DATABASE_ERROR = "Something is wrong with DB. Nothing was added";
	private static final String TYPE_ERROR = "error";
	private static final String TYPE_SUCCESS = "success";
	private static final String MESSAGE = "message";
	private static final String TYPE = "type";
	private static final String REDIRECT_TO = "add.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect(REDIRECT_TO);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String message;
		String type;

		response.setContentType(CONTENT_TYPE);

		try {

			Event newEvent = EventUtil.createEvent(request.getParameter(TITLE), request.getParameter(DAY),
					request.getParameter(TIME));

			if (EventService.add(newEvent)) {
				message = SUCCESS;
				type = TYPE_SUCCESS;
			} else {
				message = DATABASE_ERROR;
				type = TYPE_ERROR;
			}
		} catch (IllegalArgumentException e) {
			message = PARAMETER_ERROR + e.getMessage();
			type = TYPE_ERROR;
		}
		request.setAttribute(MESSAGE, message);
		request.setAttribute(TYPE, type);
		request.getRequestDispatcher(REDIRECT_TO).forward(request, response);
	}

}
