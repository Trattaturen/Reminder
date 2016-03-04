package com.reminder.conrtollers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.remider.logic.EventHandler;

@WebServlet(name = "AddEventServlet", urlPatterns = "/add")
public class AddEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "title";
	private static final String DAY = "day";
	private static final String TIME = "time";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		out.write(EventHandler.addEvent(request.getParameter(TITLE), request.getParameter(DAY),
				request.getParameter(TIME)));

	}
}
