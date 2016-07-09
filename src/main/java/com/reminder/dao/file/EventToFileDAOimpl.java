package com.reminder.dao.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reminder.dao.EventDAO;
import com.reminder.model.Event;
import com.reminder.utils.EventUtil;

public class EventToFileDAOimpl implements EventDAO {

	public static final Logger LOG = LogManager.getLogger(EventToFileDAOimpl.class.getName());

	private static final String EVENTS_FILE = "EVENTS";
	private static final String EVENTS_COUNT_FILE = "EVENTS_COUNT";

	public boolean add(Event event) {

		Event.setCount(getCountFromFile());
		event.setId(Event.getCount());
		Event.setCount(getCountFromFile() + 1);
		writeCountToFile();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(EVENTS_FILE, true));
			writer.write(String.valueOf(event.getUserId()));
			writer.newLine();
			writer.write(String.valueOf(event.getId()));
			writer.newLine();
			writer.write(event.getTitle());
			writer.newLine();
			writer.write(event.getDay());
			writer.newLine();
			writer.write(event.getTime());
			writer.newLine();
			writer.write(event.getDescription());
			writer.newLine();
			writer.write(event.getType());
			writer.newLine();
			writer.write(Boolean.toString(event.isRemind()));
			writer.newLine();
			writer.flush();
			writer.close();
			LOG.info("Event added to DB");
			return true;
		} catch (IOException ex) {
			LOG.warn("EVENTS file not found. Creating new file");
			return false;
		} catch (Exception e) {
			LOG.warn("Problems with adding event {}", e);
			return false;
		}

	}

	public boolean removeById(int eventId) {
		boolean deleted = false;
		try {

			File originalFile = new File(EVENTS_FILE);
			File tempFile = new File(originalFile.getAbsolutePath() + ".tmp");

			BufferedReader reader = new BufferedReader(new FileReader(originalFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String currentUserId = reader.readLine();

			String currentEventId = reader.readLine();

			while (currentUserId != null) {

				if (!currentEventId.trim().equals(String.valueOf(eventId))) {
					writer.write(currentUserId);
					writer.newLine();
					writer.write(currentEventId);
					writer.newLine();
					writer.write(reader.readLine());// title
					writer.newLine();
					writer.write(reader.readLine());// day
					writer.newLine();
					writer.write(reader.readLine());// time
					writer.newLine();
					writer.write(reader.readLine());// description
					writer.newLine();
					writer.write(reader.readLine());// type
					writer.newLine();
					writer.write(reader.readLine());// remind
					writer.newLine();
					currentUserId = reader.readLine();
					currentEventId = reader.readLine();

				} else {
					reader.readLine();
					reader.readLine();
					reader.readLine();
					reader.readLine();
					reader.readLine();
					reader.readLine();
					currentUserId = reader.readLine();
					currentEventId = reader.readLine();
					deleted = true;
				}
			}
			writer.flush();
			writer.close();
			reader.close();

			originalFile.delete();
			tempFile.renameTo(originalFile);
			return deleted;

		} catch (IOException ex) {
			LOG.info("Problems with EVENTS file {}", ex);
			return false;
		} catch (Exception e) {
			LOG.warn("Problems with removing event {}", e);
			return false;

		}

	}

	public List<Event> getAll(int usedId) {

		List<Event> allEvents = new ArrayList<Event>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(EVENTS_FILE));
			String currentLine = reader.readLine();

			while (currentLine != null) {

				if (Integer.parseInt(currentLine) == usedId) {
					int uId = Integer.parseInt(currentLine);
					int eventId = Integer.parseInt(reader.readLine().trim());
					String title = reader.readLine();
					String day = reader.readLine();
					String time = reader.readLine();
					String description = reader.readLine();
					String type = reader.readLine();
					String remind = reader.readLine();
					Event foundEvent = EventUtil.createEvent(uId, title, day, time);
					foundEvent.setId(eventId);
					EventUtil.updateEvent(foundEvent, description, type, remind);
					allEvents.add(foundEvent);
					currentLine = reader.readLine();

				} else {

					for (int i = 0; i < 8; i++)
						currentLine = reader.readLine();
				}
			}
			reader.close();
			return allEvents;

		} catch (IOException ex) {
			LOG.info("File with events not found", ex);
			return allEvents;
		} catch (Exception e) {
			LOG.warn("File with events not found", e);
			return allEvents;
		}

	}

	public List<Event> findByValue(int userId, String value) {
		LOG.info("Searching for Event");

		List<Event> found = new ArrayList<Event>();

		for (Event event : getAll(userId)) {

			if (event.getTitle().toLowerCase().equals(value.toLowerCase())
					|| event.getDay().toLowerCase().equals(value.toLowerCase()) || event.getTime().equals(value)) {
				found.add(event);
			}
		}
		LOG.info("Found {} matching events", found.size());
		return found;
	}

	private static int getCountFromFile() {

		try (BufferedReader reader = new BufferedReader(new FileReader(EVENTS_COUNT_FILE));) {
			int count = Integer.parseInt(reader.readLine());
			if (count != 0) {
				return count;
			}

			return 1;

		} catch (Exception e) {
			LOG.debug("File with events count not found");
			return 1;
		}
	}

	private static void writeCountToFile() {

		try {
			FileWriter writer = new FileWriter(EVENTS_COUNT_FILE, false);
			writer.write(String.valueOf(Event.getCount()));
			writer.flush();
			writer.close();
			LOG.info("Count increased and added to DB");

		} catch (IOException ex) {
			LOG.warn("No EVENTS file found {}");

		} catch (Exception e) {
			LOG.warn("Problems writing events count {}", e);

		}

	}
}
