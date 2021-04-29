package Sportstats.Exception;

/**
 * An exception class that extends the {@link RuntimeException} java class and
 * that is created whenever a problem with any {@link Sportstats.Service}
 * occurs.
 * 
 * @author Hassan Sheikha
 * @version 2019-05-27
 */

public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a domain exception exception with the specified detail message.
	 * 
	 * @param message the detail message
	 */
	public InvalidInputException(String message) {
		super(message);
	}
	
}