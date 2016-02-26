package com.lebedev;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/remove")
public class RemoveEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		ArrayList<String> paramsArray = new ArrayList<>();

		// Array to contain Events, matching remove parameters
		ArrayList<Event> toRemove = new ArrayList<>();

		// Getting all parameters as Enumeration
		Enumeration<String> paramsEnum = request.getParameterNames();

		while (paramsEnum.hasMoreElements()) {
			String paramName = paramsEnum.nextElement();
			// Clearing not needed parameter and not filled form parameters
			if (!paramName.equals("method") && request.getParameter(paramName) != "") {
				paramsArray.add(paramName);
			}
		}
		// If all forms were empty - display all Events
		if (paramsArray.size() == 0) {
			out.write("You need to specify some parameters!");

		} else {
			// big stupid loop to check if all form parameter values equals any
			// event object parameter values

			for (Event e : EventRepo.getRepo()) {
				int count = 0;
				for (int i = 0; i < paramsArray.size(); i++) {
					String arrayParamName = paramsArray.get(i);
					if (arrayParamName.equals("title") && request.getParameter(arrayParamName).equals(e.title)) {
						count++;
					} else if (arrayParamName.equals("day") && request.getParameter(arrayParamName).equals(e.day)) {
						count++;
					} else if (arrayParamName.equals("time") && request.getParameter(arrayParamName).equals(e.time)) {
						count++;
					}

				}
				// if all parameter values equals object values - print it
				if (count == paramsArray.size()) {
					toRemove.add(e);

				}
			}
		}

		// Remove matching elements
		EventRepo.getRepo().removeAll(toRemove);
		out.write("Removed " + toRemove.size() + " events!");

		request.getRequestDispatcher("mainpagebutton.html").include(request, response);

	}

}
