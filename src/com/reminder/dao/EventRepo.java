package com.reminder.dao;

import java.util.ArrayList;

import com.reminder.model.Event;

public class EventRepo {

	private static ArrayList<Event> repo = new ArrayList<Event>();

	public static ArrayList<Event> getRepo() {
		return repo;
	}

}
