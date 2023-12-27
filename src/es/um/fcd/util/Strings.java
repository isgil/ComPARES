package es.um.fcd.util;

import java.util.List;

public class Strings {
	public static String getString(List<?> input, String separator, boolean spaces) {
		String string = "";
		if (input != null) {
			for (Object item : input) {
				if (string.isEmpty()) {
					string += item;
				} else {
					if (spaces) {
						string += separator + " " + item;
					} else {
						string += separator + item;
					}
				}
			}
		}

		return string;
	}

	public static String getString(Object[] input, String separator, boolean spaces) {
		String string = "";
		if (input != null) {
			for (Object item : input) {
				if (string.isEmpty()) {
					string += item;
				} else {
					if (spaces) {
						string += separator + " " + item;
					} else {
						string += separator + item;
					}
				}
			}
		}

		return string;
	}
}
