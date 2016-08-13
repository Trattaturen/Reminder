package com.reminder.dao.list;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reminder.service.EventService;

@WebListener
public class ShutDownListener implements ServletContextListener {

	public final String EVENTS_FILE_NAME = "EVENTS.ser";
	public final String USERS_FILE_NAME = "USERS.ser";
	public static final Logger LOG = LogManager.getLogger(ShutDownListener.class);

	public void contextInitialized(ServletContextEvent arg0) {

		if (EventService.getDao().getClass().getName().equals("com.reminder.dao.list.EventToListDAOimpl")) {
			LOG.info("Deserialising, unmarshaling Events, Users");

			SaverLoader.unmarshal();

			// UNCOMMENT TO ENABLE DESERIALIZATION
			/*
			 * ArrayList<Event> deserializedEvents = (ArrayList<Event>)
			 * SaverLoader.deserialize(EVENTS_FILE_NAME); ArrayList<User>
			 * deserializedUsers = (ArrayList<User>)
			 * SaverLoader.deserialize(USERS_FILE_NAME);
			 * 
			 * if (deserializedEvents != null) {
			 * EventRepo.getInstance().setRepo(deserializedEvents); LOG.trace(
			 * "Events deserialized"); }
			 * 
			 * if (deserializedUsers != null) {
			 * UserRepo.getInstance().setRepo(deserializedUsers); LOG.trace(
			 * "Users deserialized"); }
			 */

		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {

		if (EventService.getDao().getClass().getName().equals("com.reminder.dao.list.EventToListDAOimpl")) {
			LOG.info("Serializing, marshaling Events, Users");
			SaverLoader.marshal();

			// UNCOMMENT TO ENABLE SERIALIZATION
			/*
			 * SaverLoader.serialize(EventRepo.getInstance().getRepo(),
			 * EVENTS_FILE_NAME);
			 * SaverLoader.serialize(UserRepo.getInstance().getRepo(),
			 * USERS_FILE_NAME);
			 */

		}

	}

}
