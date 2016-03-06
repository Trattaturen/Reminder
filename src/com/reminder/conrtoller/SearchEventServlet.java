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
	private static final String NOT_FOUND_ERROR = "Nothing was found!";
	private static final String SUCCESS = "Matching event found <br>";
	private static final String PARAMETER_ERROR = "Specify at least one parameter!";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		PrintWriter out = response.getWriter();

		Enumeration<String> paramsEnum = request.getParameterNames();

		if (paramsEnum.hasMoreElements()) {
			List<Event> foundEvents = EventService.find(paramsEnum.nextElement());

			if (foundEvents.size() == 0) {
				out.write(NOT_FOUND_ERROR);
			} else {
				out.write(SUCCESS);
				for (Event event : foundEvents) {
					out.write(event.toString());
				}
			}
		} else {
			out.write(PARAMETER_ERROR);
		}

	}
}
