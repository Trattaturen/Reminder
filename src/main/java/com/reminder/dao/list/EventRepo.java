package com.reminder.dao.list;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.reminder.model.Event;

@XmlRootElement(name = "events")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventRepo {

	private static EventRepo eventRepo;

	@XmlElement(name = "event")
	private ArrayList<Event> repo;

	public EventRepo() {
		repo = new ArrayList<Event>();
	}

	public static EventRepo getInstance() {
		if (eventRepo == null) {
			eventRepo = new EventRepo();
		}
		return eventRepo;
	}

	public static void setInstance(EventRepo eventRepo) {
		EventRepo.eventRepo = eventRepo;
	}

	public ArrayList<Event> getRepo() {
		return repo;
	}

	public void setRepo(ArrayList<Event> repo) {
		this.repo = repo;
	}

}
