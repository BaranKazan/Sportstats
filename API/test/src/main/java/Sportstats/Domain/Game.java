package Sportstats.Domain;

import java.time.LocalDate;

/**
 * Domain class for Game.
 * 
 * @author Baran Kazan
 * @version 2019-05-27
 */
public class Game {

	private int id;
	private LocalDate dateOfGame;
	private int gameArena;
	private int homeTeam;
	private int awayTeam;
	private int roundId;
	private int homeGoals;
	private int awayGoals;

	/**
	 * Constructs a game with an id and goal results
	 * 
	 * @param id        the id of this game
	 * @param localDate the date of the game
	 * @param gameArena the arena the game is being held in
	 * @param homeTeam  id of the home team
	 * @param awayTeam  id of the away team
	 * @param roundId   id of the round this game is in
	 */
	public Game(int id, LocalDate localDate, int gameArena, int homeTeam, int awayTeam, int roundId, int homeGoals,
			int awayGoals) {
		this.id = id;
		this.dateOfGame = localDate;
		this.gameArena = gameArena;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.roundId = roundId;
		this.awayGoals = awayGoals;
		this.homeGoals = homeGoals;
	}

	/**
	 * Constructs a Game.
	 * 
	 * @param localDate date of the game
	 * @param gameArena id of the arena the game is being held in
	 * @param homeTeam  id of the home team
	 * @param awayTeam  id of the away team
	 * @param roundId   id of the round this game is in
	 */
	public Game(LocalDate localDate, int gameArena, int homeTeam, int awayTeam, int roundId) {
		this.dateOfGame = localDate;
		this.gameArena = gameArena;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.roundId = roundId;
	}

	/**
	 * Constructs a game with an id.
	 * 
	 * @param id        the id of this game
	 * @param localDate date of the game
	 * @param gameArena id of the arena the game is being held in
	 * @param homeTeam  id of the home team
	 * @param awayTeam  id of the away team
	 * @param roundId   id of the round this game is in
	 */
	public Game(int id, LocalDate localDate, int gameArena, int homeTeam, int awayTeam, int roundId) {
		this.id = id;
		this.dateOfGame = localDate;
		this.gameArena = gameArena;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.roundId = roundId;
	}

	/**
	 * Constructs a game with an id, goals and a round.
	 * 
	 * @param id        the id of this game
	 * @param roundId   the id of the round this game is in
	 * @param homeGoals the amount of home goals made
	 * @param awayGoals the amount of away goals made
	 */
	public Game(int id, int roundId, int homeGoals, int awayGoals) {
		this.awayGoals = awayGoals;
		this.homeGoals = homeGoals;
		this.roundId = roundId;
		this.id = id;
	}

	/**
	 * Constructs a game with an id and goals.
	 * 
	 * @param id        the id of this game
	 * @param homeGoals the amount of home goals made
	 * @param awayGoals the amount of away goals made
	 */
	public Game(int id, int homeGoals, int awayGoals) {
		this.awayGoals = awayGoals;
		this.homeGoals = homeGoals;
		this.id = id;
	}

	/**
	 * Constructs a game with an id, goals and teams.
	 * 
	 * @param id        the id of the game
	 * @param homeTeam  the id of the home team
	 * @param awayTeam  the id of the away team
	 * @param homeGoals the amount of goals made by the home team
	 * @param awayGoals the amount of goals made by the away team
	 */
	public Game(int id, int homeTeam, int awayTeam, int homeGoals, int awayGoals) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.awayGoals = awayGoals;
		this.homeGoals = homeGoals;
		this.id = id;
	}

	/**
	 * Contructs a game with teams as the only parameters.
	 * 
	 * @param homeTeam id of the home team
	 * @param awayTeam id of the home team
	 */
	public Game(int homeTeam, int awayTeam) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}

	/**
	 * Returns the amount of goals made by the away team in this game.
	 * 
	 * @return the amount of away goals
	 */
	public int getAwayGoals() {
		return awayGoals;
	}

	/**
	 * Returns the amount of goals made by the home team.
	 * 
	 * @return the amount of home goals
	 */
	public int getHomeGoals() {
		return homeGoals;
	}

	/**
	 * Sets the amount of goals made by the home team.
	 * 
	 * @param goals the amount of home goals
	 */
	public void setHomeGoals(int goals) {
		this.homeGoals = goals;
	}

	/**
	 * Sets the amount of goals made by the home team.
	 * 
	 * @param goals the amount of away goals
	 */
	public void setAwayGoals(int goals) {
		this.awayGoals = goals;
	}

	/**
	 * Method that returns the id of this game.
	 * 
	 * @return the id of this game
	 */
	public int getId() {
		return id;
	}

	/**
	 * Method that saves id of the game.
	 * 
	 * @param id the id to set for this game
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Method that returns date this game will be in
	 * 
	 * @return the date of the game
	 */
	public LocalDate getDateOfThisGame() {
		return dateOfGame;
	}

	/**
	 * Method that sets date of the game.
	 * 
	 * @param localDate the date to set
	 */
	public void setLocalDate(LocalDate localDate) {
		this.dateOfGame = localDate;
	}

	/**
	 * Method that returns id of the arena in game.
	 * 
	 * @return the arena of the game
	 */
	public int getGameArena() {
		return gameArena;
	}

	/**
	 * Method that set id of the arena in game.
	 * 
	 * @param gameArena the id of the arena
	 */
	public void setGameArena(int gameArena) {
		this.gameArena = gameArena;
	}

	/**
	 * Method that return the score of the home team in game.
	 * 
	 * @return the id of the home team
	 */
	public int getHomeTeam() {
		return homeTeam;
	}

	/**
	 * Method that sets the score of the home team in game.
	 * 
	 * @param homeTeam the id of the home team
	 */
	public void setHomeTeam(int homeTeam) {
		this.homeTeam = homeTeam;
	}

	/**
	 * Method that returns the score of the away team in game.
	 * 
	 * @return the id of the away team
	 */
	public int getAwayTeam() {
		return awayTeam;
	}

	/**
	 * Method that sets the score of the away team in game.
	 * 
	 * @param awayTeam the id of the away team
	 */
	public void setAwayTeam(int awayTeam) {
		this.awayTeam = awayTeam;
	}

	/**
	 * Method that returns the round of the game.
	 * 
	 * @return id of the round
	 */
	public int getRoundId() {
		return roundId;
	}

	/**
	 * Method that set the round of the game.
	 * 
	 * @param roundId the id of the round
	 */
	public void setRoundId(int roundId) {
		this.roundId = roundId;
	}
}