package Sportstats.Service;

/**
 * Base class that accepts an object that implements the Runnable interface and
 * runs its run method.
 * 
 * @param <T> the generic type that can be set to what the service will return
 * 
 * @author Ali Shakeri
 * @version 2019-05-31
 */

public class ServiceRunner<T> {

	private Runnable<T> service;

	/**
	 * Constructs a service runner that takes a runnable object that will become the
	 * service this service runner executes.
	 * 
	 * @param service the service that will be executed
	 */
	public ServiceRunner(Runnable<T> service) {
		this.service = service;
	}

	/**
	 * Runs the service and returns the result. The run method can be simplified
	 * without instantiating the return variable before return.
	 * 
	 * @return the result of the service
	 */
	public T run() {
		T serviceResult;
		serviceResult = service.run();
		return serviceResult;
	}
	
}