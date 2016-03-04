package com.reminder.dao;

import java.util.ArrayList;

import com.reminder.model.Event;

public class EventRepoHandler {

	// Adds Event if all parameters given
	public static void addEvent(String title, String day, String time) {

		EventRepo.getRepo().add(new Event(title, day, time));

	}

	public static void remove(ArrayList<Event> toDelete) {
		EventRepo.getRepo().removeAll(toDelete);

	}

	public static String displayAll() {
		String all = "";
		for (Event e : EventRepo.getRepo()) {
			all = all + e.toString();
		}
		return all;
	}

	// Following methods can be "polymorphized". Need advice

	public static ArrayList<Event> findByTitle(String value) {
		ArrayList<Event> found = new ArrayList<Event>();
		for (Event e : EventRepo.getRepo()) {
			if (e.getTitle().equals(value)) {
				found.add(e);
			}
		}
		return found;
	}

	public static ArrayList<Event> findByDay(String value) {
		ArrayList<Event> found = new ArrayList<Event>();
		for (Event e : EventRepo.getRepo()) {
			if (e.getDay().equals(value)) {
				found.add(e);
			}
		}
		return found;
	}

	public static ArrayList<Event> findByTime(String value) {
		ArrayList<Event> found = new ArrayList<Event>();
		for (Event e : EventRepo.getRepo()) {
			if (e.getTime().equals(value)) {
				found.add(e);
			}
		}
		return found;
	}
}
