package com.reminder.utils;

import com.reminder.model.User;

public class UserUtil {

	public static User createUser(String mail, String password, String confirmedPassword) {

		return new User(mail, password);
	}

}