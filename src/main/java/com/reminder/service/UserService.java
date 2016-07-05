package com.reminder.service;

import com.reminder.dao.UserRepoHandler;
import com.reminder.model.User;

public class UserService {

	public static boolean add(User user) {

		return UserRepoHandler.add(user);
	}

	public static boolean findUser(String mail, String password) {

		return UserRepoHandler.findUser(mail, password);

	}

	public static int getUserId(String mail) {

		return UserRepoHandler.getUserId(mail);

	}

}
