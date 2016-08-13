package com.reminder.dao.list;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.reminder.dao.UserDAO;
import com.reminder.model.User;
import com.reminder.utils.UserUtil;

public class UserToListDAOimpl implements UserDAO {

	public static final Logger LOG = LogManager.getLogger(UserToListDAOimpl.class);

	public boolean add(User user) {

		user.setId(UserUtil.getCount());

		try {
			UserRepo.getInstance().getRepo().add(user);
			LOG.info("User added to DB");
			return true;
		} catch (Exception e) {
			LOG.warn("Problems with adding user {}", e);
			return false;
		}

	}

	public boolean findUser(String mail, String password) {
		if (UserRepo.getInstance().getRepo() == null) {
			return false;
		}
		for (User u : UserRepo.getInstance().getRepo()) {
			if (u.getMail().equals(mail) && u.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public int getUserId(String mail) {
		for (User u : UserRepo.getInstance().getRepo()) {
			if (u.getMail().equals(mail)) {
				return u.getId();
			}
		}
		return -1;

	}
}
