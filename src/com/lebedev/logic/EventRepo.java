package com.lebedev.logic;

import java.util.ArrayList;

public class EventRepo {

	private static ArrayList<Event> repo = new ArrayList<Event>();

	public static void addEvent(Event e) {
		repo.add(e);

	}

	public static ArrayList<Event> getRepo() {
		return repo;
	}

}
