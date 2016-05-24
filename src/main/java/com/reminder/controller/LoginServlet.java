package com.reminder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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

	private static final String PARAM_MAIL = "mail";

	private static final String PARAM_PASSWORD = "password";

	private static final String MESSAGE = "message";
	private static final String TYPE = "type";
	private static final String MESSAGE_SUCCESS = "Loged In succesfully";
	private static final String MESSAGE_ERROR = "Wrong e-mail or password";
	private static final String TYPE_ERROR = "error";
	private static final String TYPE_SUCCESS = "success";

	private static final String REDIRECT_TO_HOME = "index.jsp";
	private static final String REDIRECT_TO_LOGIN = "login.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(REDIRECT_TO_LOGIN).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String mail = request.getParameter(PARAM_MAIL);
		String password = request.getParameter(PARAM_PASSWORD);
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (UserService.findUser(mail, password)) {
			LOG.debug("Found user with email {}", mail);

			HttpSession session = httpRequest.getSession();
			session.setAttribute("user", mail);

			session.setMaxInactiveInterval(30 * 60);
			Cookie userName = new Cookie("user", mail);
			userName.setMaxAge(30 * 60);
			httpResponse.addCookie(userName);

			request.setAttribute(MESSAGE, MESSAGE_SUCCESS);
			request.setAttribute(TYPE, TYPE_SUCCESS);
			LOG.debug("Redirecting authorized user to {}", REDIRECT_TO_HOME);
			request.getRequestDispatcher(REDIRECT_TO_HOME).forward(request, response);
		} else {

			LOG.warn("User {} not found or password is incorrect", mail);
			request.setAttribute(MESSAGE, MESSAGE_ERROR);
			request.setAttribute(TYPE, TYPE_ERROR);
			LOG.debug("Redirecting back to {}", REDIRECT_TO_LOGIN);
			request.getRequestDispatcher(REDIRECT_TO_LOGIN).forward(request, response);
		}

	}

}
