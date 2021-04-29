package Sportstats.Exception;

/**
 * An exception class that extends the {@link RuntimeException} java class and
 * that is created whenever a problem with the {@link DateTranslator} occurs.
 * 
 * @author Lara Aula
 * @since 2019-05-24
 */

public class DateTranslatorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a date translator exception exception with the specified detail
	 * message.
	 * 
	 * @param message the detail message
	 */
	public DateTranslatorException(String message) {
		super(message);
	}
}
