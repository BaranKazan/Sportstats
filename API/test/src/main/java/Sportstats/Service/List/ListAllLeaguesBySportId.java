package Sportstats.Service.List;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import Sportstats.Dao.LeagueDao;
import Sportstats.Dao.SportDao;
import Sportstats.Domain.League;
import Sportstats.Service.Runnable;

/**
 * Service class that lists all leagues in a sport.
 * 
 * @author Hassan Sheikha
 * @author Lara Aula
 * @version 2019-05-30
 */

public class ListAllLeaguesBySportId implements Runnable<List<League>> {
	private int sportID;

	public ListAllLeaguesBySportId(int sportId) {
		this.sportID = sportId;
	}

	/**
	 * Runs the service and returns all leagues in a sport.
	 * 
	 * @param sportId of the sport linked to the league
	 * @return list of leagues
	 */
	@Override
	public List<League> run() {

		try {
			new SportDao().get(sportID);
		} catch (NoSuchElementException e) {
			throw e;
		}
		
		List<League> listOfleagues = new LeagueDao().getAll();
		return listOfleagues.stream().filter(league -> league.getSportId() == sportID).collect(Collectors.toList());
	}
}
