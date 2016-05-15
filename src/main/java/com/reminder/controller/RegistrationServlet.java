package com.reminder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.reminder.model.User;
import com.reminder.service.UserService;
import com.reminder.utils.UserUtil;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final Logger LOG = LogManager.getLogger(RegistrationServlet.class);

	private static final String CONTENT_TYPE = "text/html";
	private static final String SUCCESS = "Successfully registered";
	private static final String DATABASE_ERROR = "Something is wrong with DB. Could not register";
	private static final String TYPE_ERROR = "error";
	private static final String TYPE_SUCCESS = "success";
	private static final String MESSAGE = "message";
	private static final String TYPE = "type";
	private static final String PARAM_MAIL = "mail";
	private static final String PARAM_PASS = "password";
	private static final String PARAM_CONF_PASS = "confirmed-password";
	private static final String REDIRECT_TO_REG = "registration.jsp";
	private static final String REDIRECT_TO_HOME = "index.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect(REDIRECT_TO_REG);

		LOG.debug("GET request redirected to {}", REDIRECT_TO_REG);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOG.debug("Got Filtered POST request");

		String message;
		String type;

		response.setContentType(CONTENT_TYPE);

		LOG.debug("Creating new user");
		User newUser = UserUtil.createUser(request.getParameter(PARAM_MAIL), request.getParameter(PARAM_PASS),
				request.getParameter(PARAM_CONF_PASS));

		if (UserService.add(newUser)) {

			LOG.debug("New user successfully registered");
			message = SUCCESS;
			type = TYPE_SUCCESS;

		} else {

			LOG.warn("Could not register new user");
			message = DATABASE_ERROR;
			type = TYPE_ERROR;
		}

		LOG.debug("Forwarding request to {}", REDIRECT_TO_HOME);
		request.setAttribute(MESSAGE, message);
		request.setAttribute(TYPE, type);
		request.getRequestDispatcher(REDIRECT_TO_HOME).forward(request, response);
	}

}
