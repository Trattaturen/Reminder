package com.reminder.dao.list;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reminder.model.Event;
import com.reminder.model.User;
import com.reminder.service.EventService;

@WebListener
public class ShutDownListener implements ServletContextListener {

	public final String EVENTS_FILE_NAME = "EVENTS.ser";
	public final String USERS_FILE_NAME = "USERS.ser";
	public static final Logger LOG = LogManager.getLogger(ShutDownListener.class);

	@SuppressWarnings("unchecked")
	public void contextInitialized(ServletContextEvent arg0) {

		if (EventService.getDao().getClass().getName().equals("com.reminder.dao.list.EventToListDAOimpl")) {
			LOG.info("Deserialising Events, Users");
			ArrayList<Event> deserializedEvents = (ArrayList<Event>) deserialize(EVENTS_FILE_NAME);
			ArrayList<User> deserializedUsers = (ArrayList<User>) deserialize(USERS_FILE_NAME);

			if (deserializedEvents != null) {
				EventRepo.setRepo(deserializedEvents);
				LOG.trace("Events deserialized");
			}

			if (deserializedUsers != null) {
				UserRepo.setRepo(deserializedUsers);
				LOG.trace("Users deserialized");
			}

		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {

		if (EventService.getDao().getClass().getName().equals("com.reminder.dao.list.EventToListDAOimpl")) {
			LOG.info("Serializing Events, Users");
			serialize(EventRepo.getRepo(), EVENTS_FILE_NAME);
			serialize(UserRepo.getRepo(), USERS_FILE_NAME);
		}

	}

	private void serialize(Object obj, String fileName) {

		try (FileOutputStream fileStream = new FileOutputStream(fileName);
				ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);) {

			objectStream.writeObject(obj);

		} catch (IOException e) {
			LOG.warn("Could not serialize {}", e);
		}

	}

	private Object deserialize(String fileName) {
		Object result = null;

		try (FileInputStream fileStream = new FileInputStream(fileName);
				ObjectInputStream objectStream = new ObjectInputStream(fileStream);) {

			result = objectStream.readObject();

		} catch (IOException e) {
			LOG.warn("Could not deserialize {}", e);
		} catch (ClassNotFoundException ex) {
			LOG.warn("Could not deserialize {}", ex);
		}
		return result;

	}

}
