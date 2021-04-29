package Sportstats.Domain;

/**
 * Domain class for Sport.
 * 
 * @author Ali Shakeri
 * @version 2019-05-27
 */
public class Sport {

	private int id;
	private String name;

	/**
	 * Constructs a sport with only a name.
	 * 
	 * @param sport_name name of this sport
	 */
	public Sport(String sport_name) {
		this.name = sport_name;
	}

	/**
	 * Constructs a sport with a name and an id.
	 * 
	 * @param id         id of the sport
	 * @param sport_name name of the sport
	 */
	public Sport(int id, String sport_name) {
		this.id = id;
		this.name = sport_name;
	}

	/**
	 * Returns the name of this sport.
	 * 
	 * @return sport name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this sport.
	 * 
	 * @param name sport name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the id of this sport.
	 * 
	 * @return sport id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of this sport
	 * 
	 * @param id the id of this sport
	 */
	public void setId(int id) {
		this.id = id;
	}

}