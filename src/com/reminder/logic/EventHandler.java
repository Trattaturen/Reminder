package com.reminder.logic;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.reminder.dao.EventRepo;
import com.reminder.dao.EventRepoHandler;
import com.reminder.model.Event;

public class EventHandler {

	public static String addEvent(String title, String day, String time) {

		if (RequestParamsHandler.checkParameters(title, day, time)) {

			EventRepoHandler.addEvent(title, day, time);
			return ("New event added succesfully!");

		} else {

			return ("Adding failed! (Wrong parameters in request)");

		}
	}

	// Displays events, found by FIND method
	public static String display(Map<String, String[]> paramMap) {
		String result = "";

		for (Event e : EventHandler.find(paramMap)) {
			result = result + e.toString();
		}

		return result;
	}

	// Removes Events, found by FIND method
	public static String remove(Map<String, String[]> paramMap) {
		int found = EventHandler.find(paramMap).size();
		EventRepoHandler.remove(EventHandler.find(paramMap));

		return ("Removed " + found + " events");

	}

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
