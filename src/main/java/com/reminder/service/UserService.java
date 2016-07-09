package com.reminder.service;

import com.reminder.dao.UserDAO;
import com.reminder.dao.file.UserToFileDAOimpl;
import com.reminder.model.User;

public class UserService {

	private static UserDAO dao = new UserToFileDAOimpl();

	public static boolean add(User user) {

		return dao.add(user);
	}

	public static boolean findUser(String mail, String password) {

		return dao.findUser(mail, password);

	}

	public static int getUserId(String mail) {

		return dao.getUserId(mail);

	}

}
