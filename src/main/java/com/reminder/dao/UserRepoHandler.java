package com.reminder.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reminder.model.User;

public class UserRepoHandler {

	public static final Logger LOG = LogManager.getLogger(UserRepoHandler.class);

	public static boolean add(User user) {

		try {
			UserRepo.getRepo().add(user);
			LOG.info("User added to DB");
			return true;
		} catch (Exception e) {
			LOG.warn("Problems with adding user {}", e);
			return false;
		}

	}

	public static boolean findUser(String mail, String password) {
		for (User u : UserRepo.getRepo()) {
			if (u.getMail().equals(mail) && u.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

}
