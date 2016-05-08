package com.reminder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.reminder.model.User;
import com.reminder.service.UserService;
import com.reminder.utils.UserUtil;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final Logger LOG = Logger.getLogger(RegistrationServlet.class);

	private static final String CONTENT_TYPE = "text/html";
	private static final String SUCCESS = "Successfully registered";
	private static final String DATABASE_ERROR = "Something is wrong with DB. Could not register";
	private static final String TYPE_ERROR = "error";
	private static final String TYPE_SUCCESS = "success";
	private static final String PARAM_MAIL_ERROR = "mail_error";
	private static final String PARAM_PASS_ERROR = "pass_error";
	private static final String PARAM_PASS_MISSMATCH = "pass_mismatch";

	private static final String MESSAGE = "message";
	private static final String TYPE = "type";

	private static final String PARAM_MAIL = "mail";
	private static final String PARAM_PASS = "password";
	private static final String PARAM_CONF_PASS = "confirmed-password";

	private static final String REDIRECT_TO_REG = "registration.jsp";
	private static final String REDIRECT_TO_HOME = "index.jsp";

	private static final String WRONG_EMAIL = "Wrong e-mail!";
	private static final String WRONG_PASSWORD = "Wrong password!";
	private static final String WRONG_PASSWORD_MISMATCH = "Passwords do not match!";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect(REDIRECT_TO_REG);

		LOG.debug("GET request redirected to " + REDIRECT_TO_REG);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOG.debug("POST request");

		String message;
		String type;

		response.setContentType(CONTENT_TYPE);
		/* 	I understand that best practice is not to overload servlets
		 	with such mess (better to give it to Utils/Service level), but due 
		 	to functional requirements we need to set different 
		 	request parameters depending on user mistakes. 
		 	I could not think of a "beautiful" way to do it. 
		 	Maybe these kind of validations are	made by means of JS */
		if (!UserUtil.checkMail(request.getParameter(PARAM_MAIL))) {
			LOG.warn("E-mail is incorrect");
			type = TYPE_ERROR;
			request.setAttribute(PARAM_MAIL_ERROR, WRONG_EMAIL);
		} else if (!UserUtil.checkPassword(request.getParameter(PARAM_PASS))) {
			LOG.warn("Password is incorrect");
			type = TYPE_ERROR;
			request.setAttribute(PARAM_PASS_ERROR, WRONG_PASSWORD);
		} else if (!UserUtil.matchPasswords(request.getParameter(PARAM_PASS), request.getParameter(PARAM_CONF_PASS))) {
			LOG.warn("Passwords mismatch");
			type = TYPE_ERROR;
			request.setAttribute(PARAM_PASS_MISSMATCH, WRONG_PASSWORD_MISMATCH);
		} else {
			LOG.debug("Creating new user");
			User newUser = UserUtil.createUser(request.getParameter(PARAM_MAIL), request.getParameter(PARAM_PASS),
					request.getParameter(PARAM_CONF_PASS));

			if (UserService.add(newUser)) {
				LOG.debug("User added to DB");
				message = SUCCESS;
				type = TYPE_SUCCESS;
			} else {
				LOG.warn(DATABASE_ERROR);
				message = DATABASE_ERROR;
				type = TYPE_ERROR;
			}

			LOG.debug("Forwarding request to " + REDIRECT_TO_HOME);
			request.setAttribute(MESSAGE, message);
			request.setAttribute(TYPE, type);
			request.getRequestDispatcher(REDIRECT_TO_HOME).forward(request, response);
		}
		LOG.debug("Forwarding request to " + REDIRECT_TO_REG);
		request.setAttribute(TYPE, type);
		request.getRequestDispatcher(REDIRECT_TO_REG).forward(request, response);
	}

}
