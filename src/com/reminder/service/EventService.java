package com.reminder.service;

import java.util.List;

import com.reminder.dao.EventRepoHandler;
import com.reminder.model.Event;

public class EventService {

	public static boolean add(Event toAddEvent) {
		
		return EventRepoHandler.add(toAddEvent);
	}

	public static List<Event> getAll() {
		
		return EventRepoHandler.getAll();
	}

	public static boolean remove(int toRemoveId) {

		return EventRepoHandler.removeById(toRemoveId);

	}

	public static List<Event> find(String value) {

		return EventRepoHandler.findByValue(value);

	}
}
