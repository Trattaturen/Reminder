package com.reminder.dao;

import java.util.ArrayList;
import java.util.List;

import com.reminder.model.Event;

public class EventRepoHandler {

	public static boolean add(Event toAddEvent) {

		try {
			EventRepo.getRepo().add(toAddEvent);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean removeById(int id) {

		for (Event event : EventRepo.getRepo()) {
			if (event.getId() == id) {
				EventRepo.getRepo().remove(event);
				return true;
			}
		}
		return false;

	}

	public static List<Event> getAll() {
		List<Event> allEvents = new ArrayList<>();
		for (Event e : EventRepo.getRepo()) {
			allEvents.add(e);
		}
		return allEvents;
	}

	public static List<Event> findByValue(String value) {
		List<Event> found = new ArrayList<Event>();
		for (Event event : EventRepo.getRepo()) {
			if (event.getTitle().equals(value) || event.getDay().equals(value) || event.getTime().equals(value)) {
				found.add(event);
			}
		}
		return found;
	}
}
