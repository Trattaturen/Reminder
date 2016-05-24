package com.reminder.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(filterName = "LoginFilter", servletNames = "LoginServlet")
public class LoginFilter implements Filter {

	public static final Logger LOG = LogManager.getLogger(LoginFilter.class);

	private static final String PARAM_PASSWORD = "password";
	private static final String PARAM_PASSWORD_ERROR = "pass_error";
	private static final String PARAM_MAIL = "mail";
	private static final String PARAM_USER_ERROR = "mail_error";
	private static final String PARAM_TYPE = "type";

	private static final String TYPE_ERROR = "error";
	private static final String REDIRECT_TO_LOG = "login.jsp";
	private static final String USER_ERROR = "You need to provide e-mail";
	private static final String PASSWORD_ERROR = "You need to provide password";

	public LoginFilter() {
	}

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String mail = request.getParameter(PARAM_MAIL);
		String password = request.getParameter(PARAM_PASSWORD);
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (!httpRequest.getMethod().equalsIgnoreCase("POST")) {
			LOG.debug("GET method is ignored");
			chain.doFilter(request, response);

		} else {
			LOG.debug("Filtering POST request");
			if (mail == null || mail.isEmpty()) {
				LOG.warn("E-mail field is empty");
				httpRequest.setAttribute(PARAM_USER_ERROR, USER_ERROR);
				httpRequest.setAttribute(PARAM_TYPE, TYPE_ERROR);
				LOG.debug("Redirecting back to {}", REDIRECT_TO_LOG);
				httpRequest.getRequestDispatcher(REDIRECT_TO_LOG).forward(request, response);
			} else if (password == null || password.isEmpty()) {
				LOG.warn("Password field is empty");
				httpRequest.setAttribute(PARAM_PASSWORD_ERROR, PASSWORD_ERROR);
				httpRequest.setAttribute(PARAM_TYPE, TYPE_ERROR);
				LOG.debug("Redirecting back to {}", REDIRECT_TO_LOG);
				httpRequest.getRequestDispatcher(REDIRECT_TO_LOG).forward(request, response);
			} else {
				LOG.warn("Redirecting to LoginServlet to validate User");
				chain.doFilter(request, response);
			}

		}
	}
}
