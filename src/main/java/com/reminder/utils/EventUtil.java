package com.reminder.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reminder.model.Event;

public class EventUtil {

	public static final Logger LOG = LogManager.getLogger(EventUtil.class);

	public static Event createEvent(int userId, String title, String day, String time) {
		LOG.debug("Checking parameters: {} {} {}", title, day, time);
		String invalidParameters = checkParameters(title, day, time);
		if (invalidParameters.isEmpty()) {
			LOG.debug("All parameters correct. Returning new Event");
			return new Event(userId, title, day, time);
		}
		LOG.warn("Incorrect parameters: {}", invalidParameters);
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

	public static void updateEvent(Event event, String description, String type, String remind) {
		if (!description.isEmpty()) {
			event.setDescription(description);
		} else {
			event.setDescription("");
		}
		if (!type.isEmpty()) {
			event.setType(type);
		} else {
			event.setType("");
		}
		if (remind != null && !remind.isEmpty()) {
			event.setRemind(true);
		} else {
			event.setRemind(false);
		}

	}

}