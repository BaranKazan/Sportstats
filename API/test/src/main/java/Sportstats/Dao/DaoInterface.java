package Sportstats.Dao;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Interface for all CRUD operations of DAO classes
 *
 * @author Lara Aula
 *
 * @param <T> One of the intended model classes that a DAO implementation should
 *        be able to handle
 */
public interface DaoInterface<T> {

	/**
	 * Method that gets the specific data and specific table from the database.
	 * Takes and integer as argument.
	 * 
	 * @throws NoSuchElementException if there is no object with the given id
	 * @param id of the object to find
	 * @return the object with the given id
	 */
	T get(int id) throws NoSuchElementException;

	/**
	 * This method returns all the data from the database that belongs to a specific
	 * domain.
	 * 
	 * @throws NoSuchElementException if there is no data in the domain table
	 * @return {@link List} of all objects
	 */
	List<T> getAll() throws NoSuchElementException;

	/**
	 * Saves the object in the database
	 * 
	 * @param t the object to store in the database
	 * @return {@code true} if the save was successful, {@code false} otherwise
	 */
	boolean save(T t);

	/**
	 * Updates an already existing column in the database with new values.
	 * 
	 * @param id     the id of the object whose values will be updated
	 * @param params the new values that the already existing object acquires
	 */
	void update(T t);

	/**
	 * Removes an object from the database
	 * 
	 * @param id the unique id of the object to be removed
	 */
	void delete(int id);
}