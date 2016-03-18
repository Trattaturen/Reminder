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

	public static String toTable(Event event) {

		return "<tr>"
				+ "<td>" + event.getId() + "</td>" + 
				"<td>" + event.getTitle() + "</td>"+ 
				"<td>Temporary description</td>" + 
				"<td>" + event.getDay() + "</td>" + 
				"<td>" + event.getTime() + "</td>" + 
				"<td><button class='button' value='Delete'>Delete</button></td>" + 
				"</tr>";

	}
}