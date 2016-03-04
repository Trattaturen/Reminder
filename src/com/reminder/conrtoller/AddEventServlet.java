package com.reminder.conrtoller;

import java.io.IOException;
import java.io.PrintWriter;

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
	private static final String SUCCESS = "New Event added succesfully";
	private static final String ERROR = "Wrong parameters. Nothing added!";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		response.setContentType(CONTENT_TYPE);

		Event newEvent = EventUtil.createEvent(request.getParameter(TITLE), request.getParameter(DAY),
				request.getParameter(TIME));

		if (newEvent == null) {

			out.write(ERROR);

		} else {

			EventService.addEvent(newEvent);
			out.write(SUCCESS);
		}

	}
}
