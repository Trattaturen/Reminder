package com.lebedev.eventhandlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lebedev.logic.Event;
import com.lebedev.logic.EventRepo;

@WebServlet("/LoadEventsFromFile")
public class TemporaryLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "<h1>Succesfully loaded from file (temporary operation)</h1>";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();

		InputStream in = getServletContext().getResourceAsStream("/tempeventholder.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		while ((reader.readLine()) != null) {
			EventRepo.addEvent(new Event(reader.readLine(), reader.readLine(), reader.readLine()));
		}
		out.write(SUCCESS);
		request.getRequestDispatcher("mainpagebutton.html").include(request, response);
	}

}
