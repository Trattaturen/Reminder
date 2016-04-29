package com.reminder.conrtoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.reminder.service.EventService;

@WebServlet(name = "RemoveEventServlet", urlPatterns = "/remove")
public class RemoveEventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final Logger LOG = Logger.getLogger(RemoveEventServlet.class);

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.info("POST request");
		response.setContentType(CONTENT_TYPE);
		String message;
		String type;

		try {

			int toRemoveId = Integer.parseInt(request.getParameter(ID));
			LOG.info("Parsing given parameter to integer");
			if (EventService.remove(toRemoveId)) {
				LOG.info(SUCCESS);
				message = SUCCESS;
				type = TYPE_SUCCESS;
			} else {
				LOG.info(NOT_FOUND_ERROR);
				message = NOT_FOUND_ERROR;
				type = TYPE_ERROR;
			}
		} catch (NumberFormatException e) {
			LOG.warn(PARAMETER_ERROR + e);
			message = PARAMETER_ERROR;
			type = TYPE_ERROR;

		} catch (Exception e) {
			LOG.error(PARAMETER_ERROR + e);
			message = (PARAMETER_ERROR + BREAK + e.getMessage());
			type = TYPE_ERROR;

		}
		request.setAttribute(REMOVE_MESSAGE, message);
		request.setAttribute(REMOVE_TYPE, type);
		request.getRequestDispatcher("display").forward(request, response);

	}
}
