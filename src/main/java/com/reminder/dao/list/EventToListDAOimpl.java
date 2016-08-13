package com.reminder.dao.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.reminder.dao.EventDAO;
import com.reminder.model.Event;
import com.reminder.utils.EventUtil;

public class EventToListDAOimpl implements EventDAO {

	public static final Logger LOG = LogManager.getLogger(EventToListDAOimpl.class.getName());

	public boolean add(Event event) {

		event.setId(EventUtil.getCount());

		try {
			EventRepo.getInstance().getRepo().add(event);
			LOG.info("Event added to List");
			return true;
		} catch (Exception e) {
			LOG.warn("Problems with adding event" + e);
			return false;
		}

	}

	public boolean removeById(int id) {

		for (Event event : EventRepo.getInstance().getRepo()) {
			LOG.info("Searching for Event to remove");
			if (event.getId() == id) {
				EventRepo.getInstance().getRepo().remove(event);
				LOG.info("Succesfully removed Event");
				return true;
			}
		}
		LOG.info("Event to remove not found");
		return false;

	}

	public List<Event> getAll(int usedId) {
		List<Event> allEvents = new ArrayList<Event>();
		for (Event e : EventRepo.getInstance().getRepo()) {
			if (e.getUserId() == usedId) {
				allEvents.add(e);
			}
		}

		return allEvents;
	}

	public List<Event> findByValue(int userId, String value) {
		LOG.info("Searching for Event");
		List<Event> found = new ArrayList<Event>();
		for (Event e : EventRepo.getInstance().getRepo()) {
			if (e.getUserId() == userId && (e.getTitle().toLowerCase().equals(value.toLowerCase())
					|| e.getDay().toLowerCase().equals(value.toLowerCase()) || e.getTime().equals(value))) {
				found.add(e);
			}
		}
		LOG.info("Found {} matching events", found.size());
		return found;
	}

}