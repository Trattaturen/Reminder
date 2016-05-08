package com.reminder.utils;

import org.apache.log4j.Logger;

import com.reminder.model.Event;

public class EventUtil {

	public static final Logger LOG = Logger.getLogger(EventUtil.class);

	public static Event createEvent(String title, String day, String time) {
		LOG.debug("Checking parameters:" + title + day + time);
		String invalidParameters = checkParameters(title, day, time);
		if (invalidParameters.isEmpty()) {
			LOG.debug("All parameters correct. Returning new Event");
			return new Event(title, day, time);
		}
		LOG.warn("Incorrect parameters: " + invalidParameters);
		throw new IllegalArgumentException(invalidParameters);

	}

	private static String checkParameters(String title, String day, String time) {

		String result = "";
		if (title == null || title.isEmpty()) {
			result = result + " Title";
		}
		if (day == null || day.isEmpty()) {
			result = result + " Day";
		}
		if (time == null || time.isEmpty()) {
			result = result + " Time";
		}

		return result;

	}

}