package com.reminder.dao.list;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.reminder.model.User;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRepo {

	private static UserRepo userRepo;

	@XmlElement(name = "user")
	private ArrayList<User> repo;

	public UserRepo() {
		repo = new ArrayList<User>();
	}

	public static UserRepo getInstance() {
		if (userRepo == null) {
			userRepo = new UserRepo();
		}
		return userRepo;
	}

	public static void setInstance(UserRepo userRepo) {
		UserRepo.userRepo = userRepo;
	}

	public ArrayList<User> getRepo() {
		return repo;
	}

	public void setRepo(ArrayList<User> repo) {
		this.repo = repo;

	}

}
