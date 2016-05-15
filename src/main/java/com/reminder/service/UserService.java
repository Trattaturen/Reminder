package com.reminder.service;

import com.reminder.dao.UserRepoHandler;
import com.reminder.model.User;

public class UserService {


	public static boolean add(User user) {
		
		return UserRepoHandler.add(user);
	}

}
