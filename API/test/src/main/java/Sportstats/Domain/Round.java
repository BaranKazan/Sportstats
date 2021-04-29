package Sportstats.Domain;

/**
 * Domain class for a round in a game.
 * 
 * @author Lara Aula
 * @version 2019-04-15
 */

public class Round {

	private int id;
	private int round_nbr;
	private int season_id;

	/**
	 * Constructs a round with a season id as parameter to show the existing
	 * dependency. A round can't be created if there is no season to play in.
	 * 
	 * @param seasonID the id of the season this round is played in
	 */
	public Round(int id, int round_nbr, int seasonID) {
		this.id = id;
		this.round_nbr = round_nbr;
		this.season_id = seasonID;
	}

	public Round(int round_nbr, int seasonID) {
		this.round_nbr = round_nbr;
		this.season_id = seasonID;
	}

	public Round(int id) {
		this.id = id;
	}

	/**
	 * Returns the id of the season this round is played in.
	 * 
	 * @return the season id
	 */
	public int getSeasonID() {
		return season_id;
	}

	/**
	 * Sets the id of the season this round is played in.
	 * 
	 * @param seasonID the season id
	 */
	public void setSeasonID(int seasonID) {
		this.season_id = seasonID;
	}

	/**
	 * Returns the id of this round.
	 * 
	 * @return the round id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of this round.
	 * 
	 * @param id the round id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the number of this round.
	 * 
	 * @return the round number
	 */
	public int getRoundNumber() {
		return round_nbr;
	}

	/**
	 * Sets the number of this round.
	 * 
	 * @param round_nbr the round number
	 */
	public void setRoundNumber(int round_nbr) {
		this.round_nbr = round_nbr;
	}
}