package com.reminder.dao;

import java.util.ArrayList;
import java.util.List;

import com.reminder.model.Event;

public class EventRepoHandler {

	
	public static boolean add(Event event) {

		try {
			EventRepo.getRepo().add(event);
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
		List<Event> allEvents = new ArrayList<Event>();
		for (Event e : EventRepo.getRepo()) {
			allEvents.add(e);
		}
		return allEvents;
	}

	public static List<Event> findByValue(String value) {
		List<Event> found = new ArrayList<Event>();
		for (Event event : EventRepo.getRepo()) {
			if (event.getTitle().toLowerCase().equals(value.toLowerCase()) || 
					event.getDay().toLowerCase().equals(value.toLowerCase()) || 
						event.getTime().equals(value)) {
				found.add(event);
			}
		}
		return found;
	}
}
