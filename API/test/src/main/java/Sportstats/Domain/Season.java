package Sportstats.Domain;

/**
 * Domain class for Season.
 * 
 * @author Baran Kazan
 * @version 2019-05-27
 */
public class Season {

	private int id;
	private int leagueId;
	private int startYear;
	private int endYear;
	private int maxRounds;

	/**
	 * Constructor that saves all the attributes.
	 * 
	 * @param id        id of this season
	 * @param leagueId  id of the league this season is in
	 * @param startYear the year this season starts
	 * @param endYear   the year this season ends
	 * @param maxRounds maximum amount of rounds in this season
	 */
	public Season(int id, int leagueId, int startYear, int endYear, int maxRounds) {
		this.id = id;
		this.leagueId = leagueId;
		this.startYear = startYear;
		this.endYear = endYear;
		this.maxRounds = maxRounds;
	}

	/**
	 * Constructor that save all the attributes besides Id.
	 * 
	 * @param leagueId  id of the league this season is in
	 * @param startYear the year this season starts
	 * @param endYear
	 */
	public Season(int leagueId, int startYear, int endYear, int maxRounds) {
		this.leagueId = leagueId;
		this.startYear = startYear;
		this.endYear = endYear;
		this.maxRounds = maxRounds;
	}

	/**
	 * Constructor that sets start year and end year of the season.
	 * 
	 * @param startYear of the season
	 * @param endYear   of the season
	 */
	public Season(int startYear, int endYear) {
		this.startYear = startYear;
		this.endYear = endYear;
	}

	/**
	 * Returns the ID of the season.
	 * 
	 * @return season id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the season.
	 * 
	 * @param id season id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the id of the league this season is connected to.
	 * 
	 * @return league id
	 */
	public int getLeagueId() {
		return leagueId;
	}

	/**
	 * Sets the id of the league this season is connected to.
	 * 
	 * @param leagueId league id
	 */
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}

	/**
	 * Returns the start year of the season.
	 * 
	 * @return season start year
	 */
	public int getStartYear() {
		return startYear;
	}

	/**
	 * Sets the start year of the season.
	 * 
	 * @param startYear season start year
	 */
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	/**
	 * Returns the end year of the season.
	 * 
	 * @return season end year
	 */
	public int getEndYear() {
		return endYear;
	}

	/**
	 * Sets the end year of the season.
	 * 
	 * @param endYear
	 */
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	/**
	 * Sets the maximum amount of rounds for a season.
	 * 
	 * @param maxRounds the maximum amount of rounds for this season
	 */
	public void setMaximumRounds(int maxRounds) {
		this.maxRounds = maxRounds;
	}

	/**
	 * Returns the maximum amount of rounds for this season.
	 * 
	 * @return the maximum amount of rounds
	 */
	public int getMaximumRounds() {
		return this.maxRounds;
	}
}