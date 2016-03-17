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
	private static final String SUCCESS = "New Event was added succesfully";
	private static final String PARAMETER_ERROR = "Nothing was added! Wrong parameters:";
	private static final String DATABASE_ERROR = "Something is wrong with DB. Nothing was added";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		response.setContentType(CONTENT_TYPE);

		try {

			Event newEvent = EventUtil.createEvent(request.getParameter(TITLE), request.getParameter(DAY),
					request.getParameter(TIME));

			if (EventService.add(newEvent)) {
				out.write(SUCCESS);
			} else {
				out.write(DATABASE_ERROR);
			}
		} catch (IllegalArgumentException e) {
			out.write(PARAMETER_ERROR + e.getMessage());
		}

	}

}
