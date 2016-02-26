package com.lebedev;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/do")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String method = (String) request.getParameter("method");

		if (method.equals("add")) {
			request.getRequestDispatcher("/add").forward(request, response);
		} else if (method.equals("remove")) {
			request.getRequestDispatcher("/remove").forward(request, response);
			/*
			 * I think there is no reason to search something as there is
			 * display method } else if (method.equals("search")) {
			 * request.getRequestDispatcher("/search").forward(request,
			 * response);
			 */ } else {
			request.getRequestDispatcher("/display").forward(request, response);
		}

	}

}
