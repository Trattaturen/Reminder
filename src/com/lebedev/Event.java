package com.lebedev;

public class Event {
	String title = "";
	String day = "";
	String time = "";

	public Event(String title) {
		this.title = title;
	}

	public Event(String title, String day) {
		this(title);
		this.day = day;

	}

	public Event(String title, String day, String time) {
		this(title, day);
		this.time = time;

	}

	@Override
	public String toString() {
		return ("<fieldset><legend>" + title + "</legend>" + "<b>Day: </b>" + day + "<br>" + "<b>Time: </b>" + time
				+ "<br></fieldset>");
	}

	@Override
	public boolean equals(Object obj) {
		Event e = (Event) obj;
		if (e.title.equals(this.title) && e.day.equals(this.day) && e.time.equals(this.time)) {
			return true;
		}

		return false;
	}

}
