package com.reminder.service;

import java.util.List;

import com.reminder.dao.EventDAO;
import com.reminder.dao.file.EventToFileDAOimpl;
import com.reminder.model.Event;

public class EventService {

	private static EventDAO dao = new EventToFileDAOimpl();

	public static boolean add(Event event) {

		return dao.add(event);
	}

	public static List<Event> getAll(int userId) {

		return dao.getAll(userId);
	}

	public static boolean remove(int eventId) {

		return dao.removeById(eventId);

	}

	public static List<Event> find(int userId, String value) {

		return dao.findByValue(userId, value);

	}
}
