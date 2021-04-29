package Sportstats.Service.Filter;

import Sportstats.Service.List.ListTable;

/**
 * A class that lets the {@link ListTable} use an instance of this class to
 * store season results in.
 * 
 * @author Lara Aula
 * @version 2019-05-23
 */
public class ResultTable {

	private String teamName;
	private int gamesPlayed;
	private int totalGoalsScored;
	private int totalGoalsConceded;
	private int gamesWon;
	private int gamesLost;
	private int gamesDrawn;
	private int points;

	/**
	 * Constructs the table with all the attributes to store for the season result.
	 * 
	 * @param teamName           name of the team the result belongs to
	 * @param gamesPlayed        total games played in the season by the team
	 * @param totalGoalsScored   total goals scored in the season by the team
	 * @param totalGoalsConceded total goals conceded in the season by the team
	 * @param gamesWon           total games won in the season by the team, both
	 *                           home and away
	 * @param gamesLost          total games lost in the season by the team, both
	 *                           home and away
	 * @param gamesDrawn         total games drawn in the season by the team, both
	 *                           home and away
	 * @param points             total amount of point in the season for the team
	 */
	public ResultTable(String teamName, int gamesPlayed, int totalGoalsScored, int totalGoalsConceded, int gamesWon,
			int gamesLost, int gamesDrawn, int points) {
		this.teamName = teamName;
		this.gamesPlayed = gamesPlayed;
		this.totalGoalsScored = totalGoalsScored;
		this.totalGoalsConceded = totalGoalsConceded;
		this.gamesWon = gamesWon;
		this.gamesLost = gamesLost;
		this.gamesDrawn = gamesDrawn;
		this.points = points;
	}

	/**
	 * @return the name of the team
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @return the amount of games played by the team
	 */
	public int getGamesPlayed() {
		return gamesPlayed;
	}

	/**
	 * @return the amount of goals scored by the team
	 */
	public int getTotalGoalsScored() {
		return totalGoalsScored;
	}

	/**
	 * @return the amount of goals conceded by the team
	 */
	public int getTotalGoalsConceded() {
		return totalGoalsConceded;
	}

	/**
	 * @return the amount of games won by the team for the season
	 */
	public int getGamesWon() {
		return gamesWon;
	}

	/**
	 * @return the amount of games lost by the team for the season
	 */
	public int getGamesLost() {
		return gamesLost;
	}

	/**
	 * @return the amount of games drawn by the team for the season
	 */
	public int getGamesDrawn() {
		return gamesDrawn;
	}

	/**
	 * @return the amount of points obtained by the team for the season
	 */
	public int getPoints() {
		return points;
	}

}
