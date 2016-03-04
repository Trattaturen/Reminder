package com.reminder.dao;

import java.util.Map;

import com.remider.logic.FindEvent;
import com.reminder.model.Event;

public class EventRepoHandler {

	// Adds Event if all parameters given
	public static void addEvent(String title, String day, String time) {

		EventRepo.getRepo().add(new Event(title, day, time));

	}

	// Displays events, found by FIND method
	public static String display(Map<String, String[]> paramMap) {
		String result = "";

		for (Event e : FindEvent.find(paramMap)) {
			result = result + e.toString();
		}

		return result;
	}

	// Removes Events, found by FIND method
	public static String remove(Map<String, String[]> paramMap) {
		String result = "Removed " + FindEvent.find(paramMap).size() + " events";
		EventRepo.getRepo().removeAll(FindEvent.find(paramMap));
		return result;

	}

}
