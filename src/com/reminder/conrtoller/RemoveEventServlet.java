package com.reminder.conrtoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reminder.service.EventService;

@WebServlet(name = "RemoveEventServlet", urlPatterns = "/remove")
public class RemoveEventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html";
	private static final String PARAMETER_ERROR = "Nothing to delete! Wrong parameters. Try /remove?id=NUMBER";
	private static final String NOT_FOUND_ERROR = "Event with provided ID not found!";
	private static final String SUCCESS = "Event was successfully deleted";
	private static final String TYPE_ERROR = "error";
	private static final String TYPE_SUCCESS = "success";
	private static final String ID = "id";
	private static final String BREAK = "<br>";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);
		String message;
		String type;

		try {

			int toRemoveId = Integer.parseInt(request.getParameter(ID));

			if (EventService.remove(toRemoveId)) {
				message = SUCCESS;
				type = TYPE_SUCCESS;
			} else {
				message = NOT_FOUND_ERROR;
				type = TYPE_ERROR;
			}
		} catch (NumberFormatException e) {

			message = PARAMETER_ERROR;
			type = TYPE_ERROR;

		} catch (Exception e) {

			message = (PARAMETER_ERROR + BREAK + e.getMessage());
			type = TYPE_ERROR;

		}
		request.setAttribute("removeMessage", message);
		request.setAttribute("removeType", type);
		request.getRequestDispatcher("display").forward(request, response);

	}
}
