package com.reminder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.reminder.service.EventService;

@WebServlet(name = "RemoveEventServlet", urlPatterns = "/remove")
public class RemoveEventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final Logger LOG = LogManager.getLogger(RemoveEventServlet.class);

	private static final String CONTENT_TYPE = "text/html";
	private static final String PARAMETER_ERROR = "Wrong parameters";
	private static final String NOT_FOUND_ERROR = "Event with provided ID not found!";
	private static final String SUCCESS = "Event was successfully deleted";
	private static final String TYPE_ERROR = "error";
	private static final String TYPE_SUCCESS = "success";
	private static final String ID = "id";
	private static final String BREAK = "<br>";
	private static final String REMOVE_MESSAGE = "removeMessage";
	private static final String REMOVE_TYPE = "removeType";
	private static final String REDIRECT_TO = "display";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.debug("POST request");
		response.setContentType(CONTENT_TYPE);
		String message;
		String type;

		try {

			int toRemoveId = Integer.parseInt(request.getParameter(ID));
			LOG.debug("Parsing given parameter to integer");
			if (EventService.remove(toRemoveId)) {
				LOG.info("Deleted Event");
				message = SUCCESS;
				type = TYPE_SUCCESS;
			} else {
				LOG.debug("Event not found");
				message = NOT_FOUND_ERROR;
				type = TYPE_ERROR;
			}
		} catch (NumberFormatException e) {
			LOG.warn("Wrong parameters", e);
			message = PARAMETER_ERROR;
			type = TYPE_ERROR;

		} catch (Exception e) {
			LOG.error("Wrong parameters {}",  e);
			message = (PARAMETER_ERROR + BREAK + e.getMessage());
			type = TYPE_ERROR;

		}
		LOG.debug("Forwarding request to {}", REDIRECT_TO);
		request.setAttribute(REMOVE_MESSAGE, message);
		request.setAttribute(REMOVE_TYPE, type);
		request.getRequestDispatcher(REDIRECT_TO).forward(request, response);

	}
}
