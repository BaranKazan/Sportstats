package Sportstats.Service.List;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import Sportstats.Dao.LeagueDao;
import Sportstats.Dao.SeasonDao;
import Sportstats.Domain.Season;
import Sportstats.Service.Runnable;

/**
 * Service class that lists all season for a specific league.
 * 
 * @author Ali Shakeri
 * @version 2019-05-30
 */
public class ListAllSeasonsForSpecificLeague implements Runnable<Object> {

	int leagueId;

	public ListAllSeasonsForSpecificLeague(int leagueId) {
		this.leagueId = leagueId;
	}

	/**
	 * Runs the service and returns a list of all the seasons for a specific league.
	 * 
	 * @throws NoSuchElementException if no league with the given id was found
	 * @return the list of seasons for a league
	 */
	@Override
	public ArrayList<Season> run() {
		try {
			new LeagueDao().get(leagueId);
		} catch (NoSuchElementException e) {
			throw e;
		}

		return new SeasonDao().getAllSeasonsForLeague(leagueId);
	}
}