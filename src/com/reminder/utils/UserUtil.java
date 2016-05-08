package com.reminder.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.reminder.model.User;

public class UserUtil {

	public static final Logger LOG = Logger.getLogger(UserUtil.class);
	private static final String MAIL_PATTERN = "^[a-zA-Z0-9\\.\\_]{4,31}+@+[a-zA-Z]{1}+[a-zA-Z0-9]{0,}\\.+[a-zA-Z]{2,3}$";
	private static final String PASS_PATTERN = "^.{5,3}$";

	public static User createUser(String mail, String password, String confirmedPassword) {

		return new User(mail, password);
	}

	public static boolean checkMail(String mail) {
		Pattern r = Pattern.compile(MAIL_PATTERN);
		Matcher m = r.matcher(mail);
		return m.find();
	}

	public static boolean checkPassword(String password) {
		Pattern r = Pattern.compile(PASS_PATTERN);
		Matcher m = r.matcher(password);
		return m.find();
	}

	public static boolean matchPasswords(String password, String confirmedPassword) {
		return password.equals(confirmedPassword);
	}

}