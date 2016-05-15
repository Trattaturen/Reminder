package com.reminder.service;

import java.util.List;

import com.reminder.dao.EventRepoHandler;
import com.reminder.model.Event;

public class EventService {


	public static boolean add(Event event) {
		
		return EventRepoHandler.add(event);
	}

	public static List<Event> getAll() {
		
		return EventRepoHandler.getAll();
	}


	public static boolean remove(int id) {

		return EventRepoHandler.removeById(id);

	}

	public static List<Event> find(String value) {

		return EventRepoHandler.findByValue(value);

	}
}
