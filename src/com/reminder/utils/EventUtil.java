package com.reminder.utils;

import com.reminder.model.Event;

public class EventUtil {

	public static Event createEvent(String title, String day, String time) {

		if (checkParameters(title, day, time)) {
			return new Event(title, day, time);

		}
		return null;
	}

	public static boolean checkParameters(String title, String day, String time) {

		if ((title == null || day == null || time == null || (title.isEmpty() || day.isEmpty() || time.isEmpty()))) {

			return false;

		}

		return true;
	}
}