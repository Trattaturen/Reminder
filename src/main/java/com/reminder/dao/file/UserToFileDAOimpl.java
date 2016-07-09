package com.reminder.dao.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reminder.dao.UserDAO;
import com.reminder.model.User;

public class UserToFileDAOimpl implements UserDAO {

	public static final Logger LOG = LogManager.getLogger(UserToFileDAOimpl.class);

	private static final String USERS_FILE = "USERS";
	private static final String USER_COUNT_FILE = "USERS_COUNT";

	public boolean add(User user) {
		User.setCount(getCountFromFile());
		user.setId(User.getCount());
		User.setCount(getCountFromFile() + 1);
		writeCountToFile();
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true));
			writer.write(String.valueOf(user.getId()));
			writer.newLine();
			writer.write(user.getMail());
			writer.newLine();
			writer.write(user.getPassword());
			writer.newLine();
			writer.flush();
			writer.close();
			LOG.info("User added to DB");
			return true;
		} catch (IOException ex) {
			LOG.warn("No USERS file found. Created new{}");
			return false;
		} catch (Exception e) {
			LOG.warn("Problems with adding user {}", e);
			return false;
		}

	}

	private void writeCountToFile() {
		try {
			FileWriter writer = new FileWriter(USER_COUNT_FILE, false);
			writer.write(String.valueOf(User.getCount()));
			writer.flush();
			writer.close();
			LOG.info("Count of users increased");

		} catch (IOException ex) {
			LOG.warn("Problems with USERS count file {}", ex);

		} catch (Exception e) {
			LOG.warn("Problems writing users count {}", e);

		}

	}

	private int getCountFromFile() {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(USER_COUNT_FILE));
			int count = Integer.parseInt(reader.readLine());
			reader.close();
			if (count != 0) {
				return count;
			}

			return 1;

		} catch (Exception e) {
			LOG.debug("File with user count not found");
			return 1;
		}
	}

	public boolean findUser(String mail, String password) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE));
			String currentLine;

			while ((reader.readLine()) != null) {
				currentLine = reader.readLine();

				if (currentLine.equals(mail) && reader.readLine().equals(password)) {
					reader.close();
					return true;
				}
				reader.readLine();

			}
			reader.close();
			return false;

		} catch (IOException ex) {

			LOG.info("File with users not found");
			return false;
		} catch (Exception e) {
			LOG.warn("File with users not found", e);
			return false;
		}

	}

	public int getUserId(String mail) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE));

			String userId = reader.readLine();
			String userMail = reader.readLine();
			while (userId != null) {
				if (userMail.equals(mail)) {
					reader.close();
					return Integer.parseInt(userId);
				} else {
					reader.readLine();
					userId = reader.readLine();
					userMail = reader.readLine();
				}

			}
			reader.close();
			return -1;

		} catch (IOException ex) {

			LOG.info("File with users not found", ex);
			return -1;
		} catch (Exception e) {
			LOG.warn("File with users not found", e);
			return -1;
		}
	}

}
