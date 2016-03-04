package com.reminder.service;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import com.reminder.dao.EventRepoHandler;
import com.reminder.model.Event;

public class EventService {

	public static void addEvent(Event toAddEvent) {
		EventRepoHandler.addEvent(toAddEvent);
	}

	public static List<Event> displayEvent() {
		return EventRepoHandler.displayAll();
	}

	public static boolean removeEvent(int toRemoveId) {

		return EventRepoHandler.removeEventById(toRemoveId);

	}

	public static List<Event> findEvent(Enumeration<String> paramsEnum) {

		if (paramsEnum.hasMoreElements()) {

			return EventRepoHandler.findByValue(paramsEnum.nextElement());
		}

		return Collections.emptyList();

	}
}
