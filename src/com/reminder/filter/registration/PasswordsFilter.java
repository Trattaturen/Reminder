package com.reminder.filter.registration;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

@WebFilter(filterName = "PasswordsFilter", servletNames = "RegistrationServlet")
public class PasswordsFilter implements Filter {

	public static final Logger LOG = Logger.getLogger(PasswordsFilter.class);

	private static final String TYPE_ERROR = "error";
	private static final String TYPE = "type";
	private static final String PARAM_PASS_ERROR = "pass_error";
	private static final String PARAM_PASS_MISSMATCH = "pass_mismatch";
	private static final String WRONG_PASSWORD = "Wrong password!";
	private static final String WRONG_PASSWORD_MISMATCH = "Passwords do not match!";
	private static final String PARAM_PASS = "password";
	private static final String PARAM_CONF_PASS = "confirmed-password";
	private static final String REDIRECT_TO_REG = "registration.jsp";

	private static final String PASS_PATTERN = "^.{5,}$";

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (!httpRequest.getMethod().equalsIgnoreCase("POST")) {

			chain.doFilter(request, response);

		} else {

			LOG.debug("Registration POST request. Filtering passwords");
			String password = request.getParameter(PARAM_PASS);
			String confirmedPassword = request.getParameter(PARAM_CONF_PASS);

			Pattern r = Pattern.compile(PASS_PATTERN);
			Matcher m = r.matcher(password);

			if (!m.matches()) {

				LOG.warn("Password is incorrect");
				request.setAttribute(PARAM_PASS_ERROR, WRONG_PASSWORD);
				request.setAttribute(TYPE, TYPE_ERROR);
				LOG.debug("Forwarding back to " + REDIRECT_TO_REG);
				request.getRequestDispatcher(REDIRECT_TO_REG).forward(request, response);

			} else if (!password.equals(confirmedPassword)) {

				LOG.warn("Passwords do not match");
				request.setAttribute(PARAM_PASS_MISSMATCH, WRONG_PASSWORD_MISMATCH);
				request.setAttribute(TYPE, TYPE_ERROR);
				LOG.debug("Forwarding back to " + REDIRECT_TO_REG);
				request.getRequestDispatcher(REDIRECT_TO_REG).forward(request, response);

			} else {

				LOG.debug("Password is correct");
				chain.doFilter(request, response);
			}
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
