package com.reminder.utils;

import org.apache.log4j.Logger;

import com.reminder.model.User;

public class UserUtil {

	public static final Logger LOG = Logger.getLogger(UserUtil.class);

	public static User createUser(String mail, String password, String confirmedPassword) {

		return new User(mail, password);
	}

}