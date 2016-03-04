package com.reminder.dao;

import java.util.ArrayList;
import java.util.List;

import com.reminder.model.Event;

public class EventRepoHandler {

	public static void addEvent(Event toAddEvent) {

		EventRepo.getRepo().add(toAddEvent);

	}

	public static boolean removeEventById(int toRemoveId) {

		for (Event event : EventRepo.getRepo()) {
			if (event.getId() == toRemoveId) {
				EventRepo.getRepo().remove(event);
				return true;
			}
		}
		return false;

	}

	public static List<Event> displayAll() {
		List<Event> allEvents = new ArrayList<>();
		for (Event e : EventRepo.getRepo()) {
			allEvents.add(e);
		}
		return allEvents;
	}

	public static List<Event> findByValue(String value) {
		System.out.println(value);
		List<Event> found = new ArrayList<Event>();
		for (Event event : EventRepo.getRepo()) {
			if (event.getTitle().equals(value) || event.getDay().equals(value) || event.getTime().equals(value)) {
				found.add(event);
			}
		}
		return found;
	}
}
