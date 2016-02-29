package com.lebedev.eventhandlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lebedev.logic.Event;
import com.lebedev.logic.EventRepo;
import com.lebedev.logic.RepoHandler;

@WebServlet(name = "RemoveEventServlet", urlPatterns = "/remove")
public class RemoveEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAIN_PAGE_BUTTON = "mainpagebutton.html";
	 private static final String NULL_PARAMETERS = "<h1>Nothing removed</h1>";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		ArrayList<Event> toRemove = new ArrayList<>();
		Enumeration<String> paramsEnum = request.getParameterNames();

		while (paramsEnum.hasMoreElements()) {
			int paramId = Integer.parseInt(paramsEnum.nextElement());
			System.out.println(paramId);
			toRemove.add(RepoHandler.findById(paramId));
		}

		if (toRemove.size() != 0) {

			EventRepo.getRepo().removeAll(toRemove);

			out.write("<h1> Succesfully removed " + toRemove.size() + " events </h1>");
		} else {
			out.write(NULL_PARAMETERS);
		}
		
		request.getRequestDispatcher(MAIN_PAGE_BUTTON).include(request, response);
		/*
		 * ArrayList<Event> foundEvents = RepoHandler.find(request);
		 * 
		 * if (foundEvents != null) {
		 * 
		 * EventRepo.getRepo().removeAll(foundEvents); out.write(
		 * "<h1> Succesfully removed " + foundEvents.size() + " events </h1>");
		 * 
		 * } else {
		 * 
		 * out.write(NULL_PARAMETERS);
		 * 
		 * }
		 * 
		 * request.getRequestDispatcher(MAIN_PAGE_BUTTON).include(request,
		 * response);
		 * 
		 * }
		 */
	}
}
