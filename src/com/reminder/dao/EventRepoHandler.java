package com.reminder.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reminder.model.Event;

public class EventRepoHandler {

	public static final Logger LOG = Logger.getLogger(EventRepoHandler.class);

	public static boolean add(Event event) {

		try {
			EventRepo.getRepo().add(event);
			LOG.debug("Event added to DB");
			return true;
		} catch (Exception e) {
			LOG.warn("Problems with adding event" + e);
			return false;
		}

	}

	public static boolean removeById(int id) {

		for (Event event : EventRepo.getRepo()) {
			LOG.debug("Searching for Event to remove");
			if (event.getId() == id) {
				EventRepo.getRepo().remove(event);
				LOG.debug("Succesfully removed Event");
				return true;
			}
		}
		LOG.debug("Event to remove not found");
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
		LOG.debug("Searching for Event");
		List<Event> found = new ArrayList<Event>();
		for (Event event : EventRepo.getRepo()) {
			if (event.getTitle().toLowerCase().equals(value.toLowerCase())
					|| event.getDay().toLowerCase().equals(value.toLowerCase()) || event.getTime().equals(value)) {
				found.add(event);
			}
		}
		LOG.debug("Found " + found.size() + " matching events");
		return found;
	}
}
