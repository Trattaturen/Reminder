package com.reminder.logic;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.reminder.dao.EventRepoHandler;
import com.reminder.model.Event;

public class EventHandler {

	// Add event if all parameters given
	public static String addEvent(String title, String day, String time) {

		if (RequestParamsHandler.checkParameters(title, day, time)) {

			EventRepoHandler.addEvent(title, day, time);
			return ("New event added succesfully!");

		} else {

			return ("Adding failed! (Wrong parameters in request)");

		}
	}

	// Displays all events if no parameters given, or all, found by FIND method
	public static String display(Map<String, String[]> paramMap) {

		if (paramMap.isEmpty()) {
			return EventRepoHandler.displayAll();
		}

		if (EventHandler.find(paramMap) == null) {
			return "Incorrect parameter";
		}

		String result = "";
		for (Event e : EventHandler.find(paramMap)) {
			result = result + e.toString();
		}

		return result;
	}

	// Removes Events, found by FIND method, error if no parameters given
	public static String remove(Map<String, String[]> paramMap) {
		if (paramMap.isEmpty() || EventHandler.find(paramMap) == null) {
			return "Incorrect remove request";
		}

		int found = EventHandler.find(paramMap).size();

		EventRepoHandler.remove(EventHandler.find(paramMap));

		return ("Removed " + found + " events");

	}

	// Calling specified finBy method of DAO. Can be "polymorphized" in DAO
	// method. Need advice
	public static ArrayList<Event> find(Map<String, String[]> paramMap) {

		for (Entry<String, String[]> entry : paramMap.entrySet()) {
			if (entry.getKey().equals("title")) {
				return EventRepoHandler.findByTitle(entry.getValue()[0]);
			} else if (entry.getKey().equals("day")) {
				return EventRepoHandler.findByDay(entry.getValue()[0]);
			} else if (entry.getKey().equals("time")) {
				return EventRepoHandler.findByTime(entry.getValue()[0]);
			}
		}
		return null;
	}
}
