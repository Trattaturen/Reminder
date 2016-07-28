package com.reminder.dao.list;

import java.util.ArrayList;

import com.reminder.model.Event;

public class EventRepo {

	private static ArrayList<Event> repo = new ArrayList<Event>();

	public static ArrayList<Event> getRepo() {
		return repo;
	}

	public static void setRepo(ArrayList<Event> repo) {
		EventRepo.repo = repo;
	}
	
	

}
