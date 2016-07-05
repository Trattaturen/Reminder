package com.reminder.service;

import java.util.List;

import com.reminder.dao.EventRepoHandler;
import com.reminder.model.Event;

public class EventService {

	public static boolean add(Event event) {

		return EventRepoHandler.add(event);
	}

	public static List<Event> getAll(int userId) {

		return EventRepoHandler.getAll(userId);
	}

	public static boolean remove(int eventId) {

		return EventRepoHandler.removeById(eventId);

	}

	public static List<Event> find(int userId, String value) {

		return EventRepoHandler.findByValue(userId, value);

	}
}
