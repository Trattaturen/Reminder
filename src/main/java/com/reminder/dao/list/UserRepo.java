package com.reminder.dao.list;

import java.util.ArrayList;

import com.reminder.model.User;

public class UserRepo {

	private static ArrayList<User> repo = new ArrayList<User>();

	public static ArrayList<User> getRepo() {
		return repo;
	}

}
