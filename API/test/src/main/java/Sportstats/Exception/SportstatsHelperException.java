package Sportstats.Exception;

/**
 * An exception class that extends the {@link RuntimeException} java class and
 * that is created whenever a problem with the {@link SportstatsHelper} occurs.
 * 
 * @author Lara Aula
 * @version 2019-05-09
 */
public class SportstatsHelperException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new sport stats helper exception with the specified detail
	 * message.
	 * 
	 * @param message the detail message. The message is sent to the super
	 *                constructor.
	 */
	public SportstatsHelperException(String message) {
		super(message);
	}

}