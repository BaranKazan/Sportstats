package Sportstats.Service;

/**
 * Java Interface to make a generic run method for all service classes.
 * 
 * @param <T> the generic type that can be set to what the service will return
 * 
 * @author Ali Shakeri
 * @version 2019-05-23
 */

public interface Runnable<T> {

	/**
	 * Runs the service.
	 * 
	 * @return the result from the service to run
	 */
	public T run();
}
