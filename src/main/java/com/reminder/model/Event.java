package com.reminder.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "event")
@XmlAccessorType (XmlAccessType.FIELD)
public class Event implements Serializable {

	private static final long serialVersionUID = 1L;
	private static int count = 1;
	private int id;
	private int userId;
	private String title;
	private String day;
	private String time;
	private String description;
	private String type;
	private boolean remind;

	public Event(){
		
	}
	
	public Event(int userId, String title, String day, String time) {

		this.userId = userId;
		this.title = title;
		this.day = day;
		this.time = time;
		// this.id = count;
		// count++;

	}

	@Override
	public String toString() {

		return getTitle() + "<br>" + getDay() + "<br>" + getTime() + "<br>";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int newId) {
		this.id = newId;
	}

	public int getUserId() {
		return userId;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int newCount) {
		count = newCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isRemind() {
		return remind;
	}

	public void setRemind(boolean remind) {
		this.remind = remind;
	}

}
