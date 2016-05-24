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

@WebFilter(filterName = "AuthentificationFilter", servletNames = { "AddEventServlet", "DisplayEventServlet" })
public class AuthenticationFilter implements Filter {

	public static final Logger LOG = LogManager.getLogger(AuthenticationFilter.class);

	private static final String PARAM_USER = "user";
	private static final String PARAM_ERROR_MESSAGE = "message";
	private static final String PARAM_TYPE = "type";
	private static final String NOT_LOGGED_IN = "You need to Log in to access that page!";
	private static final String TYPE_ERROR = "error";
	private static final String REDIRECT_TO_LOG = "login.jsp";

	public AuthenticationFilter() {
	}

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (httpRequest.getSession(false).getAttribute(PARAM_USER) == null) {

			LOG.warn("Unauthorized user");
			request.setAttribute(PARAM_ERROR_MESSAGE, NOT_LOGGED_IN);
			request.setAttribute(PARAM_TYPE, TYPE_ERROR);
			LOG.debug("Redirecting to {}", REDIRECT_TO_LOG);

			request.getRequestDispatcher(REDIRECT_TO_LOG).forward(request, response);
		} else {
			LOG.debug("User is authorized");
			chain.doFilter(request, response);
		}
	}

}
