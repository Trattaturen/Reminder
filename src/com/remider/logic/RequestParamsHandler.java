package com.remider.logic;

import java.util.Map;
import java.util.Map.Entry;

public class RequestParamsHandler {

	public static boolean checkParameters(String title, String day, String time) {

		if ((title == "" || day == "" || time == "") || (title == null || day == null || time == null)) {

			return false;

		}

		return true;
	}

	public static int calculateFilledParameters(Map<String, String[]> paramMap) {

		int filledParameters = 0;

		for (Entry<String, String[]> entry : paramMap.entrySet()) {
			if (!(entry.getValue()[0] == "" && !(entry.getValue()[0] == null))) {
				filledParameters++;
			}
		}
		return filledParameters;

	}

}
