package com.reminder.dao;

import org.apache.log4j.Logger;

import com.reminder.model.User;

public class UserRepoHandler {

	public static final Logger LOG = Logger.getLogger(UserRepoHandler.class);

	public static boolean add(User user) {

		try {
			UserRepo.getRepo().add(user);
			LOG.info("User added to DB");
			return true;
		} catch (Exception e) {
			LOG.warn("Problems with adding user" + e);
			return false;
		}

	}

}
