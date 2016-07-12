package com.reminder.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reminder.model.Event;

public class EventUtil {

	private static int count = 1;
	public static final Logger LOG = LogManager.getLogger(EventUtil.class);
	private static final String EVENTS_COUNT_FILE = "EVENTS_COUNT";

	public static Event createEvent(int userId, String title, String day, String time) {

		LOG.debug("Checking parameters: {} {} {}", title, day, time);

		String invalidParameters = checkParameters(title, day, time);

		if (invalidParameters.isEmpty()) {
			LOG.debug("All parameters correct. Returning new Event");
			return new Event(userId, title, day, time);
		}
		LOG.warn("Incorrect parameters: {}", invalidParameters);
		throw new IllegalArgumentException(invalidParameters);

	}

	private static String checkParameters(String title, String day, String time) {

		String result = "";

		DateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm", Locale.ENGLISH);

		if (title == null || title.isEmpty()) {
			result = result + " Title";
		}
		if (day == null || day.isEmpty()) {
			result = result + " Day";
		}
		if (time == null || time.isEmpty()) {
			result = result + " Time";
		}

		try {
			Date today = new Date();
			Date eventDate = format.parse(day+time);
			LOG.debug(today);
			LOG.debug(eventDate);
			if (eventDate.before(today)) {
				result += " Date is in the past";
			}

		} catch (ParseException e) {
			LOG.warn("Incorrect date forma {}", e);
			result += " Incorrect date format";
		}
		return result;
	}

	public static void updateEvent(Event event, String description, String type, String remind) {
		if (!description.isEmpty()) {
			event.setDescription(description);
		} else {
			event.setDescription("");
		}
		if (!type.isEmpty()) {
			event.setType(type);
		} else {
			event.setType("");
		}
		if (remind != null && !remind.isEmpty()) {
			event.setRemind(true);
		} else {
			event.setRemind(false);
		}

	}

	public static int getCount() {
		count = getCountFromFile();
		writeCountToFile();
		return count;
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

		try (FileWriter writer = new FileWriter(EVENTS_COUNT_FILE, false);) {
			writer.write(String.valueOf(count + 1));
			LOG.info("Count increased and added to DB");

		} catch (IOException ex) {
			LOG.warn("No EVENTS file found {}");

		} catch (Exception e) {
			LOG.warn("Problems writing events count {}", e);

		}

	}
}