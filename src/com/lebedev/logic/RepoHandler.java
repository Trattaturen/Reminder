package com.lebedev.logic;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class RepoHandler {

	public static ArrayList<Event> find(HttpServletRequest request) {

		ArrayList<String> cleanedParams = clearParameters(request);

		ArrayList<Event> foundEvents = new ArrayList<>();
		if (cleanedParams.size() == 0) {

			return null;

		} else {

			for (Event e : EventRepo.getRepo()) {

				int count = 0;
				for (int i = 0; i < cleanedParams.size(); i++) {

					String arrayParamName = cleanedParams.get(i);

					if (arrayParamName.equals("title") && request.getParameter(arrayParamName).equals(e.getTitle())) {
						count++;
					} else if (arrayParamName.equals("day")
							&& request.getParameter(arrayParamName).equals(e.getDay())) {
						count++;
					} else if (arrayParamName.equals("time")
							&& request.getParameter(arrayParamName).equals(e.getTime())) {
						count++;
					}

				}
				// if all parameter values equals object values - print it
				if (count == cleanedParams.size()) {
					foundEvents.add(e);
				}
			}

			return foundEvents;
		}
	}

	public static ArrayList<String> clearParameters(HttpServletRequest request) {

		Enumeration<String> paramsEnum = request.getParameterNames();

		ArrayList<String> cleanedParams = new ArrayList<>();
		while (paramsEnum.hasMoreElements()) {
			String paramName = paramsEnum.nextElement();
			if (!paramName.equals("method") && request.getParameter(paramName) != "") {

				cleanedParams.add(paramName);
			}
		}

		return cleanedParams;

	}

	public static Event findById(int id) {
		for (Event e : EventRepo.getRepo()) {

			if (e.getId() == id) {
				return e;
			}
		}

		return null;

	}
}
