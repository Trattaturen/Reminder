package com.reminder.dao;

import java.util.List;

import com.reminder.model.Event;

public interface EventDAO {

	boolean add(Event event);
	
	boolean removeById(int eventId);
	
	List<Event> getAll(int usedId);
	
	List<Event> findByValue(int userId, String value);
	
	
}
