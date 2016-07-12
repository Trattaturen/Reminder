package com.reminder.service;

import java.util.List;

import com.reminder.dao.EventDAO;
import com.reminder.dao.file.EventToFileDAOimpl;
import com.reminder.model.Event;

public class EventService {

	private static EventDAO eventDao = new EventToFileDAOimpl();

	public static boolean add(Event event) {

		return eventDao.add(event);
	}

	public static List<Event> getAll(int userId) {

		return eventDao.getAll(userId);
	}

	public static boolean remove(int eventId) {

		return eventDao.removeById(eventId);

	}

	public static List<Event> find(int userId, String value) {

		return eventDao.findByValue(userId, value);

	}
}
