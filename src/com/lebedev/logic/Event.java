package com.lebedev.logic;

public class Event {
	private String title = "";
	private String day = "";
	private String time = "";

	public Event(String title) {
		this.setTitle(title);
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
		return ("<fieldset><legend>" + getTitle() + "</legend>" + "<b>Day: </b>" + getDay() + "<br>" + "<b>Time: </b>" + getTime()
				+ "<br></fieldset>");
	}

	@Override
	public boolean equals(Object obj) {
		Event e = (Event) obj;
		if (e.getTitle().equals(this.getTitle()) && e.getDay().equals(this.getDay()) && e.getTime().equals(this.getTime())) {
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

}
