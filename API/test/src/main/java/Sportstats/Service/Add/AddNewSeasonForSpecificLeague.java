package Sportstats.Service.Add;

import Sportstats.Exception.InvalidInputException;
import Sportstats.Service.Runnable;

import java.util.NoSuchElementException;

import Sportstats.Dao.LeagueDao;
import Sportstats.Dao.SeasonDao;
import Sportstats.Domain.Season;

/**
 * 
 * A service that adds creates a new {@link Season} for a specific
 * {@link League}.
 * 
 * @author Peshang Suleiman
 * @author Lara Aula
 * @author Hassan Sheikha
 * @version 2019-05-01
 */
public class AddNewSeasonForSpecificLeague implements Runnable<Boolean> {

	private final SeasonDao seasonDao = new SeasonDao();
	private final LeagueDao leagueDao = new LeagueDao();
	private int leagueId;
	private int startYear;
	private int endYear;
	private int maximumRounds;

	/**
	 * Constructs the service.
	 * 
	 * @param leagueId      id of the league to add the season to
	 * @param startYear     the year the season starts
	 * @param endYear       the year the season ends
	 * @param maximumRounds the maximum amount of rounds allowed for this season
	 */
	public AddNewSeasonForSpecificLeague(int leagueId, int startYear, int endYear, int maximumRounds) {
		this.startYear = startYear;
		this.endYear = endYear;
		this.maximumRounds = maximumRounds;
		this.leagueId = leagueId;
	}

	/**
	 * Runs the service.
	 * 
	 * @return {@code true} if service was ran successfully, {@code false} otherwise
	 * @throws InvalidInputException  if any parameters given where invalid with a
	 *                                detail message that specifies the cause
	 * @throws NoSuchElementException if no league with the given id exists
	 */
	public Boolean run() {

		if (startYear < 1900 || startYear > endYear) {
			throw new InvalidInputException("Invalid year span");
		}

		if (maximumRounds <= 0) {
			throw new InvalidInputException("Enter a valid value of maximum rounds");
		}

		try {
			leagueDao.get(leagueId);
		} catch (NoSuchElementException e) {
			throw e;
		}

		Season newSeason = new Season(leagueId, startYear, endYear, maximumRounds);
		return seasonDao.save(newSeason);
	}
}