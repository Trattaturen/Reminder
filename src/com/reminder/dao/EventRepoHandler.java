package com.reminder.dao;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.remider.logic.RequestParamsHandler;
import com.reminder.model.Event;

public class EventRepoHandler {

	// Adds Event if all parameters given
	public static void addEvent(String title, String day, String time) {

		EventRepo.getRepo().add(new Event(title, day, time));

	}

	// Displays events, found by FIND method
	public static String display(Map<String, String[]> paramMap) {
		String result = "";

		for (Event e : find(paramMap)) {
			result = result + e.toString();
		}

		return result;
	}

	// Removes Events, found by FIND method
	public static String remove(Map<String, String[]> paramMap) {
		String result = "Removed " + find(paramMap).size() + " events";
		EventRepo.getRepo().removeAll(find(paramMap));
		return result;

	}

	// Find matching Event
	public static ArrayList<Event> find(Map<String, String[]> paramMap) {

		ArrayList<Event> result = new ArrayList<Event>();

		// calculate how many parameters in ParamMap are not ""
		int givenParams = RequestParamsHandler.calculateFilledParameters(paramMap);

		// if no parameters given return null
		if (givenParams == 0) {
			return result;
		}

		for (Event e : EventRepo.getRepo()) {

			int count = 0;
			for (Entry<String, String[]> entry : paramMap.entrySet()) {

				if (entry.getKey().equals("title") && entry.getValue()[0].equals(e.getTitle())) {
					count++;
				} else if (entry.getKey().equals("day") && entry.getValue()[0].equals(e.getDay())) {
					count++;
				} else if (entry.getKey().equals("time") && entry.getValue()[0].equals(e.getTime())) {
					count++;
				}

			}
			if (count == givenParams) {
				result.add(e);
			}
		}

		return result;
	}
}
