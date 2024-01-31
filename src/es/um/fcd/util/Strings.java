package es.um.fcd.util;

import java.text.Normalizer;
import java.util.List;

import org.apache.commons.text.similarity.LevenshteinDistance;

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
	
	public static int getSimilarity(String s1, String s2) {
		return LevenshteinDistance.getDefaultInstance().apply(s1, s2);
	}

	public static String removeAccents(String input) {
        if (input == null) {
            return null;
        }

        // Normalize the string by decomposing and removing diacritic marks
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

        // Remove characters that are not ASCII
        return normalized.replaceAll("[^\\p{ASCII}]", "");
    }
}
