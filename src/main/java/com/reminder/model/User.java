package com.reminder.model;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static int count = 1;
	private int id;
	private String mail;
	private String password;

	public User(String mail, String password) {
		this.mail = mail;
		this.password = password;
		// this.id = count;
		// count++;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static void setCount(int newCount) {
		count = newCount;
	}

	public static int getCount() {
		return count;
	}

	@Override
	public boolean equals(Object user) {
		if ((user == null) || (user.getClass() != this.getClass())) {
			return false;
		}

		if (((User) user).getMail().equals(this.mail) && ((User) user).getPassword().equals(this.password)) {
			return true;
		}
		return false;
	}

}
