package com.reminder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final Logger LOG = LogManager.getLogger(HomeServlet.class);

	private static final String REDIRECT_TO = "index.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOG.debug("GET request redirected to {}", REDIRECT_TO);
		response.sendRedirect(REDIRECT_TO);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.warn("Somehow got POST request");
	}
}
