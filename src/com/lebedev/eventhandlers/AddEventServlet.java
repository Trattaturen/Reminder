package com.lebedev.eventhandlers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lebedev.logic.Event;
import com.lebedev.logic.EventRepo;

@WebServlet(name = "AddEventServlet", urlPatterns = "/add")
public class AddEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TITLE = "title";
	private static final String DAY = "day";
	private static final String TIME = "time";
	private static final String WRONG_PARAMETERS = "Adding failed! (Wrong parameters in request) ";
	private static final String SUCCESS = "<h1>New event added succesfully!</h1>";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		response.setContentType("text/html");

		String title = request.getParameter(TITLE);
		String day = request.getParameter(DAY);
		String time = request.getParameter(TIME);

		if (title == "" || day == "" || time == "") {

			out.write(WRONG_PARAMETERS);

		} else {
			EventRepo.addEvent(new Event(title, day, time));
			out.write(SUCCESS);

		}

		request.getRequestDispatcher("mainpagebutton.html").include(request, response);

	}
}
