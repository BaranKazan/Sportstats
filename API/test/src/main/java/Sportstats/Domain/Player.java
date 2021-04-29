package Sportstats.Domain;

import java.time.LocalDate;

/**
 * Domain class for Player.
 * 
 * @author Ali Shakeri
 * @version 2019-05-27
 */
public class Player {

	private int id;
	private String name;
	private String position;
	private LocalDate dob;
	private int teamId;

	/**
	 * Constructs a player with a player id.
	 * 
	 * @param id       id of this player
	 * @param name     name of this player
	 * @param position the position of this player
	 * @param dob      the date of birth for this player
	 * @param teamId   id of the team this player plays in
	 */
	public Player(int id, String name, String position, LocalDate dob, int teamId) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.dob = dob;
		this.teamId = teamId;
	}

	/**
	 * Constructs a player.
	 * 
	 * @param name     name of this player
	 * @param position position of this player
	 * @param dob      the date of birth for this player
	 * @param teamId   the id of the team this player plays in
	 */
	public Player(String name, String position, LocalDate dob, int teamId) {
		this.name = name;
		this.position = position;
		this.dob = dob;
		this.teamId = teamId;
	}

	/**
	 * Returns the id of this player.
	 * 
	 * @return the player's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns this players name.
	 * 
	 * @return the player's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the date this player was born
	 * 
	 * @return date of birth for this player
	 */
	public LocalDate getBirthDate() {
		return dob;
	}

	/**
	 * Returns this players position
	 * 
	 * @return the player's position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Returns the id of the team this player plays in.
	 * 
	 * @return the id of this player's team
	 */
	public int getTeamId() {
		return this.teamId;
	}

	/**
	 * Sets the id of this player.
	 * 
	 * @param id the player id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the name for this player.
	 * 
	 * @param name the player name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the date of birth for this player.
	 * 
	 * @param dob the date of birth to set
	 */
	public void setBirthDate(LocalDate dob) {
		this.dob = dob;
	}

	/**
	 * Sets the position of this player.
	 * 
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * Sets the id of the team this player plays in.
	 * 
	 * @param teamId the team id to set
	 */
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
}