package Sportstats.Domain;

import Sportstats.Exception.DomainException;

/**
 * Domain class for the Arena.
 * 
 * @author Baran Kazan
 * @version 2019-04-25
 */
public class Arena {

	private int id;
	private String name;
	private int capacity;

	/**
	 * Constructor that takes attribute of id, capacity and name of arena.
	 * 
	 * @param id       id of this arena
	 * @param name     name of this arena
	 * @param capacity the maximum amount of people the arena can hold
	 */
	public Arena(int id, String name, int capacity) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;

	}

	/**
	 * Constructs a arena with a name and maximum capacity.
	 * 
	 * @param name name of this arena
	 */
	public Arena(String name, int capacity) {

		this.name = name;
		this.capacity = capacity;

	}

	/**
	 * Methods that returns id of the arena.
	 * 
	 * @return the id of this arena
	 */
	public int getId() {
		return id;
	}

	/**
	 * Method that sets id of the arena.
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Method that returns name of the arena.
	 * 
	 * @return the name of this arena
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method that sets name of the arena.
	 * 
	 * @param name the name to set for this arena
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the capacity of the arena.
	 * 
	 * @param capacity the amount of spectators this arena can hold
	 * @throws DomainException if the spectators is set to less than 1
	 */
	public void setCapacity(int capacity) {
		if (capacity <= 0)
			throw new DomainException("An arena can't have a capacity less than 1");
		else {
			this.capacity = capacity;
		}
	}

	/**
	 * Returns the arena capacity.
	 * 
	 * @return arena capacity
	 */
	public int getCapacity() {
		return this.capacity;
	}
}