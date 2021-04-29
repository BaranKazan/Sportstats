package Sportstats.Exception;

/**
 * An exception class that extends the {@link RuntimeException} java class and
 * that is created whenever a problem with any {@link Sportstats.Domain} occurs.
 * 
 * @author Lara Aula
 * @version 2019-04-18
 */

public class DomainException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a domain exception exception with the specified detail message.
	 * 
	 * @param message the detail message
	 */
	public DomainException(String message) {
		super(message);
	}

}
