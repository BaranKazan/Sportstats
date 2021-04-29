package Sportstats.Helper;

/**
 * Java class that contains a single method
 * {@link #isStringOnlyAlphabeticAndNotEmpty(String)} that checks if a string is
 * valid.
 * 
 * @author Ali Shakeri
 * @author Baran Kazan
 * @version 2019-05-23
 */
public class InputControll {

	/**
	 * Static method that checks if a string contains any invalid characters.
	 * 
	 * @param str the string to check
	 * @return {@code true} if string is only alphabetical, {@code false} otherwise
	 */
	public static boolean isStringOnlyAlphabeticAndNotEmpty(String str) {
		return ((str != null) && (!str.equals("")) && (str.matches("^[a-zA-Z ]*$")));
	}
}
