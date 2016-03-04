package com.reminder.conrtoller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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
	private static final String ERROR = "Nothing found! Wrong parameters";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		Enumeration<String> paramsEnum = request.getParameterNames();

		PrintWriter out = response.getWriter();

		List<Event> foundEvents = EventService.findEvent(paramsEnum);

		if (foundEvents.size() == 0) {
			out.write(ERROR);
		} else {

			for (Event event : foundEvents) {
				out.write(event.toString());
			}
		}

	}
}
