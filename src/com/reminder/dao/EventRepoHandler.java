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

}
