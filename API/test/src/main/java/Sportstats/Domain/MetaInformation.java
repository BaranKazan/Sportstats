package Sportstats.Domain;

import Sportstats.Exception.DomainException;

/**
 * Domain class for MetaInformation. An instance of this class will be connected
 * to a {@link Game} where the meta information will contain meta data about a
 * game when it has been finished.
 * 
 * @author Peshang Suleiman
 * @author Lara Aula
 * @author Hassan Sheikha
 * @version 2019-05-09
 */
public class MetaInformation {

	private int id;
	private int gameId;
	private int spectators;
	private int winner;
	private int extraTime;
	private int partialHomeGoals;
	private int partialAwayGoals;

	/**
	 * Possible value for the {@code winner} field. Specifies that the home team
	 * won.
	 * 
	 * @since 2019-05-06
	 */
	public static final int HOME = 0;

	/**
	 * Possible value for the {@code winner} field. Specifies that the away team
	 * won.
	 * 
	 * @since 2019-05-06
	 */
	public static final int AWAY = 1;

	/**
	 * Possible value for the {@code winner} field. Specifies that the game was a
	 * tie.
	 * 
	 * @since 2019-05-06
	 */
	public static final int NONE = 2;

	/**
	 * Constructs meta-information to a game with a meta-information id.
	 * 
	 * @param id         the id of this meta information
	 * @param gameId     the id of the game to connect this meta information to
	 * @param spectators the amount of people that attended the game
	 * @param winner     the winner of the game
	 */
	public MetaInformation(int id, int gameId, int spectators, int winner, int extraTime, int partialHomeGoals,
			int partialAwayGoals) {
		this.id = id;
		this.gameId = gameId;
		this.spectators = spectators;
		this.extraTime = extraTime;
		this.partialHomeGoals = partialHomeGoals;
		this.partialAwayGoals = partialAwayGoals;
		setWinner(winner);
	}

	/**
	 * Constructs meta-information to a game without a meta-information id.
	 * 
	 * @param id         the id of this meta information
	 * @param gameId     the id of the game this meta information is connected to
	 * @param spectators the amount of people that attended the game
	 * @param winner     the winner of the game
	 */
	public MetaInformation(int gameId, int spectators, int winner, int extraTime, int partialHomeGoals,
			int partialAwayGoals) {
		this.gameId = gameId;
		this.spectators = spectators;
		this.extraTime = extraTime;
		this.partialHomeGoals = partialHomeGoals;
		this.partialAwayGoals = partialAwayGoals;
		setWinner(winner);
	}

	/**
	 * Constructs this service with extra time as the only parameter.
	 * 
	 * @param extraTime the extra time
	 */
	public MetaInformation(int extraTime) {
		this.extraTime = extraTime;
	}

	/**
	 * Sets the id of this meta-information
	 * 
	 * @param id the id of this meta-information
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the id of this meta-information.
	 * 
	 * @return id of this meta-information
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the game id of the game to connect this meta-information to.
	 * 
	 * @param gameId the id of the game
	 */
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	/**
	 * Specifies what team it was that won the game connected to this
	 * meta-information, or if it was a tie. The following values are valid for
	 * {@code winner}
	 * <ul>
	 * <li>{@code HOME}: Specifies that the home team won.</li>
	 * <li>{@code AWAY}: Specifies that the away team won.</li>
	 * <li>{@code NONE}: Specifies that the game was a tie.</li>
	 * </ul>
	 * 
	 * @param winner the winner of this game
	 */
	public void setWinner(int winner) {
		if (winner == AWAY || winner == HOME || winner == NONE)
			this.winner = winner;
		else
			throw new DomainException("Enter a valid result. The possible values are AWAY, HOME or NONE");
	}

	public int getWinner() {
		return this.winner;
	}

	/**
	 * Returns the id of the game this meta-information is connected to
	 * 
	 * @return the game id
	 */
	public int getGameId() {
		return gameId;
	}

	/**
	 * Sets the amount of people that watched the game.
	 * 
	 * @param spectators the amount of spectators
	 */
	public void setSpectators(int spectators) {
		this.spectators = spectators;
	}

	/**
	 * Returns the amount of people that watched the game.
	 * 
	 * @return the amount of spectators
	 */
	public int getSpectators() {
		return spectators;
	}

	/**
	 * Sets the a value of if this game was played to injury time.
	 * 
	 * @param extraTime the value to represent if the game was played to injury time
	 */
	public void setExtraTime(int extraTime) {
		this.extraTime = extraTime;
	}

	/**
	 * Returns if this game was played to injury time.
	 * 
	 * @return the value to represent if injury time was played
	 */
	public int getExtraTime() {
		return extraTime;
	}

	/**
	 * Sets the partial goals made by the home team.
	 * 
	 * @param partialHomeGoals amount of partial home goals
	 */
	public void setPartialHomeGoals(int partialHomeGoals) {
		this.partialHomeGoals = partialHomeGoals;
	}

	/**
	 * Returns the amount of partial goals made by the home team.
	 * 
	 * @return amount of partial home goals
	 */
	public int getPartialHomeGoals() {
		return this.partialHomeGoals;
	}

	/**
	 * Sets the amount of partial goals made by the away team.
	 * 
	 * @param partialAwayGoals amount of partial away goals
	 */
	public void setPartialAwayGoals(int partialAwayGoals) {
		this.partialAwayGoals = partialAwayGoals;
	}

	/**
	 * Returns the amount of partial goals made by the away team.
	 * 
	 * @return amount of partial away goals
	 */
	public int getPartialAwayGoals() {
		return this.partialAwayGoals;
	}
}
