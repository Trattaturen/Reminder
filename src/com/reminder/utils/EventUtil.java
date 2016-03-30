package com.reminder.utils;

import com.reminder.model.Event;

public class EventUtil {

	public static Event createEvent(String title, String day, String time) {

		String invalidParameters = checkParameters(title, day, time);
		if (invalidParameters.isEmpty()) {
			return new Event(title, day, time);

		}

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