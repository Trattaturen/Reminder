package com.reminder.service;

import com.reminder.dao.UserDAO;
import com.reminder.dao.file.UserToFileDAOimpl;
import com.reminder.model.User;

public class UserService {

	private static UserDAO userDao = new UserToFileDAOimpl();

	public static boolean add(User user) {

		return userDao.add(user);
	}

	public static boolean findUser(String mail, String password) {

		return userDao.findUser(mail, password);

	}

	public static int getUserId(String mail) {

		return userDao.getUserId(mail);

	}

}
