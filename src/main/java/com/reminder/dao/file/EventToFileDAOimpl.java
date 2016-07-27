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

	public boolean add(Event event) {

		LOG.debug("Event is {}", event.toString());
		event.setId(EventUtil.getCount());

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(EVENTS_FILE, true));) {

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
		File originalFile = new File(EVENTS_FILE);
		File tempFile = new File(originalFile.getAbsolutePath() + ".tmp");

		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {

			reader = new BufferedReader(new FileReader(originalFile));
			writer = new BufferedWriter(new FileWriter(tempFile));
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

					skipLines(reader, 6);
					currentUserId = reader.readLine();
					currentEventId = reader.readLine();
					deleted = true;
				}
			}

		} catch (IOException ex) {
			LOG.info("Problems with EVENTS file {}", ex);
			return false;
		} catch (Exception e) {
			LOG.warn("Problems with removing event {}", e);
			return false;

		} finally {
			
			try {
				writer.flush();
				writer.close();
				reader.close();
				originalFile.delete();
				tempFile.renameTo(originalFile);
				
			} catch (IOException e) {
				LOG.error("Could not close FileWriter/Reader! {}", e);
			}

		}
		return deleted;

	}

	public List<Event> getAll(int usedId) {

		List<Event> allEvents = new ArrayList<Event>();

		try (BufferedReader reader = new BufferedReader(new FileReader(EVENTS_FILE));) {

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

					skipLines(reader, 8);
				}
			}
			return allEvents;

		} catch (IOException ex) {
			LOG.info("File with events not found");
			return allEvents;
		} catch (Exception e) {
			LOG.warn("File with events not found");
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

	private BufferedReader skipLines(BufferedReader reader, int linesQuantity) {
		for (int i = linesQuantity; i > 0; i--) {
			try {
				reader.readLine();
			} catch (IOException e) {
				LOG.warn("Event file was modified during reading {}", e);
			}
		}
		return reader;

	}

}
