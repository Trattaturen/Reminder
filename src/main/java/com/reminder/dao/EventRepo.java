package com.reminder.dao;

import java.util.ArrayList;

import com.reminder.model.Event;


//Why do you need this wraper for ArrayList?
public class EventRepo {

	private static ArrayList<Event> repo = new ArrayList<Event>();

	public static ArrayList<Event> getRepo() {
		return repo;
	}

}
