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
import com.reminder.utils.UserUtil;

public class UserToFileDAOimpl implements UserDAO {

	public static final Logger LOG = LogManager.getLogger(UserToFileDAOimpl.class);

	private static final String USERS_FILE = "USERS";

	public boolean add(User user) {

		user.setId(UserUtil.getCount());

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true));) {
			writer.write(String.valueOf(user.getId()));
			writer.newLine();
			writer.write(user.getMail());
			writer.newLine();
			writer.write(user.getPassword());
			writer.newLine();
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

	public boolean findUser(String mail, String password) {
		try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE));) {

			String currentLine;

			while ((reader.readLine()) != null) {
				currentLine = reader.readLine();

				if (currentLine.equals(mail) && reader.readLine().equals(password)) {

					return true;
				}
				reader.readLine();

			}
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

		try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE));) {

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
