package com.reminder.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reminder.model.User;

public class UserUtil {

	public static final Logger LOG = LogManager.getLogger(UserUtil.class);
	private static final String USER_COUNT_FILE = "USERS_COUNT";
	private static int count = 1;

	public static User createUser(String mail, String password, String confirmedPassword) {

		return new User(mail, password);
	}

	public static int getCount() {
		count = getCountFromFile();
		writeCountToFile();
		return count;
	}

	private static int getCountFromFile() {

		try (BufferedReader reader = new BufferedReader(new FileReader(USER_COUNT_FILE));) {

			int count = Integer.parseInt(reader.readLine());
			if (count != 0) {
				return count;
			}
			return 1;
		} catch (Exception e) {
			LOG.debug("File with user count not found");
			return 1;
		}
	}

	private static void writeCountToFile() {
		try (FileWriter writer = new FileWriter(USER_COUNT_FILE, false);) {
			writer.write(String.valueOf(count + 1));
			LOG.info("Count of users increased");

		} catch (IOException ex) {
			LOG.warn("Problems with USERS count file {}", ex);

		} catch (Exception e) {
			LOG.warn("Problems writing users count {}", e);

		}

	}
}