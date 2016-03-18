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

@WebServlet(name = "DisplayEventServlet", urlPatterns = "/display")
public class DisplayEventServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html";
	private static final String MESSAGE_ERROR = "DB is empty";
	private static final String MESSAGE_SUCCESS = "Displaying all Events in DB";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String events = "";
		String message = "";
		response.setContentType(CONTENT_TYPE);

		if (!EventService.getAll().isEmpty()) {
			for (Event event : EventService.getAll()) {
				events = events + EventUtil.toTable(event);

			}
			message = MESSAGE_SUCCESS;
			request.setAttribute("events", events);
		} else {
			message = MESSAGE_ERROR;
			/* out.write(DATABASE_EMPTY); */
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}
}
