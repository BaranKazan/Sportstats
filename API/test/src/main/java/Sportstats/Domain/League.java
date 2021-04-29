package Sportstats.Domain;

/**
 * Domain class for League.
 * 
 * @author Peshang Suleiman
 * @version 2019-05-27
 */
public class League {
	
	private String name;
	private int id;
	private int sportId;
	
	/**
	 * Constructs a league with a name and id of the sport to connect the league to.
	 * 
	 * @param name
	 * 		name of this league
	 * @param sportId
	 * 		id of the sport to connect this league to
	 */
	public League(String name, int sportId) {
		this.name = name;
		this.sportId = sportId;
	}
	
	/**
	 * Constructs a league with an id, name and sport id.
	 * 
	 * @param id
	 * 		id of this league
	 * @param name
	 * 		name of this league
	 * @param sportId
	 * 		id of the sport to connect this league to 
	 */
	public League(int id, String name, int sportId) {
		this.id = id;
		this.name = name;
		this.sportId = sportId;
	}
	
	/**
	 * Sets the name of this league.
	 * 
	 * @param name
	 * 		the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of this league.
	 * 
	 * @return
	 * 		this leagues name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the id for this league.
	 * 
	 * @param id
	 * 		the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Returns the id of this league
	 * 
	 * @return
	 * 		the league id
	 */
	public int getId() {
		return id;
	}	
	
	/**
	 * Sets the id of the sport this league is connected to.
	 * 
	 * @param sportId	
	 * 		the id of the sport
	 */
	public void setSportId(int sportId) {
		this.sportId = sportId;
	}
	
	/**
	 * Returns the id of the sport this league is connected to.
	 * 
	 * @return
	 * 		the id of the sport
	 */
	public int getSportId() {
		return sportId;
	}
}