package com.reminder.conrtoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final Logger LOG = Logger.getLogger(HomeServlet.class);

	private static final String REDIRECT_TO = "index.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOG.info("GET request redirected to " + REDIRECT_TO);
		response.sendRedirect(REDIRECT_TO);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.warn("Somehow got POST request");
	}
}
