package com.lebedev.logic;

public class Event {

	private static int nextId;

	private String title = "";
	private String day = "";
	private String time = "";
	private int Id;

	public Event(String title) {
		this.setTitle(title);
		setId(nextId++);

	}

	public Event(String title, String day) {
		this(title);
		this.setDay(day);

	}

	public Event(String title, String day, String time) {
		this(title, day);
		this.setTime(time);

	}

	@Override
	public String toString() {

		return ("<tr><td>" + getTitle() + "</td><td>" + getDay() + "</td><td>" + getTime() + "</td>");
	}

	@Override
	public boolean equals(Object obj) {
		Event e = (Event) obj;
		if (e.getTitle().equals(this.getTitle()) && e.getDay().equals(this.getDay())
				&& e.getTime().equals(this.getTime())) {
			return true;
		}

		return false;
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
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

}
