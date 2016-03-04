package com.reminder.conrtollers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reminder.dao.EventRepoHandler;

@WebServlet(name = "DisplayEventServlet", urlPatterns = "/display")
public class DisplayEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, String[]> paramMap = request.getParameterMap();

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		out.write(EventRepoHandler.display(paramMap));

	}
}
