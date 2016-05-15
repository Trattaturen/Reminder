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

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@WebFilter(filterName = "MailFilter", servletNames = "RegistrationServlet")
public class MailFilter implements Filter {

	public static final Logger LOG = LogManager.getLogger(MailFilter.class);

	private static final String PARAM_MAIL = "mail";
	private static final String PARAM_MAIL_ERROR = "mail_error";
	private static final String WRONG_EMAIL = "Wrong e-mail!";
	private static final String TYPE_ERROR = "error";
	private static final String REDIRECT_TO_REG = "registration.jsp";
	private static final String TYPE = "type";
	private static final String MAIL_PATTERN = "^[a-zA-Z0-9\\.\\_]{4,31}+@+[a-zA-Z]{1}+[a-zA-Z0-9]{0,}\\.+[a-zA-Z]{2,3}$";

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		if (!httpRequest.getMethod().equalsIgnoreCase("POST")) {

			chain.doFilter(request, response);

		} else {

			LOG.debug("Registration POST request. Filtering e-mail");
			String mail = request.getParameter(PARAM_MAIL);

			Pattern r = Pattern.compile(MAIL_PATTERN);
			Matcher m = r.matcher(mail);

			if (!m.matches()) {

				LOG.warn("E-mail is incorrect");
				request.setAttribute(PARAM_MAIL_ERROR, WRONG_EMAIL);
				request.setAttribute(TYPE, TYPE_ERROR);
				LOG.debug("Forwarding back to {}", REDIRECT_TO_REG);
				request.getRequestDispatcher(REDIRECT_TO_REG).forward(request, response);

			} else {

				LOG.debug("E-mail is correct");
				chain.doFilter(request, response);
			}
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
