package com.reminder.model;

public class Event {

	private static int count;

	private int id;
	private String title;
	private String day;
	private String time;

	public Event(String title, String day, String time) {

		this.title = title;
		this.day = day;
		this.time = time;
		this.id = count;
		count++;

	}

	@Override
	public String toString() {

		return (getTitle() + "<br>" + getDay() + "<br>" + getTime() + "<br>");
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

}
