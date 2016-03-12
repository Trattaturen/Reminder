package com.reminder.conrtoller;

import java.io.IOException;
import java.io.PrintWriter;

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
	private static final String ID = "id";
	private static final String BREAK = "<br>";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);

		PrintWriter out = response.getWriter();

		try {

			int toRemoveId = Integer.parseInt(request.getParameter(ID));

			if (EventService.remove(toRemoveId)) {
				out.write(SUCCESS);
			} else {
				out.write(NOT_FOUND_ERROR);
			}
		} catch (NumberFormatException e) {

			out.write(PARAMETER_ERROR);

		} catch (Exception e) {

			out.write(PARAMETER_ERROR + BREAK + e.getMessage());

		}

	}
}