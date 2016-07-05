package com.reminder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

	public static final Logger LOG = LogManager.getLogger(LogoutServlet.class);
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE_ATTRIBUTE_NAME = "message";
	private static final String TYPE_ATTRIBUTE_NAME = "type";
	private static final String MESSAGE_SUCCESS = "Loged out successfully";
	private static final String TYPE_SUCCESS = "success";
	private static final String REDIRECT_TO_HOME = "index.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		request.setAttribute(MESSAGE_ATTRIBUTE_NAME, MESSAGE_SUCCESS);
		request.setAttribute(TYPE_ATTRIBUTE_NAME, TYPE_SUCCESS);
		LOG.debug("Redirecting to {}", REDIRECT_TO_HOME);
		request.getRequestDispatcher(REDIRECT_TO_HOME).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
