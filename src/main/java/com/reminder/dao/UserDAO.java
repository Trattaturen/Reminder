package com.reminder.dao;

import com.reminder.model.User;

public interface UserDAO {

	boolean add(User user);
	
	boolean findUser(String mail, String password);
	
	int getUserId(String mail);
	
	
}
