package com.reminder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reminder.service.UserService;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public static final Logger LOG = LogManager.getLogger(LoginServlet.class);

	private static final String MAIL_PARAMETER_NAME = "mail";

	private static final String PASSWORD_PARAMETER_NAME = "password";

	private static final String MESSAGE_ATTRIBUTE_NAME = "message";
	private static final String TYPE_ATTRIBUTE_NAME = "type";
	private static final String MESSAGE_SUCCESS = "Loged In succesfully";
	private static final String MESSAGE_ERROR = "Wrong e-mail or password";
	private static final String TYPE_ERROR = "error";
	private static final String TYPE_SUCCESS = "success";
	private static final String USER_ATTRIBUTE_NAME = "user";
	private static final String USER_ID_ATTRIBUTE_NAME = "userId";

	private static final String REDIRECT_TO_HOME = "index.jsp";
	private static final String REDIRECT_TO_LOGIN = "login.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(REDIRECT_TO_LOGIN).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mail = request.getParameter(MAIL_PARAMETER_NAME);
		String password = request.getParameter(PASSWORD_PARAMETER_NAME);
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (UserService.findUser(mail, password)) {

			LOG.debug("Found user with email {}", mail);

			HttpSession session = httpRequest.getSession();
			session.setAttribute(USER_ATTRIBUTE_NAME, mail);
			session.setAttribute(USER_ID_ATTRIBUTE_NAME, UserService.getUserId(mail));

			session.setMaxInactiveInterval(30 * 60);

			request.setAttribute(MESSAGE_ATTRIBUTE_NAME, MESSAGE_SUCCESS);
			request.setAttribute(TYPE_ATTRIBUTE_NAME, TYPE_SUCCESS);
			
			LOG.debug("Redirecting authorized user to {}", REDIRECT_TO_HOME);
			request.getRequestDispatcher(REDIRECT_TO_HOME).forward(request, response);

		} else {

			LOG.warn("User {} not found or password is incorrect", mail);
			
			request.setAttribute(MESSAGE_ATTRIBUTE_NAME, MESSAGE_ERROR);
			request.setAttribute(TYPE_ATTRIBUTE_NAME, TYPE_ERROR);
			
			LOG.debug("Redirecting back to {}", REDIRECT_TO_LOGIN);
			request.getRequestDispatcher(REDIRECT_TO_LOGIN).forward(request, response);
		}

	}

}
