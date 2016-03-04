package com.remider.logic;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.reminder.dao.EventRepo;
import com.reminder.model.Event;

public class FindEvent {
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
